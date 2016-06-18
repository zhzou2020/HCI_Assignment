<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>甜品预订</title>
<%@include file="../nav.jsp"%>
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
		<s:if test="#session.user.state == 1">
			<input type="button" value="添加商品" id="addSaleItem_pre"
				class="btn btn-primary btn-block" />
		</s:if>
		<s:else>
			<input type="button" value="请先激活账号" class="btn btn-primary btn-block"/>
		</s:else>
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
		<input type="button" value="结账" id="pre_settle" data-toggle="modal" data-target="#myModal"
			class="btn btn-primary btn-block" />
	</div>
	
</form>
<table class="table table-responsive table-hover sale_table" id="saleitems">
	<tr>
		<th>商品id</th>
		<th>商品单价</th>
		<th>商品数量</th>
		<%
			if (session.getAttribute("user") != null) {
		%>
		<s:if test="#session.user.state == 1">
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
				if (session.getAttribute("user") != null) {
			%>
			<s:if test="#session.user.state == 1">
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
        <p class="modal-title">销售分店id：</p>
      </div>
      <div class="modal-body">
        <form method="post" action="#">
        	需付款：<div id="amount"><s:property value="#session.amount"/></div>
			<br/>
			会员id：<div id="memberid"><s:property value="#session.user.serialid"/></div>
			<br/>
			会员姓名：<div id="membername"><s:property value="#session.user.name"/></div>
			<br/>
			会员折扣：<div id="discount">
			<s:if test="#session.user.rank == 0">十折</s:if>
			<s:elseif test="#session.user.rank == 1">九五折</s:elseif>
			<s:elseif test="#session.user.rank == 2">九折</s:elseif>
			<s:elseif test="#session.user.rank == 3">八五折</s:elseif>
			<s:elseif test="#session.user.rank == 4">八折</s:elseif>
			<s:else>七五折</s:else>
			</div>
			<input type="hidden" value="
			<s:if test="#session.user.rank == 0">1.0</s:if>
			<s:elseif test="#session.user.rank == 1">0.95</s:elseif>
			<s:elseif test="#session.user.rank == 2">0.9</s:elseif>
			<s:elseif test="#session.user.rank == 3">0.85</s:elseif>
			<s:elseif test="#session.user.rank == 4">0.8</s:elseif>
			<s:else>0.75</s:else>" id ="discountrate"/>
			<br/>
			扣款金额：<div id="discount_pay_amount"></div>
			<br/>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
        <%
			if(session.getAttribute("user") != null){
		%>
			<s:if test="#session.user.state == 1">
				<button type="button" class="btn btn-primary" name="preorder" id="preorder">预订</button>
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