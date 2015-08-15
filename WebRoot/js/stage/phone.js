$(function(){
	$("#d1").show();
	$("#d3").hide();
	$("#d2").hide();
	
	
	
});

function f1(){
	$("#d2").show();
	$("#d1").hide();
	$("#d3").hide();
}

$("#load_button").click(function(){
	$("#d1").hide();
	$("#d3").hide();
	$("#d2").show();
	var phone = $("#phone").val();
	$.post("/Shop/stage/order/find_by_phone.do",{
		"phone":phone
	},function(jo){
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
					str +='<td><a class="order_look" data-target="#ajax_target" data-trigger="ajax" href="javascript:void(0);">查看</a>/<a class="queren" style="cursor: pointer;">确认收货</a></td></tr>';
				 $("#order_tbody").append(str);
				 str="";
				 $("#order_tbody tr:last").data("order",this);
			});
		}else{
			alert(jo.RESULT_MESSAGE);
		}
	});
});

$(document).on("click",'.order_look',function(){
	$("#d1").hide();
	$("#d2").hide();
	$("#d3").show();
	var id=$(this).parent().parent().data("order").order_id;
	$.post("/Shop/pre/findOrderDetail.do",{
		"uri":id
	},function(jo){
		var str="";
		if(jo.REQUEST_SUCCESS=="1"){
			$(".col-md-4").html("");
			var order = jo.RESULT_CONTENT.order;
			var list = jo.RESULT_CONTENT.detail;
			str='<p>订单日期：'+(order.order_time.month+1)+'月'+order.order_time.date+'日'+order.order_time.hours+'时'+order.order_time.minutes+'分'+
			'</p><p>客户姓名：'+order.address_person+'</p><p>手机号码：'+order.address_phone+'</p><p>收货地址：'+order.address+'</p><p>订单备注：'+order.order_mark+'</p>';
			$(".col-md-4").append(str);
			str ="";
			if(order.order_status=="0"){
				$(".orderd").html("已收货");
			}else{
				$(".orderd").html("为收货");
			}
			$("#detail_body").html("");
			$(list).each(function(){
				str+='<tr><td>'+this.goods_name+'</td><td>'+this.good_price+'元</td><td>'+this.order_goods_num+'</td><td>'+this.good_price*this.order_goods_num+'元</td></tr>';
				
			});
			$("#detail_body").append(str);
			$(".orderd_total").html(order.order_price);
			
		}
	});
});

$(document).on("click",'.queren',function(){
	$(this).parent().parent().find("td:eq(4)").text("已收货");
	var id=$(this).parent().parent().data("order").order_id;

	$.post("/Shop/stage/order/receive.do",{
		"id":id
	},function(){
		
	});
});