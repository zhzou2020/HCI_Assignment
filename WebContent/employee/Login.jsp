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
	
	<form method="post" action="#" class="login_info" onsubmit="return false;">
		<div class="form-group container has-feedback">
			<input name="id" type="text" class="form-control login-field"
				id="id" style="width:270px" placeholder="工号"/>
		</div>
		<div class="form-group container has-feedback">
			<input name="password" type="password" class="form-control login-field"
				id="password" style="width:270px" placeholder="密码"/>
		</div>
		<p id="login_alert">账号或密码错误!</p>
		<input type="submit" class="btn btn-primary" name="submit" id="login"
			value="登录" style="width: 270px; margin-left: 15px"/>
			
			<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="top" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
  Popover on 顶部
</button>
<a href="#" data-toggle="popover" title="Example popover">
   请悬停在我的上面
</a>
<script>$(function () 
      { $("[data-toggle='popover']").popover();
      });
   </script>
	</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap-3.3.5/js/tooltip.js"></script>
<script src="../bootstrap-3.3.5/js/popover.js"></script>
<script src="../js/employee.js"></script>"