package com.pyp.fram.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.pyp.fram.dao.UserDao;


@WebServlet(name = "LoginServlet", urlPatterns = "/login.json")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonObj = getClientJSON(request);
		String username = jsonObj.getString("username");
		String password = jsonObj.getString("password");
		String json = null;
		try {
			json = UserDao.getInstance().checkLogin(username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		sendXml(response,json);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
