package com.java1234.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.Comment;

/**
 * 博客评论Dao
 * @author Administrator
 *
 */
public interface CommentDao {
	
	/**
	 * 根据博客ID查询评论内容
	 * @param blogId 博客ID
	 * @return
	 */
	public List<Comment> getCommentByBlogId(Integer blogId);
	
	/**
	 * 新增评论
	 * @param comment
	 * @return
	 */
	public int addComment(@Param("comment")Comment comment);
	
	/**
	 * 查询评论回复
	 * @param blogId
	 * @return
	 */
	public List<Comment> getReplyComment(Integer blogId);
	
	/**
	 * 分页查询评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> getComment(Map<String,Object> map);
	
	/**
	 * 查询有效评论数量
	 * @return
	 */
	public int getCommentCount();
	
	/**
	 * 修改评论
	 * @return
	 */
	public int updateComment(@Param("ids")List<Integer> ids);

}
