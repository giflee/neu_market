$(function(){
	var URL = window.location.search;
	var ind = URL.indexOf("=");
	var URI = URL.substring(ind+1);
	$.ajax({
		type:"post",
		url:"/Shop/pre/findOrderDetail.do",
		dataType:"json",
		data:{"uri":URI},
		success:function(jo){
			var str="";
			if(jo.REQUEST_SUCCESS=="1"){
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
				$(list).each(function(){
					str+='<tr><td>'+this.goods_name+'</td><td>'+this.good_price+'元</td><td>'+this.order_goods_num+'</td><td>'+this.good_price*this.order_goods_num+'元</td></tr>';
				});
				$("#detail_body").append(str);
				$(".orderd_total").html(order.order_price);
				
			}
				
		}
	});
	
});