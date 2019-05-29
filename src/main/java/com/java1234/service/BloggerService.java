package com.java1234.service;

import com.java1234.entity.Blogger;

public interface BloggerService {
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName 用户名
	 * @return
	 */
	public Blogger getUserByUserName(String userName);
	
	/**
	 * 查询博主信息
	 * @return
	 */
	public Blogger getUser();
	
	/**
	 * 修改博主信息
	 * @param blogger
	 * @return
	 */
	public boolean updateUser(Blogger blogger);


}
