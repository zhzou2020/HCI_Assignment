$('.branch_info').click(function(){
	var bid = $(this).find('.id').html();
	$.ajax({
		url : "branchInformation.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'bid' : bid
		},
		success : function(data) {
			var dataRole = $.parseJSON(data["result"]);
			$('#nameTitle').html(dataRole['name']);
			$('#id').html(dataRole['id']);
			$('#bid').html(dataRole['id']);
			$('#name').val(dataRole['name']);
			$('#name').html(dataRole['name']);
			$('#address').val(dataRole['address']);
			$('#address').html(dataRole['address']);
			$('#info').html(dataRole['info']);
			$('#myModal').modal('show');
		},
		error : function() {
			alert("failed");
		}
	})
})

$('body').delegate('.b_info', 'click', function() {
	var bid = $(this).attr('data-id');
	$.ajax({
		url : "branchInformation.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'bid' : bid
		},
		success : function(data) {
			var dataRole = $.parseJSON(data["result"]);
			$('#nameTitle').html(dataRole['name']);
			$('#id').html(dataRole['id']);
			$('#bid').html(dataRole['id']);
			$('#name').val(dataRole['name']);
			$('#name').html(dataRole['name']);
			$('#address').val(dataRole['address']);
			$('#address').html(dataRole['address']);
			$('#info').html(dataRole['info']);
		},
		error : function() {
			alert("failed");
		}
	})
})
$('#update').click(function() {
	var id = $('#bid').html();
	var name = $('#name').val();
	var address = $('#address').val();
	var info = $('#info').val();
	$.ajax({
		url : "updateBranch.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'bid' : id,
			'name' : name,
			'address' : address,
			'info' : info
		},
		success : function(data) {
			alert(data['result']);
		},
		error : function() {
			alert("failed");
		}
	})
})
$('#delete').click(function() {
	var id = $('#bid').val();
	$.ajax({
		url : "deleteBranch.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'bid' : id
		},
		success : function(data) {
			alert(data['result']);
		},
		error : function() {
			alert("failed");
		}
	})
})
$('#add').click(function() {
	var name = $('#name').val();
	var address = $('#address').val();
	var info = $('#info').val();
	$.ajax({
		url : "addBranch.action",
		type : 'POST',
		dataType : 'JSON',
		data : {
			'name' : name,
			'address' : address,
			'info' : info
		},
		success : function(data) {
			alert(data['result']);
		},
		error : function() {
			alert("failed");
		}
	})
})
$('body').delegate('.d_info', 'click', function() {
	$(this).parent().parent().remove();
})