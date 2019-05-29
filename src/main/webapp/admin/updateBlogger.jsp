<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
<!-- 引入UEditor -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>
</head>
<body class="body_style">
	<div id="p" class="easyui-panel" title="修改个人信息" data-options="fit:true">
		<form id="fm1" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td class="desc">用户名：</td>
					<td><input id="userName" name="userName" type="text" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="desc">昵称：</td>
					<td><input id="nickName" name="nickName" type="text"></td>
				</tr>
				<tr>
					<td class="desc">个性签名：</td>
					<td><input id="sign" name="sign" type="text" style="width: 400px;"></td>
				</tr>
				<tr>
					<td class="desc">个性头像：</td>
					<td><input id="imageName" name="imageName" type="file"></td>
				</tr>
				<tr>
					<td class="desc">个人简介：</td>
					<td>
					    <script id="profile" type="text/plain" style="width:1000px;height:500px;"></script>
					    <input id="pf" name="profile" type="hidden">
					</td>
				</tr>
				<tr>
					<td class="desc"></td>
					<td>
						<a id="update_blogger" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 实例化UE编辑器并加载数据 -->
	<script type="text/javascript">
		loadBloggerData();
	</script>
</body>
</html>