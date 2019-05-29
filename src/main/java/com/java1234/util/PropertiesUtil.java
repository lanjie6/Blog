package com.java1234.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 该类是一个properties配置文件解析工具类
 * @author Administrator
 *
 */
public class PropertiesUtil {
	
	/**
	 * 通过配置的key获取对应的value
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		Properties properties = new Properties();
		//获取路径,具体的路径去项目中的target中查看，这里的定位始终定在classes下
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String)properties.get(key);
	}
}
