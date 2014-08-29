package com.shopex.android.prism;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.shopex.android.prism.application.PrismApplication;
import com.shopex.android.prism.auth.PrismOauth;
import com.shopex.android.prism.auth.PrismOauthListener;
import com.shopex.android.prism.common.AConstants;
import com.shopex.android.prism.exception.PrismException;
import com.shopex.android.prism.info.OAuth;
import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.network.ShopExAsynchResponseHandler;
import com.shopex.android.prism.network.parser.IResponseParser;
import com.shopex.android.prism.network.parser.impl.JsonResponseParser;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.req.OAuthReq;
import com.shopex.android.prism.resp.OAuthResp;
import com.shopex.android.prism.utils.UIUtils;

public class MainActivity extends Activity {

	protected NetworkClient mClient;

	private PrismApplication mApplication;

	private Button btnOauth;

	private Button btnToken;

	private OAuth oAuth;

	protected IResponseParser mGson = new JsonResponseParser();

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
		btnOauth = (Button) findViewById(R.id.Oauth);
		btnOauth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*OAuthReq req = new OAuthReq("buwb2lii",
						"http://buwb2lii.com/oauth-adapter?action=callback");

				Intent intent = new Intent(MainActivity.this,
						WebViewActivity.class);
				intent.putExtra("data",
						AConstants.REQUEST_API.SECURITY.AUTHORIZE_URL
								+ "?client_id=" + req.getClientId()
								+ "&redirect_uri=" + req.getRedirectUri());
				startActivityForResult(intent, 300);*/
				
				PrismOauth oauth = new PrismOauth(MainActivity.this,"buwb2lii","http://buwb2lii.com/oauth-adapter?action=callback");
				oauth.authorize(new PrismOauthListener() {
					
					@Override
					public void onSuccess(OAuth data) {
						System.out.println("登录成功:"+data.getAccess_token());
						UIUtils.showAlert(MainActivity.this, "成功","登录成功:"+data.getAccess_token());
						
					}
					
					@Override
					public void onFaliure(int code, String result) {
						UIUtils.showAlert(MainActivity.this, "失败",result+":"+code);
						
					}
					
					@Override
					public void onException(PrismException exception) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onCancel() {
						UIUtils.showAlert(MainActivity.this, "失败","用户返回");
						
					}
				});
			}
		});
		btnToken = (Button) findViewById(R.id.access_token);
		btnToken.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				mClient.secret(oAuth.getAccess_token(), "buwb2lii", "ucr72ygfutspqeuu6s36", new ShopExAsynchResponseHandler(mClient){

					@Override
					public void onSuccess(int status, Header[] headers,
							byte[] body) {
						
						super.onSuccess(status, headers, body);
						
						System.out.println(new String(body));
					}

					@Override
					public void onFailure(int status, Header[] headers,
							byte[] body, Throwable e) {
						super.onFailure(status, headers, body, e);
					}
					
				});

			}
		});

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { mClient.connect("buwb2lii",
		 * "ucr72ygfutspqeuu6s36", new PrismMsgHandler() {
		 * 
		 * @Override public void onOpen(WebSocket socket) {
		 * System.out.println("---> open");
		 * 
		 * }
		 * 
		 * @Override public void onMessage(WebSocket socket, PrismMsg prismMsg)
		 * { System.out.println("---> receive msg:"+prismMsg);
		 * 
		 * }
		 * 
		 * @Override public void onError(WebSocket socket, WebSocketException e)
		 * { e.printStackTrace(); System.out.println("---> error:"+e);
		 * 
		 * }
		 * 
		 * @Override public void onClose(WebSocket socket) {
		 * System.out.println("---> close"); } }); } }).start();
		 */

		/*
		 * mClient.write("buwb2lii", "ucr72ygfutspqeuu6s36","buwb2lii",
		 * "ucr72ygfutspqeuu6s36", new ShopExAsynchResponseHandler(mClient){
		 * 
		 * @Override public void onSuccess(int status, Header[] headers, byte[]
		 * body) { // TODO Auto-generated method stub super.onSuccess(status,
		 * headers, body); for(Header header: headers){
		 * System.out.println("header:"+header.getName()+","+header.getValue());
		 * } if (null != body && body.length > 0) { String json = new
		 * String(body); System.out.println(json); } }
		 * 
		 * @Override public void onFailure(int status, Header[] headers, byte[]
		 * body, Throwable e) {
		 * 
		 * super.onFailure(status, headers, body, e);
		 * 
		 * System.out.println(); }
		 * 
		 * });
		 */
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {

			if (requestCode == 300) {

				mClient.grant(
						new GrantTypeReq("authorization_code", data
								.getStringExtra("code")),
						new ShopExAsynchResponseHandler(mClient) {

							@Override
							public void onSuccess(int status, Header[] headers,
									byte[] body) {
								super.onSuccess(status, headers, body);
								String json = new String(body);
								oAuth = mGson.fromJson(json, OAuth.class);
								System.out.println(oAuth.getAccess_token()
										+ "," + oAuth.getExpires_in() + ","
										+ oAuth.getRefresh_token() + ","
										+ oAuth.getData().getaId());
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
