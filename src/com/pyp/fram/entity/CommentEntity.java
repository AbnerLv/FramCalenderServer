package com.pyp.fram.entity;

/**
 * Created by Administrator on 2016/5/31.
 */
public class CommentEntity {

    private String id;
    private String messageId;
    private String userId;
    private String content;
    private String time;

    
    public CommentEntity() {
	
	}

	public CommentEntity(String id, String messageId, String userId,
			String content, String time) {
		this.id = id;
		this.messageId = messageId;
		this.userId = userId;
		this.content = content;
		this.time = time;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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
   
}
