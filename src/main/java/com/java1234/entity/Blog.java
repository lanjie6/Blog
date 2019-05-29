package com.java1234.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 博客实体
 * @author gucaini
 *
 */
public class Blog implements Serializable{

	private static final long serialVersionUID = -5275636726402068158L;
	
	private Integer id;//编号
	
	private String title;//标题
	
	private String summary;//摘要
	
	private String content;//博客内容
	
	private String releaseTime;//发布时间
	
	private Integer hitsCount;//阅读次数
	
	private Integer commentCount;//评论次数
	
	private Integer typeId;//类别编号
	
	private String keyWord;//关键词
	
	//二级映射
	private List<String> imageName; //用于存放图片名称的集合
	
	private BlogType blogType;//级联查询的映射对象
	
	private Integer count;//数量
	
	private String releaseTimeStr;//按年月的发布时间
	
	private String contentNoTag;//用于存放无标签的博客内容，供lucene使用
	
	public Blog() {
		super();
	}

	

	public Blog(String title, String summary, String content, Integer typeId, String keyWord,
			String contentNoTag) {
		super();
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.typeId = typeId;
		this.keyWord = keyWord;
		this.contentNoTag = contentNoTag;
	}
	
	public Blog(Integer id,String title, String summary, String content, Integer typeId, String keyWord,
			String contentNoTag) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.typeId = typeId;
		this.keyWord = keyWord;
		this.contentNoTag = contentNoTag;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getHitsCount() {
		return hitsCount;
	}

	public void setHitsCount(Integer hitsCount) {
		this.hitsCount = hitsCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getReleaseTimeStr() {
		return releaseTimeStr;
	}

	public void setReleaseTimeStr(String releaseTimeStr) {
		this.releaseTimeStr = releaseTimeStr;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<String> getImageName() {
		return imageName;
	}

	public void setImageName(List<String> imageName) {
		this.imageName = imageName;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}
	

}
