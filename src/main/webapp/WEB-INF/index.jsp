<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleInfo }</title>
<!-- 引入浏览器网页图标 -->
<link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
<!-- 引入bootstrap3样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<!-- 引入自定义样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
<!-- 引入jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<!-- 引入bootstrap3 JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<!-- 引入代码高亮显示插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<!-- 引入百度分享工具JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/share.js"></script>
<!-- 引入自定义JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/blog.js"></script>
</head>
<body>
	<!-- 布局容器开始 -->
	<div class="container">
		<!-- 博客LOGO和天气预报开始 -->
  		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/reception/common/head.jsp"></jsp:include>
  		<!-- 博客LOGO和天气预报结束 -->
  		
  		<!-- 导航条开始 -->
  		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/reception/common/navigation.jsp"></jsp:include>
  		<!-- 导航条结束 -->
  		
  		<!-- 内容布局开始 -->
  		<div class="row">
  			<!-- 主内容区开始 -->
  			<jsp:include page="${mainPage }"></jsp:include>
  			<!-- 主内容区结束 -->
  			
  			<!-- 博主信息和分类开始 -->
  			<jsp:include page="${pageContext.request.contextPath}/WEB-INF/reception/common/classify.jsp"></jsp:include>
  			<!-- 博主信息和分类结束 -->
  		</div>
  		<!-- 内容布局结束-->
  		
  		<!-- 分页开始 -->
  		<div class="row">
  			<div class="col-md-9">
  				<div class="paging">
		  			<nav aria-label="...">
		  				<ul class="pagination pagination-sm">
		  					${pageCode }
		  				</ul>
		  			</nav>
	  			</div>
  			</div>
  		</div>
  		<!-- 分页结束 -->
  		
  		<!-- 提示信息模态框 开始-->
		<jsp:include page="${pageContext.request.contextPath }/WEB-INF/reception/common/modal.jsp"></jsp:include>
		<!-- 提示信息模态框结束 -->
		
	</div>
	<!-- 布局容器结束 -->
</html>