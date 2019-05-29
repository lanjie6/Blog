<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理-兰杰的博客</title>
<!-- 引入easyui -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</head>
<body class="easyui-layout">
	<!-- head开始 -->
	<div class="admin_title" data-options="region:'north',border:false">
		<img src="${pageContext.request.contextPath}/static/images/logo.png">
		<span id="admin_span">
			欢迎:${currentUser.userName }&nbsp;
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'">安全退出</a>
		</span>
	</div>
	<!-- head结束 -->
	<!-- 菜单栏开始 -->
	<div class="admin_menu" data-options="region:'west',title:'导航菜单'">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div class="menuContent" title="常用操作" data-options="selected:true,iconCls:'icon-item'">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'">写博客</a>
			</div>
			<div class="menuContent" title="博客管理"  data-options="iconCls:'icon-bkgl'">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'">写博客</a>
				<a href="javascript:openTab('博客信息管理','blogManage.jsp','icon-bkgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bkgl'">博客信息管理</a>
			</div>
			<div class="menuContent" title="博客类别管理" data-options="iconCls:'icon-bklb'">
				<a href="javascript:openTab('博客类别信息管理','blogTypeManage.jsp','icon-bklb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bklb'">博客类别信息管理</a>
			</div>
			<div class="menuContent" title="评论管理"  data-options="iconCls:'icon-plgl'">
				<a href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-plgl'" >评论信息管理</a>
			</div>
			<div class="menuContent" title="个人信息管理"  data-options="iconCls:'icon-grxx'">
				<a href="javascript:openTab('修改个人信息','updateBlogger.jsp','icon-grxxxg')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxxxg'" >修改个人信息</a>
			</div>
			<div class="menuContent" title="系统管理"  data-options="iconCls:'icon-system'">
			    <a href="javascript:openTab('友情链接管理','linkManage.jsp','icon-link')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-link'" >友情链接管理</a>
				<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" >修改密码</a>
				<a href="javascript:refreshSystem()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" >刷新系统缓存</a>
			</div>
		</div>
	</div>
	<!-- 菜单栏结束 -->
	<!-- 主内容区开始 -->
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" data-options="iconCls:'icon-home'">
				<div class="shouye"></div>
			</div>
		</div>
	</div>
	<!-- 主内容区结束 -->
	
	<!-- 修改密码窗口开始 -->
	<div class="easyui-dialog" id="updatePasswordDialog" closed="true" buttons="#tb3">
		<form id="fm3">
			<table cellpadding="8px">
				<tr>
					<td>用户名：</td>
					<td><input id="userName" name="userName" value="${currentUser.userName }" class="easyui-validatebox textbox" readonly="readonly"></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="password" name="password" class="easyui-validatebox textbox orderNoInput" required="true"></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" id="password2" name="password2" class="easyui-validatebox textbox orderNoInput" required="true"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 修改密码窗口结束 -->
	<div id="tb3">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save"  id="pwd_dailog_save_btn">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="pwd_dailog_closed_btn">关闭</a>
	</div>
</body>
</html>