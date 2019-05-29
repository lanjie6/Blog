$(function(){
	//绑定发布博客事件
	$("#publish_blog").click(publishBLog);
	
	//绑定博客管理搜索按钮点击事件
	$("#s_title_btn").click(searchBlogByTitle);
	
	//绑定博客管理搜索框回车事件
	$("#s_title").keydown(searchKeyDown);
	
	//绑定博客管理刷新事件
	$("#s_reload_btn").click(reloadBlog);
	
	//绑定博客管理删除按钮事件
	$("#s_delete_btn").click(deleteBlog);
	
	//绑定博客管理修改按钮事件
	$("#s_edit_btn").click(toUpdatePage);
	
	//绑定修改博客按钮事件
	$("#update_blog").click(updateBLog);
	
	//绑定博客类别管理新增按钮事件
	$("#type_insert_btn").click(openDialog);
	
	//绑定博客类别添加里关闭dialog窗口按钮事件
	$("#dailog_closed_btn").click(closeDialog);
	
	//绑定博客类别管理修改按钮事件
	$("#type_edit_btn").click(openUpdateDialog);
	
	//绑定博客类别管理新增或修改的dialog窗口保存按钮点击事件
	$("#dailog_save_btn").click(saveBlogType);
	
	//绑定博客类别管理删除按钮事件
	$("#type_delete_btn").click(deleteBlogType);
	
	//绑定博客评论管理删除按钮点击事件
	$("#comment_delete_btn").click(deleteComment);
	
	//绑定修改个人信息提交按钮点击事件
	$("#update_blogger").click(updateBlogger);
	
	//绑定修改密码窗口关闭按钮点击事件
	$("#pwd_dailog_closed_btn").click(closePwdDialog);
	
	//绑定修改密码窗口保存按钮点击事件
	$("#pwd_dailog_save_btn").click(updatePassword);
	
	//绑定友情链接新增按钮点击事件
	$("#link_insert_btn").click(openLinkDialog);
	
	//绑定友情链接dialog窗口关闭按钮事件
	$("#link_closed_btn").click(closeLinkDialog);
	
	//绑定友情链接dialog窗口保存按钮事件
	$("#link_save_btn").click(saveLink);
	
	//绑定友情链接修改按钮点击事件
	$("#link_edit_btn").click(openUpdateLinkDialog);
	
	//绑定友情链接删除按钮点击事件
	$("#link_delete_btn").click(deleteLink);
});

/**
 * 添加选项卡
 * @param text
 * @param url
 * @param iconCls
 */
function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='/admin/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
}

/**
 * 发布博客
 */
function publishBLog(){
	//获取标题
	var title = $("#title").val();
	//获取类别ID
	var typeId = $("#blogTypeId").combobox("getValue");
	//获取内容
	var content = UE.getEditor("container").getContent();
	//获取关键词
	var keyWord = $("#keyWord").val();
	//获取无标签的内容
	var contentNoTag = UE.getEditor("container").getContentTxt();
	//获取摘要
	var summary = contentNoTag.substr(0,155);
	
	if(title==null || title==""){
		$.messager.alert("系统提示","请输入博客标题!","info");
		return;
	}
	
	if(typeId==null || typeId==0){
		$.messager.alert("系统提示","请选择博客类型！","info");
		return;
	}
	
	if(content==null || content==""){
		$.messager.alert("系统提示","请编写您的博客！","info");
		return;
	}
	
	$.ajax({
		url:"/admin/saveBlog.do",
		type:"post",
		dataType:"json",
		data:{"title":title,"typeId":typeId,"content":content,"keyWord":keyWord,"contentNoTag":contentNoTag,"summary":summary},
		success:function(result){
			if(result.resultCode==001){
				$.messager.alert("系统提示",result.resultContent,"info");
				//成功后重置一下
				$("#title").val("");
				$("#blogTypeId").combobox("setValue","0");
				$("#keyWord").val("");
				UE.getEditor("container").setContent("");
			}else{
				$.messager.alert("系统提示",result.resultContent,"error");
			}
		},
		error:function(){
			$.messager.alert("系统提示","系统异常，请稍后重试！","error");
		}
	});
}

/**
 * 格式化博客信息管理列表（博客标题列）
 * @param value
 * @param row
 * @returns {String}
 */
function titleFormatter(value,row){
	
	return '<a target="_blank" href="/findBlog/'+row.id+'.html">'+value+'</a>'
}

/**
 * 根据博客标题模糊查询博客信息
 */
function searchBlogByTitle(){
		$("#dg").datagrid("load",{
			title:$("#s_title").val()
		});
}

/**
 * 通过回车按钮来发送：根据博客标题模糊查询博客信息的请求
 * @param event
 */
