package com.java1234.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.Blog;

/**
 * 博客Dao
 * @author gucaini
 *
 */
public interface BlogDao {
	
	/**
	 * 按日期查询博客数量
	 * @return
	 */
	public List<Blog> getBlogCountByReleaseTime();
	
	/**
	 * 查询博客数量
	 * @return
	 */
	public int getBlogCount(Map<String,Object> map);
	
	/**
	 * 分页查询最新博客
	 * 
	 */
	public List<Blog> getBlog(Map<String,Object> map);
	
	/**
	 * 根据博客ID查询博客信息
	 * @param id
	 * @return
	 */
	public Blog getBlogById(Integer id);
	
	/**
	 * 修改博客
	 * @param blog
	 * @return
	 */
	public int updateBlog(@Param("blog")Blog blog);
	
	/**
	 * 查询上一篇博客
	 * @param id
	 * @return
	 */
	public Blog getLastBlog(Integer id);
	
	/**
	 * 查询下一篇博客
	 * @param id
	 * @return
	 */
	public Blog getNextBlog(Integer id);
	
	/**
	 * 新增博客
	 * @param blog
	 * @return
	 */
	public int addBlog(Blog blog);
	
	/**
	 * 批量删除博客
	 * @param ids
	 * @return
	 */
	public int deleteBlogs(@Param("ids")List<Integer> ids);

}
