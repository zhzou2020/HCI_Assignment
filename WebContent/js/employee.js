$("#submit").click(function(){
	var name = $("#name").val();
	var passwordOne = $("#passwordOne").val();
	var passwordTwo = $("#passwordTwo").val();
	var authority = $("#authority").val();
	var bid = $("#bid").val();
	$.ajax({
		url: "addemployee.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'name':name, 'passwordOne':passwordOne, 'passwordTwo':passwordTwo, 'authority':authority, 'bid':bid},
		success:function(data){
			alert(data["result"]);
//			$("#result").append(data["result"]);
		},
		error:function(){
			alert("failed");
		}
	})
});

$("#update").click(function(){
	var authority = $("#authority").val();
	var id = $("#id").val();
	var bid = $("#bid").val();
	$.ajax({
		url: "updateEmployee.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'id':id, 'bid':bid, 'authority':authority},
		success:function(data){
			alert(data["result"]);
		},
		erroe:function(){
			alert("failed");
		}
	})
});

$("#delete").click(function(){
	var id = $("#id").val();
	$.ajax({
		url: "deleteEmployee.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'id':id},
		success:function(data){
			alert(data["result"]);
		},
		erroe:function(){
			alert("failed");
		}
	})
})