function searchKeyDown(event){
	if(event.keyCode==13){
		searchBlogByTitle();
	}
}

/**
 * 刷新博客信息列表
 */
function reloadBlog(){
	$("#dg").datagrid("reload");
}

/**
 * 删除博客
 */
function deleteBlog(){
	
	//获取所有被选中的行
	var selectedRows = $("#dg").datagrid("getSelections");
	
	if(selectedRows.length==0){
		$.messager.alert("系统提示","请选择要删除的博客","info");
		
		return;
	}
	
	var ids = [];
	
	for(var i=0;i<selectedRows.length;i++){
		ids.push(selectedRows[i].id);
	}
	
	$.messager.confirm("系统提示","您确认要删除这"+selectedRows.length+"条博客吗？",function(r){
		
		if(r){
			$.ajax({
				url:"/admin/deleteBlog.do",
				type:"post",
				dataType:"json",
				data:{"ids":ids},
				success:function(result){
					if(result.resultCode==001){
						$.messager.alert("系统提示",result.resultContent,"info");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示",result.resultContent,"info");
					}
				},
				error:function(){
					$.messager.alert("系统提示","删除博客失败！请稍后重试。","error");
				}
			});
		}
		
	});
}

/**
 * 前往博客修改页面
 */
function toUpdatePage(){
	
	var selectedRows = $("#dg").datagrid("getSelections");
	
	if(selectedRows.length!=1){
		$.messager.alert("系统提示","请选择要一条要修改的博客","info");
		
		return;
	}
	
	//html5的机制，将博客id存入session中，带到下一个页面中
	sessionStorage["blogId"]=selectedRows[0].id;
	
	//调用父窗体的添加选项卡方法
	window.parent.openTab("修改博客","updateBlog.jsp","icon-edit");
}

/**
 * 在修改博客信息页面加载博客数据
 */
function loadBlogData(){
		var ue = UE.getEditor('container');
		$(document).ready(function(){
			//从session中取出带过来的ID
			var id = sessionStorage["blogId"];
			//加载博客数据
			$.ajax({
				 url:"/admin/findBlog.do",
				 type:"post",
				 dataType:"json",
				 data:{"id":id},
				 success:function(result){
					 $("#title").val(result.title);
					 $("#blogTypeId").combobox("setValue",result.typeId);
					 $("#keyWord").val(result.keyWord);
					 //等待UE编辑器加载完成后，再赋值
					 ue.ready(function(){
						ue.setContent(result.content); 
					 });
				 }
			 });
		});
}

/**
 * 修改博客
 */
function updateBLog(){
	//从Session中获取ID
	var id = sessionStorage["blogId"];
	//获取标题
	var title = $("#title").val();
	//获取类别ID
	var typeId = $("#blogTypeId").combobox("getValue");
	//获取内容
	var content = UE.getEditor("container").getContent();
	//获取关键词
	var keyWord = $("#keyWord").val();
	//获取无标签的内容
	var contentNoTag = UE.getEditor("container").getContentTxt();
	//获取摘要
	var summary = contentNoTag.substr(0,155);
	
	if(title==null || title==""){
		$.messager.alert("系统提示","请输入博客标题!","info");
		return;
	}
	
	if(typeId==null || typeId==0){
		$.messager.alert("系统提示","请选择博客类型！","info");
		return;
	}
	
	if(content==null || content==""){
		$.messager.alert("系统提示","请编写您的博客！","info");
		return;
	}
	
	$.ajax({
		url:"/admin/saveBlog.do",
		type:"post",
		dataType:"json",
		data:{"id":id,"title":title,"typeId":typeId,"content":content,"keyWord":keyWord,"contentNoTag":contentNoTag,"summary":summary},
		success:function(result){
			if(result.resultCode==001){
				$.messager.alert("系统提示",result.resultContent,"info");
			}else{
				$.messager.alert("系统提示",result.resultContent,"error");
			}
		},
		error:function(){
			$.messager.alert("系统提示","系统异常，请稍后重试！","error");
		}
	});
}

/**
 * 打开博客类别新增窗口
 */
function openDialog(){
	$("#blogTypeDialog").dialog({
		title:"新增博客类别",
		iconCls:"icon-add",
		width:500,
		height:200,
		top:150,
		closed:false
	});
}

/**
 * 关闭dailog对话框
 */
function closeDialog(){
	$("#id").val("");
	$("#typeName").val("");
	$("#orderNo").val("");
	$("#blogTypeDialog").dialog("close");
}

/**
 * 打开博客类别修改窗口
 */
