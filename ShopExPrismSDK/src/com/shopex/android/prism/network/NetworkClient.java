package com.shopex.android.prism.network;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.shopex.android.prism.application.PrismApplication;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.req.SecurityReq;
import com.shopex.android.prism.req.WriteReq;
import com.shopex.android.prism.utils.SystemUtils;

public class NetworkClient implements INetworkAPI.SECURITY {

	private final Map<String, String> headerMap = new HashMap<String, String>();
	private final PersistentCookieStore cookieStore;
	private AsyncHttpClient client = null;
	private PrismApplication application;

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
		client.get(AConstants.REQUEST_API.SECURITY.SECURITY_URL, req,
				responseHandler);
	}

	@Override
	public void write(String clientId,String clientSecret,String data, String contentType,
			ShopExAsynchResponseHandler responseHandler) {
		WriteReq req = new WriteReq(data, contentType);
		req.setContentType(contentType);
		req.setData(data);
		client.post(AConstants.REQUEST_API.SECURITY.WRITE_URL+"?client_id="+clientId+"&client_secret="+clientSecret, req,responseHandler);
		
	}
}
