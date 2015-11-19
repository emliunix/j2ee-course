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
		//1 ����������ע������
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//2 ��ȡ���ݿ�����
		Connection conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1483;DatabaseName=test1", "sa", "");
		//3 ������ʾsql���Ķ��󣬲����͵����ݿ�
		Statement stmt=conn.createStatement();
		
		
		//4 ִ��sql������з���ֵ��д������ResultSet
		int result=stmt.executeUpdate
		("insert into users(name,nickName,pwd,gender,birthday,hobby,tel,email,grade,description) "
				+ "values('����','������','123',1,'2012-02-19','�Է�,˯��,д����','13000000123','123@sina.com',2,'����')");
		//5 ���������
		System.out.println("Ӱ�������Ϊ��"+result);
		//6 �ر���Դ
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
