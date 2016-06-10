package com.pyp.fram.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.pyp.fram.dao.MessageBoardDao;
import com.pyp.fram.entity.CommentEntity;
import com.pyp.fram.entity.MessageBoardEntity;
import com.pyp.fram.entity.ResponseEntity;
import com.pyp.fram.utils.JsonUtil;


@WebServlet(name="/AddMessagesServlet",urlPatterns="/addMessages.json")
public class AddMessagesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObj = getClientJSON(request);
		String userId = jsonObj.getString("userId");
		String content = jsonObj.getString("content");
		String time = jsonObj.getString("time");
		MessageBoardEntity entity = new MessageBoardEntity();
		entity.setContent(content);
		entity.setUserId(userId);
		entity.setTime(time);
	
		sendXml(response,JsonUtil.createJsonString(MessageBoardDao.getInstance().addMessages(entity)));
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
