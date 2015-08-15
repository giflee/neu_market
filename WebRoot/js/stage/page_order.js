function check(){
	var userName = getCookie("user");
	if (userName != "" && userName != null) {
		$("#user_name").html(userName);
		$("#user_login").html("");
		$("#user_register").html("");
		return true;
	} else {
		location.href = "findByPhone.html";
		return false;
	}
}


function show(){
	$.ajax({
		type:"post",
		url:"/Shop/pre/findOrders.do",
		dataType:"json",
		success:function(jo){
			if(jo.REQUEST_SUCCESS=="1"){
				var data = jo.RESULT_CONTENT.orderList;
				var str = "";
				$(data).each(function(){
					str +='<tr data-order-id="'+this.order_id+'">'+
						'<td>'+this.address_person+'</td>'+
						'<td>'+this.address_phone+'</td>'+
						'<td>'+this.address+'</td>'+
						'<td>'+this.order_price+'</td>';
						if(this.order_status=="1"){
							str +='<td>未收货</td>';
						}else{
							str +='<td>已收货</td>';
						}
						str +='<td><a class="order_look" data-target="#ajax_target" data-trigger="ajax" href="#">查看</a>/<a class="queren" style="cursor: pointer;">确认收货</a></td></tr>';
					 $("#order_tbody").append(str);
					 str="";
					 $("#order_tbody tr:last").data("order",this);
				});
			}else{
				alert(jo.RESULT_MESSAGE);
			}
		}
	});
}
$(document).on("click",'.order_look',function(){
	order_id=$(this).parent().parent("tr").attr("data-order-id");
	location.href="orderDetail.html?order_id="+order_id;
});

$(document).on("click",'.queren',function(){
	var id=$(this).parent().parent().data("order").order_id;
	$.ajax({
		type:"post",
		url:"/Shop/pre/shouhuo.do",
		dataType:"json",
		data:{"id":id},
		success:function(jo){
			if(jo.RESULT_SUCCESS=="1")
				alert(jo.RESULT_MESSAGE);
			 $("#order_tbody").html("");
			check();
			show();
		}
		
	});
});


$(function(){
	if(check()){
		show();
	}
});