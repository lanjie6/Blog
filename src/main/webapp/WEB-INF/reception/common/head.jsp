<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 博客LOGO和天气预报开始 -->
<div class="row">
	<div class="col-md-4">
		<img alt="兰杰的博客" src="${pageContext.request.contextPath}/static/images/logo.png">
	</div>
	<div class="col-md-8">
		<iframe id="tianqi" scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5&site=12"></iframe>
	</div>
</div>
