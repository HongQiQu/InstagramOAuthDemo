package com.worthed;

import com.google.gson.annotations.Expose;


public class InstagramToken {

	@Expose
	private String access_token;
	@Expose
	private InstagramUser user;
	
	public InstagramToken(String access_token, InstagramUser user) {
		super();
		this.access_token = access_token;
		this.user = user;
	}

	public InstagramToken() {
		// TODO Auto-generated constructor stub
	}

	public InstagramUser getUser() {
		return user;
	}

	public void setUser(InstagramUser user) {
		this.user = user;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}
