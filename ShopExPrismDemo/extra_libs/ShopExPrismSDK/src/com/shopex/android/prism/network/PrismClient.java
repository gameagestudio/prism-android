package com.shopex.android.prism.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.a840.websocket.WebSocket;
import jp.a840.websocket.WebSockets;
import jp.a840.websocket.exception.WebSocketException;
import jp.a840.websocket.frame.Frame;
import jp.a840.websocket.handler.WebSocketHandler;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.loopj.android.http.handler.response.JsonHttpResponseHandler;
import com.shopex.android.prism.network.parser.IResponseParser;
import com.shopex.android.prism.network.parser.impl.JsonResponseParser;
import com.shopex.android.prism.utils.SignTools;
import com.shopex.android.prism.utils.WebUtils;

/**
 * <p>prism 客户端</p>
 * <p>提供API调用</p>
 * <p>提供websocket连接</p>
 * <p>提供oauth授权</p>
 * User: yangyong
 * Date: 14-6-11 下午1:03
 */
public class PrismClient {
  private Map<String,String> sysParams;//api请求系统级参数
  private String urlStr;//API请求URL
  private String appkey;//应用appkey
  private String secret;//应用密钥
  private int connectTimeout;//http请求连接超时时间，单位ms
  private int readTimeout;//http请求读取数据超时时间，单位ms
  private String authorizeUrl;//oauth用户授权获取临时访问令牌地址
  private String tokenUrl;//根据临时访问令牌来获取访问令牌地址
  private String checkSessionUrl;//检查seesion_id地址
  private String token;//访问令牌
  private String sessionId;
  private String refreshToken;//刷新令牌
  private URLParser urlParser;
  private IResponseParser mGson = new JsonResponseParser();
  private WebSocket webSocket;
  private PrismMsgHandler prismMsgHandler;

  public PrismClient(String url,String appkey,String secret){
    this.urlStr = url;
    this.appkey = appkey;
    this.secret = secret;
    this.connectTimeout = 5000;//默认5秒
    this.readTimeout = 5000;//默认5秒
    urlParser = new URLParser(urlStr);
    this.authorizeUrl = urlParser.getSite("/oauth/authorize");
    this.tokenUrl = urlParser.getSite("/oauth/token");
    this.checkSessionUrl = urlParser.getSiteWithAppendPath("/platform/oauth/session_check");
    initSysParams();
  }

  /**
   * 初始化组装系统级参数
   */
  private void initSysParams(){
    sysParams = new HashMap<String, String>();
    sysParams.put(Constants.CLIENT_ID,appkey);//appkey
    sysParams.put(Constants.SIGN_METHOD,"md5");//加密方式，默认md5加密
    sysParams.put(Constants.SIGN_TIME, String.valueOf(new Date().getTime() / 1000));//时间戳
  }

/*  *//**
   * 执行api post请求
   * @param method api对应的path路径
   * @param appParams 应用级参数
   * @return
   * @throws IOException
   *//*
  public String doPost(String method,Map<String,String> appParams) throws IOException {
    String postUrlStr = urlParser.getSiteWithAppendPath(method);
    return WebUtils.doPost(postUrlStr,assembleParams(getHeaders(),appParams,Constants.METHOD_POST,new URL(postUrlStr).getPath()),getHeaders(),connectTimeout,readTimeout);
  }

  *//**
   * 执行api get请求
   * @param method api对应的path路径
   * @param appParams 应用级参数
   * @return
   * @throws IOException
   *//*
  public String doGet(String method,Map<String,String> appParams) throws IOException {
    String getUrlStr = urlParser.getSiteWithAppendPath(method);
    System.out.println(getUrlStr);
    return WebUtils.doGet(getUrlStr, assembleParams(getHeaders(), appParams, Constants.METHOD_GET, new URL(getUrlStr).getPath()), getHeaders());
  }*/

  /**
   * 设置HTTP header
   */
  private Map<String,String> getHeaders(){
    Map<String,String> headers = new HashMap<String, String>();
    headers.put("User-Agent","PrismSDK/ANDROID");
    if (token != null) {
      headers.put("Authorization",token);
    }
    return headers;
  }

