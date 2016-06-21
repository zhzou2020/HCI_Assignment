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
				data-toggle="dropdown">
				&nbsp;&nbsp;登录&nbsp;&nbsp;
			</button>
		</div>
		<div class="btn-group">
			<button class="btn btn-default col-xs-12" type="button"
				data-toggle="dropdown">
				&nbsp;&nbsp;注册&nbsp;&nbsp;
			</button>
		</div>
		
		<%
			}
		%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</form>
	</nav>
	<ul class="sidebar">
		<li><a href="#">DESSERT</a></li>
		<hr />
		<li><img/></li>
		
		<li><a href="<%=request.getContextPath() + "/goods/goodslist"%>">甜品介绍</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			if (session.getAttribute("employee") == null) {
				if (session.getAttribute("user") != null) {
		%>
		<li><a
			href="<%=request.getContextPath()
							+ "/sale/branchDateChoose"%>">甜品预订</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
			} else {
		%>
		<s:if test="#session.employee.authority == 0">
			<li><a
				href="<%=request.getContextPath()
							+ "/employee/employeeList"%>">店员管理</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
			</s:if>
		<s:if test="#session.employee.authority == 2">
			<li><a href="<%=request.getContextPath() + "/plan/makePlan"%>">计划制定</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
				<li><a href="<%=request.getContextPath() + "/plan/planList"%>">我的计划</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
			</s:if>
		<s:if test="#session.employee.authority == 1">
			<li><a href="<%=request.getContextPath() + "/plan/planList"%>">计划审批</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
				<li><a
				href="<%=request.getContextPath()
							+ "/employee/Analyse.jsp"%>">月度统计</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
			</s:if>
		<s:if test="#session.employee.authority == 3">
			<li><a href="<%=request.getContextPath() + "/sale/saleCash"%>">商品销售</a></li> &nbsp;&nbsp;&nbsp;&nbsp;	
				<li><a href="<%=request.getContextPath() + "/sale/saleList"%>">我售出的订单</a></li> &nbsp;&nbsp;&nbsp;&nbsp;
				<li><a href="<%=request.getContextPath() + "/user/userList"%>">会员列表</a></li> &nbsp;&nbsp;&nbsp;&nbsp;	
			</s:if>
		<%
			}
		%>
		<li><a
			href="<%=request.getContextPath() + "/branch/branchList"%>">门店信息</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			if (session.getAttribute("user") == null
					&& session.getAttribute("employee") == null) {
		%>
		<li><a href="<%=request.getContextPath() + "/user/Login.jsp"%>">用户登录</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<li><a
			href="<%=request.getContextPath() + "/employee/Login.jsp"%>">员工登录</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<li><a
			href="<%=request.getContextPath() + "/user/register.jsp"%>">会员注册</a></li>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
		%>
	</ul>