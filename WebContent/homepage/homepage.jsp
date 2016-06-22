<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../bootstrap/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../flatui/dist/css/flat-ui.css" rel="stylesheet">
<link href="../css/min.css" rel="stylesheet">
</head>
<body>
	<%
		if (session.getAttribute("user") != null) {
	%>
	<jsp:useBean id="user" type="edu.nju.desserthouse.model.User"
		scope="session"></jsp:useBean>
	<%
		} else if (session.getAttribute("employee") != null) {
	%>
	<jsp:useBean id="employee" type="edu.nju.desserthouse.model.Employee"
		scope="session"></jsp:useBean>
	<%
		}
	%>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<form class="navbar-form navbar-right" action="#" role="search">
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-btn">
					<button type="button" class="btn">
						<span class="fui-new"></span>
					</button>
				</span> <input class="form-control" id="navbarInput-01" type="search"
					placeholder="Search"> <span class="input-group-btn">
					<button type="submit" class="btn">
						<span class="fui-search"></span>
					</button>
				</span>
			</div>
		</div>

		<%
			if (session.getAttribute("user") != null) {
		%>
		<div class="btn-group">
			<button class="btn btn-default dropdown-toggle" type="button"
				data-toggle="dropdown" id="nav-name">
				<jsp:getProperty name="user" property="name" />
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="<%=request.getContextPath() + "/user/Profile"%>">个人设置</a></li>
				<li><a
					href="<%=request.getContextPath() + "/user/passwordChange.jsp"%>">密码修改</a></li>
				<li><a href="<%=request.getContextPath() + "/sale/saleList"%>">我的订单</a></li>
				<li><a href="<%=request.getContextPath() + "/user/payRecord"%>">充值记录</a></li>
				<li><a
					href="<%=request.getContextPath() + "/user/pointChange.jsp"%>">积分兑换</a></li>
				<li><a
					href="<%=request.getContextPath() + "/user/top_up.jsp"%>">充值</a>
				<li class="divider"></li>
				<li><a href="<%=request.getContextPath() + "/user/logout"%>">退出登录</a></li>
			</ul>
		</div>
		<%
			} else if (session.getAttribute("employee") != null) {
		%>
		<div class="btn-group">
			<button class="btn btn-default dropdown-toggle" type="button"
				data-toggle="dropdown">
				<jsp:getProperty name="employee" property="name" />
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a
					href="<%=request.getContextPath() + "/user/memberInfo.jsp"%>">个人设置</a></li>
				<li><a
					href="<%=request.getContextPath() + "/employee/password.jsp"%>">密码修改</a></li>
				<li class="divider"></li>
				<li><a
					href="<%=request.getContextPath() + "/employee/logout"%>">退出登录</a></li>
			</ul>
		</div>
		<%
			} else {
		%>
		<div class="btn-group">
			<button class="btn btn-primary col-xs-12" type="button"
				data-toggle="dropdown" onclick="javascript:window.location.href='<%=request.getContextPath() + "/user/Login.jsp"%>';">
				&nbsp;&nbsp;登录&nbsp;&nbsp;
			</button>
		</div>
		<div class="btn-group">
			<button class="btn btn-default col-xs-12" type="button"
				data-toggle="dropdown" onclick="javascript:window.location.href='<%=request.getContextPath() + "/user/register.jsp"%>';">
				&nbsp;&nbsp;注册&nbsp;&nbsp;
			</button>
		</div>
		
		<%
			}
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</form>
	</nav>
	
	<div id="myCarousel" class="carousel slide site-wrapper" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img class="first-slide" src="<%=request.getContextPath() %>/resource/homepage-image.jpg" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                    	<h1>测试巨幕heading1</h1>
                    	<p>测试巨幕p1</p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="<%=request.getContextPath() %>/resource/homepage-image.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
						<h1>测试巨幕heading2</h1>
                    	<p>测试巨幕p2</p>
                    </div>
                </div>
            </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->
	
</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>