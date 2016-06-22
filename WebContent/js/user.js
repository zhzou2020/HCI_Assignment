$("#submit").click(function(){
	var name = $("#name").val();
	var gender = $('input:radio[name="gender"]:checked').val();
	var birthday = $("#birthday").val();
	$.ajax({
		url: "changeProfile.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'name':name, 'gender':gender, 'birthday':birthday},
		success:function(data){
			alert(data['result']);
		},
		error:function(){
			alert("failed");
		}
	})
});

$("#stop").click(function(){
	var phoneNo = $("#phoneNo").val();
	$.ajax({
		url: "stopUser.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'phoneNo':phoneNo},
		success:function(data){
			alert(data['result']);
			$("#stop").hide();
			$("#state").val("已停止，无法恢复");
		},
		error:function(){
			alert("failed");
		}
	})
});

$("#topup").click(function(){
	var account = $("#account").val();
	var password = $("#password").val();
	var topupamount = $("#topupamount").val();
	$.ajax({
		url: "topup.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'account':account, "password":password, "topupamount":topupamount},
		success:function(data){
			alert(data['result']);
		},
		error:function(){
			alert("failed");
		}
	})
});

$("#changepoint").change(function(){
	var change = $("#changepoint").val();
	if(isNaN(change)){
		$("#errormessage").html("输入必须为数字！");
		$("#point-to-money").html("");
	} else{
		$("#errormessage").html("");
		var money = change/10;
		$("#point-to-money").html(money);
	}
})

$("#pointChange").click(function(){
	var change = $("#changepoint").val();
	$.ajax({
		url: "pointChange.action",
		type: 'POST',
		dataType: 'JSON',
		data:{"change":change},
		success:function(data){
			var dataRole = $.parseJSON(data['result']);
			alert(dataRole['result']);
			var point = dataRole['point'];
			if(point == undefined){
				
			} else{
				$("#showPoint").html("您现有积分数为" + point);
			}
		},
		error:function(){
			alert("failed");
		}
	})
})

$('#login').click(function(){
	var phoneNo = $('#phoneNo').val();
	var password = $('#password').val();
	if(isNaN(phoneNo) || phoneNo == ""){
		$("#login_alert").html("账号应由数字组成，请重新填写～");
		$("#login_alert").css("visibility", "visible");
	} else{
		$.ajax({
			url: "login.action",
			type: 'POST',
			dataType: 'JSON',
			data:{"phoneNo":phoneNo, "password":password},
			success:function(data){
				if(data['result'] == "success"){
					$("#login_alert").css("visibility", "hidden");
					window.location.href = $("#path").html() + "/goods/goodslist";
				} else{
					$("#login_alert").html("账号或密码错误!");
					$("#login_alert").css("visibility", "visible");
				}
			},
			error:function(){
				alert("failed");
			}
		})
	}
	return false;
})



//$("#saleRecord").click(function(){
//	var mid = $("#mid").val();
//	console.log(mid);
//})