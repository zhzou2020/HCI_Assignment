<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			
<title>Profile</title>
<%@include file="../nav.jsp"  %>
<br/>
<br/>
<jsp:useBean id="thisuser" type="edu.nju.desserthouse.model.User"
			scope="session"></jsp:useBean>
			

	<form class="form_info">
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">会员卡id:</div>
		<div class="col-md-5">
			<input type="text" id="serialid" name="serialid"
				value="<jsp:getProperty property="serialid" name="thisuser"/>"
				class="form-control login-field" disabled="disabled" />
		</div>
		<input type="hidden" id="mid" name="mid"
			value="<jsp:getProperty property="id" name="thisuser"/>"/>
		<br/>
		<br/>
		<input type="hidden" id="id" name="id" value="<jsp:getProperty property="id" name="thisuser"/>" name="id" id="id"/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">电话号码:</div>
		<div class="col-md-5">
		<input type="text" id="phoneNo" name="phoneNo" id="phoneNo"
			value="<jsp:getProperty property="phoneNo" name="thisuser"/>"
			class="form-control login-field" disabled="disabled" />
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">姓名:</div>
		<div class="col-md-5">
			<input type="text" id="name" name="name"
				value="<jsp:getProperty property="name" name="thisuser"/>"
				class="form-control login-field" <s:if test="#session.user==null">disabled="disabled"</s:if>/>
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">姓别:</div>
		<div class="col-md-6">
			<div class="col-md-2">
				<label class="radio"> 
					<input type="radio" name="gender"
						value="0" id="male" data-toggle="radio" class="custom-radio"
						data-radiocheck-name="radio" required <s:if test="#session.thisuser.gender==0">checked</s:if> <s:if test="#session.user==null">disabled="disabled"</s:if>> <span class="icons">
						<span class="icon-unchecked">
					</span> <span class="icon-checked">
					</span>
				</span> 男
				</label>
			</div>
			<div class="col-md-2">
				<label class="radio"> 
					<input type="radio" name="gender"
						value="1" id="female" data-toggle="radio" class="custom-radio"
						data-radiocheck-name="radio"  <s:if test="#session.thisuser.gender==1">checked</s:if> <s:if test="#session.user==null">disabled="disabled"</s:if>> 
					<span class="icons">
						<span class="icon-unchecked"> </span> 
						<span class="icon-checked"> <!--::before--></span>
					</span>女
				</label>
			</div>
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">出生日期:</div>
		<div class="col-md-5">
			<input type="date" id="birthday" name="birthday"
				value="<jsp:getProperty property="birthday" name="thisuser"/>"
				class="form-control" <s:if test="#session.user==null">disabled="disabled"</s:if>/>
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">余额:</div>
		<div class="col-md-5">
			<input type="text" id="balance" name="balance"
				value="<jsp:getProperty property="balance" name="thisuser"/>"
				class="form-control" disabled="disabled" />
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">积分:</div>
		<div class="col-md-5">
			<input type="text" id="point" name="point"
				value="<jsp:getProperty property="point" name="thisuser"/>"
				class="form-control" disabled="disabled" />
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">会员卡状态:</div>
		<div class="col-md-5">
			<input type="text" id="state" name="state"
				value="<s:if test="#session.thisuser.state==0">未激活</s:if><s:elseif test="#session.thisuser.state==1">已激活</s:elseif><s:elseif test="#session.thisuser.state==2">暂止中，支付后可激活</s:elseif><s:else>已停止，无法恢复</s:else>"
				class="form-control" disabled="disabled" />
		</div>
		<br/>
		<br/>
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-3">会员级别:</div>
		<div class="col-md-5">
			<input type="text" id="rank" name="rank"
				value="<jsp:getProperty property="rank" name="thisuser"/>"
				class="form-control" disabled="disabled" />
		</div>
		<br/>
		<br/>
		<s:if test="#session.user != null">
		<div class="col-md-5"></div>
		<div class="col-md-2">
			<input type="button" name="submit" id="submit" value="修改资料" class="btn btn-primary"/>
		</div>
		<s:if test="#session.thisuser.state!=-1">
			<div class="col-md-3">
				<input type="button" name="stop" id="stop" value="停止会员资格" class="btn btn-danger"/>
			</div>
		</s:if>
		</s:if>
	</form>
	<s:if test="#session.employee != null">
			<div class="col-md-6"></div>
			<div class="col-md-2">
				<button name="saleRecord" id="saleRecord" value="" class="btn btn-primary" onclick="javascript:window.location.href='saleRecord?id=<s:property value="#session.thisuser['id']"/>';">订单记录</button>
			</div>
	</s:if>
</body>
</html>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/user.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>