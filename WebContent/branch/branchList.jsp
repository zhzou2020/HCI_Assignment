<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>门店信息</title>
<%@include file="../nav.jsp" %>
<div class="form_info">
	<%
		if (session.getAttribute("employee") != null) {
	%>
	<s:if test="#session.employee.authority == 0">
		<div class="col-md-10">
		</div>
		<button class="btn btn-primary"
			onclick="javascript:window.location.href='addBranch.jsp'">添加新分店</button>
		<br/>
	<br/>
	</s:if>
	<%
		}
	%>
	<table class="table table-responsive table-hover">
		<tr>
			<th>店面id</th>
			<th>店名</th>
			<th>地址</th>
			<th>详情查看</th>
		</tr>
		<s:iterator id="branch" value="#session['branchlist']">
			<tr>
				<td><s:property value="#branch['id']" /></td>
				<td><s:property value="#branch['name']" /></td>
				<td><s:property value="#branch['address']" /></td>
				<td><button class="btn btn-primary b_info" data-toggle="modal" data-id="<s:property value="#branch['id']"/>" data-target="#myModal">
 					 查看详情
				</button></td>
			</tr>
		</s:iterator>
	</table>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="id">Modal title</h4>
        <h4 class="modal-title" id="nameTitle">Name</h4>
      </div>
      <div class="modal-body">
        <form method="post" action="changeGoodsInfo">
        	店铺id:<input type="text" id="bid" name="bid" value="test" class="form-control login-field" disabled="disabled"/>
			店名:<input type="text" id="name" name="name" value="test" class="form-control login-field"
			<%
				if(session.getAttribute("employee") == null){
			%>
			disabled="disabled"
			<%} else{ %>
			<s:if test="#session.employee.authority != 0 && #session.employee.authority != 1">disabled="disabled"</s:if>
			<%} %>
			/>
			地址:<input type="text" id="address" name="address" value="test" class="form-control login-field"
			<%
				if(session.getAttribute("employee") == null){
			%>
			disabled="disabled"
			<%} else{ %>
			<s:if test="#session.employee.authority != 0 && #session.employee.authority != 1">disabled="disabled"</s:if>
			<%} %>
			/>
			基本信息:<textarea cols="10" class="form-control" name="info" id="info" 
			<%
				if(session.getAttribute("employee") == null){
			%>
			disabled="disabled"
			<%} else{ %>
			<s:if test="#session.employee.authority != 0 && #session.employee.authority != 1">disabled="disabled"</s:if>
			<%} %>
			>test</textarea>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
        <%
			if(session.getAttribute("employee") != null){
		%>
			<s:if test="#session.employee.authority == 0 || #session.employee.authority == 1">
				<button type="button" class="btn btn-primary" name="update" id="update">保存更改信息</button>
				<button type="button" class="btn btn-danger" name="delete" id="delete">删除店面</button>
			</s:if>
		<%} %>
      </div>
    </div>
  </div>
</div>

</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/branch.js"></script>