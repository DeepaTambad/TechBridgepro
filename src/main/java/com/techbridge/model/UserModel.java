package com.techbridge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserModel implements Serializable{

	private static final long serialVersionUID = 4214452155277999478L;
	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(name = "gen1", sequenceName = "USER_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1")
	private Integer userId;
	@Column(name = "USER_NAME", length = 20)
	private String userName;
	@Column(name = "USER_EMAIL", length = 20)
	private String email;
	@Column(name = "USER_PASSWORD", length = 20)
	private String password;
	@Column(name = "USER_MOBILE_NO")
	private String mobileNumber;
	@Column(name = "USER_ACTIVE")
	private String userActive;
	@Column(name = "ROLE", length = 20)
	private String role;
	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(Integer userId, String userName, String email, String password, String mobileNumber,
			String userActive, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.userActive = userActive;
		this.role = role;
	}
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", userActive=" + userActive + ", role=" + role + "]";
	}
	
	
}

