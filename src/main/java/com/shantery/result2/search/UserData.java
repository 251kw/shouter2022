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
	@Column
	//@NotNull
	private String userId;

	@Column(nullable = true)	
	private String loginId;

	@Column(nullable = true)
	private String userName;

	@Column(nullable = true)
	private String icon1;

	@Column(nullable = true)
	private String icon2;

	@Column(nullable = true)
	private String profile;
	
	public UserData(String loginId, String userName, String icon1, String icon2, String profile) {
		this.loginId = loginId;
		this.userName = userName;
		this.icon1 = icon1;
		this.icon2 = icon2;
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
	
	public String getIcon1() {
		return icon1;
	}
	
	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
	
	public String getIcon2() {
		return icon2;
	}
	
	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
