package com.niit.collaboration.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "C_FORUM_COMMENT")
@Component
public class ChatForumComment extends BaseDomain {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "forum_id")
	private String forumID;

	@Column(name = "user_id")
	private String userID;

	private String message;

	@Column(name = "commented_date")
	private Date commentedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForumID() {
		return forumID;
	}

	public void setForumID(String forumID) {
		this.forumID = forumID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCommentedDate() {
		return commentedDate;
	}

	public void setCommentedDate(Date commentedDate) {
		this.commentedDate = commentedDate;
	}

}
