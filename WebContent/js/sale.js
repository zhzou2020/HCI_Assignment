$("#addSaleItem").click(function(){
	var number = $("#sale_number").val();
	var stock = $("#stock").html();
	var gid = $("#gid").val();
	if(isNaN(number) || number == ""){
		alert("销售数量必须为数字！");
	} else if(parseInt(number) > parseInt(stock)){
		alert("无法出售大于现有库存量的商品！");
	} else{
		$.ajax({
			url: "addSaleItem",
			method: 'POST',
			dataType: 'JSON',
			data:{"number":number, "gid":gid},
			success:function(data){
				var dataRole = $.parseJSON(data['result']);
				alert(dataRole['message']);
				if(dataRole['result'] == "true"){
					var append = "<tr><td>" +gid+ "</td><td>" + dataRole['price'] + "</td><td>" + number + "</td><td><button class=\"btn btn-primary d_info\" data-id=" +
					"\"" + gid + "\"" + ">删除条目</button></td></tr>";
					$("#saleitems").append(append);
					$("#totalamount").html(dataRole['amount']);
				}
			},
			error:function(){
				alert("failed");
			}
		})
	}
})

$("#addSaleItem_pre").click(function(){
	var number = $("#sale_number").val();
	var stock = $("#stock").html();
	var gid = $("#gid").val();
	if(isNaN(number)){
		alert("销售数量必须为数字！");
	} else if(parseInt(number) > parseInt(stock)){
		alert("无法出售大于现有库存量的商品！");
	} else{
		$.ajax({
			url: "addSaleItem_pre",
			method: 'POST',
			dataType: 'JSON',
			data:{"number":number, "gid":gid},
			success:function(data){
				var dataRole = $.parseJSON(data['result']);
				alert(dataRole['message']);
				if(dataRole['result'] == "true"){
					var append = "<tr><td>" +gid+ "</td><td>" + dataRole['price'] + "</td><td>" + number + "</td><td><button class=\"btn btn-primary d_info\" data-id=" +
					"\"" + gid + "\"" + ">删除条目</button></td></tr>";
					$("#saleitems").append(append);
					$("#totalamount").html(dataRole['amount']);
				}
			},
			error:function(){
				alert("failed");
			}
		})
	}
})

$('body').delegate('.d_info', 'click', function() {
	var gid = $(this).attr('data-id');
	var tr = $(this).parent().parent();
	$.ajax({
		url: "deleteSaleItem?id=" + gid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			if(data['result'] != null){
				var dataRole = $.parseJSON(data['result']);
				alert(dataRole['message']);
				tr.remove();
				$("#totalamount").html(dataRole['amount']);
			} else{
				alert("删除失败！");
			}
		},
		error:function(){
			alert("failed");
		}
	})
})

$("#settle").click(function(){
	var totalamount = $("#totalamount").html();
	$("#amount").html(totalamount);
})

$("#pre_settle").click(function(){
	var totalamount = $("#totalamount").html();
	$("#amount").html(totalamount);
	var discount = $("#discountrate").val();
	totalamount = discount*totalamount;
	$("#discount_pay_amount").html(totalamount.toFixed(2));
})

$("#pay_amount").change(function(){
	var totalamount = $("#totalamount").html();
	var pay_amount = $("#pay_amount").val();
	if(isNaN(pay_amount)){
		alert("支付金额应为数字！");
	} else if(parseInt(totalamount) > parseInt(pay_amount)){
		alert("支付金额不足！");
	} else{
		$("#return_change").html(pay_amount - totalamount);
	}
})

$("#cash_pay").click(function(){
	var totalamount = $("#totalamount").html();
	var pay_amount = $("#pay_amount").val();
	if(isNaN(pay_amount)){
		alert("支付金额应为数字！");
	} else if(parseInt(totalamount) > parseInt(pay_amount)){
		alert("支付金额不足！");
	} else{
		$.ajax({
			url: "cashSale",
			method: 'GET',
			dataType: 'JSON',
			success:function(data){
				if(data['result'] != null){
					alert(data['result']);
				} else{
					alert("结账成功！");
					$("#saleitems  tr:not(:first)").html("");
				}
			},
			error:function(){
				alert("failed");
			}
		})
	}
})

$("#card_pay").click(function(){
	var pay_amount = $("#discount_pay_amount").html();
	if(pay_amount == "" || pay_amount == ""){
		alert("请输入已激活的会员id");
	} else{
		var memberid = $("#memberid").val();
		if (isNaN(memberid)) {
			alert("请输入正确的会员编号！");
		} else {
			$.ajax({
				url : "cardSale",
				method : 'POST',
				dataType : 'JSON',
				data : {
					"amount" : pay_amount,
					"mid" : memberid
				},
				success : function(data) {
					var dataRole = $.parseJSON(data['result']);
					alert(dataRole['message']);
					if (dataRole['success'] == "ok") {
						$("#saleitems  tr:not(:first)").html("");
					} else {

					}
				},
				error : function() {
					alert("failed");
				}
			})
		}
	}
})

