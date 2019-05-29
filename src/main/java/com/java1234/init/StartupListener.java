package com.java1234.init;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.java1234.entity.Blog;
import com.java1234.entity.BlogType;
import com.java1234.entity.Blogger;
import com.java1234.entity.Link;
import com.java1234.service.BlogService;
import com.java1234.service.BlogTypeService;
import com.java1234.service.BloggerService;
import com.java1234.service.LinkService;

/**
 * 系统启动初始化
 * @author gucaini
 *
 */
public class StartupListener implements ServletContextListener{

	//系统启动加载方法
	public void contextInitialized(ServletContextEvent sce) {
		
		//加载博主信息
		ServletContext application = sce.getServletContext();
		
		BloggerService bloggerService = (BloggerService)SpringContextHolder.getBean("bloggerService");
		
		Blogger blogger = bloggerService.getUser();
		
		//为了安全起见，将密码置空
		blogger.setPassword(null);
		
		application.setAttribute("blogger", blogger);
		
		//加载友情链接
		LinkService linkService = (LinkService)SpringContextHolder.getBean("linkService");
		
		List<Link> linkList = linkService.getLink();
		
		application.setAttribute("linkList", linkList);
		
		//加载按文章类别分类
		BlogTypeService blogTypeService = (BlogTypeService)SpringContextHolder.getBean("blogTypeService");
		
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeCount(null,null);
		
		application.setAttribute("blogTypeList", blogTypeList);
		
		//加载按日期文章分类
		BlogService blogService = (BlogService)SpringContextHolder.getBean("blogService");
		
		List<Blog> blogList = blogService.getBlogCountByReleaseTime();
		
		application.setAttribute("blogList", blogList);

	}
	
	//系统销毁加载方法
	public void contextDestroyed(ServletContextEvent sce) {
		
	}


}
