<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<title>GoodsList</title>
<%@include file="../nav.jsp"%>
<br />
<br />
<div class="main_container">
	<%
		if (session.getAttribute("employee") != null) {
	%>
	<div class="col-md-10"></div>
	<button class="btn btn-primary"
		onclick="javascript:window.location.href='addGoods.jsp'">添加新商品</button>
	<br /> <br />
	<%
		}
	%>


	<div class="main_contianer">

		<s:iterator id="goods" value="#session['goodslist']">
			<div class="goods_info">
				<img />
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
			<div class="modal-body f-14">
				<form>
					<p id="info_id">商品id:</p>
					<%
						if (session.getAttribute("employee") != null) {
					%>
					商品单价:<input type="text" id="info_price" value="test"
						class="form-control login-field" />
					商品信息:<textarea id="info_info" name="info"
						placeholder=""
						class="form-control" rows="7">aaa</textarea>
					<%
						} else {
					%>
					<p id="info_price_user">商品单价: test2</p>
					<p id="info_info_user">商品信息: aaaa</p>
					<%
						}
					%>
				</form>
			</div>
			<div class="modal-footer">
				<%
						if (session.getAttribute("employee") != null) {
				%>
					<button type="button" class="btn btn-primary" name="update" id="update">保存更改信息</button>
				<%		
						} 
				%>
				<button type="button" class="btn btn-primary" data-dismiss="modal">我知道了</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/goods.js"></script>