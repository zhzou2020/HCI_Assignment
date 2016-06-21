<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			
<title>Profile</title>
<%@include file="../nav.jsp"  %>
<br/>
<br/>
<jsp:useBean id="thisuser" type="edu.nju.desserthouse.model.User"
			scope="session"></jsp:useBean>
			
<div class="main_container">
	<p class="position">首页 > 账户管理 > 个人设置</p>
	<form class="form_info">
		<!-- <div class="col-md-3"></div> -->
		<div class="col-xs-4 info_label">会员卡id:</div>
		<p class="col-xs-8" id="serialid">
			<jsp:getProperty property="serialid" name="thisuser"/>
		</p>
		<input type="hidden" id="mid" name="mid"
			value="<jsp:getProperty property="id" name="thisuser"/>"/>
		<br/>
		<br/>
		<input type="hidden" id="id" name="id" value="<jsp:getProperty property="id" name="thisuser"/>" name="id" id="id"/>
		<p class="col-xs-4 info_label">电话号码:</p>
		<p class="col-xs-8"><jsp:getProperty property="phoneNo" name="thisuser"/></p>
		<br/>
		<br/>
		<s:if test="#session.user != null">
			<label class="col-xs-4 info_label" for="name">姓名:</label>
			<div class="col-xs-5">
				<input type="text" id="name" name="name"
					value="<jsp:getProperty property="name" name="thisuser"/>"
					class="form-control login-field"/>
			</div>
		</s:if>
		<s:else>
			<p class="col-xs-4 info_label">姓名</p>
			<p class="col-xs-8"><jsp:getProperty property="name" name="thisuser"/></p>
		</s:else>
		<br/>
		<br/>
		<label class="col-md-4 info_label" for="gender">姓别:</label>
		<div class="col-md-8">
			<div class="col-md-3">
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
			<div class="col-md-3">
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
		<label class="col-xs-4 info_label">出生日期:</label>
		<s:if test="#session.user != null">
			<div class="col-xs-5">
				<input type="date" id="birthday" name="birthday"
					value="<jsp:getProperty property="birthdayRegular" name="thisuser"/>"
					class="form-control"/>
			</div>
		</s:if>
		<s:else>
			<p class="col-xs-8"><jsp:getProperty property="birthdayRegular" name="thisuser"/></p>
		</s:else>
		<br/>
		<br/>
		<p class="col-xs-4 info_label">余额:</p>
		<p class="col-xs-8"><jsp:getProperty property="balanceRegular" name="thisuser"/></p>
		<br/>
		<br/>
		<p class="col-xs-4 info_label">积分:</p>
		<p class="col-xs-8"><jsp:getProperty property="point" name="thisuser"/></p>
		<br/>
		<br/>
		<p class="col-xs-4 info_label">会员卡状态:</p>
		<p class="col-xs-8"><s:if test="#session.thisuser.state==0">未激活</s:if><s:elseif test="#session.thisuser.state==1">已激活</s:elseif><s:elseif test="#session.thisuser.state==2">暂止中，支付后可激活</s:elseif><s:else>已停止，无法恢复</s:else></p>
		<br/>
		<br/>
		<p class="col-xs-4 info_label">会员级别:</p>
		<p class="col-xs-8"><jsp:getProperty property="rank" name="thisuser"/></p>
		<br/>
		<br/>
		<s:if test="#session.thisuser.state!=-1">
			<div class="col-xs-offset-2 col-xs-4">
				<input type="button" name="stop" id="stop" value="停止会员资格" class="btn btn-danger"/>
			</div>
		</s:if>
		<s:if test="#session.user != null">
		<div class="col-xs-5">
			<input type="button" name="submit" id="submit" value="修改资料" class="btn btn-primary" style="width:120px;'"/>
		</div>
		</s:if>
	</form>
	<s:if test="#session.employee != null">
			<div class="col-md-6"></div>
			<div class="col-md-2">
				<button name="saleRecord" id="saleRecord" value="" class="btn btn-primary" onclick="javascript:window.location.href='saleRecord?id=<s:property value="#session.thisuser['id']"/>';">订单记录</button>
			</div>
	</s:if>
</div>
</body>
</html>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/user.js"></script>
<script src="../bootstrap/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>