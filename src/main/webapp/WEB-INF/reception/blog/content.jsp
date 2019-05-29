<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 博客内容开始 -->
<div class="col-md-9">
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/blog_show_icon.png">&nbsp;博客信息
		</div>
		<div class="datas">
			<!-- 博客标题开始 -->
			<div class="blog_title">
				<h3><strong>${blog.title }</strong></h3>
			</div>
			<!-- 博客标题结束 -->
			<!-- 博客分享开始 -->
			<div class="blog_share">
				<div class="bdsharebuttonbox">
					<a href="#" class="bds_more" data-cmd="more"></a>
					<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
					<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
					<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
					<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
					<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
				</div>
			</div>
			<!-- 博客分享结束 -->
			<!-- 博客信息开始 -->
			<div class="blog_info">
				<!-- 格式化时间 -->
				<fmt:parseDate var="dateObj" value="${blog.releaseTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate var="stringObj" value="${dateObj }" pattern="yyyy-MM-dd HH:mm"/>
				<span>发布时间：『${stringObj}』&nbsp;博客类别：${blog.blogType.typeName}&nbsp;阅读(${blog.hitsCount })</span>
			</div>
			<!-- 博客信息结束 -->
			<!-- 博客正文开始 -->
			<div class="blog_content">
				${blog.content }
			</div>
			<!-- 博客正文结束 -->
			<!-- 关键词开始 -->
			<div class="blog_keyword">
				<span><b>关键字：</b>
				<c:choose>
					<c:when test="${keyWordList==null }">
						&nbsp;&nbsp;无
					</c:when>
					<c:otherwise>
						<c:forEach items="${keyWordList }" var="keyWord">
							<i>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/search.html?q=${keyWord}">${keyWord }</a></i>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</span>
			</div>
			<!-- 关键词结束 -->
			<!-- 做一个锚点 -->
			<a name="publish_a"></a>
			<!-- 上一篇和下一篇开始 -->
			<div class="blog_lastAndNextPage">
				<c:if test="${lastBlog!=null}">
					<p>上一篇：<a href="${pageContext.request.contextPath }/findBlog/${lastBlog.id}.html">${lastBlog.title }</a></p>
				</c:if>
				<c:if test="${nextBlog!=null}">
					<p>下一篇：<a href="${pageContext.request.contextPath }/findBlog/${nextBlog.id}.html">${nextBlog.title }</a></p>
				</c:if>
			</div>
			<!-- 上一篇和下一篇结束 -->
		</div>
	</div>
	
	<!-- 发送评论开始 -->
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/publish_comment_icon.png">&nbsp;发送评论
		</div>
		<div class="input_comment">
			<textarea id="publishComment" class="form-control publish_comment" rows="4" placeholder="来说两句吧..."></textarea>
		</div>
		<div class="ver_code">
    		<button type="button" class="btn btn-info btn-sm" onclick="publishComment(0)">发表评论</button>
    		<input type="hidden" id="blogId" value="${blog.id }"/>
		</div>
	</div>
	<!-- 发送评论结束 -->
	<!-- 评论内容开始 -->
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/comment_icon.png">&nbsp;评论信息
		</div>
		<div class="datas">
			<c:choose>
				<c:when test="${commentList.size()==0 }">
					<div class="comment_title">
						暂无评论
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${commentList }" var="comment" varStatus="status">
						<c:choose>
							<c:when test="${status.index<10 }">
								<!-- 评论头开始 -->
								<div class="comment_title">
									<b>${status.index+1}楼&nbsp;&nbsp;${comment.userIp }：</b>
									<fmt:parseDate var="dateObj" value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
									<fmt:formatDate var="commentDate" value="${dateObj }" pattern="yyyy-MM-dd HH:mm"/>
									<span class="comment_date">[${commentDate }]</span>
								</div>
								<!-- 评论头结束 -->
								<!-- 回复显示区开始 -->
								<c:forEach items="${replyList }" var="reply" >
									<c:if test="${reply.id==comment.id}">
										<div class="comment_reply_content">
											<div class="comment_title">
												<b>${reply.userIp }&nbsp;&nbsp;：</b>
												<div class="comment_content">${ reply.comment}</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<!-- 回复显示区结束 -->
								<div class="comment_content">${comment.comment }</div>
								<!-- 回复模块开始 -->
								<div class="comment_reply"><a href="javascript:void(0)" onclick="showReply(${comment.id});">回复</a></div>
								<div class="input_comment comment_reply_input oterwise_reply comment${comment.id }">
									<textarea id="replyComment${comment.id }" class="form-control publish_comment" rows="4" cols="3" placeholder="来说两句吧..."></textarea>
								</div>
								<div class="comment_reply_btn oterwise_reply comment${comment.id}">
						    		<button type="button" class="btn btn-info btn-sm" onclick="publishComment(${comment.id})">发表回复</button>
								</div>
								<!-- 回复模块结束-->
								<hr class="comment_hr">
								<c:if test="${status.index==9 }">
									<div class="comment_load">
										<span class="comment_show"><img src="${pageContext.request.contextPath }/static/images/arrow_down.png">显示所有评论</span>
										<a class="publish_a" href="#publish_a"><img src="${pageContext.request.contextPath }/static/images/file.png">发表评论</a>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<div class="comment_title oterwise">
									<b>${status.index+1}楼&nbsp;&nbsp;${comment.userIp }：</b>
									<fmt:parseDate var="dateObj" value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
									<fmt:formatDate var="commentDate" value="${dateObj }" pattern="yyyy-MM-dd HH:mm"/>
									<span class="comment_date">[${commentDate }]</span>
								</div>
								<c:forEach items="${replyList }" var="reply" >
									<c:if test="${reply.id==comment.id}">
										<div class="comment_reply_content oterwise">
											<div class="comment_title">
												<b>${reply.userIp }&nbsp;&nbsp;：</b>
												<div class="comment_content">${ reply.comment}</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<div class="comment_content oterwise">${comment.comment }</div>
								<div class="comment_reply oterwise"><a href="javascript:void(0)" onclick="showReply(${comment.id});">回复</a></div>
								<div class="input_comment comment_reply_input oterwise_reply comment${comment.id }">
									<textarea id="replyComment${comment.id }" class="form-control publish_comment" rows="4" cols="3" placeholder="来说两句吧..."></textarea>
								</div>
								<div class="comment_reply_btn oterwise_reply comment${comment.id}">
						    		<button type="button" class="btn btn-info btn-sm" onclick="publishComment(${comment.id})">发表回复</button>
								</div>
								<hr class="comment_hr oterwise">
							</c:otherwise>
						</c:choose>	
					</c:forEach>
					<div class="comment_load oterwise">
						<a class="publish_b" href="#publish_a"><img src="${pageContext.request.contextPath }/static/images/file.png">发表评论</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 评论内容结束 -->
	
	<!-- 提示信息模态框 开始-->
	<jsp:include page="${pageContext.request.contextPath }/WEB-INF/reception/common/modal.jsp"></jsp:include>
	<!-- 提示信息模态框结束 -->
</div>
<!-- 博客内容结束 -->