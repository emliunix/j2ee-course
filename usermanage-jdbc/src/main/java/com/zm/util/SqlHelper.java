package com.zm.util;

//src=类路径
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//这是一个工具类，主要用于完成对数据库的crud操作
public class SqlHelper {
	// 定义需要的变量
	private static Connection ct = null;// 连接
	private static ResultSet rs = null;// 结果
	private static PreparedStatement ps = null;
	private static CallableStatement cs = null;
	
	public static Connection getCt() {
		return ct;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static CallableStatement getCs() {
		return cs;
	}

	// 查询【带参数，返回ResultSet】
	public static ResultSet executeQuery(String sql, String[] parameters) {

		try {

			ct = DBUtil.getCon();
			ps = ct.prepareStatement(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
			// 执行
			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();// 开发阶段
			throw new RuntimeException(e.getMessage());
		} finally {
			// 此处暂时不用关闭资源，等到调用者用完了rs后再手动关闭资源
			// DBUtil.close(rs, ps, ct);
		}
		return rs;
	}

	public interface Callback {
		public void process(ResultSet rs);
	}

	public static void executeQuery(String sql, String[] parameters, Callback callback) {
		executeQuery(sql, parameters);
		callback.process(rs);
		DBUtil.close(rs, ps, ct);
	}

	// 查询【带参数，返回ArrayList，相当于业务逻辑的二次封装，常见于service层】
	// ResultSet->ArrayList
	public static ArrayList executeQuery2(String sql, String[] paras) {
		ArrayList al = new ArrayList();
		try {
			ct = DBUtil.getCon();
			ps = ct.prepareStatement(sql);
			// 给sql问号赋值
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
			// 非常有用的
			ResultSetMetaData rsmd = rs.getMetaData();
			// 用法rs可以的到有多少列
			int columnNum = rsmd.getColumnCount();
			// 循环从a1中取出数据封装到ArrayList中
			while (rs.next()) {
				Object[] objects = new Object[columnNum];
				for (int i = 0; i < objects.length; i++) {
					objects[i] = rs.getObject(i + 1); // 返回对象数组
				}
				al.add(objects);
			}
			return al;
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出运行时异常，给该函数的调用者一个选择，可以处理，也可以不处理
			throw new RuntimeException(e.getMessage());
		} finally {
			// ★★★此处可以关闭资源，原因是rs不用返回，返回的是二次封装的al。★★★
			DBUtil.close(rs, ps, ct);
		}

	}

	// 单个更新语句[insert | update | delete]
	// 【暂不考虑事务】
	public static void executeUpdate(String sql, String[] parameters) {

		try {

			ct = DBUtil.getCon();
			ps = ct.prepareStatement(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
			// 执行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();// 开发阶段
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			DBUtil.close(rs, ps, ct);
		}

	}

	// 多个更新语句[insert | update | delete]
	// 【需要考虑事务】
	public static void executeUpdate2(String[] sql, String[][] parameters) {

		try {

			ct = DBUtil.getCon();
			// 此时，用户传入的可能是多个sql语句
			ct.setAutoCommit(false); // 取消自动提交

			// 给？赋值
			for (int i = 0; i < sql.length; i++) {

				if (parameters[i] != null) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setObject(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}

			}

			ct.commit(); // 手动提交
			ct.setAutoCommit(true); // 恢复自动提交

		} catch (Exception e) {
			e.printStackTrace();// 开发阶段
			throw new RuntimeException(e.getMessage());
		} finally {

			try {
				if (ct != null) {
					ct.rollback(); // 事务回滚
					ct.setAutoCommit(true); // 恢复自动提交
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 关闭资源
			DBUtil.close(rs, ps, ct);

		}

	}

	// 调用存储过程【无返回值】
	// 【不需要考虑事务】
	// 调用形式：{call 存储过程名(?,?,?,?)}
	public static void callPro1(String sql, String[] parameters) {

		try {

			ct = DBUtil.getCon();
			cs = ct.prepareCall(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}

			cs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			DBUtil.close(rs, cs, ct);
		}

	}

	// 调用存储过程【有返回值】
	// 【不需要考虑事务】
	// 调用形式：{call 存储过程名(?,?,?,?)}
	// 适用于Oracle,其他数据库待测试
	public static CallableStatement callPro2(String sql, String[] inparameters,
			String[] outparameters) {

		try {

			ct = DBUtil.getCon();
			cs = ct.prepareCall(sql);
			// 给输入参数赋值
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}

			// 给输出参数赋值
			if (outparameters != null) {
				for (int i = 0; i < outparameters.length; i++) {
					cs.setObject(inparameters.length + i + 1, outparameters[i]);
				}
			}

			cs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 暂时不关闭，等到返回cs，用完了之后由调用者手动关闭
		}

		return cs;

	}

	/**
	 * 在Oracle中创建如下存储过程p:
	 * 		SQL>create or replace procedure p(v_in_empno number,v_in_ename varchar2)
	 * 			is 
	 * 				--定义变量 
	 * 			begin
	 * 				--执行语句
	 * 				insert into emp(empno,ename) values(v_in_empno,v_in_ename);
	 * 			end;
	 * 			/
	 * 
	 */

	// 数据分页（待定）
}
