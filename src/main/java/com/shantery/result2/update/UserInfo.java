package com.shantery.result2.update;

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

public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	private Long userId;
	
	@Column(name="loginid", nullable = false, unique = true)
	@NotEmpty	
	private String loginId;

	@Column(name="username", nullable = true)
	@NotEmpty
	private String userName;

	@Column(name="password", nullable = true)
	@NotEmpty
	private String password;

	@Column(name="icon", nullable = true)
	@NotNull
	private String icon;

	@Column(name="profile", nullable = true)
	@NotEmpty
	private String profile;
	
	//引数ありコンストラクタ
	public UserInfo(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}
	//引数なしコンストラクタ
	public UserInfo() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
