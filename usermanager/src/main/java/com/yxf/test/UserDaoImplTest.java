package com.yxf.test;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

import org.junit.Test;

import com.yxf.dao.UserDao;
import com.yxf.dao.impl.UserDaoImpl;
import com.yxf.domain.User;

public class UserDaoImplTest {
	
	UserDao dao=new UserDaoImpl();

	@Test
	public void testUserDaoImpl() {
	}

	@Test
	public void testAddUser() {
	}

	@Test
	public void testDeleteUserById() {
	}

	@Test
	public void testUpdateUser() {
	}

	@Test
	public void testFindUserById() {
	}

	@Test
	public void testFindUserByName() {
		
		String username="¿ÓÂ”";
		User user=dao.findUserByName(username);
		System.out.println(user);
	}

	@Test
	public void testFindAllUsers() {
	}

}
