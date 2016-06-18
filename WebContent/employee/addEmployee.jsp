<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>添加店员</title>
<%@include file="../nav.jsp"%>
<br />
<br />
<br />
<form action="addEmployee" method="post" class="form_info">
	<div class="form-group container has-feedback">
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">店员姓名:</div>
		<div class="col-md-3">
			<input type="text" name="name" id="name" class="form-control" />
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">密码:</div>
		<div class="col-md-3">
			<input type="password" name="passwordOne" id="passwordOne" class="form-control" />
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">确认密码:</div>
		<div class="col-md-3">
			<input type="password" name="passwordTwo" id="passwordTwo" class="form-control" />
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">店员职位:</div>
		<div class="col-md-3">
			<select data-toggle="select" name="authority" id="authority" class="form-control select select-default mrs mbm">
          		<option value="3">分店服务员</option>
          		<option value="2">总店服务员</option>
        	</select>
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">所属分店:</div>
		<div class="col-md-3">
			<select data-toggle="select" name="bid" id="bid" class="form-control select select-default mrs mbm">
				<s:iterator id="branch" value="#session.branchlist">
					<option value="<s:property value="#branch.id"/>"><s:property value="#branch.id"/></option>
          		</s:iterator>
        	</select>
		</div>
		<br /> <br />
		<div class="col-md-3"></div>
		<div class="col-md-2">
			<input type="button" value="添加店员" id="submit" name="submit"
				class="btn btn-primary btn-block" />
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
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/employee.js"></script>