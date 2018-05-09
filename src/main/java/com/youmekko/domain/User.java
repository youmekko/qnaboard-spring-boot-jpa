package com.youmekko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20)
	private String userId;

	@Column(nullable = false, length = 20)
	private String password;

	@Column(nullable = false, length = 20)
	private String userName;

	@Column(nullable = false, length = 20)
	private String userEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "userId : " + userId + " / password : " + password + " / userName : " + userName + " / userEmail : "
				+ userEmail;
	}

	public void update(User newUser) {
		this.password = newUser.password;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}

}
