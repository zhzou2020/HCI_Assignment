$("#submit").click(function(){
	var name = $("#name").val();
	var price = $("#price").val();
	var info = $("#info").val();
	$.ajax({
		url: "addgoods.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'name':name, 'price':price, 'info':info},
		success:function(data){
			alert(data["result"]);
//			$("#result").append(data["result"]);
		},
		error:function(){
			alert("failed");
		}
	})
});

$(".goods_info").click(function(){
	$("#update").popover("hide");
	var gid = $(this).find('.id').html();
	var path = $('#path').html();
	$.ajax({
		url : "goodsInformation.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'gid' : gid
		},
		success : function(data) {
			var dataRole = $.parseJSON(data["result"]);
			$('#info_name').html(dataRole['name']);
			$('#info_img').attr("src", path + dataRole['src']);
			$('#info_id').html(gid);
			if(dataRole['ifuser'] == "1"){
				$('#info_price_user').html(dataRole['price']);
				$('#info_info_user').html(dataRole['info']);
			} else{
				$('#info_price').val(dataRole['price']);
				$('#info_info').val(dataRole['info']);
			}
			$("#myModal").modal('show');
		},
		error : function() {
			alert("failed");
		}
	})
})

$("#update").click(function(){
	var id = $("#info_id").html();
	var name = $("#info_name").html();
	var price = $("#info_price").val();
	var info = $("#info_info").val();
	$("#update").popover();
	$.ajax({
		url: "updategoods.action",
		type: 'POST',
		dataType: 'JSON',
		data:{'id':id, 'name':name, 'price':price, 'info':info},
		success:function(data){
//			alert(data["result"]);
//			$("#result").append(data["result"]);
			$("#update").attr("data-content", data["result"]);
			$("#update").popover("show");
		},
		error:function(){
			alert("failed");
		}
	})
});

$('body').delegate('.g_info', 'click', function() {
	var gid = $(this).attr('data-id');
	var path = $('#path').html();
	$.ajax({
		url : "goodsInformation.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'gid' : gid
		},
		success : function(data) {
			var dataRole = $.parseJSON(data["result"]);
			$('#info_name').html(dataRole['name']);
			$('#info_img').attr("src", path + dataRole['src']);
			$('#info_id').html(gid);
			if(dataRole['ifuser'] == "1"){
				$('#info_price_user').html(dataRole['price']);
				$('#info_info_user').html(dataRole['info']);
			} else{
				$('#info_price').val(dataRole['price']);
				$('#info_info').val(dataRole['info']);
			}
		},
		error : function() {
			alert("failed");
		}
	})
})
