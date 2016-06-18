<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Goods</title>
<%@include file="../nav.jsp" %>
	<br/>
	<br/>
	<br/>
	<form action="addgoods" method="post" class="form_info">
		<div class="form-group container has-feedback">
			<div class="col-md-3"></div>
			<div class="col-md-2">商品名称:</div>
			<div class="col-md-3">
				<input type="text" name="name" id="name" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-3"></div>
			<div class="col-md-2">商品价格:</div>
			<div class="col-md-3">
				<input type="text" name="price" id="price" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-3"></div>
			<div class="col-md-2">商品简介:</div>
			<div class="col-md-3">
				<input type="text" name="info" id="info" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-6"></div>
			<div class="col-md-2">
				<input type="button" value="添加商品" id="submit" class="btn btn-primary btn-block" />
			</div>
			<br /> <br />
			<div class="col-md-6"></div>
			<div class="col-md-2">
				<s:reset value="重新输入" class="btn btn-primary btn-block" />
			</div>
		</div>
	</form>
</body>
</html>

<script type="text/javascript" src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/goods.js"></script>