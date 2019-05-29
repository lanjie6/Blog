package com.java1234.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java1234.dao.LinkDao;
import com.java1234.entity.Link;
import com.java1234.service.LinkService;

/**
 * 友情链接Service层
 * @author Gucaini
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{
	
	@Autowired
	private LinkDao linkDao;

	public List<Link> getLink() {
		
		return linkDao.getLink();
	}

	public List<Link> getLinkList(Map<String, Object> map) {
		
		return linkDao.getLinkList(map);
	}

	public int getLinkListCount() {
		
		return linkDao.getLinkListCount();
	}

	public boolean addLink(Link link) {
		
		return linkDao.addLink(link)>0?true:false;
	}

	public boolean updateLink(Link link) {
		
		return linkDao.updateLink(link)>0?true:false;
	}

	public boolean deleteLink(List<Integer> ids) {
		
		return linkDao.deleteLink(ids)==ids.size()?true:false;
	}
	
	

}
