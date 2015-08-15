$(function(){
	
	$(document).scroll(function(){
		if($(document).scrollTop()>=$(document).height()-$(window).height()-200&&$(document).scrollTop()>800){
			//alert(1);
		}
	});
	check();
	load_types_by_page();
	load_goods_by_page();
	$("#buy_for_nologin").click(function(){
	});
	
	
	
});

function check(){
	var userName = getCookie("user");
	if(userName!=""&&userName!=null){
			$("#user_name").html(userName);
			$("#user_login").html("");
			$("#user_register").html("");
		}
}
function load_goods_to_main(goods_list){
	
	$(goods_list).each(function(){
		var $div = $('<div class="col-sm-4 col-xs-6 simpleCart_shelfItem row-padding"><div class="thumbnail i_list"><a  href="#" > <img src="../'+this.goods_pic+'"> </a><div class="caption"><h3 class="item_name" style="font-size:16px">'+this.goods_name+'</h3><input value="1" class="item_Quantity" type="hidden"><p><span class="pull-left item_price" style="color:#FF7700;font-weight:600;font-size:14px">￥'+this.goods_price+' </span> <span class="pull-right "><a href="javascript:;" class="btn btn-warning btn-sm item_add" style="font-size:16px"><i class="fa fa-shopping-cart"></i> 加入购物车</a> </span></p><div class="clearfix"></div></div></div></div>');
		$div.data("goods",this);
		var goods_name=this.goods_name;
		var goods_price = this.goods_price;
		var goods_pic = this.goods_pic;
		$div.find("img").click(function(){
			$("#goods_show_name").text(goods_name);
			$("#modal_goods_name").text(goods_name);
			$("#goods_show_price").text(goods_price);
			$("#goods_show_pic").attr("src",project_name+goods_pic);
			$("#goods_show_num").val("1");
			
			$(this).attr("data-toggle","modal").attr("data-target","#modal");
		});
		$("#goods_list").append($div);
	});
}
function load_goods(){
	$.post(project_name+"/stage/main/load_goods.do",function(result){
		if(result.REQUEST_SUCCESS){
			var goods_list = result.RESULT_CONTENT.goodsList;
			load_goods_to_main(goods_list);
		}else{
			alert(0);
		}
	});
}
function load_goods_by_page(){
	$.post(project_name+"/stage/main/load_page_by_all.do",{
		"currentPage":"1"
	},function(result){
		if(result.REQUEST_SUCCESS){
			var goods_list = result.RESULT_CONTENT.goods;
			var page_count = result.RESULT_CONTENT.page_count;
			
			load_page_to_main(goods_list,page_count);
		}else{
			alert(0);
		}
	});
}
//没用
function load_types(){
	
	$.post(project_name+"/stage/main/load_types.do",function(result){
		if(result.REQUEST_SUCCESS){
			var type_list = result.RESULT_CONTENT.typeList;
			$(".ul_list").empty();
			$(type_list).each(function(index){
				var $li = $('<li><a class="btn" href="javascript:void(0);">'+this.type_name+'</a></li>');
				$li.data("type",this);
				var type_id = this.type_id;
				$li.click(function(){
					load_goods_by_type(type_id);
				});
				$(".ul_list").append($li);
				
			});
		}else{
			alert(0);
		}
	});
}
function load_types_by_page(){
	
	$.post(project_name+"/stage/main/load_types.do",function(result){
		if(result.REQUEST_SUCCESS){
			var type_list = result.RESULT_CONTENT.typeList;
			$(".list-unstyled ").empty().html('<li><a href="javascript:void(0);" class="list-group-item list-group-item-warning"><span style="margin-left:10px;">浏览菜单</span></a></li>');
			$(type_list).each(function(index){
				var $li = $('<li><a href="javascript:void(0);" class="list-group-item"><span style="margin-left:10px;">'+this.type_name+'</span><span class="fa fa-caret-right pull-right"></span></a></li>');
				$li.data("type",this);
				var type_id = this.type_id;
				$li.click(function(){
					load_page_by_type(type_id);
				});
				$(".list-unstyled").append($li);
				
			});
		}else{
			alert(0);
		}
	});
}
//没用
function load_goods_by_type(type_id){
	$.post(project_name+"/stage/main/load_goods_by_type_id.do",{
		"typeId":type_id
	},function(result){
		if(result.REQUEST_SUCCESS){
			$("#goods_list").empty();
			var goods_list = result.RESULT_CONTENT.goods;
			load_goods_to_main(goods_list);
		}else{
			alert(0);
		}
	});
}
function load_page_by_type(type_id){
	$.post(project_name+"/stage/main/load_page_by_type_id.do",{
		"typeId":type_id,
		"currentPage":"1"
	},function(result){
		if(result.REQUEST_SUCCESS){
			$("#goods_list").empty();
			var goods_list = result.RESULT_CONTENT.goods;
			var page_count = result.RESULT_CONTENT.page_count;
			load_goods_to_type(goods_list,page_count,type_id);
		}else{
			alert(0);
		}
	});
}

