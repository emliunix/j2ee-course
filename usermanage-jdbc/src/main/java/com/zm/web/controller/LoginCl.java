package com.zm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
//		
		//到数据库验证
		
//		if("张三".equals(username)&&password.equals("123")){
//			//跳转到成功界面
//			//request转发
//			request.getRequestDispatcher("/Main")
//			.forward(request, response);
//			//response重定向
//			//response.sendRedirect("/UserManage2/Main");
//		}else{
//			
//			String err="用户名不存在或密码错误，请重新输入！";
//			
//			request.setAttribute("err", err);
//			
//			request.getRequestDispatcher("/Login")
//			.forward(request, response);
//			//response重定向
//			//response.sendRedirect("/UserManage2/Login");
//		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
