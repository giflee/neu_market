$(function(){
	load_list();
	
});


function load_list(){
	
	$.post(
	"/Shop/problem/find.do",
	{},
	function(data){
		var list = data.RESULT_CONTENT.problemList;
		$(list).each(function(){
			var conText = '<a href="javascript:void(0);" class="list-group-item list-border">'+this.problem_title+'</a>';
			$(".list-group").append(conText);
			$(".list-group a:last").data("datalist",this);
			$(".list-group a:last").click(function(){
				var datalist = $(this).data("datalist");
				$("#article_title").text(datalist.problem_title);
				$("#article_body").text(datalist.problem_body);
			});
		});
	}
	);
	
	
	
	
//	$a1 = $('<a href="javascript:void(0);" class="list-group-item list-border">运费问题</a>');
//	$a1.click(function(){
//		$("#article_title").text("运费问题");
//		$("#article_body").text("统一收取配送费5元");
//	});
//	$(".list-group").append($a1);
//	
//	
//	$a2 = $('<a href="javascript:void(0);" class="list-group-item list-border">维修问题</a>');
//	$a2.click(function(){
//		$("#article_title").text("维修问题");
//		$("#article_body").text("凭购物小票，可维修保修商品");
//	});
//	$(".list-group").append($a2);
//	$a3 = $('<a href="javascript:void(0);" class="list-group-item list-border">发票问题</a>');
//	$a3.click(function(){
//		$("#article_title").text("发票问题");
//		$("#article_body").text("凭购物小票，可在30天内到前台换取发票");
//	});
//	$(".list-group").append($a3);
}