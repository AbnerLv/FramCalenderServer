package com.pyp.fram.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.pyp.fram.dao.UserDao;
import com.pyp.fram.entity.ResponseEntity;
import com.pyp.fram.utils.JsonUtil;

/**
 * ÓÃ»§×¢²á
 */
@WebServlet(name="/ResgisterServlet", urlPatterns="/register.json")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObj = getClientJSON(request);
		String password = jsonObj.getString("password");
		String username = jsonObj.getString("username");
		String email = jsonObj.getString("email");
		ResponseEntity responseEntity = new ResponseEntity();
		int flag = 0;
		try {
			flag = UserDao.getInstance().register(username, password, email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		responseEntity.setSuccess(""+flag);
		sendXml(response,JsonUtil.createJsonString(responseEntity));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
