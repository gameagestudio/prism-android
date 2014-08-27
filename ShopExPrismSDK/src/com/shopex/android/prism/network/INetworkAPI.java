package com.shopex.android.prism.network;

public class INetworkAPI {
	
	/**
	 * 安全
	 * @author JazzyYang
	 *
	 */
	public interface SECURITY{
		void secret(String clientId,String clientSecret,ShopExAsynchResponseHandler responseHandler);
		
		void write(String clientId,String clientSecret,String data,String contentType,ShopExAsynchResponseHandler responseHandler);
	}
}
