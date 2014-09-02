package com.shopex.android.prism.auth;

import com.shopex.android.prism.exception.PrismException;
import com.shopex.android.prism.info.OAuth;

public abstract interface PrismOauthListener {

	
	public abstract void onSuccess(OAuth data);
	
	public abstract void onFaliure(int code,String result);
	
	public abstract void onException(PrismException exception);
	
	public abstract void onCancel();
}
