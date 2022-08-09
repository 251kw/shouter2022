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

	@Column(name="username", nullable = false)
	@NotEmpty
	private String userName;

	@Column(name="icon", nullable = false)
	@NotNull
	private String icon;

	@Column(name="profile", nullable = false)
	@NotEmpty
	private String profile;
	
	//引数ありコンストラクタ
	public UserInfo(Long userId, String loginId, String userName, String icon, String profile) {
		this.userId = userId;
		this.loginId = loginId;
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
