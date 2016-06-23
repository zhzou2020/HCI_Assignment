<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<title>GoodsList</title>
<%@include file="../nav.jsp"%>
<br />
<br />
<div class="main_container">
	<p class="position">首页 > 甜品介绍</p>
	<%
		if (session.getAttribute("employee") != null) {
	%>
	<button class="btn btn-primary"
		onclick="javascript:window.location.href='addGoods.jsp'" id="add_new_item">添加新商品</button>
	<br /> <br />
	<%
		}
	%>
	
	<div id="path" style="display:none"><%=request.getContextPath() %></div>
		<s:iterator id="goods" value="#session['goodslist']">
			<div class="goods_info">
				<img src="<%=request.getContextPath() %><s:property value="#goods['img']"/>"/>
				<div class="id">
					<s:property value="#goods['id']" />
				</div>
				<div class="goods_name">
					<s:property value="#goods['name']" />
				</div>
				<div class="price">
					单价：
					<s:property value="#goods['price']" />
					元
				</div>
				<button class="btn btn-primary g_info" data-toggle="modal"
					data-id="<s:property value="#goods['id']"/>" data-target="#myModal">查看详情</button>
			</div>
		</s:iterator>
	</div>
</div>

<div class="modal fade notice-modal" id="myModal" tabindex="-1"
	role="dialog" aria-labelledby="modal-title-notice">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">x</button>
				<h4 class="modal-title" id="info_name">测试</h4>
				<span></span>
			</div>
			<div class="modal-body f-14 single_goods_info">
				<img src="#" id="info_img"/>
				<form class="p_container form-inline">
					<p class="col-xs-5 info_label">甜品编号:</p>
					<p id="info_id" class="col-xs-offset-1 col-xs-6 info_tag"></p>
					<br />
					<%
						if (session.getAttribute("employee") != null) {
					%>
					<label class="col-xs-5 info_label" for="info_price">甜品单价:</label>
					<input type="text" id="info_price" value="test"
						class="form-control login-field col-xs-offset-1 col-xs-6" style="width:140px;"/>
					
					<br/>
					<br/>
					<label class="col-xs-5 info_label" for="info_info">甜品信息:</label>
					<textarea id="info_info" name="info"
						placeholder=""
						class="form-control col-xs-offset-1 col-xs-6" rows="7" style="width:140px;">aaa</textarea>
					<%
						} else {
					%>
					<p class="col-xs-5 info_label">甜品单价:</p> 
					<p id="info_price_user" class="col-xs-offset-1 col-xs-6 info_tag"></p>
					<p class="col-xs-5 info_label">甜品信息:</p>
					<p id="info_info_user" class="col-xs-offset-1 col-xs-6 info_tag"></p>
					<%
						}
					%>
				</form>
			</div>
			<div class="modal-footer">
			<%
						if (session.getAttribute("employee") != null) {
				%>
					<button type="button" class="btn btn-primary" name="update" id="update" data-placement="top" data-content="修改成功">保存更改信息</button>
				<%		
						} 
				%>
				<button type="button" class="btn btn-primary" data-dismiss="modal">我知道了</button>
			</div>
		</div>
</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/goods.js"></script>