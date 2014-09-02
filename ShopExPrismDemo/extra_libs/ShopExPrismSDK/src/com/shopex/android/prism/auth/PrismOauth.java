package com.shopex.android.prism.auth;

import com.shopex.android.prism.common.AConstants;

import com.shopex.android.prism.req.OAuthReq;
import com.shopex.android.prism.utils.NetWorkHelper;
import com.shopex.android.prism.utils.UIUtils;

import android.content.Context;

public class PrismOauth {
	
	public static final String TAG = "PrismOauth";
	
	private OAuthReq auth;
	
	private Context context;
	
	public PrismOauth(Context context,String clientId,String redirectUri){
		this.context = context;
		auth = new OAuthReq(clientId, redirectUri);
	}
	
	public PrismOauth(Context context,OAuthReq auth){
		this.context = context;
		this.auth = auth;
	}

	public OAuthReq getAuth() {
		return auth;
	}

	public void setAuth(OAuthReq auth) {
		this.auth = auth;
	}
	
	public void authorize(PrismOauthListener listener){
		authorize(listener, 0);
	}
	
	public void authorize(PrismOauthListener listener , int type){
		startDialog(listener, type);
	}
	
	private void startDialog(PrismOauthListener listener,int type){
		
		if(listener == null){
			return;
		}
		
		PrismOauthParameter params = new PrismOauthParameter();
		params.put(AConstants.KEY.AUTH.CLIENT_ID, auth.getClientId());
		params.put(AConstants.KEY.AUTH.REDIRECT_URI,auth.getRedirectUri());
		params.put(AConstants.KEY.AUTH.RESPONSE_TYPE, auth.getCode());
		params.put(AConstants.KEY.AUTH.VIEW, auth.getView());
		params.put(AConstants.KEY.AUTH.TTL, auth.getTtl());
		
		String url = AConstants.REQUEST_API.SECURITY.AUTHORIZE_URL+"?"+params.encodeUrl();
		if(!NetWorkHelper.hasInternetPermission(context)){
			UIUtils.showAlert(context, "Error", "No Permission to Access the Internet!");
		}else if(NetWorkHelper.isNetworkAvailable(context)){
			new PrismDialog(context, url, listener, this).show();
		}else{
			//TODO
		}
		
	}

}
