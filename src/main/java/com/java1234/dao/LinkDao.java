package com.java1234.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.Link;

/**
 * 友情链接Dao
 * @author Gucaini
 *
 */
public interface LinkDao {
	
	/**
	 * 查询友情链接
	 * @return
	 */
	public List<Link> getLink();
	
	/**
	 * 分页查询友情链接
	 * @param map
	 * @return
	 */
	public List<Link> getLinkList(Map<String,Object> map);
	
	/**
	 * 查询友情链接数量
	 * @return
	 */
	public int getLinkListCount();
	
	/**
	 * 新增友情链接
	 * @param link
	 * @return
	 */
	public int addLink(@Param("link")Link link);
	
	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	public int updateLink(@Param("link")Link link);
	
	/**
	 * 批量删除友情链接
	 * @param ids
	 * @return
	 */
	public int deleteLink(@Param("ids")List<Integer> ids);

}
