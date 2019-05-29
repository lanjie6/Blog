package com.java1234.entity;

import java.io.Serializable;

/**
 * 博客类别实体
 * @author gucaini
 *
 */
public class BlogType implements Serializable{

	private static final long serialVersionUID = 1920974830941786580L;
	
	private Integer id;//编号
	
	private String typeName;//博客名称
	
	private Integer orderNo;//排序
	
	//二级映射
	private Integer count; //数量
	
	public BlogType() {
		super();
	}

	public BlogType(String typeName, Integer orderNo) {
		super();
		this.typeName = typeName;
		this.orderNo = orderNo;
	}

	public BlogType(Integer id, String typeName, Integer orderNo) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.orderNo = orderNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