function openUpdateDialog(){
	
	var selectRows = $("#dg").datagrid("getSelections");
	
	if(selectRows.length!=1){
		$.messager.alert("系统提示","请选择一条需要修改的博客类别记录","info");
		return;
	}
	
	$("#blogTypeDialog").dialog({
		title:"修改博客类别",
		iconCls:"icon-edit",
		width:500,
		height:200,
		top:150,
		closed:false
	});
	
	$("#fm").form("load",selectRows[0]);
}

/**
 * 新增或修改博客类别
 */
function saveBlogType(){
	var typeName = $("#typeName").val();
	var orderNo = $("#orderNo").val();
	var id = $("#id").val();
	
	$("#fm").form("submit",{
		url:"/admin/saveBlogType.do",
		onSubmit:function(){
			
			return $(this).form("validate");
		},
		success:function(result){
			
			//easyUi提供的表单请求，success返回的参数是JSON字符串，并非是JSON对象，所以我们需要单独处理一下。
			var resultObj = eval('('+result+')');
			
			if(resultObj.resultCode==001){
				$.messager.alert("系统提示",resultObj.resultContent,"info");
				closeDialog();
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示",resultObj.resultContent,"error");
			}
		}
	});
}

/**
 * 批量删除博客
 */
function deleteBlogType(){
	
	var selectRows = $("#dg").datagrid("getSelections");
	
	if(selectRows.length==0){
		$.messager.alert("系统提示","请选择要删除的博客类别","info");
		return;
	}
	
	var ids = [];
	
	for(var i=0;i<selectRows.length;i++){
		if(selectRows[i].count!=0){
			$.messager.alert("系统提示","博客类别名："+selectRows[i].typeName+"下有博客，无法删除！","error");
			return;
		}
		
		ids.push(selectRows[i].id);
	}
	
	$.messager.confirm("系统提示","您确定要删除这"+selectRows.length+"条博客类别信息吗？",function(r){
		if(r){
			$.ajax({
				url:"/admin/deleteBlogType.do",
				dataType:"json",
				data:{"ids":ids},
				type:"post",
				success:function(result){
					if(result.resultCode==001){
						$.messager.alert("系统提示",result.resultContent,"info");
						$("#dg").datagrid("reload");
					}else{
						$.meassager.alert("系统提示",result.resultContent,"error");
					}
				},
				error:function(){
					$.meassager.alert("系统提示","系统忙！请稍后重试。","error");
				}
			});
		}
	});
}


/**
 * 格式化评论列表博客标题
 */
function formatBlogTitle(value,row){
	if(value==null){
		return "<font color='red'>该博客已删除！</font>";
	}else{
		
		return '<a target="_blank" href="/findBlog/'+value.id+'.html">'+value.title+'</a>';
	}
}

/**
 * 删除评论
 */
function deleteComment(){
	
	var selectRows = $("#dg").datagrid("getSelections");
	
	if(selectRows.length==0){
		$.messager.alert("系统提示","请选择要删除的评论","info");
		return;
	}
	
	var ids = [];
	
	for(var i=0;i<selectRows.length;i++){
		ids.push(selectRows[i].id);
	}
	
	$.messager.confirm("系统提示","您确定要删除这"+selectRows.length+"条评论信息吗？",function(r){
		if(r){
			$.ajax({
				url:"/admin/deleteComment.do",
				dataType:"json",
				data:{"ids":ids},
				type:"post",
				success:function(result){
					if(result.resultCode==001){
						$.messager.alert("系统提示",result.resultContent,"info");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示",result.resultContent,"error");
					}
				},
				error:function(){
					$.messager.alert("系统提示","系统忙！请稍后重试。","error");
				}
			});
		}
	});
}

/**
 * 加载个人信息
 */
function loadBloggerData(){
	var ue = UE.getEditor('profile');
	$(function(){
		//加载博客数据
		$.ajax({
			 url:"/admin/findBlogger.do",
			 type:"post",
			 dataType:"json",
			 success:function(result){
				 $("#userName").val(result.blogger.userName);
				 $("#nickName").val(result.blogger.nickName);
				 $("#sign").val(result.blogger.sign);
				 //等待UE编辑器加载完成后，再赋值
				 ue.ready(function(){
					ue.setContent(result.blogger.profile); 
				 });
			 }
		 });
	});
}

