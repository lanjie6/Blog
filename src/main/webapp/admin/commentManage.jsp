<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</head>
<body class="body_style">
	<table id="dg" class="easyui-datagrid" title="评论管理" fit="true" fitColumns="true" 
	toolbar="#tb" pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/getCommentList.do"
	pageList="[30,40,50]" pageSize="30">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="5%" align="center">编号</th>
				<th field="blog" width="30%" align="center" formatter="formatBlogTitle">博客名称</th>
				<th field="userIp" width="10%" align="center">用户IP</th>
				<th field="comment" width="30%" align="center">评论内容</th>
				<th field="commentDate" width="20%" align="center">评论时间</th>
				<th field="parentCommentId" width="5%" align="center">评论类型</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="comment_delete_btn">删除</a>
	</div>
</body>
</html>