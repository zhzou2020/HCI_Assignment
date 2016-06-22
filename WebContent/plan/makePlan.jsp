<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>制定计划</title>
<%@include file="../nav.jsp"%>
<br/>
<br/>
<div class="main_container">
	<p class="position">首页 > 计划制定</p>
	<div class="form_info">
		<div class="alert alert-success form_alert" role="alert" id="success">请添加商品</div>
		<div class="alert alert-danger form_alert" role="alert" id="fail" style="display:none;">添加失败</div>
		<br/>
		<form action="" method="post" >
			<div class="form-group has-feedback">
				<div class="col-md-3">商品id:</div>
				<div class="col-md-5">
					<select data-toggle="select" name="gid" id="gid"
						class="form-control select select-primary mrs mbm">
						<s:iterator id="goods" value="#session['goodslist']">
							<option value="<s:property value="#goods['id']"/>"><s:property
									value="#goods['id']" /></option>
						</s:iterator>
					</select>
				</div>
				<br />
				<br />
				<div class="col-md-3">商品名称:</div>
				<p class="col-md-8" id="gname"><s:property value="#session['gname1']"/></p>
				<br /> <br />
				<div class="col-md-3">商品价格:</div>
				<div class="col-md-6">
					<input type="text" name="price" id="price" class="form-control" value="<s:property value="#session['gprice1']"/>"/>
				</div>
				<br /> <br />
				<div class="col-md-3">商品数量:</div>
				<div class="col-md-6">
					<input type="text" id="number" name="number" value="10"
						class="form-control">
				</div>
				<br /> <br /><br/>
				<div class="col-md-offset-1 col-md-4">
					<s:reset value="重新输入" class="btn btn-default btn-block" />
				</div>
				<div class="col-md-4">
					<input type="button" value="添加商品" id="addItem"
						class="btn btn-primary btn-block" />
				</div>
				<div class="col-md-3"></div>
				<br /> <br />
				
				
			</div>
		</form>
		
		<div class="col-md-5"></div>
		<div class="col-md-4">
			<button class="btn btn-primary btn-block" id="showPlan" name="showPlan" data-toggle="modal" data-target="#myModal" >查看本次计划</button>
		</div>
	</div>
</div>
		

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="title">本次计划信息</h4>
			</div>
			<div class="modal-body">
				<form>
					<p class="col-md-4">
        				店铺id:
        			</p>
					<div class="col-md-3">
						<select data-toggle="select" name="b-id" id="b-id" class="form-control select select-primary mrs mbm">
							<s:iterator id="branch" value="#session['branchlist']">
								<option value="<s:property value="#branch['id']"/>"><s:property
									value="#branch['id']" /></option>
							</s:iterator>
        				</select>
        			</div>
        			<br/>
        			<br/>
        			<p class="col-md-4">
        				店名:
        			</p>
        			<p class="col-md-6" id="b_name"><s:property value="#session['bname1']"/></p>
					<br/>
        			<br/>
        			<p class="col-md-4">
        				计划开始时间:
        			</p>
        			<p class="col-md-6" id="start_date"><s:property value="#session['plan_start_date']"/></p>
					<br/>
					<br/>
					<p class="col-md-4">
        				计划结束时间:
        			</p>
        			<p class="col-md-6" id="end_date"><s:property value="#session['plan_end_date']"/></p>
				</form>
				<br/>
				<br/>
				<table class="table table-responsive table-hover" id="planitems">
					<tr>
						<th width="250px">商品id</th>
						<th width="250px">商品单价</th>
						<th width="250px">商品数量</th>
						<%
							if(session.getAttribute("employee") != null){
						%>
						<s:if test="#session.employee.authority == 2">
							<th width="80px">删除商品</th>
						</s:if>
						<%	} %>
					</tr>
					<s:iterator id="item" value="#session['planItemList']">
						<tr>
							<td><s:property value="#item['gid']"/></td>
							<td><s:property value="#item['price']"/></td>
							<td><s:property value="#item['number']"/></td>
							<%
								if(session.getAttribute("employee") != null){
							%>
							<s:if test="#session.employee.authority == 2">
								<td><button class="btn btn-primary d_info" data-id="<s:property value="#item['gid']"/>">删除条目</button></td>
							</s:if>
							<%	} %>	
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">我了解了</button>
				<%
					if (session.getAttribute("employee") != null) {
				%>
				<s:if
					test="#session.employee.authority == 2">
					<button type="button" class="btn btn-primary" name="createPlan"
						id="createPlan">提交计划</button>
				</s:if>
				&nbsp;
				<%
					}
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>

<script src="../js/plan.js"></script>
<script src="../js/jquery-ui.js"></script>