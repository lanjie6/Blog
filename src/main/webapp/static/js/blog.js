$(function(){
		//显示更多评论
		$(".comment_show").click(function(){
			$(".comment_show").hide();
			$(".publish_a").hide();
			$(".oterwise").show();
		});
		
		$(".comment_show,.publish_a,.publish_b").on({
			mouseover:function(){
				$(this).addClass("comment_show_mouseover");
			},
			mouseout:function(){
				$(this).removeClass("comment_show_mouseover");
			}
		});
	
		//刷新验证码图片
		$("#verImage").click(function(){
			$("#verImage").attr("src","/image.jsp?"+Math.random());
		});
});

/**
 * 发布评论
 */
function publishComment(commentId){
	
	//如果commentId为0，说明是直接发布评论，取发布评论的内容
	//如果不为0，则说明是回复评论，取回复内容
	if(commentId==0){
		var publishComment = $("#publishComment").val();
	}else{
		var publishComment = $("#replyComment"+commentId).val();
	}	
	var id = $("#blogId").val();
	
	if(publishComment==null || publishComment==''){
		$("#myModalBody").html("请输入评论内容！");
		$("#myModal").modal("show");
		return;
	}
	
	$.ajax({
		url:"/saveComment.do",
		type:"post",
		data:{"publishComment":publishComment,"id":id,"parentCommentId":commentId},
		dataType:"json",
		success:function(result){
			if(result.resultCode==001){
				$("#myModalBody").html(result.resultContent);
				$("#myModal").modal("show");
				//当模态框关闭时，刷新一下数据
				$('#myModal').on("hidden.bs.modal",function(){
					window.location.reload();
				});
			}else{
				$("#myModalBody").html(result.resultContent);
				$("#myModal").modal("show");
			}
		},
		error:function(){
			$("#myModalBody").html("发表评论失败！请重试。");
			$("#myModal").modal("show");
		}
	});
	
}

/**
 * 效验表单提交
 * @returns {Boolean}
 */
function checkContent(){
	var q = $("#q").val().trim();
	if(q==null || q==""){
		$("#myModalBody").html("请输入您要搜索的关键字");
		$("#myModal").modal("show");
		return false;
	}else{
		return true;
	}
	
}

function showReply(id){
	$(".comment_reply_btn").hide();
	$(".comment_reply_input").hide();
	$(".comment"+id).show();
}


