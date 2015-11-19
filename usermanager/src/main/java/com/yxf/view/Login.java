package com.yxf.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String err=(String) request.getAttribute("err");
		System.out.println("err="+err);

		out.println("<h1>用户登录</h1>");
		out.println("<form action='/UserManage2/LoginCl' method='post'>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td>用户名：</td>");
		out.println("<td><input type='text' name='username' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>密码：</td>");
		out.println("<td><input type='password' name='password' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type='submit' value='登录' /></td>");
		out.println("<td><input type='reset' value='重写' /></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		if(err!=null)
			out.println(err);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
