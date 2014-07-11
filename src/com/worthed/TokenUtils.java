package com.worthed;

public class TokenUtils {

	public TokenUtils() {
		// TODO Auto-generated constructor stub
	}

	// http://worthed.com/#access_token=1422018522.f55ddc4.81a550ee1d8b4b13866da1d3a106f055
	
	private static String KEY_TOKEN = "access_token=";
	private static String KEY_CODE = "code=";
	
	public static String getToken(String url) {
		return getParm(url, KEY_TOKEN);
	}
	
	public static String getCode(String url) {
		return getParm(url, KEY_CODE);
	}
	
	private static String getParm(String url, String key) {
		if (url.contains(Constants.INSTAGRAM_REDIRECT_URI)
				&& url.contains(key)) {
			return url.substring(url.indexOf(key) + key.length());
		} 
		return null;
	}
	
}
