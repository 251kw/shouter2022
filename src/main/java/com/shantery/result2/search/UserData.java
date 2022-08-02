package com.shantery.result2.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NotAllBlank(fields = { "loginId","userName","icon1","icon2","profile" })
@Table(name = "users")

public class UserData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	//@NotNull
	private String userId;

	@Column(name="loginid",nullable = true)	
	private String loginId;

	@Column(name="username",nullable = true)
	private String userName;

	@Column(nullable = true)
	private String icon;

	@Column(nullable = true)
	private String profile;
	
	public UserData() {
		
	}
	
	public UserData(String loginId, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
