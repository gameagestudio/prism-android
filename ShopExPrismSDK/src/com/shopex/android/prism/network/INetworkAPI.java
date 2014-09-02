package com.shopex.android.prism.network;

import com.shopex.android.prism.info.OAuth;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.req.OAuthReq;
import com.shopex.android.prism.req.SessionCheckReq;

public class INetworkAPI {
	
	
	
	/**
	 * 安全
	 * @author JazzyYang
	 *
	 */
	public interface SECURITY{
		void secret(String token,String clientId,String clientSecret,ShopExAsynchResponseHandler responseHandler);
		
		void write(String clientId,String clientSecret,String data,String contentType,ShopExAsynchResponseHandler responseHandler);
		
		void oauth(OAuthReq req,ShopExAsynchResponseHandler responseHandler );
		
		void grant(GrantTypeReq req,ShopExAsynchResponseHandler responseHandler);
		
		void checkSession(SessionCheckReq auth,String clientId,String clientSecret,ShopExAsynchResponseHandler responseHandler);
	}
	
	/**
	 * WebSocket
	 */
	public interface WEBSOCKET{
		void connect(String clientId,String clientSecret,PrismMsgHandler handler);
	}
}
