package com.shantery.result2.login;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.shantery.common.constants.*;

/**
 * @author r.totoki
 *
 */

@Entity
@Table(name = TABLE_USERS)

public class UserLoginData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=COLUMN_USERID)
	private Long userId;

	@Column(name=COLUMN_LOGINID,nullable = false)	
	private String loginId;
	
	@Column(nullable = false)	
	private String password;
	
	@Column(nullable = false)	
	private String userName;
	
	@Column(nullable = false)	
	private String icon;
	
	@Column(nullable = false)	
	private String profile;
	
	public UserLoginData() {
		
	}
	
	public UserLoginData(Long userId, String loginId, String password, String userName, String icon, String profile) {
		this.userId = userId;
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
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
