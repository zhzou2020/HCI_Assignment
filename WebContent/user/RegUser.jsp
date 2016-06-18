<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<title>register user</title>
<%@include file="../nav.jsp" %>
<br/>
<br/>

<p>欢迎加入dessert house，
<jsp:getProperty name="user" property="name" />！
<s:if test="#session.user.state==0">
	<a href="<%=request.getContextPath()+"/user/top_up.jsp" %>">激活您的会员资格</a>
</s:if>
</p>
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>