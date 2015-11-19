package com.yxf.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;





import com.yxf.dao.UserDao;
import com.yxf.domain.User;
import com.yxf.exception.DaoException;
import com.yxf.exception.IdIsNullException;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
	}

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
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		User user=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1483;DatabaseName=test1", "sa", "");
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from users where name='"+username+"'");
			if(rs.next()){
				user=new User();
				//封装
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setNickName(rs.getString("nickName"));
				user.setGender(rs.getString("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setHobby(rs.getString("hobby"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setGrade(rs.getString("grade"));
				user.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					rs=null;//垃圾回收，上！
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					stmt=null;//垃圾回收，上！
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					conn=null;//垃圾回收，上！
				}
			}
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		return null;
	}
	
	/////////////////////////////////////////////
	


}
