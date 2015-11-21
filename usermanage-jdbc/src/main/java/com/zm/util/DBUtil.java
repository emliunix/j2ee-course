package com.zm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

////这是一个工具类，用于得到连接和关闭连接
public class DBUtil {
	private static Connection ct = null;// 连接
	// private static ResultSet rs=null;//结果
	// private static PreparedStatement ps=null;
	// private static CallableStatement cs=null;
	// 连接数据库参数
	private static String url;
	private static String drivername = "";
	private static String username = "";
	private static String password = "";

	public static String getUrl() {
		return url;
	}

	public static String getDrivername() {
		return drivername;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	// 加载驱动，一次
	static {
		InputStream is = null;
		//FileInputStream fis=null;
		try {
			Properties properties = new Properties();
			//【写法一】：【web项目】db.properties放在src目录下【要使用类加载器，默认目录就是src目录】
			is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
			//【写法二】：【web项目】db.properties放在src目录下的com.zm.util包下
			//is = DBUtil.class.getClassLoader().getResourceAsStream("com/zm/util/db.properties");
			properties.load(is);
			//【写法三】：【普通java项目】db.properties放在项目根目录下【可以直接读】
			// fis= new FileInputStream("db.properties");
			// properties.load(fis);

			// 属性文件读取信息
			drivername = properties.getProperty("driver");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			url = properties.getProperty("url");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					is = null;// 使用垃圾回收
				}				
			}
		}
	}

	// 得到连接
	// 注意配置文件
	public static Connection getCon() {
		try {
			Class.forName(drivername);
			// ct= DriverManager.getConnection(url2); //windows身份验证
			ct = DriverManager.getConnection(url, username, password); // SQLServer身份验证
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;// 谁调用谁拿到Connection
	}

	// public static void main(String args [])
	// {
	// System.out.println(drivername);
	// System.out.println(username);
	// System.out.println(password);
	// System.out.println(url);
	// }

	// 关闭资源函数
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;// 使用垃圾回收
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps = null;
			}
		}

		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ct = null;
			}
		}

	}

}
