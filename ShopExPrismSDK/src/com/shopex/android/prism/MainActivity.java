package com.shopex.android.prism;


import jp.a840.websocket.WebSocket;
import jp.a840.websocket.exception.WebSocketException;

import org.apache.http.Header;

import com.shopex.android.prism.application.PrismApplication;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.network.PrismClient;
import com.shopex.android.prism.network.PrismMsg;
import com.shopex.android.prism.network.PrismMsgHandler;
import com.shopex.android.prism.network.ShopExAsynchResponseHandler;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.req.OAuthReq;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	protected NetworkClient mClient;
	
	private PrismApplication mApplication;
	
	private Button btnOauth;
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
		
	btnOauth = (Button)findViewById(R.id.Oauth);
	btnOauth.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			OAuthReq req = new OAuthReq("buwb2lii", "http://buwb2lii.com/oauth-adapter?action=callback", "", "", "");
			
			
			 Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
             intent.putExtra("data", AConstants.REQUEST_API.SECURITY.AUTHORIZE_URL+"?client_id="+req.getClientId()+"&redirect_uri="+req.getRedirectUri());
             startActivityForResult(intent,300);
		}
	});
	mClient = new NetworkClient(getShopExApplication());
		
		
	/*	new Thread(new Runnable() {
			
			@Override
			public void run() {
				mClient.connect("buwb2lii", "ucr72ygfutspqeuu6s36", new PrismMsgHandler() {
					
					@Override
					public void onOpen(WebSocket socket) {
						 System.out.println("---> open");
						
					}
					
					@Override
					public void onMessage(WebSocket socket, PrismMsg prismMsg) {
						 System.out.println("---> receive msg:"+prismMsg);
						
					}
					
					@Override
					public void onError(WebSocket socket, WebSocketException e) {
						  e.printStackTrace();
					        System.out.println("---> error:"+e);
						
					}
					
					@Override
					public void onClose(WebSocket socket) {
						System.out.println("---> close");
					}
				});
			}
		}).start();*/
		
		/*mClient.write("buwb2lii", "ucr72ygfutspqeuu6s36","buwb2lii", "ucr72ygfutspqeuu6s36", new ShopExAsynchResponseHandler(mClient){

			@Override
			public void onSuccess(int status, Header[] headers, byte[] body) {
				// TODO Auto-generated method stub
				super.onSuccess(status, headers, body);
				for(Header header: headers){
					System.out.println("header:"+header.getName()+","+header.getValue());
				}
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
			
		});*/
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == RESULT_OK){
			
			if(requestCode == 300){
				
				mClient.grant(new GrantTypeReq("authorization_code",data.getStringExtra("code")), new ShopExAsynchResponseHandler(mClient){

					@Override
					public void onSuccess(int status, Header[] headers,
							byte[] body) {
						super.onSuccess(status, headers, body);
						System.out.println(new String(body));
					}

					@Override
					public void onFailure(int status, Header[] headers,
							byte[] body, Throwable e) {
						// TODO Auto-generated method stub
						super.onFailure(status, headers, body, e);
					}
					
				});
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
