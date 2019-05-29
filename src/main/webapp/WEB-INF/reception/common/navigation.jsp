<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	
</script>
	<!-- 导航条开始 -->
 	<div class="row" id="daohang">
 		<div class="col-md-12">
  			<nav class="navbar navbar-default">
			   <div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">首页</a>
			    </div>
	
			   	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li><a href="${pageContext.request.contextPath}/aboutMe.html">关于博主</a></li>
			      </ul>
			      <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/search.html" method="post" onsubmit="return checkContent()">
			        <div class="form-group">
			          <input type="text" id="q" name="q" value="${q }" class="form-control" placeholder="请输入要搜索的关键字...">
			        </div>
			        <button type="submit" class="btn btn-default">搜索</button>
			      </form>
			    </div>
			  </div>
			</nav>
		</div>
 	</div>
  	<!-- 导航条结束 -->
