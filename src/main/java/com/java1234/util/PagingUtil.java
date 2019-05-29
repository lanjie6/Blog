package com.java1234.util;

/**
 * 分页工具类
 * 该类用于处理bootStrap分页工具
 * @author gucaini
 *
 */
public class PagingUtil {
	
	/**
	 * 搜索结果分页
	 * @param totalNum 总记录数
	 * @param pageSize 每页显示条数
	 * @param page 当前页数
	 * @param targetUrl 请求地址
	 * @param q 查询关键词
	 * @return
	 */
	public static String paginationSearch(int totalNum,int pageSize,int page,String targetUrl,String q){
		
		StringBuffer sb = new StringBuffer();
		
		//计算总共有多少页,用总记录数对每页显示的条数进行取余,如果余数为0,则总页数就是他们的商,否则是商+1
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		
		//如果结果为0，那么则不渲染分页组件
		if(totalPage==0){
		
			return "";
			
		}
		
		//当前页大于第1页的时候，显示上一页标签
		if(page>1){
			sb.append("<li><a href='"+targetUrl+"?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
		}
		
		//当前页小于最后一页的时候，显示下一页标签
		if(page<totalPage){
			sb.append("<li><a href='"+targetUrl+"?page="+(page+1)+"&q="+q+"'>下一页</a></li>");
		}
		
		return sb.toString();
		
	}
	
	/**
	 * 博客列表分页
	 * @param totalNum 总记录数
	 * @param pageSize 每页显示条数
	 * @param page 当前页
	 * @param targetUrl 目标地址
	 * @param param 请求需要带的参数
	 * @return
	 */
	public static String pagination(int totalNum,int pageSize,int page,String targetUrl,String param){
		
		StringBuffer sb = new StringBuffer();
		
		//计算总共有多少页,用总记录数对每页显示的条数进行取余,如果余数为0,则总页数就是他们的商,否则是商+1
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		
		//拼接首页
		sb.append("<li><a href='"+targetUrl+"?page=1"+param+"' aria-label='Previous'><span aria-hidden='true'>首页</span></a></li>");
		
		//拼接上一页
		if(page>1){
			sb.append("<li><a href='"+targetUrl+"?page="+(page-1)+param+"'>上一页</a></li>");
		}else{
			sb.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}
		
		//拼接页码，显示当前页前2页和后2页以及当前页码数
		for(int i=page-2;i<=page+2;i++){
			
			if(i<1 || i>totalPage){//如果i小于1或者i大于totalPage,就不执行这次循环，因为起始页必须是第1页，最后一页也必须是totalPage
				
				continue;
				
			}else if(i==page){//如果当前是选中的这一页，添加选中样式
				
				sb.append("<li class='active'><a href='"+targetUrl+"?page="+i+param+"'>"+i+"</a></li>");
				
			}else{
				
				sb.append("<li><a href='"+targetUrl+"?page="+i+param+"'>"+i+"</a></li>");
				
			}
		}
		
		//拼接下一页
		if(page<totalPage){
			sb.append("<li><a href='"+targetUrl+"?page="+(page+1)+param+"'>下一页</a></li>");
		}else{
			sb.append("<li class='disabled'><a href='#'>下一页</a></li>");
		}
		
		//拼接尾页
		sb.append("<li><a href='"+targetUrl+"?page="+totalPage+param+"' aria-label='Next'><span aria-hidden='true'>尾页</span></a></li>");
		return sb.toString();
	}

}
