<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>会员列表</title>
<%@include file="../nav.jsp" %>
<div class="form_info">
	<table class="table table-responsive table-hover">
		<tr>
			<th>会员id</th>
			<th>会员姓名</th>
			<th>会员地址</th>
			<th>会员状态</th>
			<th>详情查看</th>
		</tr>
		<s:iterator id="user" value="#session['userlist']">
			<tr>
				<td><s:property value="#user['id']" /></td>
				<td><s:property value="#user['name']" /></td>
				<td><s:property value="#user['city']" /></td>
				<td><s:if test="#user['state'] == 0">未激活</s:if><s:elseif test="#user['state'] == 1">已激活</s:elseif><s:elseif test="#user['state'] == 2">暂停中</s:elseif><s:else>已停止</s:else></td>
				<td><button class="btn btn-primary" data-id="<s:property value="#user['id']"/>" onclick="javascript:window.location.href='userInfo?id=<s:property value="#user['id']"/>';">
 					 查看详情
				</button></td>
			</tr>
		</s:iterator>
	</table>
</div>


</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="../js/user.js"></script>