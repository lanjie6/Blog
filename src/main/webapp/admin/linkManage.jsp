<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</head>
<body class="body_style">
	<table id="dg" class="easyui-datagrid" title="友情链接管理" fit="true" fitColumns="true" 
	toolbar="#tb" pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/linkList.do"
	pageList="[30,40,50]" pageSize="30">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="id" width="5%" align="center">编号</th>
				<th field="linkName" width="40%" align="center">友情链接名</th>
				<th field="linkUrl" width="40%" align="center">友情链接地址</th>
				<th field="orderNo" width="15%" align="center">排序序号</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="link_insert_btn">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="link_edit_btn">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="link_delete_btn">删除</a>
	</div>
	
	<!-- 新增或修改窗口开始 -->
	<div class="easyui-dialog" id="linkDialog" closed="true" buttons="#link_tb">
		<form id="link_fm">
			<table cellpadding="8px">
				<tr>
					<td>友情链接名称：</td>
					<td><input id="linkName" name="linkName" class="easyui-validatebox textbox" required="true"></td>
				</tr>
				<tr>
					<td>友情链接地址：</td>
					<td>
						<input id="linkUrl" name="linkUrl" class="easyui-validatebox textbox orderNoInput" validType="url" required="true">
					</td>
				</tr>
				<tr>
					<td>友情链接排序：</td>
					<td>
						<input id="orderNo" name="orderNo" class="easyui-numberbox textbox orderNoInput" required="true">&nbsp;(友情链接排序按从小到大排)
						<input type="hidden" id="id" name="id">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 新增或修改窗口结束 -->
	
	<div id="link_tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save"  id="link_save_btn">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" id="link_closed_btn">关闭</a>
	</div>
</body>
</html>