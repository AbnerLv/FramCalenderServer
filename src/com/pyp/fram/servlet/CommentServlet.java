package com.pyp.fram.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pyp.fram.dao.MessageBoardDao;

/**
 * Servlet implementation class MyTaskServlet
 */
@WebServlet(name="/CommentServlet",urlPatterns="/getCommentById")
public class CommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String messageId = request.getParameter("messageId");
		String 	json = MessageBoardDao.getInstance().getCommentById(messageId);
		
		response.setContentType("text/html;charset=utf-8");
		 //页面输出JSONArray的内容  
        PrintWriter out = response.getWriter();  
        out.print(json);  
        out.flush();
        out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
