<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>销售</title>
<%@include file="../nav.jsp"%>

<div class="personal-info">
    <h1>商品销售</h1>
    <hr align=center width=210 size="3"/>
</div>

<div class = "left-list-admin">
    <ul>
        <li><a href="<%=request.getContextPath()+"/sale/saleCash"%>">现金销售</a></li>
        <li><a href="<%=request.getContextPath()+"/sale/saleCard"%>">有卡销售</a></li>
    </ul>
</div>

<form action="addgoods" method="post" class="sale_add">
	<div class="col-md-1"></div>
	<div class="col-md-2">商品id:</div>
	<div class="col-md-4">
		<select data-toggle="select" name="gid" id="gid"
			class="form-control select select-default mrs mbm">
			<s:iterator id="stock" value="#session['stocklist']">
				<option value="<s:property value="#stock['gid']"/>"><s:property
						value="#stock['gid']" /></option>
			</s:iterator>
		</select>
	</div>
	<br /> <br />
	<div class="col-md-1"></div>
	<div class="col-md-2">商品名称:</div>
	<div class="col-md-4" id="gname">
		<s:property value="#session.stockname" />
	</div>
	<br /> <br />
	<div class="col-md-1"></div>
	<div class="col-md-2">商品库存:</div>
	<div class="col-md-4" id="stock">
		<s:property value="#session.stocknumber" />
	</div>
	<br /> <br />
	<div class="col-md-1"></div>
	<div class="col-md-2">销售数量:</div>
	<div class="col-md-4">
		<input type="text" value="1" id="sale_number" class="form-control" />
	</div>

	<br /> <br />
	<div class="col-md-4"></div>
	<div class="col-md-3">
		<input type="button" value="添加商品" id="addSaleItem"
			class="btn btn-primary btn-block" />
	</div>
	
	<br/><br/>
	<div class="col-md-2">
	</div>
	<div class="col-md-3">总价：</div>
	<div class="col-md-3" id="totalamount"><s:property value="#session.amount"/></div>
	
	<br/>
	<br/>
	<div class="col-md-4"></div>
	<div class="col-md-3">
		<input type="button" value="结账" id="settle" data-toggle="modal" data-target="#myModal"
			class="btn btn-primary btn-block" />
	</div>
	
</form>
<table class="table table-responsive table-hover sale_table" id="saleitems">
	<tr>
		<th>商品id</th>
		<th>商品单价</th>
		<th>商品数量</th>
		<%
			if (session.getAttribute("employee") != null) {
		%>
		<s:if test="#session.employee.authority == 3">
			<th>删除商品</th>
		</s:if>
		<%
			}
		%>
	</tr>
	<s:iterator id="item" value="#session['saleitemlist']">
		<tr>
			<td><s:property value="#item['gid']" /></td>
			<td><s:property value="#item['item_price']" /></td>
			<td><s:property value="#item['number']" /></td>
			<%
				if (session.getAttribute("employee") != null) {
			%>
			<s:if test="#session.employee.authority == 3">
				<td><button class="btn btn-primary d_info"
						data-id="<s:property value="#item['gid']"/>">删除条目</button></td>
			</s:if>
			<%
				}
			%>
		</tr>
	</s:iterator>
</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">销售信息</h4>
        <p class="modal-title">销售分店id：<s:property value="#session.employee.bid"/></p>
      </div>
      <div class="modal-body">
        <form method="post" action="changeGoodsInfo">
        	需付款：<div id="amount"><s:property value="#session.amount"/></div>
			<br/>
			实付款：<input type="text" id="pay_amount" name="pay_amount" value="0" class="form-control login-field"/>
			<br/>
			需找零：<div id="return_change">0</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
        <%
			if(session.getAttribute("employee") != null){
		%>
			<s:if test="#session.employee.authority == 3 || #session.employee.authority == 1">
				<button type="button" class="btn btn-primary" name="cash_pay" id="cash_pay">结账</button>
			</s:if>
		<%} %>
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