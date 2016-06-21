<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>积分兑换</title>

<%@include file="../nav.jsp" %>

<br/>
<br/>
<div class="main_container">
	<p class="position">首页 > 积分兑换</p>
</div>

<form method="post" action="user/pointChange" class="form_info2">
		<div class="col-md-3"></div>
		<div class="col-md-3">兑换积分数:</div>
		<div class="col-md-3">
			<input name="changepoint" type="text" class="form-control login-field"
				id="changepoint" style="width:200px"/>
			<div id="errormessage">   </div>
		</div>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">可兑换现金:</div>
		<div class="col-md-3">
			<div id="point-to-money"></div>
		</div>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">现有积分数：</div>
		<div class="col-md-3">
		<p id="showPoint"><s:property value="#session.user.point"/></p>
		</div>
		<br/>
		<div class="col-md-8"></div>
		<div class="col-md-4">
			<input type="button" class="btn btn-primary" name="pointChange" id="pointChange"
				value="&nbsp;&nbsp;&nbsp;兑换&nbsp;&nbsp;&nbsp;"/>
		</div>
	</form>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/user.js"></script>