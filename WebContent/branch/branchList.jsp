<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>门店信息</title>
<%@include file="../nav.jsp" %>
<br/>
<br/>
<div class="main_container">
	<p class="position">首页 > 门店信息</p>
	<%
		if (session.getAttribute("employee") != null) {
	%>
	<s:if test="#session.employee.authority == 0">
		<button class="btn btn-primary" id="add_new_item" 
			onclick="javascript:window.location.href='addBranch.jsp'">添加新分店</button>
		<br/>
	<br/>
	</s:if>
	<%
		}
	%>
	
	<div class="main_contianer">

		<s:iterator id="branch" value="#session['branchlist']">
			<div class="branch_info">
				<div class="row-fluid">
					<div class="col-md-6">
						<%-- <div class="id">
							<s:property value="#branch['id']" />
						</div> --%>
						<div class="branch_name">
							<s:property value="#branch['name']" />
						</div>
						<div class="branch_address">
							<s:property value="#branch['address']" />
						</div>
					</div>
					<div class="col-md-6">
						<button class="btn btn-primary g_info" data-toggle="modal"
						data-id="<s:property value="#branch['id']"/>" data-target="#myModal">查看详情</button>
					</div>
				</div>
			</div>
<%-- 			<tr>
				<td><s:property value="#branch['id']" /></td>
				<td><s:property value="#branch['name']" /></td>
				<td><s:property value="#branch['address']" /></td>
				<td><button class="btn btn-primary b_info" data-toggle="modal" data-id="<s:property value="#branch['id']"/>" data-target="#myModal">
 					 查看详情
				</button></td>
			</tr> --%>
		</s:iterator>
	</div>
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
      	<p class="col-xs-3 info_label">店铺id:</p>
        <p id="bid" class="col-xs-offset-1 col-xs-8"></p>
      	<s:if test="#session.employee != null && (#session.employee.authority == 0 || #session.employee.authority == 1)">
        <form method="post" action="changeGoodsInfo">
        	<br/><br/>
        	<label class="col-xs-3 info_label" for="name">店名:</label>
			<input type="text" id="name" name="name" value="test" class="form-control login-field col-xs-offset-1 col-xs-8" style="width:340px"/>
			<br/><br/>
			<label class="col-xs-3 info_label" for="address">地址:</label>
			<input type="text" id="address" name="address" value="test" class="form-control login-field col-xs-offset-1 col-xs-8" style="width:340px"/>
			<br/><br/>
			<label class="col-xs-3 info_label" for="info">基本信息:</label>
			<textarea cols="10" class="form-control col-xs-offset-1 col-xs-8" name="info" id="info" style="width:340px">test</textarea>
			<br/><br/>
		</form>
		</s:if>
		<s:else>
			<p class="col-xs-3 info_label">店名:</p>
        	<p id="name" class="col-xs-offset-1 col-xs-8"></p>
        	<br/><br/>
        	<p class="col-xs-3 info_label">地址:</p>
        	<p id="address" class="col-xs-offset-1 col-xs-8"></p>
        	<br/><br/>
      		<p class="col-xs-3 info_label">基本信息:</p>
        	<p id="info" class="col-xs-offset-1 col-xs-8"></p>
        	<br/><br/>
		</s:else>
      </div>
      <div class="modal-footer">
      	<s:if test="#session.employee != null && (#session.employee.authority == 0 || #session.employee.authority == 1)">
        	<button type="button" class="btn btn-default col-xs-offset-4 col-xs-2" data-dismiss="modal">我了解了</button>
			<button type="button" class="btn btn-primary col-xs-offset-1 col-xs-3" name="update" id="update">保存更改信息</button>
			<button type="button" class="btn btn-danger col-xs-offset-1 col-xs-2" name="delete" id="delete">删除店面</button>
		</s:if>
		<s:else>
			<button type="button" class="btn btn-default col-xs-offset-10 col-xs-2" data-dismiss="modal">我了解了</button>
		</s:else>
      </div>
    </div>
  </div>
</div>

</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/branch.js"></script>