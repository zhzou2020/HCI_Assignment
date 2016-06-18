window.onload = function() {
	$.ajax({
		url : "getLivePlace",
		method : 'GET',
		dataType : 'JSON',
		success : function(data) {
			var dataRole = $.parseJSON(data['result']);
			var nanjing = dataRole['nanjing'];
			var jiaxing = dataRole['jiaxing'];
			var chengdu = dataRole['chengdu'];
			var guangzhou = dataRole['guangzhou'];
			var beijing = dataRole['beijing'];
			var shanghai = dataRole['shanghai'];
			var pieData = [ {
				value : nanjing,
				color : "#F7464A",
				highlight : "#FF5A5E",
				label : "南京"
			}, {
				value : jiaxing,
				color : "#46BFBD",
				highlight : "#5AD3D1",
				label : "嘉兴"
			}, {
				value : shanghai,
				color : "#FDB45C",
				highlight : "#FFC870",
				label : "上海"
			}, {
				value : guangzhou,
				color : "#949FB1",
				highlight : "#A8B3C5",
				label : "广州"
			}, {
				value : beijing,
				color : "#4D5360",
				highlight : "#616774",
				label : "北京"
			} ];
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myLine = new Chart(ctx).Pie(pieData, {
				responsive : true
			});
		},
		error : function() {
			alert("failed");
		}
	})
	
	$.ajax({
		url : "getGender",
		method : 'GET',
		dataType : 'JSON',
		success : function(data){
			var dataRole = $.parseJSON(data['result']);
			var male = dataRole['male'];
			var female = dataRole['female'];
			var pieData = [ {
				value : female,
				color : "#F7464A",
				highlight : "#FF5A5E",
				label : "女"
			}, {
				value : male,
				color : "#2980B9",
				highlight : "#3498DB",
				label : "男"
			} ]
			var ctx = document.getElementById("gender").getContext("2d");
			window.genderPie = new Chart(ctx).Pie(pieData, {
				responsive : true
			});
		},
		error : function(){
			alert("failed");
		}
	})
	
	
	$.ajax({
		url : "getCardState",
		method : 'GET',
		dataType : 'JSON',
		success : function(data){
			var dataRole = $.parseJSON(data['result']);
			var pause = dataRole['pause'];
			var stop = dataRole['stop'];
			var valid = dataRole['valid'];
			var notvalid = dataRole['notvalid'];
			var pieData = [ {
				value : valid,
				color : "#F7464A",
				highlight : "#FF5A5E",
				label : "正常"
			}, {
				value : notvalid,
				color : "#46BFBD",
				highlight : "#5AD3D1",
				label : "未激活"
			}, {
				value : pause,
				color : "#FDB45C",
				highlight : "#FFC870",
				label : "暂停"
			}, {
				value : stop,
				color : "#949FB1",
				highlight : "#A8B3C5",
				label : "停止"
			} ];
			var ctx = document.getElementById("cardstate").getContext("2d");
			window.genderPie = new Chart(ctx).Pie(pieData, {
				responsive : true
			});
		},
		error : function(){
			alert("failed");
		}
	})
	
	$.ajax({
		url : "getSaleOrder",
		method : 'GET',
		dataType : 'JSON',
		success : function(data){
			var dataRole = $.parseJSON(data['result']);
			var label = new Array();
			var preorder = new Array();
			var saleorder = new Array();
			
			for(var i = 0; i != dataRole['branches'].length; i ++){
				label.push(dataRole['branches'][i]['bid']);
				preorder.push(dataRole['branches'][i]['preorder']);
				saleorder.push(dataRole['branches'][i]['sale']);
			}
			
			var barChartData = {
					labels : label,
					datasets : [
						{
							fillColor : "rgba(220,220,220,0.5)",
							strokeColor : "rgba(220,220,220,0.8)",
							highlightFill: "rgba(220,220,220,0.75)",
							highlightStroke: "rgba(220,220,220,1)",
							data : preorder
						},
						{
							fillColor : "rgba(151,187,205,0.5)",
							strokeColor : "rgba(151,187,205,0.8)",
							highlightFill : "rgba(151,187,205,0.75)",
							highlightStroke : "rgba(151,187,205,1)",
							data : saleorder
						}
					]

				}
			var ctx = document.getElementById("branchsale").getContext("2d");
			window.myBar = new Chart(ctx).Bar(barChartData, {
				responsive : true
			});
		},
		error : function(){
			alert("failed");
		}
	})
	
	$.ajax({
		url : "getAge",
		method : 'GET',
		dataType : 'JSON',
		success : function(data){
			var dataRole = $.parseJSON(data['result']);
			var pieData = [ {
				value : dataRole['age0_20'],
				color : "#F7464A",
				highlight : "#FF5A5E",
				label : "0-20岁"
			}, {
				value : dataRole['age20_30'],
				color : "#46BFBD",
				highlight : "#5AD3D1",
				label : "20-30岁"
			}, {
				value : dataRole['age30_40'],
				color : "#FDB45C",
				highlight : "#FFC870",
				label : "30-40岁"
			}, {
				value : dataRole['age40_50'],
				color : "#949FB1",
				highlight : "#A8B3C5",
				label : "40-50岁"
			}, {
				value : dataRole['age50_60'],
				color : "#4D5360",
				highlight : "#616774",
				label : "50岁以上"
			} ];
			var ctx = document.getElementById("age").getContext("2d");
			window.myLine = new Chart(ctx).Pie(pieData, {
				responsive : true
			});
		},
		error : function(){
			alert("failed");
		}
	})
}
