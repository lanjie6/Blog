<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客类别信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</head>
<body class="body_style">
	<table id="dg" class="easyui-datagrid" title="博客类别管理" fit="true" fitColumns="true" 
	toolbar="#tb" pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/typeList.do"
	pageList="[30,40,50]" pageSize="30">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="5%" align="center">编号</th>
				<th field="typeName" width="45%" align="center">博客类别名</th>
				<th field="orderNo" width="30%" align="center">排序序号</th>
				<th field="count" width="20%" align="center">博客数量</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="type_insert_btn">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="type_edit_btn">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="type_delete_btn">删除</a>
	</div>
	
	<!-- 新增或修改窗口开始 -->
	<div class="easyui-dialog" id="blogTypeDialog" closed="true" buttons="#tb2">
		<form id="fm" action="">
			<table cellpadding="8px">
				<tr>
					<td>博客类别名称：</td>
					<td><input id="typeName" name="typeName" class="easyui-validatebox textbox" required="true"></td>
				</tr>
				<tr>
					<td>博客类别排序：</td>
					<td>
						<input id="orderNo" name="orderNo" class="easyui-numberbox textbox orderNoInput" required="true">&nbsp;(博客类别排序按从小到大排)
						<input id="id" name="id" type="hidden">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 新增或修改窗口结束 -->
	
	<div id="tb2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save"  id="dailog_save_btn">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="dailog_closed_btn">关闭</a>
	</div>
</body>
</html>