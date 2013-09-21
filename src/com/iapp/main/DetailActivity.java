package com.iapp.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.iapp.mytest.R;
import com.iapp.view.BaseActivity;

public class DetailActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		
		setContentView(R.layout.activity_detail);
		
		Bundle bundle=getIntent().getExtras();
		String url=bundle.getString("str1");
		
		WebView mWebView = $.id(R.id.webViewDetail).getWebView();
		
		final Activity activity = this;
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				$.id(R.id.pgsWebViewPgs).getProgressBar().setProgress(newProgress*1000);
			} 
		});
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				$.id(R.id.textUrl).text(url);
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});
		
		//mWebView.addJavascriptInterface(new JsObject(), "injectedObject");
		//mWebView.loadData("", "text/html", null);
		mWebView.getSettings().setJavaScriptEnabled(true); 
		
		

		//String summary = "<html><body>You scored <b>192</b> points.</body></html>";
		//mWebView.loadData(summary, "text/html", null);
		mWebView.loadUrl(url);
//		mWebView.loadUrl("http://info.3g.qq.com/");
//		mWebView.loadUrl("http://slashdot.org/");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}

class JsObject {
	    @JavascriptInterface
	    public String toString() { return "injectedObject"; }
	 }