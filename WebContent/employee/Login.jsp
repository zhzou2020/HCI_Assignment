<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>职工登录</title>
<%@include file="../nav.jsp" %>
<br/>
	<div class="login_container">
		<ul class="nav nav-tabs">
		  <li role="presentation"><a href="<%=request.getContextPath()+"/user/Login.jsp"%>">用户登录</a></li>
		  <li role="presentation" class="active"><a href="#">员工登录</a></li>
		</ul>
	</div>
	
	<div id="path" style="display:none"><%=request.getContextPath() %></div>
	
	<form method="post" action="#" class="login_info">
		<div class="form-group container has-feedback">
			<input name="id" type="text" class="form-control login-field"
				id="id" style="width:270px" placeholder="工号" data-toggle="popover" data-placement="top" data-content="登录失败"/>
		</div>
		<div class="form-group container has-feedback">
			<input name="password" type="password" class="form-control login-field"
				id="password" style="width:270px" placeholder="密码"/>
		</div>
		<p id="login_alert">账号或密码错误!</p>
		<button type="button" class="btn btn-primary" id="login" style="width: 270px; margin-left: 15px">登录</button>
	</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap-3.3.5/js/tooltip.js"></script>
<script src="../bootstrap-3.3.5/js/popover.js"></script>
<script src="../js/employee.js"></script>"