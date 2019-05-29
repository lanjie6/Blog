package com.java1234.entity;

import java.io.Serializable;

/**
 * 友情链接实体
 * @author gucaini
 *
 */
public class Link implements Serializable{

	private static final long serialVersionUID = -3369053874733637305L;
	
	private Integer id; //编号
	
	private String linkName; //友情链接名称 
	
	private String linkUrl; //友情链接地址
	
	private Integer orderNo; //排序
	
	public Link() {
		super();
	}

	public Link(String linkName, String linkUrl, Integer orderNo) {
		super();
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.orderNo = orderNo;
	}

	public Link(Integer id, String linkName, String linkUrl, Integer orderNo) {
		super();
		this.id = id;
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.orderNo = orderNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
