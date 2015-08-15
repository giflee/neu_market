$(function(){
	showProblem();
	
	$("#problem_btn_create_save").on("click",function(){
		alert(123);
		addProblem();
	});
	
    $(".data-table").on("click", ".problem_btn_edit", function () {
    	var problem=null;
	    var problem = $(this).parent().parent().data("problem");
	    alert(problem);
	    $("#problem_btn_create_update").on("click",function(){
	    	alert(2);
	    	updateProblem(problem.problem_id);
	    });	   	
      });
    	
    
    $(".data-table").on("click", ".problem_btn_delete", function () {
    	var problem = $(this).parent().parent().data("problem");
    	console.log(problem);
    	$("#problem_btn_confire_delete").on("click",function(){
    		 deleteProblem(problem.problem_id);
    	});	   	
    });
	
});

function addProblem(){
	var problem_title = $("#add_problem_title").val();
	var problem_body = $("#add_problem_body").val();
	var Data={
			"problem_title":problem_title,
			"problem_body":problem_body,
	};
	$.ajax({
		type:"post",
		url:project_name+"/problem/add.do",
		dataproblem:"json",
    	data:Data,
    	success:function(jo){
    		$("#add_problem_title").val("");
    		$("#add_problem_body").val("");
    		showProblem();
    	}
	});
}

function showProblem(){
	$.ajax({
		type:"post",
		url:project_name+"/problem/find.do",
		dataproblem:"json",
		success:function(jo){
	    			var data = jo.RESULT_CONTENT.problemList;
	    			showProblemList(data);
	 	        }
	});
}

function updateProblem(problem_id){
	var problem_title = $("#edit_problem_title").val();
	var problem_body = $("#edit_problem_body").val();
	console.log(problem_title);
	console.log(problem_body);
	var Data={
			"problem_title":problem_title,
			"problem_body":problem_body,
			"problem_id":problem_id
	};
	$.ajax({
		type:"post",
		url:project_name+"/problem/update.do",
		dataproblem:"json",
    	data:Data,
    	success:function(){
    		//$("#edit_problem_title").val("");
    		//$("#edit_problem_body").val("");
    		showProblem();
    	}
	});
}

function deleteProblem(problem_id){
	var Data={
			"problem_id":problem_id,
	};
	$.ajax({
		type:"post",
		url:project_name+"/problem/delete.do",
		dataproblem:"json",
    	data:Data,
    	success:function(jo){
    		if(jo.REQUEST_SUCCESS=="1"){
    			showProblem();
    		}else{
    			alert("删除失败");
    		}
    	}
	});
}
function showProblemList(data){
	$(".data-table tbody").html("");
	var str="";
	$(data).each(function(){
		str+='<tr>' +
		'<td><input type="checkbox"/></td>'+
        '<td>'+this.problem_title+'</td>' +
        '<td>'+this.problem_body+'</td>' +
        '<td>'+
        '<button class="uk-button uk-float-left problem_btn_edit"'+
        'data-uk-modal="{target:\'#edit_problem_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 修改</button>'+
        '<button class="uk-button uk-float-left problem_btn_delete"'+
        'data-uk-modal="{target:\'#delete_problem_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 删除</button>'+
        '</td>'+
        '</tr>';
		 $(".data-table tbody").append(str);
		 str="";
		 $("#problem_tbody tr:last").data("problem",this);
		// alert(this.problem_title);
	});
}


