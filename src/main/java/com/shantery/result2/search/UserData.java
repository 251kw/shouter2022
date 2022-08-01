package com.shantery.result2.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")

public class UserData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	//@NotNull
	private String userId;

	@Column(nullable = true)
	@NotEmpty	
	private String loginId;

	@Column(nullable = true)
	@NotEmpty
	private String userName;

	@Column(nullable = true)
	@NotEmpty
	private String password;

	@Column(nullable = true)
	@NotNull
	private String icon;

	@Column(nullable = true)
	@NotEmpty
	private String profile;
	
	public UserData(String loginId, String userName, String password, String icon, String profile) {
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.icon = icon;
		this.profile = profile;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
