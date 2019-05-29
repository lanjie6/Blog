package com.java1234.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date时间处理工具类
 * @author gucaini
 *
 */
public class DateUtil {
	
	/**
	 * 将date类型的时间，按只指定的格式转换成字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToStr(Date date ,String format){
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
		
	}
	
	/**
	 * 将字符串类型的时间，按指定格式转换成字符串
	 * @param str 需要转换的字符串时间
	 * @param oldFormat 转换的字符串的原本时间格式
	 * @param newFormat 新的字符串时间格式
	 * @return
	 * @throws ParseException 
	 */
	public static String StrToStr(String str,String odlFormat,String newFormat){
		
		try {
			SimpleDateFormat oldSdf = new SimpleDateFormat(odlFormat);
			
			Date date = oldSdf.parse(str);
			
			SimpleDateFormat newSdf = new SimpleDateFormat(newFormat);
			
			return newSdf.format(date);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}

}
