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
 * Íü¼ÇÃÜÂë
 */
@WebServlet(name = "ForgetPasswordServlet", urlPatterns = "/forgetPassword.json")
public class ForgetPasswordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject jsonObj = getClientJSON(request);
		String username = jsonObj.getString("username");
		String email = jsonObj.getString("email");
		String phone = jsonObj.getString("phone");

		ResponseEntity entity = new ResponseEntity();
		String password = UserDao.getInstance().forgetPassword(username, email, phone);
		entity.setMessage(password);
		
		sendXml(response, JsonUtil.createJsonString(entity));

	}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
