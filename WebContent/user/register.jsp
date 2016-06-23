<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
    .info_la{
    	text-align:right;
    }
    #reg_info{
    	margin-left: -90px;
    }
</style>

<title>register page</title>
	<%@include file="../nav.jsp" %>
	<br />
	<br />
<div class="main_container">
	<p class="position">首页 > 注册</p>
	<br/>
	<br/>
	<form action="register" method="post" id="reg_info">
		<div class="form-group container has-feedback">
			<div class="col-md-2 info_la">手机号码:</div>
			<div class="col-md-3">
				<input type="text" name="phoneNo" id="phoneNo" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">姓名:</div>
			<div class="col-md-3">
				<input type="text" name="name" id="name" class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">性别:</div>
			<div class="col-md-3">
				<div class="col-md-5">
				<label class="radio"> 
					<input type="radio" name="gender"
						value="0" id="male" data-toggle="radio" class="custom-radio"
						data-radiocheck-name="radio" required <s:if test="#session.user.gender==0">checked</s:if>> <span class="icons">
						<span class="icon-unchecked">
					</span> <span class="icon-checked">
					</span>
				</span> 男
				</label>
			</div>
			<div class="col-md-2">
				<label class="radio"> 
					<input type="radio" name="gender"
						value="1" id="female" data-toggle="radio" class="custom-radio"
						data-radiocheck-name="radio"  <s:if test="#session.user.gender==1">checked</s:if>> 
					<span class="icons">
						<span class="icon-unchecked"> </span> 
						<span class="icon-checked"> <!--::before--></span>
					</span>女
				</label>
			</div>
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">密码:</div>
			<div class="col-md-3">
				<input type="password" name="passwordOne" id="passwordOne"
					class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">确认密码:</div>
			<div class="col-md-3">
				<input type="password" name="passwordTwo" id="passwordTwo"
					class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">生日:</div>
			<div class="col-md-3">
				<input type="date" name="birthday" id="birthday"
					class="form-control" />
			</div>
			<br /> <br />
			<div class="col-md-2 info_la">城市:</div>
			<div class="col-md-3">
				<select data-toggle="select" name="city" id="city" class="form-control select select-default mrs mbm">
					<option value="南京">南京</option>
					<option value="嘉兴">嘉兴</option>
					<option value="上海">上海</option>
					<option value="北京">北京</option>
					<option value="广州">广州</option>
					<option value="成都">成都</option>
        		</select>
			</div>
			<br /> <br />
			<%
				if(session.getAttribute("errormessage") != null){
			%>
			<div class="col-md-3"></div>
			<div><s:property value="#session.errormessage"/></div>
			<%
				} 
			%>
			<br/>
			<br/>
			<div class="col-md-1" style="width:100px;"></div>
			<div class="col-md-2">
				<s:reset value="重置" class="btn btn-default btn-lg btn-block" />
			</div>
			<div class="col-md-2">
				<s:submit value="注册" class="btn btn-primary btn-lg btn-block" />
			</div>
		</div>
	</form>
</div>

</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>