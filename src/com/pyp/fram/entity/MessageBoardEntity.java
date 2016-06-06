package com.pyp.fram.entity;

public class MessageBoardEntity {
	
	private String id;
	private String userid;
	private String content;
	private String time;
	private String username;
	private String commentNum;
	
	
	public MessageBoardEntity() {
	
	}
	public MessageBoardEntity(String id, String userid, String content,
			String time, String username, String commentNum) {
		this.id = id;
		this.userid = userid;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
