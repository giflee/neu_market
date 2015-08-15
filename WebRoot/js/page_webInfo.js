
$(function(){
	showAll();
	
	
	$(".data-table").on("click",".problem_btn_edit",function(){
		$that = $(this);
		update($that);
	});
	
	$("#problem_btn_create_update").on("click",function(){
		var webInfo = $("#webinfo-form").serialize();
		$.post(
		"/Shop/webinfo/update.do",
		webInfo,
		function(){
			showAll();
		}
		);
	});
	
	$(".data-table").on("click",".problem_btn_delete",function(){
		$that = $(this);
		var datalist = $that.parent().parent().data("datalist");
		var web_id = datalist.web_id;
		$("#problem_btn_confire_delete").on("click",function(){
			deleteInfo(web_id);
			$that.parent().parent().remove();
		});
	});
	
	//添加
	$("#problem_btn_create").on("click",function(){
		addInfo();
	});
	
	$("#problem_btn_create_save").on("click",function(){
		var webI = {
				web_title: $("#edit_problem_title_2").val(),
				web_words: $("#edit_problem_body_2").val(),
				web_nav1: $("#edit_problem_nav1_2").val(),
				web_nav2: $("#edit_problem_nav2_2").val(),
				web_nav3: $("#edit_problem_nav3_2").val(),
				web_nav4: $("#edit_problem_nav4_2").val(),
				web_footer: $("#edit_problem_footer_2").val(),
				web_maker: $("#edit_problem_maker_2").val()
		};
		
		console.log(webI);
		$.post(
			"/Shop/webinfo/add.do",
			webI,
			function(){
				showAll();
			}
		);
	});
	
	//通过
	$("#pass_btn").on("click",function(){
		var $a = $("#web_input:checked").parent().parent();
		var datalist = $a.data("datalist");
		var webInfo = datalist;
		$.post(
		"/Shop/webinfo/singleUpdate.do",
		webInfo,
		function(){
			alert("应用成功！")
		}
		);
	});
	
});




function showAll(){
	$("#problem_tbody").empty();
	$("#problem_tbody").html("");
	$.post(
		"/Shop/webinfo/find.do",
		{},
		function(data){
			var list = data.RESULT_CONTENT.webinfolist;
			$(list).each(function(){
				var conText = '<tr><td><input type="checkbox" id="web_input"></td><td>'+this.web_title+'</td><td>'+this.web_words+'</td><td>'+this.web_nav1+'</td><td>'+this.web_nav2+'</td><td>'+this.web_nav3+'</td><td>'+this.web_nav4+'</td><td>'+this.web_footer+'</td><td>'+this.web_maker+'</td><td><button class="uk-button uk-float-left problem_btn_edit" data-uk-modal="{target:\'#edit_problem_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 修改</button><button class="uk-button uk-float-left problem_btn_delete" data-uk-modal="{target:\'#delete_problem_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 删除</button></td></tr>';
				$("#problem_tbody").append(conText);
				$("#problem_tbody tr:last").data("datalist",this);
			});
		}
	);
}

function update($that){
	var $title = $("#edit_problem_panel #edit_problem_title");
	var $words = $("#edit_problem_panel #edit_problem_body");
	var $nav1 = $("#edit_problem_panel #edit_problem_nav1");
	var $nav2 = $("#edit_problem_panel #edit_problem_nav2");
	var $nav3 = $("#edit_problem_panel #edit_problem_nav3");
	var $nav4 = $("#edit_problem_panel #edit_problem_nav4");
	var $footer = $("#edit_problem_panel #edit_problem_footer");
	var $maker = $("#edit_problem_panel #edit_problem_maker");
	$title.val("");
	$words.val("");
	$nav1.val("");
	$nav2.val("");
	$nav3.val("");
	$nav4.val("");
	$footer.val("");
	$maker.val("");
	$("#edit_problem_id").val("");
	var datalist = $that.parent().parent().data("datalist");
	
	var title = datalist.web_title;
	var words = datalist.web_words;
	var nav1 = datalist.web_nav1;
	var nav2 = datalist.web_nav2;
	var nav3 = datalist.web_nav3;
	var nav4 = datalist.web_nav4;
	var footer = datalist.web_footer;
	var maker = datalist.web_maker;
	var webid = datalist.web_id;
	$("#edit_problem_id").val(webid);
	$title.val(title);
	$words.val(words);
	$nav1.val(nav1);
	$nav2.val(nav2);
	$nav3.val(nav3);
	$nav4.val(nav4);
	$footer.val(footer);
	$maker.val(maker);
}

function deleteInfo(web_id){
	$.post(
			"/Shop/webinfo/delete.do",
			{web_id: web_id},
			function(){
				
			}
	);
}

function addInfo(){
	var $title = $("#edit_problem_title_2");
	var $words = $("#edit_problem_body_2");
	var $nav1 = $("#edit_problem_nav1_2");
	var $nav2 = $("#edit_problem_nav2_2");
	var $nav3 = $("#edit_problem_nav3_2");
	var $nav4 = $("#edit_problem_nav4_2");
	var $footer = $("#edit_problem_footer_2");
	var $maker = $("#edit_problem_maker_2");
	$title.val("");
	$words.val("");
	$nav1.val("");
	$nav2.val("");
	$nav3.val("");
	$nav4.val("");
	$footer.val("");
	$maker.val("");
	
}

function replaceInfo(){
	$.post(
			"/Shop/webinfo/singleFindAll.do",
			{},
			function(data){
				var infolist = data.RESULT_CONTENT.webinfolist[0];
				$("title").text(infolist.web_title);
				$("#info_words").text(infolist.web_words);//加了html的id
				$(".masthead ul li:nth-child(1)").find("a").text(infolist.web_nav1);
				$(".masthead ul li:nth-child(2)").find("a").text(infolist.web_nav2);
				$(".masthead ul li:nth-child(3)").find("a").text(infolist.web_nav3);
				$(".masthead ul li:nth-child(4)").find("a").text(infolist.web_nav4);
				$(".ps").prev().text(infolist.web_footer);
				$(".ps").text(infolist.web_maker);
			}
	);
	
}











