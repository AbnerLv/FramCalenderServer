package com.pyp.fram.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pyp.fram.dao.MessageBoardDao;


@WebServlet(name="/MessageBoardServlet",urlPatterns="/get_message_infos.json")
public class MessageBoardServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String json = MessageBoardDao.getInstance().getMessageInfos();
		response.setContentType("text/html;charset=utf-8");
		 //ҳ�����JSONArray������  
        PrintWriter out = response.getWriter();  
        out.print(json);  
        out.flush();
        out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
