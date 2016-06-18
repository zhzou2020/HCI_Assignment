<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>月度统计</title>
<%@include file="../nav.jsp"%>

<div class="personal-info">
    <h1>月度统计</h1>
    <hr align=center width=210 size="3"/>
</div>

<div class = "left-list-admin">
    <ul>
        <li><a href="#canvastag">会员地域</a></li>
        <li><a href="#gendertag">会员性别</a></li>
        <li><a href="#cardstatetag">会员状态</a></li>
        <li><a href="#agetag">会员年龄</a></li>
        <li><a href="#">会员消费</a></li>
        <li><a href="#branchsaletag">分店销售</a></li>
        <li><a href="#hotgoods">热卖商品</a></li>
    </ul>
</div>
	<div class="canvas_position">
		<h4 id="branchsaletag">分店销售</h4>
		<canvas id="branchsale"></canvas>
		<br/>
		<br/>
		<h4 id="canvastag">会员地域</h4>
		<canvas id="canvas"></canvas>
		<br/>
		<br/>
		<h4 id="gendertag">会员性别</h4>
		<canvas id="gender"></canvas>
		<br/>
		<br/>
		<h4 id="cardstatetag">会员状态</h4>
		<canvas id="cardstate"></canvas>
		<br/>
		<br/>
		<h4 id="agetag">会员年龄</h4>
		<canvas id="age"></canvas>
		<br/>
		<br/>
		<h4 id="hotgoods">热卖商品</h4>	
		<div class="col-md-2">id</div>
		<div class="col-md-2">4</div>
		<br/>
		<br/>
		<div class="col-md-2">名称</div>
		<div class="col-md-2">奶昔</div>
	</div>

</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/Chart.js"></script>
<script src="../js/analyse.js"></script>