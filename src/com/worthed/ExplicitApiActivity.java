package com.worthed;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ExplicitApiActivity extends Activity {
	private final String TAG = ExplicitApiActivity.class.getSimpleName();

	private WebView oauthWebView;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.implicit_layout);
		handler = new Handler();
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
				Log.d(TAG, "WebViewClient start url : " + url);
				handlerUrl(url);
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				Log.d(TAG, "WebViewClient finish url : " + url);
				super.onPageFinished(view, url);
			}
		});
		loading(Constants.INSTAGRAM_OAUTH_EXPLICIT);
	}

	private void loading(String url) {
		Log.d(TAG, "loading HTTP " + url);
		oauthWebView.getSettings().setSupportZoom(true);
		oauthWebView.getSettings().setBuiltInZoomControls(true);
		oauthWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		oauthWebView.loadUrl(url);
	}

	private void handlerUrl(String url) {
		String code = TokenUtils.getCode(url);
		String error = TokenUtils.getError(url);
		if (!TextUtils.isEmpty(code)) {
			Toast.makeText(getApplicationContext(), "code : " + code,
					Toast.LENGTH_SHORT).show();
			Log.e(TAG, "code : " + code);
			requestToken(code);
		} else if (!TextUtils.isEmpty(error)) {
			Toast.makeText(getApplicationContext(), "error : " + error,
					Toast.LENGTH_SHORT).show();
			Log.e(TAG, "error : " + error);
		}
	}

	public void requestToken(final String code) {
		Log.d(TAG, "requestToken()");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("client_id",
						Constants.INSTAGRAM_CLIENT_ID));
				params.add(new BasicNameValuePair("client_secret",
						Constants.INSTAGRAM_CLIENT_SECRET));
				params.add(new BasicNameValuePair("grant_type",
						"authorization_code"));
				params.add(new BasicNameValuePair("redirect_uri",
						Constants.INSTAGRAM_REDIRECT_URI));
				params.add(new BasicNameValuePair("code", code));

				String responseStr = doPost(
						Constants.INSTAGRAM_ACCESS_TOKEN_URL, params, getHttpClient());

				Gson gson = new Gson();
				Type type = new TypeToken<InstagramToken>() {
				}.getType();
				InstagramToken token = gson.fromJson(responseStr, InstagramToken.class);
				if (token != null) {
					final String tokenStr = token.getAccess_token();
					Log.d(TAG, "accessToken : " + tokenStr);
					if (token.getUser() != null) {
						final String id = token.getUser().getId();
						Log.d(TAG, "id          : " + id);
						Log.d(TAG, "username    : "
								+ token.getUser().getUsername());
						handler.post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(getApplicationContext(),
										"id : " + id + "\ntoken : " + tokenStr,
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				}
			}
		}).start();
	}

	public String doPost(String url, List<NameValuePair> params, HttpClient httpClient) {
		/* 建立HTTPPost对象 */
		HttpPost httpRequest = new HttpPost(url);
		String strResult = "doPostError";

		try {
			/* 添加请求参数到请求对象 */
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				strResult = EntityUtils.toString(httpResponse.getEntity());
			} else {
				strResult = "Error Response: "
						+ httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (IOException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (Exception e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		}
		Log.e(TAG, strResult);
		return strResult;
	}

	public HttpClient getHttpClient() {
		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
		HttpParams httpParams = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小
		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		// 设置重定向，缺省为 true
		HttpClientParams.setRedirecting(httpParams, true);

		// 设置 user agent
		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// 创建一个 HttpClient 实例
		// 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient
		// 中的用法，在 Android 1.5 中我们需要使用 Apache 的缺省实现 DefaultHttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}

}
