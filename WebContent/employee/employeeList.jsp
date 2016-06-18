<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>员工列表</title>
<%@include file="../nav.jsp" %>
<div class="form_info">
	<%
		if (session.getAttribute("employee") != null) {
	%>
	<s:if test="#session.employee.authority == 0">
	<div class="col-md-10">
	</div>
	<button class="btn btn-primary"
		onclick="javascript:window.location.href='addEmployee.jsp'">添加新员工</button>
	<br/>
	<br/>
	</s:if>
	<%
		}
	%>
	<table class="table table-responsive table-hover">
		<tr>
			<th>员工id</th>
			<th>员工姓名</th>
			<th>员工职位</th>
			<th>所属分店编号</th>
			<th>查看分店信息</th>
			<th>查看员工信息</th>
		</tr>
		<s:iterator id="employee" value="#session['employeeList']">
			<tr>
				<td><s:property value="#employee['id']" /></td>
				<td><s:property value="#employee['name']" /></td>
				<%-- <td><s:property value="#employee['authority']" /></td> --%>
				<td>
					<s:if test="#employee['authority']==0">
						管理员
					</s:if>
					<s:elseif test="#employee['authority']==1">
						经理
					</s:elseif>
					<s:elseif test="#employee['authority']==2">
						总店服务员
					</s:elseif>
					<s:else>
						分店服务员
					</s:else>
				</td>
				<td>
					<s:if test="#employee['bid'] == 0">
						总店
					</s:if>
					<s:else>
						<s:property value="#employee['bid']" />
					</s:else>
				</td>
				<td><button class="btn btn-primary"
						onclick="javascript:window.location.href='branchInfo?id=<s:property value="#employee['bid']"/>';">查看详情</button></td>
				<td><button class="btn btn-primary"
						onclick="javascript:window.location.href='employeeInfo?id=<s:property value="#employee['id']"/>';">查看员工信息</button></td>
				
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/employee.js"></script>"