package com.shopex.android.prism.auth;

import org.apache.http.Header;

import android.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.shopex.android.prism.exception.PrismDialogException;
import com.shopex.android.prism.info.OAuth;
import com.shopex.android.prism.network.NetworkClient;
import com.shopex.android.prism.network.ShopExAsynchResponseHandler;
import com.shopex.android.prism.req.GrantTypeReq;
import com.shopex.android.prism.utils.LogUtil;
import com.shopex.android.prism.utils.NetWorkHelper;
import com.shopex.android.prism.utils.ResourceManager;

public class PrismDialog extends Dialog {

	private static final int WEBVIEW_CONTAINER_MARGIN_TOP = 25;
	private static final int WEBVIEW_MARGIN = 10;

	private Context context;
	

	private RelativeLayout mRootContainer;
	private RelativeLayout mWebViewContainer;
	private ProgressDialog mLoadingDlg;
	private WebView mWebView;
	private boolean mIsDetached = false;
	private String mAuthUrl;
	private PrismOauthListener listener;
	private PrismOauth oauth;
	private NetworkClient networkClient ;
	private Gson mGson = new Gson();
	private static int style = R.style.Theme_Translucent_NoTitleBar;

	public PrismDialog(Context context, String authUrl,
			PrismOauthListener listener, PrismOauth oauth) {
		super(context, style);
		this.context = context;
		this.mAuthUrl = authUrl;
		this.listener = listener;
		this.oauth = oauth;
		networkClient = new NetworkClient(context,null);
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		if (listener != null) {
			listener.onCancel();
		}

	}

	public void dismiss() {
		if (!this.mIsDetached) {
			if ((this.mLoadingDlg != null) && (this.mLoadingDlg.isShowing())) {
				this.mLoadingDlg.dismiss();
			}

			super.dismiss();
		}
	}

	public void onAttachedToWindow() {
		this.mIsDetached = false;
		super.onAttachedToWindow();
	}

	public void onDetachedFromWindow() {
		if (this.mWebView != null) {
			this.mWebViewContainer.removeView(this.mWebView);
			this.mWebView.stopLoading();
			this.mWebView.removeAllViews();
			this.mWebView.destroy();
			this.mWebView = null;
		}

		this.mIsDetached = true;
		super.onDetachedFromWindow();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		initWindow();

		initLoadingDlg();
		System.out.println("--initLoading--"+mAuthUrl);
		initWebView();

		
	//	initCloseButton();
	}

