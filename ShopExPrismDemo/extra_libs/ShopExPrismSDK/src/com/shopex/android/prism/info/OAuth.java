package com.shopex.android.prism.info;

import java.io.Serializable;

public class OAuth implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5302625242101744439L;

	private String access_token;
	
	private String expires_in;
	
	private String refresh_expires;
	
	private String refresh_token;
	
	private String session_id;
	
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_expires() {
		return refresh_expires;
	}

	public void setRefresh_expires(String refresh_expires) {
		this.refresh_expires = refresh_expires;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	
	
}
