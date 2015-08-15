$(function(){

	showActivity();
	
	$("#activity_btn_create_save").on("click",function(){
		addActivity();
	});
	
	 $(".data-table").on("click", ".activity_btn_delete", function () {
	    	var activity = $(".activity_btn_delete").parent().parent().data("activity");
	    	$("#activity_btn_confire_delete").on("click",function(){
	    		 deleteActivity(activity.activity_id);
	    	});
	    	
	    });
	 
	 $(".data-table").on("click", ".activity_btn_edit", function () {
		    var activity = $(this).parent().parent().data("activity");
		    $("#activity_btn_create_update").on("click",function(){
		    	updateActivity(activity.activity_id);
		    });	   	
	    });
});




function updateActivity(activity_id){
	var activity_title = $("#edit_activity_title").val();
	var activity_body = $("#edit_activity_body").val();
	var Data={
			"activity_title":activity_title,
			"activity_body":activity_body,
			"activity_id":activity_id
	};
	$.ajax({
		type:"post",
		url:project_name+"/activity/update.do",
		dataproblem:"json",
    	data:Data,
    	success:function(){
    		showActivity();
    	}
	});
}

function addActivity(){
	var activity_title = $("#activity_title").val();
	var activity_body = $("#activity_body").val();
	Data={
			"activity_title":activity_title,
			"activity_body":activity_body,
	};
	$.ajax({
		type:"post",
		url:project_name+"/activity/addActivity.do",
		dataType:"json",
		data:Data,
    	success:function(jo){
    		$("#activity_title").val("")
    		$("#activity_body").val("")
    		showActivity();
    	}
	});
}

function showActivity(){
	$.ajax({
		type:"post",
		url:project_name+"/activity/find.do",
		dataType:"json",
		success:function(jo){
	    			var data = jo.RESULT_CONTENT.activityList;
	    			showActivityList(data);
	 	        }
	});
}

function deleteActivity(id){
	var Data={
			"activity_id":id,
	};
	$.ajax({
		type:"post",
		url:project_name+"/activity/delete.do",
		dataType:"json",
    	data:Data,
    	success:function(jo){
    		if(jo.REQUEST_SUCCESS=="1"){
    			showActivity();
    		}else{
    			alert("删除失败");
    		}
    	}
	});
}

function showActivityList(data){
	$(".data-table tbody").html("");
	var str="";
	$(data).each(function(){
		str+='<tr>' +
		'<td><input type="checkbox"/></td>'+
        '<td>'+this.activity_title+'</td>' +
        '<td>'+this.activity_body+'</td>' +
        '<td>'+
        '<button class="uk-button uk-float-left activity_btn_edit"'+
        'data-uk-modal="{target:\'#edit_activity_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 删除</button>'+
        '<button class="uk-button uk-float-left activity_btn_delete"'+
        'data-uk-modal="{target:\'#delete_activity_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 删除</button>'+
        '</td>'+
        '</tr>';
		 $(".data-table tbody").append(str);
		 str="";
		 $("#activity_tbody tr:last").data("activity",this);
	});
}

