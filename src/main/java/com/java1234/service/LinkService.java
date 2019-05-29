package com.java1234.service;

import java.util.List;
import java.util.Map;

import com.java1234.entity.Link;

public interface LinkService {
	
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
	public boolean addLink(Link link);
	
	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	public boolean updateLink(Link link);
	
	/**
	 * 批量删除友情链接
	 * @param ids
	 * @return
	 */
	public boolean deleteLink(List<Integer> ids);

}
