<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>Goods Info</title>
<%@include file="../nav.jsp" %>
<jsp:useBean id="thisGoods"  type="edu.nju.desserthouse.model.Goods"
			scope="session"></jsp:useBean>
			
<br/>
<br/>

<form method="post" action="changeGoodsInfo" class="goods_info">
	商品id:<input type="text" id="id" name="id"
			value="<jsp:getProperty property="id" name="thisGoods"/>"
			class="form-control login-field" disabled="disabled" />
	商品名称:<input type="text" id="name" name="name"
			value="<jsp:getProperty property="name" name="thisGoods"/>"
			class="form-control login-field" 
			<%
			if(session.getAttribute("employee")==null)
			{%>
				disabled="disabled"
			<%} %> />
	商品单价:<input type="text" id="price" name="price"
			value="<jsp:getProperty property="price" name="thisGoods"/>"
			class="form-control login-field"
			<%
			if(session.getAttribute("employee")==null)
			{%>
				disabled="disabled"
			<%} %> />
	商品信息:<textarea id="info" name="info"
			placeholder="<jsp:getProperty property="info" name="thisGoods"/>"
			class="form-control"
			<%
			if(session.getAttribute("employee")==null)
			{%>
				disabled="disabled"
			<%} %> rows="7"><jsp:getProperty property="info" name="thisGoods"/></textarea>
	<%
	if(session.getAttribute("employee")!=null)
	{%>
		<br/>
		<div class="col-md-10">
		</div>
		<input type="button" value="确认修改" name="update" id="update" class="btn btn-primary"/>
	<%} else if(session.getAttribute("user")!= null){%>
		<br/>
		<div class="col-md-10">
		</div>
		<input type="button" value="购买商品" name="buy" id="buy" class="btn btn-primary"/>
	<%} %>
</form>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/goods.js"></script>"