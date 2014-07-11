package com.worthed;

import com.google.gson.annotations.Expose;

public class InstagramUser {

	@Expose
	private String id;
	
	@Expose
	private String username;
	
	@Expose
	private String full_name;
	
	@Expose
	private String profile_picture;
	
	public InstagramUser() {
		// TODO Auto-generated constructor stub
	}

	public InstagramUser(String id, String username, String full_name,
			String profile_picture) {
		super();
		this.id = id;
		this.username = username;
		this.full_name = full_name;
		this.profile_picture = profile_picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
}
