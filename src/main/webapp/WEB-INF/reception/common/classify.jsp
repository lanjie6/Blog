<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 博主信息和分类开始 -->
<div class="col-md-3">
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>&nbsp;博主信息
		</div>
		<div class="user_image">
			<img id="user_image" src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName }">
		</div>
		<div class="nick_name">
			${blogger.nickName }
		</div>
		<div class="user_sign">
			(${blogger.sign })
		</div>
	</div>
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>&nbsp;按文章类别
		</div>
		<div class="datas">
			<ul>
				<c:forEach items="${blogTypeList }" var="blogType">
					<li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogType.id }">${blogType.typeName }(${blogType.count})</a></span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>&nbsp;按文章日期
				</div>
				<div class="datas">
			<ul>
				<c:forEach items="${blogList }" var="blog">
					<li><span><a href="${pageContext.request.contextPath}/index.html?releaseTimeStr=${blog.releaseTimeStr }">${blog.releaseTimeStr }(${ blog.count})</a></span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>&nbsp;友情链接
				</div>
				<div class="datas">
			<ul>
				<c:forEach items="${linkList}" var="link">
					<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName}</a></span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<!-- 博主信息和分类结束 -->
