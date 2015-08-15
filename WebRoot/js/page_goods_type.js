$(function(){
	showType();
	
	$("#goods_type_btn_create_save").on("click",function(){
		addType();
	});
	
	$("#type_btn_search").on("click",function(){
		findType();
		$("#type_btn_search_panel").click();
	});

    $(".data-table").on("click", ".goods_type_btn_delete", function () {
    	var type = $(".goods_type_btn_delete").parent().parent().data("type");
    	$("#goods_btn_confire_delete").on("click",function(){
    		 deleteType(type.type_id);
    	});
    	
    	
    });
	
});

function addType(){
	var type_name = $("#type_name").val();
	var Data={
			"type_name":type_name,
	};
	$.ajax({
		type:"post",
		url:project_name+"/type/add.do",
		dataType:"json",
    	data:Data,
    	success:function(jo){
    		$("#type_name").val("")
    		showType();
    	}
	});
}

function showType(){
	$.ajax({
		type:"post",
		url:project_name+"/type/show.do",
		dataType:"json",
		success:function(jo){
	    			var data = jo.RESULT_CONTENT.typeList;
	    			showTypeList(data);
	 	        }
	});
}

function findType(){
	var type_name=$("#type_name_search").val();
	var Data={
			"type_name":type_name,
	};
	$.ajax({
		type:"post",
		url:project_name+"/type/find.do",
		dataType:"json",
    	data:Data,
    	success:function(jo){
    		var data = jo.RESULT_CONTENT.typeList;
    		showTypeList(data);
    	}
	});
}

function deleteType(id){
	var Data={
			"type_id":id,
	};
	$.ajax({
		type:"post",
		url:project_name+"/type/delete.do",
		dataType:"json",
    	data:Data,
    	success:function(jo){
    		if(jo.REQUEST_SUCCESS=="1"){
    			showType();
    		}else{
    			alert("删除失败");
    		}
    	}
	});
}
function showTypeList(data){
	$(".data-table tbody").html("");
	var str="";
	$(data).each(function(){
		str+='<tr>' +
		'<td></td>'+
        '<td>'+this.type_name+'</td>' +
        '<td>'+this.goods_num+'</td>' +
        '<td>'+
        '<button class="uk-button uk-float-left goods_type_btn_delete"'+
        'data-uk-modal="{target:\'#delete_goods_type_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 删除</button>'+
        '</td>'+
        '</tr>';
		 $(".data-table tbody").append(str);
		 str="";
		 $("#type_tbody tr:last").data("type",this);
	});
}