	private void initCloseButton() {
		ImageView closeImage = new ImageView(context);
		Drawable drawable = ResourceManager.getDrawable(context, 2);
		closeImage.setImageDrawable(drawable);
		closeImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();

				if (listener != null)
					listener.onCancel();
			}
		});
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				-2, -2);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.mWebViewContainer
				.getLayoutParams();
		layoutParams.leftMargin = (params.leftMargin
				- drawable.getIntrinsicWidth() / 2 + 5);
		layoutParams.topMargin = (params.topMargin
				- drawable.getIntrinsicHeight() / 2 + 5);
		this.mRootContainer.addView(closeImage, layoutParams);
	}

	private void initWindow() {
		
		requestWindowFeature(1);
		getWindow().setFeatureDrawableAlpha(0, 0);
		getWindow().setSoftInputMode(16);
		this.mRootContainer = new RelativeLayout(getContext());
		this.mRootContainer.setBackgroundColor(0);
		addContentView(this.mRootContainer, new ViewGroup.LayoutParams(-1, -1));
	}

	private void initLoadingDlg() {
		this.mLoadingDlg = new ProgressDialog(getContext());

		this.mLoadingDlg.requestWindowFeature(1);

		this.mLoadingDlg.setMessage(ResourceManager.getString(context, 1));
	}

	@SuppressLint({ "SetJavaScriptEnabled" })
	private void initWebView() {
		this.mWebViewContainer = new RelativeLayout(getContext());
		this.mWebView = new WebView(getContext());
		this.mWebView.getSettings().setJavaScriptEnabled(true);

		this.mWebView.getSettings().setSavePassword(false);
		this.mWebView.setWebViewClient(new PrismWebViewClient());
		this.mWebView.requestFocus();
		this.mWebView.setScrollBarStyle(0);
		this.mWebView.setVisibility(View.VISIBLE);
		NetWorkHelper.clearCookies(context, mAuthUrl);
		this.mWebView.loadUrl(mAuthUrl);

		RelativeLayout.LayoutParams webViewContainerLayout = new RelativeLayout.LayoutParams(
				-1, -1);

		RelativeLayout.LayoutParams webviewLayout = new RelativeLayout.LayoutParams(
				-1, -1);

		DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
		float density = dm.density;
		int margin = (int) (10.0F * density);
		webviewLayout.setMargins(margin, margin, margin, margin);
		//Drawable background = ResourceManager.getNinePatchDrawable(context, 1);

	//	this.mWebViewContainer.setBackgroundDrawable(background);

		this.mWebViewContainer.addView(this.mWebView, webviewLayout);
		this.mWebViewContainer.setGravity(Gravity.CENTER);

		Drawable drawable = ResourceManager.getDrawable(context, 1);
		int width = drawable.getIntrinsicWidth() / 2 + 1;
		//int width = 50;
		webViewContainerLayout.setMargins(width, (int) (25.0F * dm.density),
				width, width);
		this.mRootContainer.addView(this.mWebViewContainer,
				webViewContainerLayout);
	}
	
	  private void handleRedirectUrl(String url)
	  {
	    String code = url.split("code=")[1];
	    
	    if ((!mIsDetached)
				&& (mLoadingDlg != null)
				&& (!mLoadingDlg.isShowing()))
	    	mLoadingDlg.show();
	   networkClient.grant(new GrantTypeReq("authorization_code", code), new ShopExAsynchResponseHandler(networkClient){

		@Override
		public void onSuccess(int status, Header[] headers, byte[] body) {
			
			super.onSuccess(status, headers, body);
			mLoadingDlg.dismiss();
			OAuth oAuth = mGson.fromJson(new String(body), OAuth.class);
			listener.onSuccess(oAuth);
		}

		@Override
		public void onFailure(int status, Header[] headers, byte[] body,
				Throwable e) {
			mLoadingDlg.dismiss();
			super.onFailure(status, headers, body, e);
			listener.onFaliure(status, new String(body));
		}
		   
	   });
	 
	  }


	private class PrismWebViewClient extends WebViewClient {
		private boolean isCallBacked = false;

		private PrismWebViewClient() {
		}

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			LogUtil.i("WeiboDialog", "load URL: " + url);

			if (url.startsWith("sms:")) {
				Intent sendIntent = new Intent("android.intent.action.VIEW");
				sendIntent.putExtra("address", url.replace("sms:", ""));
				sendIntent.setType("vnd.android-dir/mms-sms");
				PrismDialog.this.getContext().startActivity(sendIntent);
				return true;
			}
			return super.shouldOverrideUrlLoading(view, url);
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			LogUtil.d("WeiboDialog", "onReceivedError: errorCode = "
					+ errorCode + ", description = " + description
					+ ", failingUrl = " + failingUrl);
			super.onReceivedError(view, errorCode, description, failingUrl);

			if (PrismDialog.this.listener != null) {
				PrismDialog.this.listener.onException(new PrismDialogException(
						description, errorCode, failingUrl));
			}
			PrismDialog.this.dismiss();
		}
		
		

		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			
			System.out.println("onPageStarted URL: " + url);
			LogUtil.d("WeiboDialog", "onPageStarted URL: " + url);
			if ((url.startsWith(PrismDialog.this.oauth.getAuth()
					.getRedirectUri())) && (!this.isCallBacked)) {
				this.isCallBacked = true;
				PrismDialog.this.handleRedirectUrl(url);
				view.stopLoading();
				PrismDialog.this.dismiss();

				return;
			}

			super.onPageStarted(view, url, favicon);

			if ((!mIsDetached)
					&& (mLoadingDlg != null)
					&& (!mLoadingDlg.isShowing()))
			mLoadingDlg.show();
		}

		public void onPageFinished(WebView view, String url) {
			LogUtil.d("WeiboDialog", "onPageFinished URL: " + url);
			super.onPageFinished(view, url);
			if ((!mIsDetached)
					&& (mLoadingDlg != null)) {
				mLoadingDlg.dismiss();
			}

			if (mWebView != null)
				mWebView.setVisibility(View.VISIBLE);
		}
	}

}
