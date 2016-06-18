$('#gid').change(function(){
	var p1=$(this).children('option:selected').val();//这就是selected的值
	$.ajax({
		url: "getGoodsInfo?id=" + p1,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data["result"]);
			$("#gname").val(dataRole['name']);
			$("#price").val(dataRole['price']);
		},
		error:function(){
			alert("failed");
		}
	})
})

$('#b-id').change(function(){
	var bid=$(this).children('option:selected').val();
	$.ajax({
		url: "getName?id=" + bid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data["result"]);
			$("#b_name").val(dataRole['name']);
		},
		error:function(){
			alert("failed");
		}
	})
})

$(function() {
    $( "#number" ).spinner({
       	step: 1,
       	min:0
       	/* numberFormat: "n" */
 	});
})

$('#addItem').click(function(){
	var gid = $('#gid').val();
	var price = $('#price').val();
	var number = $('#number').val();
	$.ajax({
		url: "addItem",
		method: 'POST',
		dataType: 'JSON',
		data:{'gid':gid, 'price':price, 'number':number},
		success:function(data){
			var dataRole = $.parseJSON(data["result"]);
			alert(dataRole['message']);
			if(dataRole['result'] == "true"){
				$("#planitems").append(
						"<tr><td>" +gid+ "</td><td>" + price + "</td><td>" + number + "</td><td><button class=\"btn btn-primary d_info\" data-id=" +
						"\"" + gid + "\"" + ">删除条目</button></td></tr>" 
				);
			}
		},
		error:function(){
			alert("failed");
		}
	})
})

$('body').delegate('.d_info', 'click', function() {
	var gid = $(this).attr('data-id');
	var tr = $(this).parent().parent();
	$.ajax({
		url: "deleteItem?id=" + gid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result']);
			tr.remove();
		},
		error:function(){
			alert("failed");
		}
	})
})

$('#createPlan').click(function(){
	var bid = $("#b-id").val();
	$.ajax({
		url: "createPlan?bid=" + bid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result']);
			$("#planitems  tr:not(:first)").html("");
		},
		error:function(){
			alert("failed");
		}
	})
})

$('#updatePlan').click(function(){
	var pid = $("#pid").val();
	$.ajax({
		url: "updatePlan?id=" + pid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result']);
			$("#planitems  tr:not(:first)").html("");
		},
		error:function(){
			alert("failed");
		}
	})
})

$('body').delegate('.p_info', 'click', function() {
	var pid = $(this).attr('data-id');
	$.ajax({
		url: "planInfo?id=" + pid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data["result"]);
			console.log(data['result']);
			$("#planitems  tr:not(:first)").html("");
			var append = "";
			$("#p_id").val(dataRole['plan']['id']);
			$("#b_id").val(dataRole['plan']['bid']);
			$("#e_id").val(dataRole['plan']['eid']);
			$("#start_date_modal").val(dataRole['plan']['start_date']);
			$("#end_date_modal").val(dataRole['plan']['end_date']);
			for(var i = 0; i != dataRole['items'].length; i ++){
				append += "<tr><td>" + dataRole['items'][i]['gid'] + "</td><td>" + dataRole['items'][i]['price'] + "</td><td>" + dataRole['items'][i]['number'] + "</td></tr>" ;
			}
			$("#planitems").append(append);
			if(dataRole['plan']['state'] == 0){
				$('#passPlan').attr("disabled",false);
				$('#rejectPlan').attr("disabled",false);
			} else{
				$('#passPlan').attr("disabled",true);
				$('#rejectPlan').attr("disabled",true);
			}
		},
		error:function(){
			alert("failed");
		}
	})
})

$('#passPlan').click(function(){
	var pid = $("#p_id").val();
	$.ajax({
		url: "passPlan?id=" + pid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result'])
		},
		error:function(){
			alert("failed");
		}
	})
})
	


$('#rejectPlan').click(function(){
	var pid = $("#p_id").val();
	$.ajax({
		url: "rejectPlan?id=" + pid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result'])
		},
		error:function(){
			alert("failed");
		}
	})
})