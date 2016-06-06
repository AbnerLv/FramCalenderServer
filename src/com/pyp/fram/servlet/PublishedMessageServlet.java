package com.pyp.fram.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pyp.fram.dao.MessageBoardDao;


@WebServlet(name="/PublishedMessageServlet",urlPatterns="/getPublishedMessages.json")
public class PublishedMessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userId = request.getParameter("userId");
	    System.out.println("userId = " + userId);
		try {
			String json = MessageBoardDao.getInstance().getMessagesByUserId(userId);
			sendXml(response,json);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
