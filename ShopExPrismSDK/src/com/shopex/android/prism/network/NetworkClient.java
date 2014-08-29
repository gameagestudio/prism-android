package com.shopex.android.prism.network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import jp.a840.websocket.exception.WebSocketException;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.shopex.android.prism.application.PrismApplication;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.network.INetworkAPI.SECURITY;
import com.shopex.android.prism.network.INetworkAPI.WEBSOCKET;
import com.shopex.android.prism.network.header.AuthHeader;
import com.shopex.android.prism.network.header.UserAgentHeader;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.req.OAuthReq;
import com.shopex.android.prism.req.SecurityReq;
import com.shopex.android.prism.req.WriteReq;
import com.shopex.android.prism.utils.SignTools;
import com.shopex.android.prism.utils.SystemUtils;

public class NetworkClient implements SECURITY,WEBSOCKET {

	private final Map<String, String> headerMap = new HashMap<String, String>();
	private final PersistentCookieStore cookieStore;
	private AsyncHttpClient client = null;
	private PrismApplication application;
	private PrismClient pClient = null;

	public NetworkClient(PrismApplication application) {
		this.application = application;
		this.client = new AsyncHttpClient(true, AConstants.HTTP_PORT,
				AConstants.HTTPS_PORT);
		this.cookieStore = new PersistentCookieStore(application);
		this.client.setCookieStore(cookieStore);
		this.client.addHeader("Accept-Language", SystemUtils.getLocales());
		this.client.getHttpClient().getParams()
				.setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		// 网络状态检测,会处理底层连接不可用的状态
		this.client
				.getHttpClient()
				.getParams()
				.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
		this.client.setTimeout(10 * 1000);
		// 该值在上传文件时会有很大影响，所以尽量把socket超时时间设置长一些。
		this.client.setSoTimeout(60 * 1000);
		this.client.setMaxConnections(5);
		this.client.setMaxRetriesAndTimeout(3, 0);
		DefaultHttpClient defClient = (DefaultHttpClient) client
				.getHttpClient();
		HttpProtocolParams.setUseExpectContinue(defClient.getParams(), false);

	}

	@Override
	public void secret(String clientId, String clientSecret,
			ShopExAsynchResponseHandler responseHandler) {
		SecurityReq req = new SecurityReq();
		req.setClientId(clientId);
		req.setClientSecret(clientSecret);
		client.get(null,AConstants.REQUEST_API.SECURITY.SECURITY_URL,addHeader(""), req,
				responseHandler);
	}

	@Override
	public void write(String clientId,String clientSecret,String data, String contentType,
			ShopExAsynchResponseHandler responseHandler) {
		WriteReq req = new WriteReq(data, contentType);
		req.setContentType(contentType);
		req.setData(data);
		client.post(null,addTail(AConstants.REQUEST_API.SECURITY.WRITE_URL, clientId, clientSecret),addHeader(""), req,null,responseHandler);
		
	}
	
	@Override
	public void oauth(OAuthReq req, ShopExAsynchResponseHandler responseHandler) {
		if(req.getClientId() == null && req.getClientId().trim().equals("")){
			System.out.println("clientId cant null");
			return;
		}
		
		if(req.getRedirectUri() == null && req.getRedirectUri().trim().equals("")){
			System.out.println("redirect url cant null");
			return;
		}
		req.setClientId(req.getClientId());
		req.setRedirectUri(req.getRedirectUri());
		
		client.get(AConstants.REQUEST_API.SECURITY.AUTHORIZE_URL,req, responseHandler);
	}
	@Override
	public void grant(GrantTypeReq req,
			ShopExAsynchResponseHandler responseHandler) {
		//req.setGrantType(req.getGrantType());
		req.setCode(req.getCode());
		
	//	pClient.assembleParams(headers, appParams, "GET", AConstants.REQUEST_API.SECURITY.TOKEN_URL)
		client.post(addTail(AConstants.REQUEST_API.SECURITY.TOKEN_URL, "buwb2lii", "ucr72ygfutspqeuu6s36")+"&grant_type=authorization_code",req, responseHandler);
		
	}
	@Override
	public void connect(String clientId, String clientSecret,
			PrismMsgHandler handler) {
		pClient = new PrismClient(AConstants.REQUEST_API.WEBSOCKET.NOTIFY_URL, clientId, clientSecret);
		pClient.setPrismMsgHandler(handler);
		pClient.executeNotify(AConstants.REQUEST_API.WEBSOCKET.NOTIFY_METHOD);
		pClient.consume();
		
		try {
				pClient.publish("order.new","mytest00001");
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    } catch (WebSocketException e) {
		      e.printStackTrace();
		    }
		    try {
		      new CountDownLatch(1).await();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
	}
	
	private String addTail(String url,String clientId,String clientSecret){
		return url+"?client_id="+clientId+"&client_secret="+clientSecret;
	}

	
	/**
	 * add headers
	 * @param token
	 * @return
	 */
	public Header[] addHeader(String token){
		ArrayList<Header> headers = new ArrayList<Header>();
		headers.add(new UserAgentHeader());
		if(token != null && !token.trim().equals("")){
			headers.add(new AuthHeader(token));
		}
		
		Header[] resultHeaders = new Header[headers.size()];
		for(int i= 0 ; i < resultHeaders.length; i++){
			resultHeaders[i] = headers.get(i);
		}
		return resultHeaders;
	}




	
	
	
}
