package com.youmekko.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;

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

	public boolean matchId(Long inputId) {
		if (inputId == null) {
			return false;
		}
		return inputId.equals(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "userId : " + userId + " / password : " + password + " / userName : " + userName + " / userEmail : "
				+ userEmail;
	}

	public void update(User newUser) {
		this.userId = newUser.userId;
		this.password = newUser.password;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
