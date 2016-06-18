<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>增加分店</title>
<%@include file="../nav.jsp" %>
<br/>
	<br/>
	<br/>
	<form action="addBranch" method="post" class="form_info">
		<div class="form-group container has-feedback">
			<!-- <div class="col-md-3"></div> -->
			<div class="col-md-2">分店名称:</div>
			<div class="col-md-3">
				<input type="text" name="name" id="name" class="form-control" />
			</div>
			<br /> <br />
			<!-- <div class="col-md-3"></div> -->
			<div class="col-md-2">地址:</div>
			<div class="col-md-3">
				<input type="text" name="address" id="address" class="form-control" />
			</div>
			<br /> <br />
			<!-- <div class="col-md-3"></div> -->
			<div class="col-md-2">分店简介:</div>
			<div class="col-md-3">
				<input type="text" name="info" id="info" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-3"></div>
			<div class="col-md-2">
				<input type="button" value="新增分店" id="add" class="btn btn-primary btn-block" />
			</div>
			<br /> <br />
			<div class="col-md-3"></div>
			<div class="col-md-2">
				<s:reset value="重新输入" class="btn btn-primary btn-block" />
			</div>
		</div>
	</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/branch.js"></script>