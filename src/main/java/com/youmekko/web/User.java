package com.youmekko.web;

public class User {

	String userId, password, userName, userEmail;

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

}
