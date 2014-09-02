package com.shopex.android.prism.network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import jp.a840.websocket.exception.WebSocketException;

import org.apache.http.Header;

import org.apache.http.HttpEntity;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import com.loopj.android.http.RequestParams;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.info.OAuth;
import com.shopex.android.prism.network.INetworkAPI.SECURITY;
import com.shopex.android.prism.network.INetworkAPI.WEBSOCKET;
import com.shopex.android.prism.network.header.AuthHeader;
import com.shopex.android.prism.network.header.UserAgentHeader;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.req.OAuthReq;
import com.shopex.android.prism.req.SecurityReq;



import com.shopex.android.prism.utils.SystemUtils;
import com.shopex.android.prism.utils.UIUtils;

/**
 * 
 * @author JazzyYang
 *
 */
public class NetworkClient implements SECURITY, WEBSOCKET {

	private AsyncHttpClient client = null;

	private PrismClient pClient = null;

	private OAuth oAuth = null;
	private Context context;

	public NetworkClient(Context context, OAuth oAuth) {
		this.oAuth = oAuth;
		this.context = context;
		this.client = new AsyncHttpClient(true, AConstants.HTTP_PORT,
				AConstants.HTTPS_PORT);
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

	
	public void setOAuth(OAuth oAuth){
		this.oAuth = oAuth;
	}


	@Override
	public void oauth(OAuthReq req, ShopExAsynchResponseHandler responseHandler) {
		if (req.getClientId() == null && req.getClientId().trim().equals("")) {
			UIUtils.showToast(context, "clientId cant null", Toast.LENGTH_SHORT);
			return;
		}

		if (req.getRedirectUri() == null
				&& req.getRedirectUri().trim().equals("")) {
			UIUtils.showToast(context, "redirect url cant null",
					Toast.LENGTH_SHORT);
			return;
		}
		req.setClientId(req.getClientId());
		req.setRedirectUri(req.getRedirectUri());

		client.get(AConstants.REQUEST_API.SECURITY.AUTHORIZE_URL, req,
				responseHandler);
	}

	@Override
	public void grant(GrantTypeReq req,
			ShopExAsynchResponseHandler responseHandler) {

		req.setCode(req.getCode());

		client.post(
				addTail(AConstants.REQUEST_API.SECURITY.TOKEN_URL, "buwb2lii",
						"ucr72ygfutspqeuu6s36")
						+ "&grant_type=authorization_code", req,
				responseHandler);

	}

	@Override
	public void connect(String clientId, String clientSecret,
			PrismMsgHandler handler) {
		pClient = new PrismClient(AConstants.REQUEST_API.WEBSOCKET.NOTIFY_URL,
				clientId, clientSecret);
		pClient.setPrismMsgHandler(handler);
		pClient.executeNotify(AConstants.REQUEST_API.WEBSOCKET.NOTIFY_METHOD);
		pClient.consume();

		try {
			pClient.publish("order.new", "mytest00001");
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

	private String addTail(String url, String clientId, String clientSecret) {
		return url + "?client_id=" + clientId + "&client_secret="
				+ clientSecret;
	}

	/**
	 * add headers
	 * 
	 * @param token
	 * @return
	 */
	public Header[] addHeader(String token) {
		ArrayList<Header> headers = new ArrayList<Header>();
		headers.add(new UserAgentHeader());
		if (token != null && !token.trim().equals("")) {
			headers.add(new AuthHeader(token));
		}

		Header[] resultHeaders = new Header[headers.size()];
		for (int i = 0; i < resultHeaders.length; i++) {
			resultHeaders[i] = headers.get(i);
		}
		return resultHeaders;
	}


	/**
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param url
	 * @param responseHandler
	 */
	public void get(String clientId, String clientSecret, String url,
			ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			client.get(null, addTail(url, clientId, clientSecret),
					addHeader(oAuth.getAccess_token()), null, responseHandler);
		} else {
			client.get(addTail(url, clientId, clientSecret), responseHandler);
		}

	}

	public void get(Context context, String clientId, String clientSecret,
			String url, ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			client.get(context, addTail(url, clientId, clientSecret),
					addHeader(oAuth.getAccess_token()), null, responseHandler);
		} else {
			client.get(context, addTail(url, clientId, clientSecret),
					responseHandler);
		}
	}

	public void get(String url, String clientId, String clientSecret,
			RequestParams params, ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			client.get(null, addTail(url, clientId, clientSecret),
					addHeader(oAuth.getAccess_token()), params, responseHandler);
		} else {
			client.get(addTail(url, clientId, clientSecret), params,
					responseHandler);
		}
	}

	public void get(Context context, String url, String clientId,
			String clientSecret, Header[] headers, RequestParams params,
			ShopExAsynchResponseHandler responseHandler) {

		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			Header[] finalHeaders = concatAll(
					addHeader(oAuth.getAccess_token()), headers);
			client.get(context, addTail(url, clientId, clientSecret),
					finalHeaders, params, responseHandler);
		} else {
			client.get(context, addTail(url, clientId, clientSecret), headers,
					params, responseHandler);
		}
	}

	@Deprecated
	public void post(String clientId, String clientSecret, String url,
			ShopExAsynchResponseHandler responseHandler) {
		client.post(addTail(url, clientId, clientSecret), responseHandler);
	}

	public void post(String clientId, String clientSecret, String url,
			RequestParams params, ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			client.post(null, addTail(url, clientId, clientSecret),
					addHeader(oAuth.getAccess_token()), params, null,
					responseHandler);
		} else {
			client.post(addTail(url, clientId, clientSecret), params,
					responseHandler);
		}

	}

	public void post(Context context, String url, String clientId,
			String clientSecret, RequestParams params,
			ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			client.post(context, addTail(url, clientId, clientSecret),
					addHeader(oAuth.getAccess_token()), params, null,
					responseHandler);
		} else {
			client.post(context, addTail(url, clientId, clientSecret), params,
					responseHandler);
		}

	}

	public void post(Context context, String url, String clientId,
			String clientSecret, Header[] headers, HttpEntity entity,
			String contentType, ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			Header[] finalHeaders = concatAll(
					addHeader(oAuth.getAccess_token()), headers);
			client.post(context, addTail(url, clientId, clientSecret),
					finalHeaders, entity, contentType, responseHandler);
		} else {
			client.post(context, addTail(url, clientId, clientSecret), headers,
					entity, contentType, responseHandler);
		}
	}

	public void post(Context context, String url, String clientId,
			String clientSecret, Header[] headers, RequestParams params,
			String contentType, ShopExAsynchResponseHandler responseHandler) {
		if (oAuth != null && oAuth.getAccess_token() != null
				&& !oAuth.getAccess_token().trim().equals("")) {
			Header[] finalHeaders = concatAll(
					addHeader(oAuth.getAccess_token()), headers);
			client.post(context, addTail(url, clientId, clientSecret),
					finalHeaders, params, contentType, responseHandler);
		} else {
			client.post(context, addTail(url, clientId, clientSecret), headers,
					params, contentType, responseHandler);
		}
	}

	@SuppressLint("NewApi")
	public <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

}
