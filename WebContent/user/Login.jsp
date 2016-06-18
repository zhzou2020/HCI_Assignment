<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<title>登录</title>
	<%@include file="../nav.jsp"  %>
	<br/>
	<br/>
	<br/>
	<form method="post" action="<%=request.getContextPath()+"/user/login"%>" class="login_info">
		<div class="form-group container has-feedback">
			<p>电话号码：</p>
			<input name="phoneNo" type="text" class="form-control login-field"
				id="phoneNo" style="width:200px"/>
		</div>
		<div class="form-group container has-feedback">
			<p>密码：</p>
			<input name="password" type="password" class="form-control login-field"
				id="password" style="width:200px"/>
		</div>
		<br/>
		<input type="submit" class="btn btn-primary" name="submit"
			value="登录" style="width: 120px; margin-left: 90px"/>
	</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>