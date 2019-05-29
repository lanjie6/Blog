package com.java1234.entity;

import java.io.Serializable;

/**
 * 评论实体
 * @author gucaini
 *
 */
public class Comment implements Serializable{

	private static final long serialVersionUID = 53853814381963516L;
	
	private Integer id;//编号
	
	private String userIp;//用户ip地址
	
	private String comment;//评论内容
	
	private String commentDate;//评论时间
	
	private Integer blogId;//博客id
	
	private Integer parentCommentId;//回复的评论ID
	
	private Integer status;//评论状态 0正常 1被屏蔽
	
	//关联映射
	private Blog blog;//博客实体
	
	public Comment(String userIp, String comment, String commentDate, Integer blogId,Integer parentCommentId) {
		super();
		this.userIp = userIp;
		this.comment = comment;
		this.commentDate = commentDate;
		this.blogId = blogId;
		this.parentCommentId = parentCommentId;
	}
	
	public Comment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Integer getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Integer parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
