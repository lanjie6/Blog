package com.java1234.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java1234.entity.Blog;
import com.java1234.entity.Comment;
import com.java1234.entity.PageBean;
import com.java1234.service.BlogService;
import com.java1234.service.CommentService;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 博客评论Controller
 * @author gucaini
 *
 */
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * 发表评论
	 * @param request
	 * @return
	 */
	//添加produces属性，可以解决@ResponseBody中JSON字符串的乱码问题
	@RequestMapping(value="/saveComment" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveComment(HttpServletRequest request){
		
		JSONObject result=new JSONObject();
		
		String id = request.getParameter("id");
		
		String publishComment = request.getParameter("publishComment");
		
		String parentCommentId = request.getParameter("parentCommentId");
		
		try {
			
			Comment comment = new Comment(request.getRemoteAddr(), publishComment,
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), Integer.parseInt(id),Integer.parseInt(parentCommentId));
			
			if(commentService.addComment(comment)){
				
				//新增成功后，回复数+1
				Blog blog = blogService.getBlogById(Integer.parseInt(id));
				
				blog.setCommentCount(blog.getCommentCount()+1);
				
				blogService.updateBlog(blog);
				
				result.put("resultCode", "001");
				
				result.put("resultContent", "发表评论成功！");
				
			}else{
				
				result.put("resultCode", "002");
				
				result.put("resultContent", "发表评论失败!请重试。");
				
			}
			
			return result.toString();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "系统忙！请稍后重试。");
			
			return result.toString();
			
		}
	}
	
	/**
	 * 分页查询评论信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/getCommentList" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getCommentList(HttpServletRequest request){
		
		String page = request.getParameter("page");
		
		String rows = request.getParameter("rows");
		
		page = StringUtil.isNotEmpty(page)?page:"1";
		
		rows = StringUtil.isNotEmpty(rows)?rows:"10";
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("pageStart", pageBean.getStart());
		
		map.put("pageSize", pageBean.getPageSize());
		
		List<Comment> commentList = commentService.getComment(map);
		
		JSONArray array = new JSONArray();
		
		for(Comment comment : commentList){
			
			JSONObject obj = new JSONObject();
			
			obj.put("id", comment.getId());
			
			obj.put("blog", comment.getBlog());
			
			obj.put("userIp", comment.getUserIp());
			
			String commentStr = comment.getComment();
			
			obj.put("comment", commentStr.length()>30?commentStr.substring(0,30)+"...":commentStr);
			
			obj.put("commentDate", DateUtil.StrToStr(comment.getCommentDate(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
			
			obj.put("parentCommentId", comment.getParentCommentId()==0?"评论":"回复");
			
			array.add(obj);
			
		}
		
		JSONObject result = new JSONObject();
		
		result.put("total", commentService.getCommentCount());
		
		result.put("rows", array);
		
		return result.toString();
	}
	
	/**
	 * 删除评论
	 * @return
	 */
	@RequestMapping(value="/admin/deleteComment" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteComment(HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids[]");
		
		List<Integer> idList = new ArrayList<Integer>();
		
		for(String id : ids){
			
			idList.add(Integer.parseInt(id));
			
		}
		
		boolean n = commentService.updateComment(idList);
		
		JSONObject result = new JSONObject();
		
		if(n){
			
			result.put("resultCode", "001");
			
			result.put("resultContent", "删除评论成功！共计删除了"+ids.length+"条评论信息。");
			
		}else{
			
			result.put("resultCode", "002");
			
			result.put("resultContent", "删除评论失败！");
			
		}
		
		return result.toString();
		
	}

}
