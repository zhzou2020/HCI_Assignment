<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>订单列表</title>
<%@include file="../nav.jsp"%>

<br />
<br />
<div class="form_info">
	<table class="table table-responsive table-hover" id="sales">
		<tr>
			<th>订单id</th>
			<th>店面id</th>
			<th>销售日期</th>
			<th>总价</th>
			<th>付款方式</th>
			<th>状态</th>
			<%
				if (session.getAttribute("employee") != null || session.getAttribute("user") != null) {
			%>
			<th>查看详情</th>
			<s:if test="#session.user != null">
			<th>取消订单</th>
			</s:if>
			<%} %>
		</tr>
		<s:iterator id="sale" value="#session['salelist']">
			<tr>
				<td><s:property value="#sale['id']" /></td>
				<td><s:property value="#sale['bid']" /></td>
				<td><s:property value="#sale['date']" /></td>
				<td><s:property value="#sale['amount']" /></td>
				<td><s:property value="#sale['pay_way']" /></td>
				<td><s:if test="#sale['state'] == 0">未完成</s:if><s:if test="#sale['state'] == 1">已完成</s:if><s:if test="#sale['state'] == -1">已取消</s:if>
				<td>
					<button class="btn btn-primary s_info" data-toggle="modal" data-id="<s:property value="#sale['id']"/>" data-target="#myModal">查看详情</button>
				</td>
				<%
					if (session.getAttribute("user") != null) {
				%>
				<s:if test="#sale.state == 0">
					<td>
						<button class="btn btn-danger cancel_sale" data-id="<s:property value="#sale['id']"/>">取消订单</button>
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
				<h4 class="modal-title" id="title">本订单信息</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="col-md-4">
        				订单id:
        			</div>
					<div class="col-md-3">
						<input type="text" id="s_id" name="s_id" value="" class="form-control login-field" disabled="disabled"/>
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
        				状态:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="state" name="state" value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
        			<br/>
        			<div class="col-md-4">
        				销售日期:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="s_date"value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
					<br/>
					<div class="col-md-4">
        				销售人id:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="salesman_id" value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
					<br/>
					<div class="col-md-4">
        				会员id:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="m_id" value="" class="form-control login-field" disabled="disabled"/>
					</div>
				</form>
				<br/>
				<table class="table table-responsive table-hover" id="saleitems">
					<tr>
						<th>商品id</th>
						<th>商品单价</th>
						<th>商品数量</th>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/sale.js"></script>