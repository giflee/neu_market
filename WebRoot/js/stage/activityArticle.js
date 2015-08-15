$(function(){
	load_list();
});

function load_list(){
	$.post(
	"/Shop/activity/find.do",
	{},
	function(data){
		var list = data.RESULT_CONTENT.activityList;
		$(list).each(function(){
			var conText = '<a href="javascript:void(0);" class="list-group-item list-border">'+this.activity_title+'</a>';
			$(".list-group").append(conText);
			$(".list-group a:last").data("datalist",this);
			$(".list-group a:last").click(function(){
				var datalist = $(this).data("datalist");
				$("#article_title").text(datalist.activity_title);
				$("#article_body").text(datalist.activity_body);
			});
		});
	}
  )
}
	

