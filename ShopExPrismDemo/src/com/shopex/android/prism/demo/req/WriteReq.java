package com.shopex.android.prism.demo.req;

import com.shopex.android.prism.domain.AbstractCommonReq;

public class WriteReq extends AbstractCommonReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002086054557598969L;
	
	private String data;
	
	private String contentType;
	
	public WriteReq(String data,String contentType){
		this.data = data;
		this.contentType = contentType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		add("data",data);
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
		add("content-type",contentType);
	}
	
	

}
