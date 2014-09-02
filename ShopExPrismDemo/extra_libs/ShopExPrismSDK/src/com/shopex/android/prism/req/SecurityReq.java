package com.shopex.android.prism.req;

import com.shopex.android.prism.domain.AbstractCommonReq;

public class SecurityReq extends AbstractCommonReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5308499379233587824L;
	
	private String clientId;
	
	private String clientSecret;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
		add("client_id", clientId);
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		add("client_secret",clientSecret);
	}
	
	

}
