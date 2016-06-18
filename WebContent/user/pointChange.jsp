<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>积分兑换</title>

<%@include file="../nav.jsp" %>
<form method="post" action="user/pointChange" class="form_info">
		<div class="col-md-3"></div>
		<div class="col-md-3">兑换积分数:</div>
		<div class="col-md-3">
			<input name="changepoint" type="text" class="form-control login-field"
				id="changepoint" style="width:200px"/>
			<div id="errormessage">   </div>
		</div>
		<br/>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">可兑换现金:</div>
		<div class="col-md-3">
			<div id="point-to-money"></div>
		</div>
		<br/>
		<br/>
		<div class="col-md-5"></div>
		<p id="showPoint">您现有积分数为<s:property value="#session.user.point"/></p>
		<div class="col-md-4"></div>
		<p>每10积分可以兑换1元卡余额</p>
		<div class="col-md-8"></div>
		<div class="col-md-3">
			<input type="button" class="btn btn-primary" name="pointChange" id="pointChange"
				value="兑换"/>
		</div>
	</form>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/user.js"></script>