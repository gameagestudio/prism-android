package com.shopex.android.prism.demo.req;

import com.shopex.android.prism.domain.AbstractCommonReq;
import com.shopex.android.prism.info.OAuth;

public class SessionCheckReq extends AbstractCommonReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 625547341357921806L;
	
	private OAuth oauth;
	
	public SessionCheckReq(OAuth oauth){
		this.oauth = oauth;
	}
	
	public SessionCheckReq(String sessionId){
		oauth = new OAuth();
		oauth.setSession_id(sessionId);
	}

	public OAuth getOauth() {
		return oauth;
	}

	public void setOauth() {
		
		add("session_id", oauth.getSession_id());
	}
	
	

}
