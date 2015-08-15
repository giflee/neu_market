//function adminShowUserInfo(){
$(function(){
	page = 1;
	
	adminShowAllUser(page);
	$(".data-table").on("click",".admin_user_show_info",function(){
		adminShowUserInfo($(this));
	});
	$("#admin_btn_search").on("click",function(){
		adminFindByUserName();
		$("#admin_btn_search_panel").click();
	});
	$(".data-table").on("click",".admin_btn_delete",function(){
		$that = $(this);
		$("#admin_btn_confire_delete").click(function(){
			adminResetUserPwd($that);
		});
		
	});
	$(".uk-pagination").attr("data-uk-pagination","{items:"+total+", itemsOnPage:"+size+"}");
		$('[data-uk-pagination]').on('select.uk.pagination',function(e,pageIndex){
			page=pageIndex+1;
			adminShowAllUser(page);
		});
		
	
});

var total=page_parameters.total;
var size=page_parameters.size;
page_parameters.total=null;
page_parameters.size=null;
//用户管理时显示所有用户
//加了分页
function adminShowAllUser(page){
	$(".data-table tbody").html("");
	$("#user_nanage_table tbody  tr").remove();
	$.ajax({
		type: "POST",
		data:{page1:page},
		dataType: "json",
		url: "admin_manage/user.do",
		success: function(jsonObject){
			if(jsonObject.REQUEST_SUCCESS == 1){
				var userList = jsonObject.RESULT_CONTENT.userList;
				page_parameters.total=jsonObject.RESULT_CONTENT.total;
				page_parameters.size=jsonObject.RESULT_CONTENT.size;
				var conText="";
				$(userList).each(function(){
					 conText += ' <tr>'+
		            '<td><input type="checkbox"></td>'+
		            '<td>'+this.user_name+'</td>'+
		            '<td>'+this.user_email+'</td>'+
		            '<td>'+
		                '<button class="uk-button uk-float-left uk-margin-right admin_btn_edit admin_user_show_info" id="aaaa"'+
		                        'data-uk-modal="{target:\'#edit_admin_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 查看信息'+
		                '</button>'+
		                '<button class="uk-button uk-float-left admin_btn_delete"'+
		                        'data-uk-modal="{target:\'#delete_confirm_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 密码重置'+
		                '</button>'+
		            '</td>'+
		        '</tr>';
					$("#user_nanage_table tbody").append(conText);
					conText="";
					$("#user_nanage_table  tr:last").data("user",this);
				});
				
			}
		}
	});
}

//点击查看信息
function adminShowUserInfo($this){
	$("#show_adress_content .start-delete").remove();
		$tr = $this.parent().parent();
		var userList = $tr.data("user");
		var userName = userList.user_name;
		var userId = userList.user_id;
		$("#model_user_name").val(userName);
		$.ajax({
			type: "POST",
			data: {userId: userId},
			dataType: "json",
			url: "admin_manage/findByUserId.do",
			success: function(jsonObject){
				if(jsonObject.REQUEST_SUCCESS == 1){
					var addressList = jsonObject.RESULT_CONTENT.addressList;
					$(addressList).each(function(){
						var conText = 
		                '<div class="uk-form-row start-delete">'+
		                    '<label>收获信息</label>'+
		                    '<input type="text" placeholder="" value="'+this.address_name+'  '+this.address_person+'收  '+this.address_phone+'" disabled style="width:300px; border:none;" id="model_user_adss">'+
		                '</div>';
						$("#show_adress_content").append(conText);
					});
					
				}
			}
		});
}

//点击搜索名字
function adminFindByUserName(){
	$("#user_nanage_table tbody  tr").remove();
	var userName = $(".serach_for_name").val();
	$.ajax({
		type: "POST",
		data: {userName: userName},
		dataType: "json",
		url: "admin_manage/findByUserName.do",
		success: function(jsonObject){
			if(jsonObject.REQUEST_SUCCESS == 1){
				var userList = jsonObject.RESULT_CONTENT.userList;
				$(userList).each(function(){
					var conText = ' <tr>'+
		            '<td><input type="checkbox"></td>'+
		            '<td>'+this.user_name+'</td>'+
		            '<td>'+this.user_email+'</td>'+
		            '<td>'+
		                '<button class="uk-button uk-float-left uk-margin-right admin_btn_edit admin_user_show_info" id="aaaa"'+
		                        'data-uk-modal="{target:\'#edit_admin_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 查看信息'+
		                '</button>'+
		                '<button class="uk-button uk-float-left admin_btn_delete"'+
		                        'data-uk-modal="{target:\'#delete_confirm_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 密码重置'+
		                '</button>'+
		            '</td>'+
		        '</tr>';
					$("#user_nanage_table tbody").append(conText);
					$("#user_nanage_table  tr:last").data("user",this);
				});
			}
		}
	});
}

//点击密码重置
function adminResetUserPwd($that){
	
	$tr = $that.parent().parent();
	var userList = $tr.data("user");
	var userId = userList.user_id;
	$.ajax({
		type: "POST",
		data: {userId: userId},
		dataType: "json",
		url: "admin_manage/reset.do",
		success: function(jsonObject){
			if(jsonObject.REQUEST_SUCCESS == 1){
				
				$(".uk-modal-close").click();
			}
		}
		
	});
}

//登出

























