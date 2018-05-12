package com.youmekko.web;

import javax.servlet.http.HttpSession;

import com.youmekko.domain.User;

public class HttpSessionUtils {

	public static final String USER_SESSION_KEY = "loginedUser";

	public static boolean isLoginUser(HttpSession session) {
		Object loginedUser = session.getAttribute(USER_SESSION_KEY);

		if (loginedUser == null) {
			return false;
		}
		return true;
	}

	public static User getUserFromSession(HttpSession session) {
		if (!isLoginUser(session)) {
			return null;
		}

		return (User) session.getAttribute(USER_SESSION_KEY);
	}

}
