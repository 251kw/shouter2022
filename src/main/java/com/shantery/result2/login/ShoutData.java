package com.shantery.result2.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author r.totoki
 *
 */

@Entity
@Table(name="shouts")
public class ShoutData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shoutsid", nullable = false)
	private Long shoutsId;
	
	@Column(name="username", nullable = false)
	private String userName;
	
	@Column(name="icon", nullable = false)
	private String icon;	
	
	@Column(name="date", nullable = false)
	private String date;	
	
	@Column(name="writing", nullable=false)
	@NotEmpty
	private String writing; 
	
	public ShoutData() {
		
	}
	
	public ShoutData(Long shoutsId, String userName, String icon, String date, String writing) {
		this.shoutsId = shoutsId;
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}
	
	public Long getShoutsId() {
		return shoutsId;
	}

	public void setUserId(Long shoutsId) {
		this.shoutsId = shoutsId;
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


