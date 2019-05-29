<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 博客列表开始 -->
<div class="col-md-9">
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/list_icon.png">&nbsp;最新博客
		</div>
		<div class="datas">
			<ul>
				<c:forEach items="${blogs }" var="blog">
					<li>
						<span class="date">
							<a href="${pageContext.request.contextPath}/findBlog/${blog.id }.html">
								<!-- 因为后台传过来的时间是String类型的，所以这里要间接处理转换时间格式 -->
								<fmt:parseDate var="dateObj" value="${blog.releaseTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate var="stringObj" value="${dateObj }" pattern="yyyy年MM月dd日"/>
								${stringObj }
							</a>
						</span>
	  					<span class="title">
	  					<a href="${pageContext.request.contextPath}/findBlog/${blog.id }.html">${blog.title}</a></span>
	  					<span class="summary">摘要:${blog.summary}</span>
	  					<span class="img">
	  						<c:forEach items="${blog.imageName }" var="image">
	  							<a href="${pageContext.request.contextPath}/findBlog/${blog.id }.html">${image }</a>&nbsp;&nbsp;
	  						</c:forEach>
	  					</span>
	  					<span class="info">
	  						<!-- 因为后台传过来的时间是String类型的，所以这里要间接处理转换时间格式 -->
							<fmt:formatDate var="reTime" value="${dateObj }" pattern="yyyy-MM-dd HH:mm"/>
							发表于 ${reTime } 阅读(${blog.hitsCount}) 评论(${blog.commentCount}) 
	  					</span>
	  					<br>
	  					<hr class="hr"/>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<!-- 博客列表结束 -->