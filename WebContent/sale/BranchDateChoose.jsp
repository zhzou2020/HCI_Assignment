<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>选取预订分店和日期</title>
<%@include file = "../nav.jsp" %>

<br/>
<br/>

<div class="main_container">
	<p class="position">首页 > 选取预定分店和日期</p>
	<form method="post" action="preorderStart" class="choose_info">
		<div class="col-md-3"></div>
		<div class="col-md-3">预订日期:</div>
		<div class="col-md-3">
			<select data-toggle="select" name="date" id="date" class="form-control select select-default mrs mbm">
				<s:iterator id="date" value="#session.dateset">
					<option value="<s:property value="#date"/>"><s:property value="#date"/></option>
				</s:iterator>
			</select>
		</div>
		<br/>
		<br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">店面id:</div>
		<div class="col-md-3">
			<select data-toggle="select" name="b_id" id="b_id" class="form-control select select-default mrs mbm">
				<s:iterator id="branch" value="#session.branchlist">
					<option value="<s:property value="#branch.id"/>"><s:property value="#branch.id"/></option>
				</s:iterator>
	        </select>
		</div>
		<br /> <br />
		<div class="col-md-3"></div>
		<div class="col-md-3">店名:</div>
		<div class="col-md-3" id="b_name">
		<s:property value="#session['bname1']"/>
		</div>
		<br /> <br />
		<div class="col-md-3"></div>
		<div class="col-md-3">地址:</div>
		<div class="col-md-3" id="b_addr">
		<s:property value="#session['baddr1']"/>
		</div>
		<br/><br/>
		<div class="col-md-3"></div>
		<div class="col-md-3">简介:</div>
		<div class="col-md-3" id="b_info">
		<s:property value="#session['binfo1']"/>
		</div>
		<br /> <br />
		<div class="col-md-8"></div>
		<div class="col-md-4">
			<input type="submit" class="btn btn-primary" name="" id="submit"
				value="确认选择" />
		</div>
	</form>
</div>

</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/sale.js"></script>