function showOrderGoods(order){
	var order_id = order.order_id;
	Data={
			"order_id":order_id,
	}
	$.ajax({
		type:"post",
		url:project_name+"/order/find/goods.do",
		dataType:"json",
		data:Data,
		success:function(jo){
			if(jo.REQUEST_SUCCESS=="1"){
				var data = jo.RESULT_CONTENT.orderGoodsList;
				var str="";
				$(data).each(function(){
					str +='<tr>'+
						'<td>'+this.goods_name+'</td>'+
						'<td>'+this.good_price+'元</td>'+
						'<td>'+this.order_goods_num+'</td>'+
						'<td>'+this.good_price*this.order_goods_num+'元</td>'+
						'</tr>';
					 $("#goodsOrder_body").append(str);
					 str="";
					 $("#person_og").val(this.order.address_person);
					 $("#phone_og").val(this.order.address_phone);
					 $("#price_og").val(this.order.order_price);
					 $("#address_og").val(this.order.address);
					 $("#mark_og").val(this.order.order_mark);
				});
			}else{
				alert(jo.RESULT_MESSAGE);
			}
		}
	});
}


function findOrder(page){
	var order_status= $("#order_status").val();
	var address_phone = $("#address_phone").val();
	Data={
			"order_status":order_status,
			"address_phone":address_phone,
			"page1":page,
	}
	$.ajax({
		type:"post",
		url:project_name+"/order/find.do",
		dataType:"json",
		data:Data,
		success:function(jo){
			if(jo.REQUEST_SUCCESS=="1"){
				var data = jo.RESULT_CONTENT.orderList;
				page_parameters.total=jo.RESULT_CONTENT.total;
				page_parameters.size=jo.RESULT_CONTENT.size;
				showOrderList(data);
			}else{
				alert(jo.RESULT_MESSAGE);
			}
		}
	});
}

function showOrderList(data){
	$(".data-table tbody").html("");
	var str="";
	$(data).each(function(){
		str+='<tr>' +
		'<td><input type="checkbox"></td>'+
        '<td>'+this.address_person+'</td>' +
        '<td>'+this.address_phone+'</td>' +
        '<td>'+this.address+'</td>' +
        '<td>'+this.order_price+'元</td>' ;
        if(this.order_status=="0"){
        	str+= '<td> <div class="uk-badge uk-badge-success">已收货</div></td>';
        }else{
        	str+= '<td> <div class="uk-badge uk-badge-danger">未收货</div></td>';
        }
        str+='<td>'+
        '<button class="uk-button uk-float-left order_btn_look"'+
        'data-uk-modal="{target:\'#order_detail_panel\',bgclose:false}"><i class="uk-icon-eye"></i> 查看</button>'+
        '</td>'+
        '</tr>';
		 $(".data-table tbody").append(str);
		 str="";
		 $("#order_tbody tr:last").data("order",this);
	});
	
}
var total=page_parameters.total;
var size=page_parameters.size;
page_parameters.total=null;
page_parameters.size=null;
$(function(){
	findOrder(1);
	
	//console.log(total.total);
	$(".uk-pagination").attr("data-uk-pagination","{items:"+total+", itemsOnPage:"+size+"}");
	
	$('[data-uk-pagination]').on('select.uk.pagination',function(e,pageIndex){
		page=pageIndex+1;
		findOrder(page);
	});
	$("#order_btn_search").on("click",function(){
		findOrder(1);
		
		$("#order_btn_search_panel").click();
	});
	 //查看订单的详细信息
	 $(".data-table").on("click",".order_btn_look",function(){
		 var order = $(this).parent().parent().data("order");
		 $that = $(this);
		 showOrderGoods(order);
	 });
	
	
});
