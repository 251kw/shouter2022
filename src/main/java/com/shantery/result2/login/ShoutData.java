package com.shantery.result2.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shouts")
public class ShoutData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId", nullable = false)
	private Long userId;
	
	@Column(name="username", nullable = false)
	private String userName;
	
	@Column(name="icon", nullable = false)
	private String icon;	
	
	@Column(name="date", nullable = false)
	private String date;	
	
	@Column(name="writing", nullable=false)
	private String writing; 
	
	public ShoutData() {
		
	}
	
	public ShoutData(Long userId, String userName, String icon, String date, String writing) {
		this.userId = userId;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}
}


