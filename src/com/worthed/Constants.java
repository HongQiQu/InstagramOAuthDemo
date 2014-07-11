package com.worthed;

public class Constants {

	public static final String INSTAGRAM_CLIENT_ID = "f55ddc4a4daa4041867989e0793d6645";
	public static final String INSTAGRAM_CLIENT_SECRET = "1ff63ecfda5d4065a13dc145cdbcd6ca";

	public static final String INSTAGRAM_OAUTH_TYPE_IMPLICIT = "token";
	public static final String INSTAGRAM_OAUTH_TYPE_EXPLICIT = "code";

	public static final String INSTAGRAM_REDIRECT_URI = "http://worthed.com"; // http://www.keeeweee.com
	public static final String INSTAGRAM_OAUTH = "https://api.instagram.com/oauth/authorize/?client_id="
			+ INSTAGRAM_CLIENT_ID
			+ "&redirect_uri="
			+ INSTAGRAM_REDIRECT_URI
			+ "&response_type=";
	public static final String INSTAGRAM_OAUTH_IMPLICIT = INSTAGRAM_OAUTH
			+ INSTAGRAM_OAUTH_TYPE_IMPLICIT;
	public static final String INSTAGRAM_OAUTH_EXPLICIT = INSTAGRAM_OAUTH
			+ INSTAGRAM_OAUTH_TYPE_EXPLICIT;
	// &scope=basic+relationships+likes+comments

	// https://api.instagram.com/v1/users/self/?access_token=1422018522.109a557.4307be6e5d3847be84084b992c86f5e4

}
