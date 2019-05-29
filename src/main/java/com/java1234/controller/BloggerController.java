package com.java1234.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Blogger;
import com.java1234.service.BloggerService;
import com.java1234.util.Md5Util;
import com.java1234.util.PropertiesUtil;

import net.sf.json.JSONObject;

/**
 * 博主Controller层
 * @author gucaini
 *
 */
@Controller
public class BloggerController {
	
	@Autowired
	private BloggerService bloggerService;

	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		
		Subject sb = SecurityUtils.getSubject();
		
		//加密的盐从配置文件中获取
		UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(), 
				Md5Util.md5(blogger.getPassword(), PropertiesUtil.getValue("md5.slat")));
		
		try {
			
			sb.login(token);
			
			sb.getSession().setAttribute("currentUser", blogger);
			
			return "redirect:admin/main.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorInfo", "用户名或密码错误");
			
			request.setAttribute("blogger", blogger);
			
			return "login";
		}
		
		
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public String logout(){
		
		SecurityUtils.getSubject().logout();
		
		return "redirect:/login.jsp";
		
	}
	
	/**
	 * 关于博主
	 * @param request
	 * @return
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("mainPage","reception/blogger/about.jsp");
		
		mav.addObject("titleInfo","关于博主-兰杰的博客");
		
		mav.setViewName("WEB-INF/index");
		
		return mav;
		
	}
	
	/**
	 * 查询当前登录用户的用户信息
	 * @return
	 */
	@RequestMapping(value="/admin/findBlogger" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findBlogger(HttpServletRequest request){
		
		Blogger blogger = (Blogger)request.getSession().getAttribute("currentUser");
		
		blogger = bloggerService.getUserByUserName(blogger.getUserName());
		
		JSONObject result = new JSONObject();
		
		result.put("blogger", blogger);
		
		return result.toString();
		
	}
	
	/**
	 * 
	 * @param imageFile 用户所传的图片
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/saveBlogger",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveBlogger(@RequestParam("imageName") MultipartFile imageFile,HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String nickName = request.getParameter("nickName");
		
		String sign = request.getParameter("sign");
		
		String profile = request.getParameter("profile");
		
		Blogger blogger = new Blogger(profile, nickName, sign);
		
		try {
			
			//如果图片不为空，说明用户上传了图片，需要处理图片相关信息
			if(!imageFile.isEmpty()){
				//获取用户上传的图片名字
				String name = imageFile.getOriginalFilename();
				//获取图片的后缀
				String suffix = name.split("\\.")[1];
				
				if(!"jpg".equals(suffix) && !"png".equals(suffix) && !"gif".equals(suffix) ){
					
					result.put("resultCode", "002");
					
					result.put("resultContent", "个人头像只能上传图片！请重试。");
					
					return result.toString();
				}
				
				//获取项目路径的根路径
				String path = request.getServletContext().getRealPath("/static/userImages");
				//创建新的图片名字：时间+后缀
				String imageName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"."+suffix;
				//存放图片
				imageFile.transferTo(new File(path,imageName));
				
				blogger.setImageName(imageName);
			}
			
			boolean n = bloggerService.updateUser(blogger);
			
			if(n){
				
				result.put("resultCode", "001");
				
				result.put("resultContent", "修改个人信息成功！");
				
			}else{
				
				result.put("resultCode", "002");
				
				result.put("resultContent", "修改个人信息失败！");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "系统异常！");
			
		}
		
		return result.toString();
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/updatePassword",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updatePassword(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String password = request.getParameter("password");
		
		Blogger blogger = new Blogger();
		
		blogger.setPassword(Md5Util.md5(password, PropertiesUtil.getValue("md5.slat")));
		
		boolean f = bloggerService.updateUser(blogger);
		
		if(f){
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "修改密码成功！");
			
		}else{
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "修改密码失败！");
			
		}
		
		return result.toString();
		
	}

}
