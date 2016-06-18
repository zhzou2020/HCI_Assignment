<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>计划列表</title>
<%@include file="../nav.jsp"%>

<br />
<br />

	<table class="table table-responsive table-hover table_position" id="plans">
		<tr>
			<th>计划id</th>
			<th>店面id</th>
			<th>职员id</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>状态</th>
			<%
				if (session.getAttribute("employee") != null) {
			%>
			<s:if test="#session.employee.authority == 1 || #session.employee.authority == 2">
			<th>查看详情</th>
			</s:if>
			<s:if test="#session.employee.authority == 2">
			<th>修改计划</th>
			</s:if>
			<%} %>
		</tr>
		<s:iterator id="plan" value="#session['planlist']">
			<tr>
				<td><s:property value="#plan['id']" /></td>
				<td><s:property value="#plan['bid']" /></td>
				<td><s:property value="#plan['eid']" /></td>
				<td><s:property value="#plan['start_date']" /></td>
				<td><s:property value="#plan['end_date']" /></td>
				<td><s:if test="#plan['state'] == 0">未审批</s:if><s:if test="#plan['state'] == 1">审批通过</s:if><s:if test="#plan['state'] == -1">审批不通过</s:if><s:if test="#plan['state'] == -2">已取消</s:if>
				<%
					if (session.getAttribute("employee") != null) {
				%>
				<s:if test="#session.employee.authority == 1 || #session.employee.authority == 2">
					<td>
						<button class="btn btn-primary p_info" data-toggle="modal" data-id="<s:property value="#plan['id']"/>" data-target="#myModal">查看详情</button>
					</td>
				</s:if>
				<s:if test="#session.employee.authority == 2 and #plan['state'] == -1">
					<td>
						<button class="btn btn-primary modify_plan" data-id="<s:property value="#plan['id']"/>" onclick="javascript:window.location.href='modifyPlan?id=<s:property value="#plan['id']"/>';">修改计划</button>
					</td>
				</s:if>
				<%
					}
				%>
			</tr>
		</s:iterator>
	</table>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="title">本计划信息</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="col-md-4">
        				计划id:
        			</div>
					<div class="col-md-3">
						<input type="text" id="p_id" name="p_id" value="" class="form-control login-field" disabled="disabled"/>
        			</div>
        			<br/>
        			<br/>
					<div class="col-md-4">
        				店铺id:
        			</div>
					<div class="col-md-3">
						<input type="text" id="b_id" name="b_id" value="" class="form-control login-field" disabled="disabled"/>
        			</div>
        			<br/>
        			<br/>
        			<div class="col-md-4">
        				制定人id:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="e_id" name="e_id" value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
        			<br/>
        			<div class="col-md-4">
        				计划开始时间:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="start_date_modal"value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
					<br/>
					<div class="col-md-4">
        				计划结束时间:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="end_date_modal" value="" class="form-control login-field" disabled="disabled"/>
					</div>
				</form>
				<br/>
				<table class="table table-responsive table-hover" id="planitems">
					<tr>
						<th>商品id</th>
						<th>商品单价</th>
						<th>商品数量</th>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
				<%
					if (session.getAttribute("employee") != null) {
				%>
				<s:if
					test="#session.employee.authority == 1">
					<button type="button" class="btn btn-primary" name="passPlan"
						id="passPlan">审批通过</button>
					<button type="button" class="btn btn-danger" name="rejectPlan"
						id="rejectPlan">审批不通过</button>
				</s:if>
				<%
					}
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/plan.js"></script>
<script src="../js/jquery-ui.js"></script>