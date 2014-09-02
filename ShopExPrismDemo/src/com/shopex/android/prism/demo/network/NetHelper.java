package com.shopex.android.prism.demo.network;

import android.content.Context;
import android.widget.Toast;

import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.demo.req.SessionCheckReq;
import com.shopex.android.prism.demo.util.Constants;
import com.shopex.android.prism.info.OAuth;
import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.network.ShopExAsynchResponseHandler;

import com.shopex.android.prism.req.SecurityReq;

import com.shopex.android.prism.utils.UIUtils;

public class NetHelper {

	private Context context;

	private NetworkClient client;

	private OAuth oAuth;

	public NetHelper(Context context, OAuth oAuth) {
		this.context = context;
		this.oAuth = oAuth;
		client = new NetworkClient(context, oAuth);
	}

	public void setOAuth(OAuth oAuth) {
		this.oAuth = oAuth;
	}

	public void write(String clientId, String clientSecret, String data,
			String contentType, ShopExAsynchResponseHandler responseHandler) {
		com.shopex.android.prism.demo.req.WriteReq req = new com.shopex.android.prism.demo.req.WriteReq(data, contentType);
		req.setContentType(contentType);
		req.setData(data);
		client.post(clientId, clientSecret, Constants.REQUEST_API.WRITE_URL,
				req, responseHandler);
	}

	public void checkSession(String clientId, String clientSecret,
			ShopExAsynchResponseHandler responseHandler) {
		if (oAuth == null) {
			UIUtils.showToast(context, "oAuth is null", Toast.LENGTH_SHORT);
			return;
		}
		SessionCheckReq check = new SessionCheckReq(oAuth);
		check.setOauth();

		client.get(AConstants.REQUEST_API.SECURITY.CHECKSESSION_URL, clientId,
				clientSecret, check, responseHandler);
	}

	
	public void secret(String clientId, String clientSecret,
			ShopExAsynchResponseHandler responseHandler) {

		client.get(clientId,clientSecret, AConstants.REQUEST_API.SECURITY.SECURITY_URL,
				  responseHandler);
	}
}
