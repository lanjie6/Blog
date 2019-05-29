package com.java1234.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Blog;
import com.java1234.entity.PageBean;
import com.java1234.service.BlogService;
import com.java1234.util.JsoupUtil;
import com.java1234.util.PagingUtil;
import com.java1234.util.PropertiesUtil;
import com.java1234.util.StringUtil;

/**
 * 首页Controller
 * @author gucaini
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		
		String page = request.getParameter("page");
		
		String pageSize = request.getParameter("pageSize");
		
		String typeId = request.getParameter("typeId");
		
		String releaseTimeStr = request.getParameter("releaseTimeStr");
		
		//设置page默认值
		page = StringUtil.isEmpty(page)?"1":page;
		
		//设置pageSize默认值
		pageSize = StringUtil.isEmpty(pageSize)?PropertiesUtil.getValue("pageSize"):pageSize;
		
		//封装分页实体
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(pageSize));
	
		//封装查询条件
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("typeId", typeId);

		map.put("releaseTimeStr", releaseTimeStr);
		
		map.put("start", pageBean.getStart());
		
		map.put("size", pageBean.getPageSize());
		
		//查询博客信息
		List<Blog> blogs = blogService.getBlog(map);
		
		//处理查询的博客信息中的压缩图片内容
		for(Blog b : blogs){
			
			//使用Jsoup工具对文本内容的html图片标签提取
			List<String> imageNames = JsoupUtil.getImageName(b.getContent(), new ArrayList<String>());
			
			b.setImageName(imageNames);
			
			String summary = b.getSummary();
			
			//如果摘要的长度超过155，则截取显示
			if(summary.length()>=155){
				
				b.setSummary(summary+"...");
				
			}
		}
		
		//封装分页的查询条件
		StringBuffer sb = new StringBuffer();
		
		if(StringUtil.isNotEmpty(typeId)){
			
			sb.append("&typeId="+typeId);
			
		}
		
		if(StringUtil.isNotEmpty(releaseTimeStr)){
			
			sb.append("&releaseTimeStr="+releaseTimeStr);
			
		}
		
		//获取分页
		String pageCode = PagingUtil.pagination(blogService.getBlogCount(map), Integer.parseInt(pageSize),
				Integer.parseInt(page), "index.html",sb.toString());
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("blogs",blogs);
		
		mav.addObject("mainPage","reception/blog/list.jsp");
		
		mav.addObject("titleInfo","首页-兰杰的博客");
		
		mav.addObject("pageCode",pageCode);
		
		mav.setViewName("WEB-INF/index");
		
		return mav;
	}

}
