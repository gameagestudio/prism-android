package com.shopex.android.prism.network.header;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class AuthHeader implements Header{
	
	private String token;
	
	public AuthHeader(String token){
		this.token = token;
	}

	@Override
	public HeaderElement[] getElements() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Authorization";
	}

	@Override
	public String getValue() {
		return token;
	}

}
