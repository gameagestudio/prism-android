package com.shopex.android.prism.req;

import com.shopex.android.prism.domain.AbstractCommonReq;

public class OAuthReq extends AbstractCommonReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8315698687043363932L;
	
	
	/**
	 * 应用Key Y
	 */
	private String clientId;
	
	/**
	 * 登录后跳转Url地址，必须是完整的URL地址，需要接收code参数 Y
	 */
	private String redirectUri;
	
	/**
	 * 默认为code  N
	 */
	private String code;
	
	/**
	 * 使用的页面模板，默认为default N
	 */
	private String view;
	
	/**
	 * 使用的session 有效时间
	 */
	private String ttl;
	
	public OAuthReq(String clientId,String redirectUri){
		this.clientId = clientId;
		this.redirectUri = redirectUri;
	
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
		add("client_id", clientId);
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
		add("redirect_uri",redirectUri);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		add("response_type",code);
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
		add("view",view);
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
		add("ttl",ttl);
	}
	
	
	
}
