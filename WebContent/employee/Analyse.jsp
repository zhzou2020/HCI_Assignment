<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>月度统计</title>
<%@include file="../nav.jsp"%>

<br/>
<br/>
<div class="main_container">
	<p class="position">首页 > 月度统计</p>

    <ul class="nav nav-tabs nav-stacked position" style="width:200px;margin-right:20px;float:left;">
        <li><a href="#branchsaletag">分店销售</a></li>
        <li><a href="#canvastag">会员地域</a></li>
        <li><a href="#gendertag">会员性别</a></li>
        <li><a href="#cardstatetag">会员状态</a></li>
        <li><a href="#agetag">会员年龄</a></li>
    </ul>

	<div class="canvas_position">
		<h5 id="branchsaletag">分店销售</h5>
		<canvas id="branchsale"></canvas>
		<br/>
		<br/>
		<h5 id="canvastag">会员地域</h5>
		<canvas id="canvas"></canvas>
		<br/>
		<br/>
		<h5 id="gendertag">会员性别</h5>
		<canvas id="gender"></canvas>
		<br/>
		<br/>
		<h5 id="cardstatetag">会员状态</h5>
		<canvas id="cardstate"></canvas>
		<br/>
		<br/>
		<h5 id="agetag">会员年龄</h5>
		<canvas id="age"></canvas>
		<br/>
		<br/>
	</div>
</div>

</body>
</html>
<script src="../js/jquery.js"></script>
<script src="../flatui/dist/js/flat-ui.min.js"></script>
<script src="../flatui/assets/js/application.js"></script>
<script src="../js/Chart.js"></script>
<script src="../js/analyse.js"></script>