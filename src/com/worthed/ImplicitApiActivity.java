package com.worthed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ImplicitApiActivity extends Activity {
	private final String TAG = ImplicitApiActivity.class.getSimpleName();

	private final int PROGRESS_SHOW = 1001;
	private final int PROGRESS_HIDE = 1002;
	
	private WebView oauthWebView;
	
	private ProgressDialog progressDialog;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.implicit_layout);
		init();
		initWebView();
	}

	private void init() {
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case PROGRESS_SHOW:
					if (progressDialog != null && !progressDialog.isShowing()) {
						progressDialog.show();
					}
					break;
				case PROGRESS_HIDE:
					if (progressDialog != null && progressDialog.isShowing()) {
						progressDialog.dismiss();
					}
					break;

				default:
					break;
				}
				super.handleMessage(msg);
			}
			
		};
		progressDialog = ProgressDialog.show(this, getString(R.string.loding_title), getString(R.string.loding_content), true, true);  
	}
	
	private void initWebView() {
		oauthWebView = (WebView) findViewById(R.id.oauth_instagram_webview);
		oauthWebView.clearCache(true);
		oauthWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		oauthWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsConfirm(WebView view, String url,
					String message, JsResult result) {
				// TODO Auto-generated method stub
				Log.d(TAG, "ChromeClient url : " + url);
				handlerUrl(url);
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
				handler.sendEmptyMessage(PROGRESS_SHOW);
				Log.d(TAG, "WebViewClient start url : " + url);
				handlerUrl(url);
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(PROGRESS_HIDE);
				Log.d(TAG, "WebViewClient finish url : " + url);
				super.onPageFinished(view, url);
			}
		});
		
		loading(Constants.INSTAGRAM_OAUTH_IMPLICIT);
	}
	
	private void loading(String url) {
		Log.d(TAG, "loading HTTP " + url);
		oauthWebView.getSettings().setSupportZoom(true);
		oauthWebView.getSettings().setBuiltInZoomControls(true);
		oauthWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		oauthWebView.loadUrl(url);
	}

	private void handlerUrl(String url) {
		String token = TokenUtils.getToken(url);
		if (!TextUtils.isEmpty(token)) {
			Toast.makeText(getApplicationContext(), "token : " + token,
					Toast.LENGTH_SHORT).show();
			Log.e(TAG, "token : " + token);
			handler.sendEmptyMessage(PROGRESS_HIDE);
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(PROGRESS_HIDE);
		super.onBackPressed();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(PROGRESS_HIDE);
		super.onDestroy();
	}
}
