<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-9">
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/search_icon.png">&nbsp;搜索&nbsp;<font color="red">${q }</font>
			&nbsp;的结果&nbsp;(共搜索到&nbsp;${result.resultCount }&nbsp;条记录)
		</div>
		<div class="datas">
			<c:choose>
				<c:when test="${result.resultCount==0 }">
					<div class="noSearch">未查询到结果，请换个关键字试试看！</div>
				</c:when>
				<c:otherwise>
					<ul>
						<c:forEach items="${result.blogs }" var="blog">
							<li>
								<span class="title"><a target="_blank" href="${pageContext.request.contextPath}/findBlog/${blog.id }.html">${blog.title }</a></span>
								<span class="summary">摘要：${blog.content }</span>
								<span class="info">发布日期：${blog.releaseTimeStr }</span>
								<br>
	  							<hr class="hr"/>
							</li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- 分页开始 -->
	<nav aria-label="...">
  			<ul class="pager">
    			${searchPageCode }
  			</ul>
	</nav>
	<!-- 分页结束 -->
</div>
