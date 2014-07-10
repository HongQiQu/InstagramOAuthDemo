package com.worthed;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InstagramDemoActivity extends Activity {

	private final String TAG = InstagramDemoActivity.class.getSimpleName();
	
	public static final String INSTAGRAM_CLIENT_ID = "109a557e17a54e2a85f1448f4ab62183";// f55ddc4a4daa4041867989e0793d6645
	public static final String INSTAGRAM_CLIENT_SECRET = "1ff63ecfda5d4065a13dc145cdbcd6ca";// 1ff63ecfda5d4065a13dc145cdbcd6ca
	public static final String INSTAGRAM_REDIRECT_URI = "http://www.keeeweee.com";
	public static final String INSTAGRAM_OAUTH = "https://api.instagram.com/oauth/authorize/?client_id=" 
	+ INSTAGRAM_CLIENT_ID + "&redirect_uri=" + INSTAGRAM_REDIRECT_URI + "&response_type=token";
	// &scope=basic+relationships+likes+comments
	
	// https://api.instagram.com/v1/users/self/?access_token=1422018522.109a557.4307be6e5d3847be84084b992c86f5e4
	
	private WebView oauthWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instagram_demo);
		oauthWebView = (WebView) findViewById(R.id.oauth_instagram_webview);
		oauthWebView.clearCache(true);
		oauthWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		oauthWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsConfirm(WebView view, String url,
					String message, JsResult result) {
				// TODO Auto-generated method stub
				Log.d(TAG, "ChromeClient url : " + url);
				return super.onJsConfirm(view, url, message, result);
			}
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}
		});
		oauthWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				Log.d(TAG, "WebViewClient start url : " + url);
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				Log.d(TAG, "WebViewClient finish url : " + url);
				super.onPageFinished(view, url);
			}
		});
		loading(INSTAGRAM_OAUTH);
	}

	private void loading(String url) {
		Log.d(TAG, "loading HTTP " + url);
		oauthWebView.getSettings().setSupportZoom(true);
		oauthWebView.getSettings().setBuiltInZoomControls(true);
		oauthWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		oauthWebView.loadUrl(url);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.instagram_demo, menu);
		return true;
	}

}
