<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>充值页面</title>
</head>
<body>
	<%@include file="../nav.jsp" %>
	<br />
	
	<form method="post" action="user/topup" class="form_info">
		<div class="col-md-3"></div>
		<div class="col-md-3">银行卡账号:</div>
		<div class="col-md-3">
			<input name="account" type="text" class="form-control login-field"
				id="account" style="width:200px"/>
		</div>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">银行卡密码:</div>
		<div class="col-md-3">
			<input name="password" type="password"
				class="form-control login-field" id="password" style="width:200px"/>
		</div>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">充值金额:</div>
		<div class="col-md-3">
			<input name="topupamount" type="text"
				class="form-control login-field" id="topupamount" style="width:200px"/>
		</div>
		<br/>
		<br/>
		<div class="col-md-8"></div>
		<div class="col-md-3">
			<input type="button" class="btn btn-primary" name="topup" id="topup"
				value="充值"/>
		</div>
	</form>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/user.js"></script>