function updateBlogger(){
	var nickName = $("#nickName").val();
	var sign = $("#sign").val();
	var text = UE.getEditor('profile').getContentTxt();
	
	if(nickName==null || nickName==''){
		$.messager.alert("系统提示","请填写昵称","info");
		return;
	}
	if(sign==null || sign==''){
		$.messager.alert("系统提示","请填写个性签名","info");
		return;
	}
	if(text==null || text==''){
		$.messager.alert("系统提示","请填写个人简介","info");
		return;
	}
	
	//将UE编辑器的文本内容赋值给隐藏域的input标签，方便提交到后台取值
	$("#pf").val(text);
	
	$("#fm1").form("submit",{
		url:"/admin/saveBlogger.do",
		success:function(result){
			var resultObj = eval("("+result+")");
			if(resultObj.resultCode==001){
				$.messager.alert("系统提示",resultObj.resultContent,"info");
			}else{
				$.messager.alert("系统提示",resultObj.resultContent,"error");
			}
		}
	});
}

//安全退出
function logout(){
	$.messager.confirm("系统提示","您确认要退出系统吗？",function(r){
		if(r){
			window.location.href="/admin/logout.do";
		}
	});
}

/**
 * 刷新缓存
 */
function refreshSystem(){
	$.ajax({
		url:"/admin/refresh.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.resultCode==001){
				$.messager.alert("系统提示",result.resultContent,"info");
			}else{
				$.messager.alert("系统提示",result.resultContent,"error");
			}
			
		}
	});
}

/**
 * 打开修改密码窗口
 */
function openPasswordModifyDialog(){
	$("#updatePasswordDialog").dialog({
		title:"修改密码",
		iconCls:"icon-modifyPassword",
		width:500,
		height:220,
		closed:false,
		modal: true
	});
	
}

/**
 * 关闭修改密码窗口
 */
function closePwdDialog(){
	$("#password").val("");
	$("#password2").val("");
	$("#updatePasswordDialog").dialog("close");
}

/**
 * 修改密码
 */
function updatePassword(){
	$("#fm3").form("submit",{
		url:"/admin/updatePassword.do",
		onSubmit:function(){
			
			var validate = $(this).form("validate");
			
			var password1 = $("#password").val();
			
			var password2 = $("#password2").val();
			
			if(validate){
				if(password1!=password2){
					$.messager.alert("系统提示","两次密码输入的不一致","info");
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}
		},
		success:function(result){
			var resultObj = eval("("+result+")");
			if(resultObj.resultCode==001){
				$.messager.alert("系统提示",resultObj.resultContent,"info");
			}else{
				$.messager.alert("系统提示",resultObj.resultContent,"error");
			}
			closePwdDialog();
		}
	});
}

/**
 * 打开新增窗口
 */
function openLinkDialog(){
	$("#linkDialog").dialog({
		title:"新增友情链接",
		iconCls:"icon-add",
		top:150,
		width:500,
		height:200,
		closed:false
	});
}

/**
 * 关闭友情链接Dialog
 */
function closeLinkDialog(){
	$("#linkName").val("");
	$("#linkUrl").val("");
	$("#orderNo").val("");
	$("#id").val("");
	$("#linkDialog").dialog("close");
}

/**
 * 保存友情链接
 */
function saveLink(){

	var id = $("#id").val();
	
	$("#link_fm").form("submit",{
		url:"/admin/saveLink.do",
		onSubmit:function(){
			
			return $(this).form("validate");
		},
		success:function(result){
			var resultObj = eval("("+result+")");
			if(resultObj.resultCode==001){
				$.messager.alert("系统提示",resultObj.resultContent,"info");
				closeLinkDialog();
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示",resultObj.resultContent,"error");
			}
		}
	});
}

/**
 * 修改友情链接
 */
function openUpdateLinkDialog(){
	
	var selectedLinks = $("#dg").datagrid("getSelections");
	
	if(selectedLinks.length!=1){
		$.messager.alert("系统提示","请选择一条需要修改的友情链接信息","info");
		return;
	}

	$("#linkDialog").dialog({
		title:"修改友情链接",
		iconCls:"icon-edit",
		top:150,
		width:500,
		height:200,
		closed:false
	});
	

	$("#link_fm").form("load",selectedLinks[0]);
}

/**
 * 删除友情链接
 */
function deleteLink(){
	var selectedLinks = $("#dg").datagrid("getSelections");
	
	if(selectedLinks.length==0){
		$.messager.alert("系统提示","请选择需要删除的友情链接信息","info");
		return;
	}
	
	var ids= [];
	
	for(var i=0;i<selectedLinks.length;i++){
		ids.push(selectedLinks[i].id);
	}
	
	$.messager.confirm("系统提示","您确定要删除这"+selectedLinks.length+"条友情链接信息吗?",function(r){
		if(r){
			$.ajax({
				url:"/admin/delete.do",
				data:{"ids":ids},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.resultCode==001){
						$.messager.alert("系统提示",result.resultContent,"info");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示",result.resultContent,"error");
					}
				}
			})
		}
	});
}