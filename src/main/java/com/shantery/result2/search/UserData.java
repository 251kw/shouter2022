package com.shantery.result2.search;

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
@NotAllBlank(fields = {FIELD_LOGINID,FIELD_USERNAME,FIELD_ICON,FIELD_PROFILE })
@Table(name = TABLE_USERS)

public class UserData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=COLUMN_USERID)
	private Long userId;

	@Column(name=COLUMN_LOGINID,nullable = true)	
	private String loginId;

	@Column(name=COLUMN_USERNAME,nullable = true)
	private String userName;

	@Column(nullable = true)
	private String icon;

	@Column(nullable = true)
	private String profile;
	
	public UserData() {
		
	}
	
	public UserData(Long userId, String loginId, String userName, String icon, String profile) {
		this.userId = userId;
		this.loginId = loginId;
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
