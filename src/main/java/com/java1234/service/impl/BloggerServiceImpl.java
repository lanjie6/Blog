package com.java1234.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java1234.dao.BloggerDao;
import com.java1234.entity.Blogger;
import com.java1234.service.BloggerService;

/**
 * 博主Service层
 * @author gucaini
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{
	
	@Autowired
	private BloggerDao bloggerDao;

	public Blogger getUserByUserName(String userName) {
		
		return bloggerDao.getUserByUserName(userName);
	}

	public Blogger getUser() {
		
		return bloggerDao.getUser();
	}

	public boolean updateUser(Blogger blogger) {
		
		return bloggerDao.updateUser(blogger)>0?true:false;
	}

}
