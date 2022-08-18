package com.shantery.result2.info;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.shantery.common.constants.*;

/**
 * @author y.nakaya
 *
 */
@Entity
@Table(name = TABLE_USERS)
public class UserInfoData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = COLUMN_USERID)
	private Long userId;

	@Column(name = COLUMN_LOGINID, nullable = false, unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@NotEmpty
	private String loginId;

	@Column(name = "password", nullable = true)
	@NotEmpty
	private String password;

	@Column(name = COLUMN_USERNAME, nullable = true)
	@NotEmpty
	private String userName;

	@Column(name = "icon", nullable = true)
	@NotNull
	private String icon;

	@Column(name = "profile", nullable = true)
	@NotEmpty
	private String profile;

	public UserInfoData() {

	}

	public UserInfoData(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
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
