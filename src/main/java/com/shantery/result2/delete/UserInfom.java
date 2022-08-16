package com.shantery.result2.delete;

/**
 * @author k.iwai
 *
 */
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static com.shantery.common.constants.*;

@Entity
@Table(name = TABLE_USERS)

public class UserInfom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = COLUMN_USERID)
	@NotNull
	private Long userId;

	@Column(name = "loginid", nullable = true)
	private String loginId;

	@Column(name = "username", nullable = true)
	private String userName;

	@Column(nullable = true)
	private String icon;

	@Column(nullable = true)
	private String profile;

	public UserInfom() {

	}

	// 削除に使用
	public UserInfom(Long userId) {
		this.userId = userId;
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
