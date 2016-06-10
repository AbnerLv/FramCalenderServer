package com.pyp.fram.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.pyp.fram.dao.UserDao;
import com.pyp.fram.entity.ResponseEntity;
import com.pyp.fram.entity.UserEntity;
import com.pyp.fram.utils.JsonUtil;

/**
 * ÐÞ¸ÄÃÜÂë
 */
@WebServlet(name="ModifyPerInfoServlet",urlPatterns = "/modifyPerInfo.json")
public class ModifyPerInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonObj = getClientJSON(request);
		Integer userId = Integer.parseInt(jsonObj.getString("userId"));
		String username = jsonObj.getString("username");
		Integer sex = Integer.parseInt(jsonObj.getString("sex"));
		Integer age = Integer.parseInt(jsonObj.getString("age"));
		String phone = jsonObj.getString("phone");
		String email = jsonObj.getString("email");
		String city = jsonObj.getString("city");
		String address = jsonObj.getString("address");
		String profile = jsonObj.getString("profile");
		UserEntity userEntity = new UserEntity(userId, username, email, "", profile, phone, city, age, sex, address);
		ResponseEntity entity = new ResponseEntity();
		int code =  UserDao.getInstance().modifyPerInfo(userEntity);
		entity.setCode(code);
		sendXml(response, JsonUtil.createJsonString(entity));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
