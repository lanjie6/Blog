package com.java1234.entity;
/**
 * 博主信息实体
 * @author Administrator
 *
 */
public class Blogger {
	
	private Integer id;//id
	
	private String userName;//用户名
	
	private String password;//密码
	
	private String profile;//简介
	
	private String nickName;//昵称
	
	private String sign;//个性签名
	
	private String imageName;//头像

	public Blogger() {
		super();
	}

	public Blogger(String profile, String nickName, String sign) {
		super();
		this.profile = profile;
		this.nickName = nickName;
		this.sign = sign;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	

}
