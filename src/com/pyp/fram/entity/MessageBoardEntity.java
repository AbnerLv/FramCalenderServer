package com.pyp.fram.entity;

public class MessageBoardEntity {
	
	private String id;
	private String userId;
	private String content;
	private String time;
	private String username;
	private String commentNum;
	
	
	public MessageBoardEntity() {
	
	}
	public MessageBoardEntity(String id, String userId, String content,
			String time, String username, String commentNum) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.time = time;
		this.username = username;
		this.commentNum = commentNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	


}
