<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>职工登录</title>
<%@include file="../nav.jsp" %>
<br/>
<br/>
	<form method="post" action="<%=request.getContextPath()+"/employee/login"%>" class="login_info">
		<div class="form-group container has-feedback">
			<input name="id" type="text" class="form-control login-field"
				id="id" style="width:200px" placeholder="工号"/>
			<label class="login-field-icon fui-man-16" for="login-name">
			</label>
		</div>
		<div class="form-group container has-feedback">
			<input name="password" type="password" class="form-control login-field"
				id="password" style="width:200px" placeholder="密码"/>
		</div>
		<input type="submit" class="btn btn-primary btn-lg" name="submit"
			value="登录" style="width: 200px; margin-left: 15px"/>
	</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/employee.js"></script>"