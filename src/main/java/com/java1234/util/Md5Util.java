package com.java1234.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Md5工具类
 * @author gucaini
 *
 */
public class Md5Util {
	
	/**
	 * 加密字符串
	 * @param str  需要加密的字符串内容
	 * @param salt 对加密的内容加盐
	 * @return
	 */
	public static String md5(String str,String salt){
		
		return new Md5Hash(str, salt).toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(Md5Util.md5("admin123", "java1234"));
		
	}

}
