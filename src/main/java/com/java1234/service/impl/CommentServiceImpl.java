package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java1234.dao.CommentDao;
import com.java1234.entity.Comment;
import com.java1234.service.CommentService;

/**
 * 博客评论Service
 * @author gucaini
 *
 */
@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;

	public List<Comment> getCommentByBlogId(Integer blogId) {
		
		return commentDao.getCommentByBlogId(blogId);
	}

	public boolean addComment(Comment comment) {
		
		return commentDao.addComment(comment)>0?true:false;
	}

	public List<Comment> getReplyComment(Integer blogId) {
		
		return commentDao.getReplyComment(blogId);
	}

	public List<Comment> getComment(Map<String, Object> map) {
		
		return commentDao.getComment(map);
	}

	public int getCommentCount() {
		
		return commentDao.getCommentCount();
	}

	public boolean updateComment(List<Integer> ids) {
		
		return commentDao.updateComment(ids)==ids.size()?true:false;
	}

}
