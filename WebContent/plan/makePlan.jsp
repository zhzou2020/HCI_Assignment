<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>制定计划</title>
<%@include file="../nav.jsp"%>
<form action="" method="post" class="form_info">
	<div class="form-group container has-feedback">
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">商品id:</div>
		<div class="col-md-3">
			<select data-toggle="select" name="gid" id="gid"
				class="form-control select select-default mrs mbm">
				<s:iterator id="goods" value="#session['goodslist']">
					<option value="<s:property value="#goods['id']"/>"><s:property
							value="#goods['id']" /></option>
				</s:iterator>
			</select>
		</div>
		<br />
		<br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">商品名称:</div>
		<div class="col-md-3">
			<input type="text" name="gname" id="gname" class="form-control"
				disabled="disabled" />
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">商品价格:</div>
		<div class="col-md-3">
			<input type="text" name="price" id="price" class="form-control" />
		</div>
		<br /> <br />
		<!-- <div class="col-md-3"></div> -->
		<div class="col-md-2">商品数量:</div>
		<div class="col-md-3">
			<input type="text" id="number" name="number" value="10"
				class="form-control">
		</div>
		<br /> <br /><br/>
		<div class="col-md-3"></div>
		<div class="col-md-2">
			<input type="button" value="添加商品" id="addItem"
				class="btn btn-primary btn-block" />
		</div>
		<div class="col-md-3"></div>
		<br /> <br />
		<div class="col-md-3"></div>
		<div class="col-md-2">
			<s:reset value="重新输入" class="btn btn-primary btn-block" />
		</div>
		<div class="col-md-3"></div>
		<br /> <br />
		
		
	</div>
</form>

<div class="col-md-5"></div>
<div class="col-md-2">
	<button class="btn btn-primary btn-block" id="showPlan" name="showPlan" data-toggle="modal" data-target="#myModal" style="margin-left:92px;width:160px">查看本次计划</button>
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
					<div class="col-md-4">
        				店铺id:
        			</div>
					<div class="col-md-3">
						<select data-toggle="select" name="b-id" id="b-id" class="form-control select select-default mrs mbm">
							<s:iterator id="branch" value="#session['branchlist']">
								<option value="<s:property value="#branch['id']"/>"><s:property
									value="#branch['id']" /></option>
							</s:iterator>
        				</select>
        			</div>
        			<br/>
        			<br/>
        			<div class="col-md-4">
        				店名:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="b_name" name="b_name" value="" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
        			<br/>
        			<div class="col-md-4">
        				计划开始时间:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="start_date" name="end_date" value="<s:property value="#session['plan_start_date']"/>" class="form-control login-field" disabled="disabled"/>
					</div>
					<br/>
					<br/>
					<div class="col-md-4">
        				计划结束时间:
        			</div>
        			<div class="col-md-6">
						<input type="text" id="start_date" name="end_date" value="<s:property value="#session['plan_end_date']"/>" class="form-control login-field" disabled="disabled"/>
					</div>
				</form>
				<br/>
				<table class="table table-responsive table-hover" id="planitems">
					<tr>
						<th>商品id</th>
						<th>商品单价</th>
						<th>商品数量</th>
						<%
							if(session.getAttribute("employee") != null){
						%>
						<s:if test="#session.employee.authority == 2">
							<th>删除商品</th>
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