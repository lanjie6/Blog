package com.java1234.entity;

import java.io.Serializable;
import java.util.List;

/**
 * lucene搜索结果实体
 * @author gucaini
 *
 */
public class SearchResult implements Serializable{

	private static final long serialVersionUID = -6856617923683776501L;
	
	private List<Blog> blogs;//用于封装搜索出来的博客信息集合
	
	private Integer resultCount;//总共搜索出来的数量

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
}
