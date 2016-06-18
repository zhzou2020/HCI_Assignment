<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>充值记录</title>
<%@include file="../nav.jsp" %>
<body>
	<table class="table table-responsive table-hover form_info">
		<tr>
			<th>充值时间</th>
			<th>充值金额</th>
		</tr>
		<s:iterator id="record" value="#session['payrecord']">
			<tr>
				<td><s:property value="#record['date']" /></td>
				<td><s:property value="#record['amount']" /></td>
				
			</tr>
		</s:iterator>
	</table>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>