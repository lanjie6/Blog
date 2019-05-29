package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java1234.dao.BlogDao;
import com.java1234.entity.Blog;
import com.java1234.service.BlogService;

/**
 * 博客Service层
 * @author gucaini
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogDao blogDao;

	public List<Blog> getBlogCountByReleaseTime() {
		
		return blogDao.getBlogCountByReleaseTime();
	}

	public int getBlogCount(Map<String,Object> map) {
		
		return blogDao.getBlogCount(map);
	}

	public List<Blog> getBlog(Map<String,Object> map) {
		
		return blogDao.getBlog(map);
	}

	public Blog getBlogById(Integer id) {
		
		return blogDao.getBlogById(id);
	}

	public boolean updateBlog(Blog blog) {
		
		return blogDao.updateBlog(blog)>0?true:false;
	}

	public Blog getLastBlog(Integer id) {
		
		return blogDao.getLastBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		
		return blogDao.getNextBlog(id);
	}

	public boolean addBlog(Blog blog) {
		
		return blogDao.addBlog(blog)>0?true:false;
	}

	public boolean deleteBlogs(List<Integer> ids) {
		
		return blogDao.deleteBlogs(ids)==ids.size()?true:false;
	}

}
