package com.java1234.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java1234.entity.Link;
import com.java1234.entity.PageBean;
import com.java1234.service.LinkService;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 友情链接Controller
 * @author 古采尼
 *
 */
@Controller
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	/**
	 * 分页查询友情链接
	 * @return
	 */
	@RequestMapping(value="/admin/linkList",produces="application/json;charset=utf-8")
	@ResponseBody
	public String linkList(HttpServletRequest request){
		
		String rows = request.getParameter("rows");
		
		String page = request.getParameter("page");
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("pageStart", pageBean.getStart());
		
		map.put("pageSize", pageBean.getPageSize());
		
		List<Link> linkList = linkService.getLinkList(map);
		
		JSONArray array = JSONArray.fromObject(linkList);
		
		int linkCount = linkService.getLinkListCount();
		
		JSONObject result = new JSONObject();
		
		result.put("total", linkCount);
		
		result.put("rows", array);
		
		return result.toString();
		
	}
	
	/**
	 * 保存友情链接
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/saveLink",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveLink(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String id = request.getParameter("id");
		
		String linkName = request.getParameter("linkName");
		
		String linkUrl = request.getParameter("linkUrl");
		
		String orderNo = request.getParameter("orderNo");
		
		boolean n = false;
		
		//如果ID为空说明是新增操作，反之是修改操作
		if(StringUtil.isEmpty(id)){
			
			Link link = new Link(linkName, linkUrl, Integer.parseInt(orderNo));
			
			n = linkService.addLink(link);
			
			
		}else{
			
			Link link = new Link(Integer.parseInt(id), linkName, linkUrl,Integer.parseInt(orderNo));
			
			n = linkService.updateLink(link);
			
		}
		
		if(n){
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "保存友情链接成功！");
			
		}else{
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "保存友情链接失败！");
			
		}
		
		return result.toString();
	}
	
	/**
	 * 删除友情链接
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/delete",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteLink(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String[] ids = request.getParameterValues("ids[]");
		
		List<Integer> linkIds = new ArrayList<Integer>();
		
		for(String id : ids){
			
			linkIds.add(Integer.parseInt(id));
			
		}
		
		boolean n = linkService.deleteLink(linkIds);
		
		if(n){
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "删除友情链接成功！共计删除"+ids.length+"条数据。");
			
		}else{
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "删除友情链接失败！");
			
		}
		
		return result.toString();
		
	}

}
