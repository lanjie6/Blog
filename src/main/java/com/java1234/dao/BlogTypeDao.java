package com.java1234.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.BlogType;

/**
 * 博客类别Dao
 * @author gucani
 *
 */
public interface BlogTypeDao {
	
	/**
	 * 查询博客类别数量
	 * @return
	 */
	public int findBlogTypeCount();
	
	/**
	 * 查询各博客类别文章信息
	 * @return
	 */
	public List<BlogType> getBlogTypeCount(@Param("startPage")Integer startPage,@Param("pageSize")Integer pageSize);
	
	/**
	 * 根据类别ID查询博客类别信息
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);
	
	/**
	 * 新增博客类别
	 * @param blogType
	 * @return
	 */
	public int addBlogType(@Param("blogType")BlogType blogType);
	
	/**
	 * 修改博客类别
	 * @param blogType
	 * @return
	 */
	public int updateBlogType(@Param("blogType")BlogType blogType);
	
	/**
	 * 批量删除博客类别
	 * @param ids
	 * @return
	 */
	public int deleteBlogType(@Param("ids")List<Integer> ids);

}
