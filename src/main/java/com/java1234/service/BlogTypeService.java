package com.java1234.service;

import java.util.List;

import com.java1234.entity.BlogType;

public interface BlogTypeService {
	
	/**
	 * 查询博客类别数量
	 * @return
	 */
	public int findBlogTypeCount();
	
	/**
	 * 查询各博客类别文章信息
	 * @return
	 */
	public List<BlogType> getBlogTypeCount(Integer startPage,Integer pageSize);
	
	/**
	 * 新增博客类别
	 * @param blogType
	 * @return
	 */
	public boolean addBlogType(BlogType blogType);
	
	/**
	 * 修改博客类别
	 * @param blogType
	 * @return
	 */
	public boolean updateBlogType(BlogType blogType);
	
	/**
	 * 批量删除博客类别
	 * @param ids
	 * @return
	 */
	public boolean deleteBlogType(List<Integer> ids);

}