  /**
   * 组装所有请求参数
   */
  public Map<String,String> assembleParams(Map<String,String> headers,Map<String,String> appParams,String methodType,String urlPath){
    Map<String,String> allParams = new HashMap<String, String>();
    allParams.putAll(sysParams);
    if (appParams != null && allParams.size() > 0) {
      allParams.putAll(appParams);
    }
    String sign = "";
    if (methodType.equals(Constants.METHOD_GET)) {
      sign = sign(headers,allParams,null,Constants.METHOD_GET,urlPath);
    } else if (methodType.equals(Constants.METHOD_POST)) {
      sign = sign(headers,null,allParams,Constants.METHOD_POST,urlPath);
    }
    allParams.put(Constants.SIGN,sign);
    return allParams;
  }


  /**
   * 执行签名
   * @param headerParams http头信息
   * @param getParams get参数
   * @param postParams post参数
   * @param method http请求方式
   * @param path http请求path
   * @return
   */
  private String sign(Map<String,String> headerParams,Map<String,String> getParams,Map<String,String> postParams,String method,String path){
    //header数据拼接字符串
    String mixHeaderParams =  SignTools.mixHeaderParams(headerParams);
    //get数据拼接字符串
    String mixGetParams = SignTools.mixRequestParams(getParams);
    //post数据拼接字符串
    String mixPostParams = SignTools.mixRequestParams(postParams);
    //签名拼接字符串
    String mixAllParams = secret + Constants.SEPARATOR
            + method + Constants.SEPARATOR
            + urlencode(path) + Constants.SEPARATOR
            + urlencode(mixHeaderParams) + Constants.SEPARATOR
            + urlencode(mixGetParams) + Constants.SEPARATOR
            + urlencode(mixPostParams) + Constants.SEPARATOR
            + secret;
    //加密签名
    try {
      return SignTools.byte2hex(SignTools.encryptMD5(mixAllParams),true);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 连接websocket
   */
  public WebSocket executeNotify(){
    return executeNotify("/platform/notify");
  }

  /**
   * 连接websocket
   */
  public WebSocket executeNotify(String method){
    try {
      String websocketUrl = urlParser.getWsUrl(method);
      websocketUrl = websocketUrl + "?" + WebUtils.buildQuery(assembleParams(null,null, Constants.METHOD_GET, urlParser.getWsPath(method)), Constants.DEFAULT_CHARSET);
      webSocket = WebSockets.create(websocketUrl,new PrismWebSocketHandler(),"char");
      webSocket.setBlockingMode(false);
      webSocket.connect();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (WebSocketException e) {
      e.printStackTrace();
    }
    return webSocket;
  }

  /**
   * 设置websocket生命周期函数
   */
  public void setPrismMsgHandler(PrismMsgHandler prismMsgHandler) {
    this.prismMsgHandler = prismMsgHandler;
  }

  /**
   * ACK应答
   * @param tag frame消息编号
   */
  public void ack(int tag) throws WebSocketException{
    webSocket.send(assembleAckData(tag));
  }

  /**
   * 发布消息
   * @param routingKey 路由
   * @param msg 待发送消息
   * @throws UnsupportedEncodingException
   * @throws WebSocketException
   */
  public void publish(String routingKey,String msg) throws UnsupportedEncodingException, WebSocketException {
    webSocket.send(assemblePublishData(routingKey, msg));
  }

  /**
   * 开启消息消费
   */
  public void consume() {
    try {
      webSocket.send(assembleConsumeData());
    } catch (WebSocketException e) {
      e.printStackTrace();
    }
  }

  /**
   * 组装消费信息数据
   * @return
   */
  public ByteBuffer assembleConsumeData(){
    byte action = 0x02;
    ByteBuffer byteBuffer = ByteBuffer.allocate(1);
    byteBuffer.put(action);
    return byteBuffer;
  }

  /**
   * 组装ACK应答信息数据
   * @param tag 待应答的frame编号
   * @return
   */
  public ByteBuffer assembleAckData(int tag){
    byte action = 0x03;
    byte msgTag = (byte)(tag + 48);
    ByteBuffer byteBuffer = ByteBuffer.allocate(2);
    byteBuffer.put(action);
    byteBuffer.put(msgTag);
    return byteBuffer;
  }

  /**
   * 组装发布消息数据
   * @param routringKey 路由
   * @param msgStr 待发送的消息
   * @return
   * @throws UnsupportedEncodingException
   */
  public ByteBuffer assemblePublishData(String routringKey,String msgStr) throws UnsupportedEncodingException {
    byte action = 0x01;
    byte[] routingKey = routringKey.getBytes(Constants.DEFAULT_CHARSET);
    byte[] routingKeyLength = shortToByteArray(routingKey.length);
    byte[] msg = msgStr.getBytes(Constants.DEFAULT_CHARSET);
    byte[] msgLength = intToByteArray(msg.length);
    byte[] contentType = "text/plain".getBytes(Constants.DEFAULT_CHARSET);
    byte[] contentTypeLength = shortToByteArray(contentType.length);
    ByteBuffer byteBuffer = ByteBuffer.allocate(1 + routingKeyLength.length + routingKey.length + msgLength.length + msg.length + contentTypeLength.length + contentType.length);
    byteBuffer.put(action);
    byteBuffer.put(routingKeyLength);
    byteBuffer.put(routingKey);
    byteBuffer.put(msgLength);
    byteBuffer.put(msg);
    byteBuffer.put(contentTypeLength);
    byteBuffer.put(contentType);
    return byteBuffer;
  }



 

  

 
 
  private String urlencode(String str){
    try {
      return URLEncoder.encode(str, Constants.DEFAULT_CHARSET);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return "";
    }
  }

  private class Constants{
    private static final String CLIENT_ID = "client_id";
    private static final String SIGN_METHOD = "sign_method";
    private static final String SIGN_TIME = "sign_time";
    private static final String SIGN = "sign";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String SEPARATOR = "&";
  }

  /**
   * URL解析类，用来解析URL地址
   */
  private static class URLParser{
    private String protocol;
    private String host;
    private String path;
    private int port;
    private String query;
    public URLParser(String url){
      try {
        URL currentUrl = new URL(url);
        this.protocol = currentUrl.getProtocol();
        this.host = currentUrl.getHost();
        this.port = currentUrl.getPort();
//        this.port = (currentUrl.getPort() == -1)?currentUrl.getDefaultPort() : currentUrl.getPort();
        this.path = currentUrl.getPath();
        this.query = currentUrl.getQuery();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }

    public String getSite(){
      return protocol + "://"+ host + (port == -1 ? "" : (":"+ port));
    }

    public String getSite(String path){
      return protocol + "://"+ host + (port == -1 ? "" : (":"+ port)) + path;
    }

    public String getSiteWithPath(){
      return protocol + "://"+ host + (port == -1 ? "" : (":"+ port)) + path;
    }

    public String getSiteWithAppendPath(String appendPath){
      return protocol + "://"+ host + (port == -1 ? "" : (":"+ port)) + path + appendPath;
    }

    public String getFullUrl(){
      String pathUrl = protocol + "://"+ host + (port == -1 ? "" : (":"+ port)) + path;
      if (query != null) {
        return pathUrl + "?" + query;
      }
      return pathUrl;
    }

    public String getWsUrl(String method){
      return "ws://" + host + (port == -1 ? "" : (":"+ port)) + path + method;
    }

    public String getWsPath(String method){
      return path + method;
    }
  }

  private class PrismWebSocketHandler implements WebSocketHandler {
    @Override
    public void onOpen(WebSocket webSocket) {
      if (prismMsgHandler != null) {
        prismMsgHandler.onOpen(webSocket);
      }
    }

    @Override
    public void onMessage(WebSocket webSocket, Frame frame) {
      String msg = new String(frame.getContents().array(), 0, frame.getContents().array().length);
      PrismMsg prismMsg = mGson.fromJson(msg,PrismMsg.class);
      if (prismMsgHandler != null) {
        prismMsgHandler.onMessage(webSocket, prismMsg);
      }
    }

    @Override
    public void onError(WebSocket webSocket, WebSocketException e) {
      if (prismMsgHandler != null) {
        prismMsgHandler.onError(webSocket, e);
      }
    }

    @Override
    public void onClose(WebSocket webSocket) {
      if (prismMsgHandler != null) {
        prismMsgHandler.onClose(webSocket);
      }
    }
  }

  private byte[] intToByteArray(int number){
    byte[] bytes = ByteBuffer.allocate(4).putInt(number).array();
    return bytes;
  }

  private byte[] shortToByteArray(int number){
    byte[] bytes = ByteBuffer.allocate(2).putShort((short)number).array();
    return bytes;
  }
}
