package com.egen.challenge.UserManagement;

import java.util.*;

public class UserService {

	public List<User> getAllUsers() {
		UserDAO dao = UserDAO.getInstance();
		return dao.getAllUsers();
	}

	public User createUser(User user) {

		UserDAO dao = UserDAO.getInstance();
		User toRet = dao.createUser(user);
		return toRet;
	}

	public User updateUser(User user) {

		UserDAO dao = UserDAO.getInstance();
		if (user == null)
			return null;
		User toRet = dao.updateUser(user);
		return toRet;

	}

}
