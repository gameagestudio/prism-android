package com.shopex.android.prism.network.header;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class UserAgentHeader implements Header{

	@Override
	public HeaderElement[] getElements() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "User-Agent";
	}

	@Override
	public String getValue() {
		return "PrismSDK/ANDROID";
	}

}
