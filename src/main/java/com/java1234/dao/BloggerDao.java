package com.java1234.dao;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.Blogger;

/**
 * 博主Dao层
 * @author Administrator
 *
 */
public interface BloggerDao {
	
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
	public int updateUser(@Param("blogger")Blogger blogger);

}
