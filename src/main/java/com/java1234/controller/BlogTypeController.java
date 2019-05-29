package com.java1234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java1234.entity.BlogType;
import com.java1234.entity.PageBean;
import com.java1234.service.BlogTypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class BlogTypeController {
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	/**
	 * 分页查询博客类别信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="typeList",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getBlogType(HttpServletRequest request){
		
		String page = request.getParameter("page");
		
		String rows = request.getParameter("rows");
		
		PageBean pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeCount(pageBean.getStart(),pageBean.getPageSize());
	
		JSONArray array = JSONArray.fromObject(blogTypeList);
		
		JSONObject result = new JSONObject();
		
		result.put("total", blogTypeService.findBlogTypeCount());
		
		result.put("rows", array);
		
		return result.toString();
	}
	
	/**
	 * 保存博客类别
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveBlogType",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveBlogType(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String id = request.getParameter("id");
		
		String typeName = request.getParameter("typeName");
		
		String orderNo = request.getParameter("orderNo");
		
		try {
			
			boolean n = false;
			
			if(StringUtils.isNotBlank(id)){
				
				BlogType blogType = new BlogType(Integer.parseInt(id), typeName, Integer.parseInt(orderNo));
				
				n =  blogTypeService.updateBlogType(blogType);
				
			}else{
				
				BlogType blogType = new BlogType(typeName, Integer.parseInt(orderNo));
				
				n = blogTypeService.addBlogType(blogType);
				
			}
			
			if(n){
				
				result.put("resultCode", "001");
				
				result.put("resultContent", "保存博客类别成功！");
				
			}else{
				
				result.put("resultCode", "002");
				
				result.put("resultContent", "保存博客类别失败！请重试。");
				
			}
			
		} catch (Exception e) {
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "系统异常！请重试。");
			
			e.printStackTrace();
			
		}
		
		return result.toString();
	}
	
	/**
	 * 删除博客类别
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteBlogType",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteBlogType(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String[] ids = request.getParameterValues("ids[]");
	
		List<Integer> idList = new ArrayList<Integer>();
		
		for(String id : ids){
			
			idList.add(Integer.parseInt(id));
			
		}
		
		boolean n = blogTypeService.deleteBlogType(idList);
		
		if(n){
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "删除成功！共计删除了"+idList.size()+"条记录");
			
		}else{
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "删除失败！请重试。");
			
		}
		
		
		return  result.toString();
	}

}