$("#memberid").change(function(){
	var memberid = $("#memberid").val();
	var totalamount = $("#amount").html();
	if(isNaN(memberid)){
		alert("请输入正确的会员编号！");
	} else if(memberid == null){
		alert("请输入会员编号！");
	} else{
		$.ajax({
			url: "memberInfo?id=" + memberid,
			method: 'GET',
			dataType: 'JSON',
			success:function(data){
				if(data['result'] != null){
					var dataRole = $.parseJSON(data['result']);
					$("#membername").html(dataRole['name']);
					var rank = dataRole['rank'];
					var discount = "十折";
					var truediscount = 1.0;
					if(dataRole['state'] != 1){
						alert("此会员卡不正常，请重新激活后使用!");
					} else{
						if (rank == 0) {
						} else if (rank == 1) {
							discount = "九五折";
							truediscount = 0.95;
						} else if (rank == 2) {
							discount = "九折";
							truediscount = 0.9;
						} else if (rank == 3) {
							discount = "八五折";
							truediscount = 0.85;
						} else if (rank == 4) {
							discount = "八折";
							truediscount = 0.8;
						} else {
							discount = "七五折"
							truediscount = 0.75;
						}
						$("#discount").html(discount);
						totalamount = totalamount * truediscount;
						$("#discount_pay_amount").html(totalamount.toFixed(2));
					}
					
				} else{
					alert("请输入正确的会员编号！");
					$("#membername").html("");
					$("#discount").html("");
					$("#discount_pay_amount").html("");
				}
			},
			error:function(){
				alert("请输入正确的会员编号");
				$("#membername").html("");
				$("#discount").html("");
				$("#discount_pay_amount").html("");
			}
		})
	}
})

$("#preorder").click(function(){
	var pay_amount = $("#discount_pay_amount").html();

	var memberid = $("#memberid").val();
	
	$.ajax({
		url : "preorderSale",
		method : 'POST',
		dataType : 'JSON',
		data : {
			"amount" : pay_amount,
			"mid" : memberid
		},
		success : function(data) {
			var dataRole = $.parseJSON(data['result']);
			alert(dataRole['message']);
			if (dataRole['success'] == "ok") {
				$("#saleitems  tr:not(:first)").html("");
			} else {

			}
		},
		error : function() {
			alert("failed");
		}
	})
})

$('body').delegate('.s_info', 'click', function(){
	var sale_id = $(this).attr("data-id");
	$.ajax({
		url: "saleInfo?id=" + sale_id,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data['result']);
			$("#s_id").val(dataRole['sale']['id']);
			$("#b_id").val(dataRole['sale']['bid']);
			$("#s_date").val(dataRole['sale']['date']);
			$("#salesman_id").val(dataRole['sale']['salesman_id']);
			if(dataRole['sale']['pay_way'] == 0){
				$("#m_id").val("");
			} else{
				$("#m_id").val(dataRole['sale']['mid']);
			}

			var state = dataRole['sale']['state'];
			
			var stateshow = "";
			if(state == 1){
				stateshow="已完成";
			} else if(state == 0){
				stateshow="未完成";
			} else{
				stateshow="已取消";
			}
			
			$("#saleitems  tr:not(:first)").html("");
			$("#state").val(stateshow);
			var append = "";
			for(var i = 0; i != dataRole['items'].length; i ++){
				append += "<tr><td>" + dataRole['items'][i]['gid'] + "</td><td>" + dataRole['items'][i]['price'] + "</td><td>" + dataRole['items'][i]['number'] + "</td></tr>" ;
			}
			$("#saleitems").append(append);
		},
		error:function(){
			alert("failed");
		}
	})
}) 

$('body').delegate('.cancel_sale', 'click', function(){
	var sid = $(this).attr("data-id");
	var thisbutton = $(this);
	$.ajax({
		url: "cancelSale?id=" + sid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			alert(data['result']);
			thisbutton.attr("disabled", true);
		},
		error:function(data){
			alert("fail");
		}
	})
})

$("#b_id").change(function(){
	var bid=$(this).children('option:selected').val();
	$.ajax({
		url: "getBranchAllInfo?id=" + bid,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data['result']);
			$("#b_name").html(dataRole['name']);
			$("#b_addr").html(dataRole['address']);
			$("#b_info").html(dataRole['info']);
		},
		error:function(data){
			alert("fail");
			$("#b_name").html("");
			$("#b_addr").html("");
			$("#b_info").html("");
		}
	})
})

$("#gid").change(function(){
	var p1=$(this).children('option:selected').val();//这就是selected的值
	$.ajax({
		url: "getGoodsStockInfo?id=" + p1,
		method: 'GET',
		dataType: 'JSON',
		success:function(data){
			var dataRole = $.parseJSON(data["result"]);
			$("#gname").html(dataRole['name']);
			$("#stock").html(dataRole['number']);
		},
		error:function(){
			alert("failed");
		}
	})
})
