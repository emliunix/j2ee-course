package com.zm.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistView extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<base href=\"" + getServletContext().getContextPath() + "\"/>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img width='120' height='30' src='imgs/conch.gif' />");
		out.println("<hr/>");


		// 错误消息
		String errmsg = request.getAttribute("errorMessage").toString();
		if(!(null == errmsg || errmsg.isEmpty())) {
			out.println("<p style=\"color: red; \">" + errmsg + "</p>");
		}
		
		out.println("<h1>用户注册</h1>");
		out.println("<form action='/RegistCl?operation=regist' method='post'>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td>用户名：</td>");
		out.println("<td><input type='text' name='username' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>昵称：</td>");
		out.println("<td><input type='text' name='nickName' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>密码：</td>");
		out.println("<td><input type='password' name='password' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>确认密码：</td>");
		out.println("<td><input type='password' name='password1' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>性别：</td>");
		out.println("<td><input type='radio' name='gender' value='1'/>男");
		out.println("<input type='radio' name='gender' value='0'/>女</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>生日：</td>");
		out.println("<td><input type='text' name='birthday' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>爱好：</td>");
		out.println("<td><input type='checkbox' name='hobby' value='吃饭'/>吃饭");
		out.println("<input type='checkbox' name='hobby' value='睡觉'/>睡觉");
		out.println("<input type='checkbox' name='hobby' value='写程序'/>写程序</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>电话：</td>");
		out.println("<td><input type='text' name='tel' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>邮箱：</td>");
		out.println("<td><input type='text' name='email' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>级别：</td>");
		out.println("<td><input type='text' name='grade' value=''/></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>描述：</td>");
		out.println("<td><textarea row='3' cols='36' name='description' value=''></textarea></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'><input type='submit' value='注册' /></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		
		out.println("<hr/>");
		out.println("<img width='120' height='30' src='imgs/fish.gif' />");
		out.println("</body>");
		out.println("</html>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
