
package com.shopex.android.prism.network;

import org.apache.http.Header;

import android.content.Intent;

import com.loopj.android.http.handler.response.AsyncHttpResponseHandler;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.common.AConstants.STATUS_CODE;



public class ShopExAsynchResponseHandler extends AsyncHttpResponseHandler {

    private static final String TAG = "AustriaAsynchResponseHandler";
    private NetworkClient mClient;

    public ShopExAsynchResponseHandler(NetworkClient mClient) {
        this.mClient = mClient;
    }

    @Override
    public void onSuccess(final int status, final Header[] headers,
            final byte[] body) {
    	String json = null;
    	if (null != body && body.length > 0) {
            json = new String(body);
        }
    	if(AConstants.DEBUG)
    	System.out.println(">>>>>> onSuccess " + status + "\nbody=" + json);
        
    }

    @Override
    public void onFailure(final int status, final Header[] headers,
            final byte[] body, final Throwable e) {
    	if(AConstants.DEBUG)
        	System.out.println(">>>>>> onFailure " + status + "\nbody=" + e.getMessage());
        
    }
}
