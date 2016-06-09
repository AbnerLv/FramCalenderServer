package com.pyp.fram.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pyp.fram.entity.CommentEntity;
import com.pyp.fram.entity.MessageBoardEntity;
import com.pyp.fram.entity.ResponseEntity;

public class MessageBoardDao {

	private DBMan manager;
	public static MessageBoardDao dao = null;

	public static synchronized MessageBoardDao getInstance() {

		if (dao == null) {
			try {
				dao = new MessageBoardDao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dao;
	}

	public MessageBoardDao() throws ClassNotFoundException, IOException {
		manager = DBMan.getInstance();
	}

	/**
	 * 获取所有的说说
	 * 
	 * @return
	 */
	public String getMessageInfos() {
		String sql = "SELECT m.id,m.content,m.userid,m.date,m.time,u.username,(CASE WHEN c.num IS NULL THEN 0 ELSE c.num END ) count "
				+ " FROM t_message m LEFT JOIN "
				+ "(SELECT COUNT(message_id) AS num,id,message_id FROM t_comment GROUP BY message_id) c "
				+ "ON c.message_id = m.id  LEFT JOIN t_user u ON u.id = m.userid";

		List<MessageBoardEntity> mMessageInfos = new ArrayList<MessageBoardEntity>();
		String json = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);

			System.out.println("sql = " + sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String content = rs.getString("content");
				String hourStr = rs.getString("time");
				String userId = rs.getString("userid");
				if (hourStr == null) {
					hourStr = "";
				}
				String time = rs.getDate("date").toString() + " " + hourStr;
				String commentNum = rs.getString("count");
				MessageBoardEntity entity = new MessageBoardEntity(id, userId,
						content, time, username, commentNum);
				mMessageInfos.add(entity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		json = gson.toJson(mMessageInfos);
		System.out.println("json = " + json);
		return json;
	}

	/*
	 * 根据说的MessageID获取所有的评论
	 */
	public String getCommentById(String messageId) {
		String sql = "SELECT * FROM t_comment WHERE message_id = '" + messageId
				+ "'";

		List<CommentEntity> comments = new ArrayList<CommentEntity>();
		String json = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);

			System.out.println("sql = " + sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String t_messageId = rs.getString("message_id");
				String userId = rs.getString("comment_user_id");
				String content = rs.getString("comment_detail");
				String time = rs.getString("comment_time");
				CommentEntity entity = new CommentEntity(id, t_messageId,
						userId, content, time);
				comments.add(entity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		json = gson.toJson(comments);
		System.out.println("json = " + json);
		return json;
	}

	/**
	 * 添加评论
	 * 
	 * @param messageId
	 * @param commentContent
	 * @return
	 */
	public ResponseEntity addComment(CommentEntity entity) {
		String sql = "INSERT INTO  t_comment(message_id,comment_user_id,comment_detail,comment_time) VALUES("
				+ "'"+ entity.getMessageId() + "', '"  + entity.getUserId() + "','" + entity.getContent() + "','" + entity.getTime() + "')";
		System.out.println("addComment sql = " + sql);
		int flag = 0;
		try {
			manager.connDB();
			flag = manager.executeUpdate(sql);
			System.out.println("sql = " + sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResponseEntity response = new ResponseEntity();
		response.setCode(flag);
		return response;
	}

	/**
	 * 获取该用户已经发表的说说
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public String getMessagesByUserId(String userId) {
		String sql = "SELECT m.id,m.content,m.date,m.time,u.username,(CASE WHEN c.num IS NULL THEN 0 ELSE c.num END ) count "
				+ " FROM t_message m LEFT JOIN "
				+ "(SELECT COUNT(message_id) AS num,id,message_id FROM t_comment GROUP BY message_id) c "
				+ "ON c.message_id = m.id  LEFT JOIN t_user u ON u.id = m.userid WHERE m.userid = '"
				+ userId + "'";

		List<MessageBoardEntity> mMessageInfos = new ArrayList<MessageBoardEntity>();
		String json = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);

			System.out.println("sql = " + sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String content = rs.getString("content");
				String hourStr = rs.getString("time");
				if (hourStr == null) {
					hourStr = "";
				}
				String time = rs.getDate("date").toString() + " " + hourStr;
				String commentNum = rs.getString("count");
				MessageBoardEntity entity = new MessageBoardEntity(id, userId,
						content, time, username, commentNum);
				mMessageInfos.add(entity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		json = gson.toJson(mMessageInfos);
		System.out.println("json = " + json);
		return json;
	}

}
