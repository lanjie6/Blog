package com.java1234.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java1234.dao.BlogTypeDao;
import com.java1234.entity.BlogType;
import com.java1234.service.BlogTypeService;

/**
 * 博客类别Service
 * @author gucaini
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{
	
	@Autowired	
	private BlogTypeDao blogTypeDao;

	public List<BlogType> getBlogTypeCount(Integer startPage,Integer pageSize) {
		
		return blogTypeDao.getBlogTypeCount(startPage,pageSize);
	}

	public int findBlogTypeCount() {
		
		return blogTypeDao.findBlogTypeCount();
	}

	public boolean addBlogType(BlogType blogType) {
		
		return blogTypeDao.addBlogType(blogType)>0?true:false;
	}

	public boolean updateBlogType(BlogType blogType) {
		
		return blogTypeDao.updateBlogType(blogType)>0?true:false;
	}

	public boolean deleteBlogType(List<Integer> ids) {
		
		return blogTypeDao.deleteBlogType(ids)==ids.size()?true:false;
	}

}
