package com.java1234.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Blog;
import com.java1234.entity.Comment;
import com.java1234.entity.PageBean;
import com.java1234.entity.SearchResult;
import com.java1234.service.BlogService;
import com.java1234.service.CommentService;
import com.java1234.util.DateUtil;
import com.java1234.util.LuceneUtil;
import com.java1234.util.PagingUtil;
import com.java1234.util.PropertiesUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 博客信息Controller层
 * @author gucaini
 *
 */
@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 查询博客信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBlog/{id}") //这里让请求动态化，形成伪静态页面{id}代表请求后面的URL动态参数，PathVariable则可以讲这个参数直接获取
	public ModelAndView findBlogById(@PathVariable("id") Integer id){
		
		Blog blog = blogService.getBlogById(id);
		
		blog.setHitsCount(blog.getHitsCount()+1);
		
		blogService.updateBlog(blog);
		
		ModelAndView mav = new ModelAndView();
	
		//处理keyWord关键字信息，按空格分割，并去掉多余的空格，返回一个集合
		List<String> keyWordList = StringUtil.isNotEmpty(blog.getKeyWord())?
				StringUtil.filterWhite(Arrays.asList(blog.getKeyWord().split(" "))):null;
				
		//查询上一篇博客和下一篇博客
		Blog lastBlog = blogService.getLastBlog(id);
		
		Blog nextBlog = blogService.getNextBlog(id);
		
		//查询博客评论内容
		List<Comment> commentList = commentService.getCommentByBlogId(id);
		
		//查询博客评论回复内容
		List<Comment> replyList = commentService.getReplyComment(id);
		
		mav.addObject("blog",blog);
		
		mav.addObject("keyWordList",keyWordList);
		
		mav.addObject("commentList",commentList);
		
		mav.addObject("replyList",replyList);
		
		mav.addObject("lastBlog",lastBlog);
		
		mav.addObject("nextBlog",nextBlog);
		
		mav.addObject("mainPage","reception/blog/content.jsp");
		
		mav.addObject("titleInfo",blog.getTitle()+"-兰杰的博客");
		
		mav.setViewName("WEB-INF/index");
		
		return mav;
	}
	
	/**
	 * 添加或修改博客
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/saveBlog",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String addBlog(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String title = request.getParameter("title");
		
		String content = request.getParameter("content");
		
		String contentNoTag = request.getParameter("contentNoTag");
		
		String summary = request.getParameter("summary");
		
		String keyWord = request.getParameter("keyWord");
		
		String typeId  = request.getParameter("typeId");
		
		String id = request.getParameter("id");
		
		try {
			
			if(StringUtil.isEmpty(id)){
				
				Blog blog = new Blog(title, summary, content, Integer.parseInt(typeId), keyWord,contentNoTag);
				
				boolean b = blogService.addBlog(blog);
				
				if(b){
					//博客新增成功后，创建lucene索引
					LuceneUtil.blogIndex(blog);
					
					result.put("resultCode", "001");
					
					result.put("resultContent", "发布博客成功！");
					
				}else{
					
					result.put("resultCode", "002");
					
					result.put("resultContent", "发布博客失败！");
					
				}
				
			}else{
				
				Blog blog = new Blog(Integer.parseInt(id), title, summary, content, Integer.parseInt(typeId), keyWord,
						contentNoTag);
				
				boolean b = blogService.updateBlog(blog);
				
				if(b){
					//博客修改成功后，修改lucene索引
					LuceneUtil.updateBlogIndex(blog);
					
					result.put("resultCode", "001");
					
					result.put("resultContent", "修改博客成功！");
					
				}else{
					
					result.put("resultCode", "002");
					
					result.put("resultContent", "修改博客失败！");
					
				}
				
			}
			
			return result.toString();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "发布博客失败！");
			
			return result.toString();
			
		}
		
	}
	
	/**
	 * 搜索博客
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView searchBlog(HttpServletRequest request){
		
		String q = request.getParameter("q");
		
		String page = request.getParameter("page");
		
		String pageSize = request.getParameter("pageSize");
		
		//设置page默认值
		page = StringUtil.isNotEmpty(page)?page:"1";
		
		//设置pageSize默认值
		pageSize = StringUtil.isNotEmpty(pageSize)?pageSize:PropertiesUtil.getValue("pageSize");
		
		ModelAndView mav = new ModelAndView();
		
		try {
			
			SearchResult result = LuceneUtil.searchBlog(q,Integer.parseInt(page),Integer.parseInt(pageSize));
			
			mav.addObject("mainPage","reception/blog/search.jsp");
			
			mav.addObject("titleInfo","搜索'"+q+"'的结果页面-兰杰的博客");
			
			mav.addObject("result",result);
			
			mav.addObject("searchPageCode", PagingUtil.paginationSearch(result.getResultCount(),
					Integer.parseInt(pageSize), Integer.parseInt(page), "search.html", q));
			
			mav.addObject("q",q);
			
			mav.setViewName("WEB-INF/index");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return mav;
	}
	
	/**
	 * 分页查询博客信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/list",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getBlogList(HttpServletRequest request){

		JSONObject result = new JSONObject();
		
		JSONArray array = new JSONArray();
		
		String page = request.getParameter("page");
		
		String rows = request.getParameter("rows");
		
		String title = request.getParameter("title");
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("title", StringUtil.formatLike(title));
		
		map.put("start", pageBean.getStart());
		
		map.put("size", pageBean.getPageSize());
		
		try {
			
			List<Blog> blogs = blogService.getBlog(map);
			
			for(Blog blog : blogs){
				
				JSONObject obj = new JSONObject();
				
				obj.put("id", blog.getId());
				
				obj.put("title", blog.getTitle());
				
				obj.put("releaseTime", DateUtil.StrToStr(blog.getReleaseTime(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
				
				obj.put("typeName", blog.getBlogType().getTypeName());
				
				array.add(obj);
				
			}
			
			result.put("total", blogService.getBlogCount(map));
			
			result.put("rows", array);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result.toString();
	}
	
	/**
	 * 删除博客
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/deleteBlog",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteBlog(HttpServletRequest request){
		
		JSONObject result = new JSONObject();
		
		String[] ids = request.getParameterValues("ids[]");
		
		List<Integer> idList = new ArrayList<Integer>();
		
		for(String str : ids){
			
			idList.add(Integer.parseInt(str));
			
		}
		
		try {
			
			boolean b = blogService.deleteBlogs(idList);
			
			if(b){
				
				LuceneUtil.deleteIndex(ids);
				
				result.put("resultCode", "001");
				
				result.put("resultContent", "成功删除了"+ids.length+"条博客信息！");
				
			}else{
				
				result.put("resultCode", "002");
				
				result.put("resultContent", "删除博客信息失败！请重试。");
				
			}
			
			return result.toString();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "系统异常！请稍后重试。");
			
			return result.toString();
			
		}
		
	}
	
	/**
	 * 后台根据博客ID查询博客信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/findBlog",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getBlogById(HttpServletRequest request){
		
		String id = request.getParameter("id");
		
		Blog blog = blogService.getBlogById(Integer.parseInt(id));
		
		JSONObject result = JSONObject.fromObject(blog);
		
		return result.toString();
	}
	

}
