<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改博客</title>
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
	<div id="p" class="easyui-panel" title="修改博客" data-options="fit:true">
		<table>
			<tr>
				<td class="desc">博客标题：</td>
				<td><input id="title" type="text"></td>
			</tr>
			<tr>
				<td class="desc">博客类别：</td>
				<td>
					<select id="blogTypeId" class="easyui-combobox" editable="false" panelHeight="auto">
					    <option value="0">请选择博客类别...</option>
					    <c:forEach items="${blogTypeList }" var="blogType">
					    	<option value="${blogType.id }">${blogType.typeName }</option>
					    </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="desc">博客内容：</td>
				<td>
				    <script id="container" name="content" type="text/plain" style="width:1000px;height:500px;"></script>
				</td>
			</tr>
			<tr>
				<td class="desc">关键词：</td>
				<td>
					<input type="text" id="keyWord" name="keyWord"/>&nbsp;(多个关键字中间用空格隔开)
				</td>
			</tr>
			<tr>
			<td class="desc"></td>
			<td>
				<a id="update_blog" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改博客</a>
			</td>
		</tr>
		</table>
	</div>
	<!-- 实例化UE编辑器并加载数据 -->
	<script type="text/javascript">
		loadBlogData();
	</script>
</body>
</html>