package com.java1234.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.java1234.entity.Blogger;
import com.java1234.service.BloggerService;

/**
 * 自定义Realm
 * @author gucaini
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private BloggerService bloggerService;
	
	
	/**
	 * 为当前用户赋予角色和授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}
	
	/**
	 * 登录效验
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//获取当前用户的用户名
		String userName = (String)token.getPrincipal();
		
		Blogger blogger = bloggerService.getUserByUserName(userName);

		if(blogger!=null){
			
			AuthenticationInfo authc = new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "loginRealm");
			
			return authc;
		}
		
		return null;
	}

}
