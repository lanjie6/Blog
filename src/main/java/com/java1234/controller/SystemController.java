package com.java1234.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.java1234.entity.Blog;
import com.java1234.entity.BlogType;
import com.java1234.entity.Blogger;
import com.java1234.entity.Link;
import com.java1234.service.BlogService;
import com.java1234.service.BlogTypeService;
import com.java1234.service.BloggerService;
import com.java1234.service.LinkService;

import net.sf.json.JSONObject;

/**
 * 系统缓存Controller
 * @author gucaini
 *
 */
@Controller
public class SystemController {
	
	@Autowired
	BloggerService bloggerService;
	
	@Autowired
	LinkService linkService;
	
	@Autowired
	BlogTypeService blogTypeService;
	
	@Autowired
	BlogService blogService;
	
	/**
	 * 刷新系统缓存
	 * @return
	 */
	@RequestMapping(value="/admin/refresh",produces="application/json;charset=utf-8")
	@ResponseBody
	public String refresh(){
		
		JSONObject result = new JSONObject();
		
		try {
			
			//Spring提供的获取Context的方法
			ServletContext application = ContextLoader.getCurrentWebApplicationContext().getServletContext();
			
			//刷新博主信息	
			Blogger blogger = bloggerService.getUser();
			
			//为了安全起见，将密码置空
			blogger.setPassword(null);
			
			application.setAttribute("blogger", blogger);
			
			//刷新友情链接
			List<Link> linkList = linkService.getLink();
			
			application.setAttribute("linkList", linkList);
			
			//刷新按文章类别分类
			List<BlogType> blogTypeList = blogTypeService.getBlogTypeCount(null,null);
			
			application.setAttribute("blogTypeList", blogTypeList);
			
			//刷新按日期文章分类
			List<Blog> blogList = blogService.getBlogCountByReleaseTime();
			
			application.setAttribute("blogList", blogList);
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "刷新缓存成功！");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "刷新缓存失败！");
		}
		
		return result.toString();
		
	}
}
