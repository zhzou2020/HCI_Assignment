<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>员工个人信息</title>
<%@include file="../nav.jsp" %>
<jsp:useBean id="thisEmployee"  type="edu.nju.desserthouse.model.Employee"
			scope="session"></jsp:useBean>
			
<br/>
<br/>

<form method="post" action="changeEmployeeInfo" class="form_info">
	<!-- <div class="col-md-3"></div> -->
	<div class="col-md-3">员工id:</div>
	<div class="col-md-3">
		<input type="text" id="id" name="id"
			value="<jsp:getProperty property="id" name="thisEmployee"/>"
			class="form-control login-field" disabled="disabled" />
	</div>
	<br/>
	<br/>
	<!-- <div class="col-md-3"></div> -->
	<div class="col-md-3">员工姓名:</div>
	<div class="col-md-3">
		<input type="text" id="name" name="name"
			value="<jsp:getProperty property="name" name="thisEmployee"/>"
			class="form-control login-field" disabled="disabled"/>
	</div>
	<br/>
	<br/>
	<!-- <div class="col-md-3"></div> -->
	<div class="col-md-3">员工职位:</div>
	<div class="col-md-3">
		<select data-toggle="select" name="authority" id="authority" class="form-control select select-default mrs mbm">
          	<option value="3" <s:if test="#session.thisEmployee.authority==3">selected="selected"</s:if>>分店服务员</option>
          	<option value="2" <s:if test="#session.thisEmployee.authority==2">selected="selected"</s:if>>总店服务员</option>
          	<option value="1" <s:if test="#session.thisEmployee.authority==1">selected="selected"</s:if>>经理</option>
          	<option value="0" <s:if test="#session.thisEmployee.authority==0">selected="selected"</s:if>>管理员</option>
        </select>
    </div>
    <br/>
	<br/>
	<!-- <div class="col-md-3"></div> -->
	<div class="col-md-3">员工所属分店id:</div>
	<div class="col-md-3">
		<select data-toggle="select" name="bid" id="bid" class="form-control select select-default mrs mbm">
			<s:iterator id="branch" value="#session.branchlist">
				<option value="<s:property value="#branch.id"/>"><s:property value="#branch.id"/></option>
          	</s:iterator>
     	</select>
     </div>
     <br/>
     <br/>
	<%
	if(session.getAttribute("employee")!=null)
	{%>
		<s:if test="#session.employee.authority == 0">
			<br/>
			<div class="col-md-4">
			</div>
			<input type="button" value="确认修改" name="update" id="update" class="btn btn-primary"/>
			<input type="button" value="删除员工" name="delete" id="delete" class="btn btn-danger"/>
		</s:if>
	<%} %>
</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/employee.js"></script>"