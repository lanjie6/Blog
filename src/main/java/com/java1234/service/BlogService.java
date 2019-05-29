package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Blog;

public interface BlogService {
	
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
	 * @return
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
	public boolean updateBlog(Blog blog);
	
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
	public boolean addBlog(Blog blog);
	
	/**
	 * 博客数组
	 * @param ids
	 * @return
	 */
	public boolean deleteBlogs(List<Integer> ids);
	
	
}
