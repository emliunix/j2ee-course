package com.yxf.service;

import java.util.List;

import com.yxf.domain.User;
import com.yxf.exception.IdIsNullException;

public interface UserService {
	/**
	 * ����û���Ϣ
	 * @param use
	 */
	public void addUser(User user);
	/**
	 * �����û�IDɾ���û���Ϣ
	 * @param userId
	 */
	public void deleteUserById(Integer userId)throws IdIsNullException;
	/**
	 * 
	 * �޸��û���Ϣ
	 * @param user
	 */
	public void updateUser(User user)throws IdIsNullException;
	/**
	 * 
	 * ����id��ѯ�����û���Ϣ
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId)throws IdIsNullException;
	/**
	 * 
	 * �����û�����ѯ�����û���Ϣ
	 * @param username
	 * @return
	 */
	public User findUserByName(String  username);
	/**
	 * 
	 * ��ѯ�����û���Ϣ
	 * @return
	 */
	public List<User> findAllUsers();
}
