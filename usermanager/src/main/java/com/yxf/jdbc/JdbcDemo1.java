package com.yxf.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * --SQLServer:
 * create database test1

use test1

create table users(
	id int identity(1,1) primary key,
	name nvarchar(20),
	nickName nvarchar(20),
	pwd varchar(24),
	gender varchar(2),
	birthday datetime,
	hobby nvarchar(50),
	tel varchar(20),
	email varchar(30),
	grade int default 1,
	description nvarchar(200)
)
 *
 */

/**
 * 
 * --MySQL:
 * 
create database test1;

use test1;

create table users(
	id int auto_increment primary key,
	name varchar(30),
	nickName varchar(30),
	pwd varchar(24),
	gender varchar(2),
	birthday datetime,
	hobby varchar(50),
	tel varchar(20),
	email varchar(30),
	grade int default 1,
	description varchar(300)
);
 *
 */


public class JdbcDemo1{
	public static void main(String[] args)  throws Exception{
		//1 加载驱动并注册驱动
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//2 获取数据库连接
		Connection conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1483;DatabaseName=test1", "sa", "");
		//3 创建表示sql语句的对象，并发送到数据库
		Statement stmt=conn.createStatement();
		
		
		//4 执行sql，如果有返回值，写入结果集ResultSet
		int result=stmt.executeUpdate
		("insert into users(name,nickName,pwd,gender,birthday,hobby,tel,email,grade,description) "
				+ "values('李逵','黑旋风','123',1,'2012-02-19','吃饭,睡觉,写程序','13000000123','123@sina.com',2,'蛮人')");
		//5 遍历结果集
		System.out.println("影响的行数为："+result);
		//6 关闭资源
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		if(conn!=null){
			conn.close();
			conn=null;
		}
		
		
		
	}
}
