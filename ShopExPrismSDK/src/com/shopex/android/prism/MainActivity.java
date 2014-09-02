package com.shopex.android.prism;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
import com.shopex.android.prism.req.SessionCheckReq;
import com.shopex.android.prism.resp.OAuthResp;
import com.shopex.android.prism.utils.UIUtils;

public class MainActivity extends Activity {

	protected NetworkClient mClient;

	private PrismApplication mApplication;

	private Button btnOauth;

	private Button btnToken;

	private Button btnClearSession;
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
		
		
		btnClearSession = (Button)findViewById(R.id.check_session);
		btnClearSession.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(oAuth == null){
					UIUtils.showToast(MainActivity.this,"oAuth 为空", Toast.LENGTH_SHORT);
					return;
				}
				mClient = new NetworkClient(MainActivity.this,oAuth);
				mClient.checkSession(new SessionCheckReq(oAuth),"buwb2lii", "ucr72ygfutspqeuu6s36", new ShopExAsynchResponseHandler(mClient){

					@Override
					public void onSuccess(int status, Header[] headers,
							byte[] body) {
						// TODO Auto-generated method stub
						super.onSuccess(status, headers, body);
					}

					@Override
					public void onFailure(int status, Header[] headers,
							byte[] body, Throwable e) {
						// TODO Auto-generated method stub
						super.onFailure(status, headers, body, e);
					}
					
				});
				
			}
		});
		btnOauth = (Button) findViewById(R.id.Oauth);
		btnOauth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {				
				PrismOauth oauth = new PrismOauth(MainActivity.this,"buwb2lii","http://buwb2lii.com/oauth-adapter?action=callback");
				oauth.authorize(new PrismOauthListener() {
					
					@Override
					public void onSuccess(OAuth data) {
						oAuth = data;
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
			if(oAuth == null){
				UIUtils.showToast(MainActivity.this, "OAuth 为空", Toast.LENGTH_SHORT);
				return;
			}
				mClient = new NetworkClient(MainActivity.this,oAuth);
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

	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
