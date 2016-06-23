<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>订单列表</title>
<%@include file="../nav.jsp"%>

<br />
<br />
<div class="main_container">
	<p class="position">首页 > 订单查看</p>
	
	<table class="table table-responsive table-hover table_position2" id="sales" style="width:1000px;">
		<tr>
			<th>订单编号</th>
			<th>店面编号</th>
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
				<s:else>
					<td>
						<button class="btn btn-danger cancel_sale" data-id="<s:property value="#sale['id']"/>" disabled="disabled">取消订单</button>
					</td>
				</s:else>
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
					<p class="col-md-3">
        				订单id:
        			</p>
					<p class="col-md-3" id="s_id"></p>
        			<br/>
        			<br/>
					<p class="col-md-3">
        				店铺id:
        			</p>
					<p class="col-md-3" id="b_id"></p>
        			<br/>
        			<br/>
        			<p class="col-md-3">
        				状态:
        			</p>
        			<p class="col-md-6" id="state"></p>
					<br/>
        			<br/>
        			<p class="col-md-3">
        				销售日期:
        			</p>
        			<p class="col-md-6" id="s_date"></p>
					<br/>
					<br/>
					<p class="col-md-3">
        				销售人id:
        			</p>
        			<p class="col-md-6" id="salesman_id"></p>
					<br/>
					<br/>
					<p class="col-md-3">
        				会员id:
        			</p>
        			<p class="col-md-6" id="m_id"></p>
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