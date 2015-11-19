package com.yxf.service;

import java.util.List;

import com.yxf.domain.User;
import com.yxf.exception.IdIsNullException;

public interface UserService {
	/**
	 * 添加用户信息
	 * @param use
	 */
	public void addUser(User user);
	/**
	 * 根据用户ID删除用户信息
	 * @param userId
	 */
	public void deleteUserById(Integer userId)throws IdIsNullException;
	/**
	 * 
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user)throws IdIsNullException;
	/**
	 * 
	 * 根据id查询单个用户信息
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId)throws IdIsNullException;
	/**
	 * 
	 * 根据用户名查询单个用户信息
	 * @param username
	 * @return
	 */
	public User findUserByName(String  username);
	/**
	 * 
	 * 查询所有用户信息
	 * @return
	 */
	public List<User> findAllUsers();
}
