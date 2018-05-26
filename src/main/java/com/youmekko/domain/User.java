package com.youmekko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {

	@Column(nullable = false, length = 20, unique = true)
	@JsonProperty
	private String userId;

	@Column(nullable = false, length = 20)
	@JsonIgnore
	private String password;

	@Column(nullable = false, length = 20)
	@JsonProperty
	private String userName;

	@Column(nullable = false, length = 20)
	@JsonProperty
	private String userEmail;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean matchPassword(String inputPassword) {
		if (inputPassword == null) {
			return false;
		}

		return inputPassword.equals(password);
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public void update(User newUser) {
		this.userId = newUser.userId;
		this.password = newUser.password;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}
	
	public boolean matchId(Long inputId) {
		if (inputId == null) {
			return false; 
		}
		return inputId.equals(getId());
	}

	@Override
	public String toString() {
		return "User [" + super.toString() + " / userId : " + userId + " / password : " + password + " / userName : " + userName + " / userEmail : "
				+ userEmail;
	}

}
