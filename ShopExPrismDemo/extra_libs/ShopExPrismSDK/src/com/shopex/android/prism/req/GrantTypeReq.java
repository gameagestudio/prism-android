package com.shopex.android.prism.req;

import com.shopex.android.prism.domain.AbstractCommonReq;

public class GrantTypeReq extends SecurityReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118619108433697893L;
	
	private String grantType;
	private String code;
	
	public GrantTypeReq(String grantType,String code){
		this.grantType = grantType;
		this.code = code;
	}
	
	

	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
		add("code",code);
	}



	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
		add("grant_type",grantType);
	}
	
	

}
