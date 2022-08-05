package com.shantery.result2.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shantery.result2.search.NotAllBlank;

@Entity
@NotAllBlank(fields = { "userName","icon","date","writing" })
@Table(name="shouts")
public class ShoutData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public ShoutData(String userName, String icon, String date, String writing) {
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
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


