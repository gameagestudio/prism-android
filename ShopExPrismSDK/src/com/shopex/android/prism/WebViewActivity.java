package com.shopex.android.prism;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	private WebView webView;
	
	private String data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		webView = new WebView(this);
		setContentView(webView);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("--->"+url);
				if(url.startsWith("http://buwb2lii.com/")){
					String code = url.split("code=")[1];
					System.out.println(code);
					if(code != null && !code.trim().equals("")){
						Toast.makeText(WebViewActivity.this, "登录成功，code:"+code, Toast.LENGTH_SHORT).show();
						Intent i = new Intent(WebViewActivity.this,MainActivity.class);
						i.putExtra("code", code);
						setResult(RESULT_OK, i);
						finish();
					}
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
			
		});
		webView.clearCache(true);
		
		webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		data = getIntent().getStringExtra("data");
		webView.loadUrl(data);
		//webView.loadDataWithBaseURL("about:blank",data, "text/html", "utf-8",null);
	}

}
