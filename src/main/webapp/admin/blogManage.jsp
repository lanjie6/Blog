<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</head>
<body class="body_style">
	<table id="dg" class="easyui-datagrid" title="博客管理" fit="true" fitColumns="true" 
	toolbar="#tb" pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/list.do"
	pageList="[30,40,50]" pageSize="30">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="5%" align="center">编号</th>
				<th field="title" width="45%" align="center" formatter="titleFormatter">博客标题</th>
				<th field="releaseTime" width="20%" align="center">发布时间</th>
				<th field="typeName" width="30%" align="center">博客类别</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="s_edit_btn">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="s_delete_btn">删除</a>
		标题：<input type="text" id="s_title">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="s_title_btn">搜索</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="s_reload_btn">刷新</a>
	</div>
</body>
</html>