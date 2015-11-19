package com.yxf.service.impl;

import java.util.List;

import com.yxf.dao.UserDao;
import com.yxf.dao.impl.UserDaoImpl;
import com.yxf.domain.User;
import com.yxf.exception.IdIsNullException;
import com.yxf.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao=new UserDaoImpl();

	@Override
	public void addUser(User user) {

	}

	@Override
	public void deleteUserById(Integer userId) throws IdIsNullException {

	}

	@Override
	public void updateUser(User user) throws IdIsNullException {

	}

	@Override
	public User findUserById(Integer userId) throws IdIsNullException {
		return null;
	}

	@Override
	public User findUserByName(String username) {
		return dao.findUserByName(username);
	}

	@Override
	public List<User> findAllUsers() {
		return null;
	}

}
