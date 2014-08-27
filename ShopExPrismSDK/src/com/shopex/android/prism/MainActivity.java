package com.shopex.android.prism;


import org.apache.http.Header;

import com.shopex.android.prism.application.PrismApplication;
import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.network.ShopExAsynchResponseHandler;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	
	protected NetworkClient mClient;
	
	private PrismApplication mApplication;
	
	 /**
     * 获取自有的application
     * 
     * @return
     */
    protected PrismApplication getShopExApplication() {
        if (null == mApplication) {
            mApplication = PrismApplication.getInstance();
        }
        return mApplication;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mClient = new NetworkClient(getShopExApplication());
		
	
		mClient.secret("buwb2lii", "ucr72ygfutspqeuu6s36", new ShopExAsynchResponseHandler(mClient){

			@Override
			public void onSuccess(int status, Header[] headers, byte[] body) {
				// TODO Auto-generated method stub
				super.onSuccess(status, headers, body);
				 if (null != body && body.length > 0) {
                     String json = new String(body);
                     System.out.println(json);
				 }
			}

			@Override
			public void onFailure(int status, Header[] headers, byte[] body,
					Throwable e) {
				
				super.onFailure(status, headers, body, e);
				
				System.out.println();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