function load_goods_to_type(goods_list,page_count,type_id){
	$(goods_list).each(function(){
		var $div = $('<div class="col-sm-4 col-xs-6 simpleCart_shelfItem row-padding"><div class="thumbnail i_list"><a  href="#" > <img src="../'+this.goods_pic+'"> </a><div class="caption"><h3 class="item_name" style="font-size:16px">'+this.goods_name+'</h3><input value="1" class="item_Quantity" type="hidden"><p><span class="pull-left item_price" style="color:#FF7700;font-weight:600;font-size:14px">￥'+this.goods_price+' </span> <span class="pull-right "><a href="javascript:;" class="btn btn-warning btn-sm item_add" style="font-size:16px"><i class="fa fa-shopping-cart"></i> 加入购物车</a> </span></p><div class="clearfix"></div></div></div></div>');
		$div.data("goods",this);
		var goods_name=this.goods_name;
		var goods_price = this.goods_price;
		var goods_pic = this.goods_pic;
		$div.find("img").click(function(){
			$("#goods_show_name").text(goods_name);
			$("#modal_goods_name").text(goods_name);
			$("#goods_show_price").text(goods_price);
			$("#goods_show_pic").attr("src",project_name+goods_pic);
			$("#goods_show_num").val("1");
			
			$(this).attr("data-toggle","modal").attr("data-target","#modal");
		});
		$("#goods_list").append($div);
	});
	$("#page_field").empty();
	
	for(var i = 1;i<=page_count;i++){
		$li = $('<li><a  href="javascript:void(0);"> '+i+' </a></li>');
		$li.data("cp",i);
		$li.click(function(){
			var iii = $(this).data("cp");
			load_page_by_button(type_id,iii);
		});
		$("#page_field").append($li);
	}
}
function load_page_to_main(goods_list,page_count){
	$("#goods_list").empty();
	$(goods_list).each(function(){
		var $div = $('<div class="col-sm-4 col-xs-6 simpleCart_shelfItem row-padding"><div class="thumbnail i_list"><a  href="#" > <img src="../'+this.goods_pic+'"> </a><div class="caption"><h3 class="item_name" style="font-size:16px">'+this.goods_name+'</h3><input value="1" class="item_Quantity" type="hidden"><p><span class="pull-left item_price" style="color:#FF7700;font-weight:600;font-size:14px">￥'+this.goods_price+' </span> <span class="pull-right "><a href="javascript:;" class="btn btn-warning btn-sm item_add" style="font-size:16px"><i class="fa fa-shopping-cart"></i> 加入购物车</a> </span></p><div class="clearfix"></div></div></div></div>');
		$div.data("goods",this);
		var goods_name=this.goods_name;
		var goods_price = this.goods_price;
		var goods_pic = this.goods_pic;
		$div.find("img").click(function(){
			$("#goods_show_name").text(goods_name);
			$("#modal_goods_name").text(goods_name);
			$("#goods_show_price").text(goods_price);
			$("#goods_show_pic").attr("src",project_name+goods_pic);
			$("#goods_show_num").val("1");
			
			$(this).attr("data-toggle","modal").attr("data-target","#modal");
		});
		$("#goods_list").append($div);
	});
	$("#page_field").empty();
	
	for(var i = 1;i<=page_count;i++){
		$li = $('<li><a  href="javascript:void(0);"> '+i+' </a></li>');
		$li.data("cp",i);
		$li.click(function(){
			var iii = $(this).data("cp");
			load_page_by_button1(iii);
		});
		$("#page_field").append($li);
	}
}

function load_page_by_button(type_id,currentPage){
	$.post(project_name+"/stage/main/load_page_by_type_id.do",{
		"typeId":type_id,
		"currentPage":currentPage
	},function(result){
		if(result.REQUEST_SUCCESS){
			$("#goods_list").empty();
			var goods_list = result.RESULT_CONTENT.goods;
			load_goods_to_main(goods_list);
		}else{
			alert(0);
		}
	});
}
function load_page_by_button1(currentPage){
	$.post(project_name+"/stage/main/load_page_by_all.do",{
		"currentPage":currentPage
	},function(result){
		if(result.REQUEST_SUCCESS){
			$("#goods_list").empty();
			var goods_list = result.RESULT_CONTENT.goods;
			load_goods_to_main(goods_list);
		}else{
			alert(0);
		}
	});
}