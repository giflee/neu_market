$(function(){
	getTypeList();//添加商品模态里的商品类别
	
	findGood(1);
	$(".uk-pagination").attr("data-uk-pagination","{items:"+total+", itemsOnPage:"+size+"}");
	 page=1;
	$('[data-uk-pagination]').on('select.uk.pagination',function(e,pageIndex){
		page=pageIndex+1;
		findGood(page);
	});
	$("#upLoadFile").change(function(){ 
    	findSize(this);
    	checkType(this);
     });
	//删除商品
	 $(".data-table").on("click", ".goods_btn_delete", function () {
	    	var good = $(this).parent().parent().data("good");
	    	$that = $(this);
	    	$("#goods_btn_confire_delete").on("click",function(){
	    		 deleteGoods(good.goods_id,$that);
	    	});
	 });
	 //修改商品
	 $(".data-table").on("click",".goods_btn_edit",function(){
		 var good = $(this).parent().parent().data("good");
		 $that = $(this);
		 showEdit(good);
		 $("#g1").on("click",function(){
			 editGood($that,good);
    	});
	 });
	
	$("#goods_btn_create_save").on("click",function(){
		var  type_id=$("#goods_type_add").val();
		var goods_name=$("#goods_name_add").val();
		var goods_price=$("#goods_price_add").val();
		if(checkString(goods_name)&&checkNum(goods_price)){
			addGoods(type_id,goods_name,goods_price);
			$("#goods_type_add").val("");
			$("#goods_name_add").val("");
			$("#goods_price_add").val("");
			$("#upLoadFile").val("");
		}
	});
	
	$("#goods_btn_search").on("click",function(){
		findGood(1);
		$("#goods_btn_search_panel").click();
		//$(".uk-pagination li:first").addClass("uk-active");
	});
});

var total=page_parameters.total;
var size=page_parameters.size;
page_parameters.total=null;
page_parameters.size=null;

//修改
function editGood($that,good){
	var good_id = good.goods_id;
	var goods_name=$("#goods_name_edit").val();
	var goods_price=$("#goods_price_edit").val();
	var Data={
			"goods_id":good_id,
			"goods_name":goods_name,
			"goods_price":goods_price,
	}
	$.ajaxFileUpload({
		url:project_name+"/goods/editGood.do",
		secureuri:false,  //是否启用安全提
 	    fileElementId: 'upLoadFile_edit',
 	    dataType:"text",
 	    data:Data,
 	    success:function(jo){
 		   //因为返回格式的问题  必须对返回的数据进行处理  否则无法捕获
 		  jo = parseJson(jo);
	   	  if(jo.REQUEST_SUCCESS=="1"){
	   		$that.parent().prev().prev().prev().html(goods_name);
	   		$that.parent().prev().html(goods_price);
	   	  }else if(jo.ERROR_CODE=="2"){
	   		  alert(jo.RESULT_MESSAGE);
	   	  }else{
	   		 alert(jo.RESULT_MESSAGE);
	   	  }
  		}
	});
	
}

//显示修改页面
function showEdit(good){
	$("#type_name_edit").val(good.type.type_name);
	$("#goods_name_edit").val(good.goods_name);
	$("#goods_price_edit").val(good.goods_price);
	$("#goods_pic_edit").attr("src",project_name+good.goods_pic);
}

//删除商品
function deleteGoods(id,$that){
	$.ajax({
		type:"post",
		url:project_name+"/goods/delete.do",
		dataType:"json",
		data:{"goods_id":id},
		success:function(jo){
			if(jo.REQUEST_SUCCESS=="1"){
				$that.parent().parent().remove();
			}else{
				alert(jo.RESULT_MESSAGE);
			}
		}
	});
}

//查找商品
function findGood(page){
	var type_id= $("#goods_type").val();
	var goods_name = $("#goods_name").val();
	Data={
			"type_id":type_id,
			"goods_name":goods_name,
			"page1":page,
	}
	$.ajax({
		type:"post",
		url:project_name+"/goods/find.do",
		dataType:"json",
		data:Data,
		success:function(jo){
			if(jo.REQUEST_SUCCESS=="1"){
				var data = jo.RESULT_CONTENT.goods;
				page_parameters.total=jo.RESULT_CONTENT.total;
				page_parameters.size=jo.RESULT_CONTENT.size;
				showGoodsList(data);
			}else{
				alert(jo.RESULT_MESSAGE);
			}
		}
	});
	
}

//渲染页面
function showGoodsList(data){
	$(".data-table tbody").html("");
	var str="";
	$(data).each(function(){
		str+='<tr>' +
		'<td><input type="checkbox"></td>'+
        '<td>'+this.goods_name+'</td>' +
        '<td>'+this.type.type_name+'</td>' +
        '<td>'+this.goods_price+'元</td>' +
        '<td>'+
        '<button class="uk-button uk-float-left uk-margin-right goods_btn_edit"'+
        'data-uk-modal="{target:\'#edit_goods_panel\',bgclose:false}"><i class="uk-icon-edit"></i> 修改价格</button>'+
        '<button class="uk-button uk-float-left goods_btn_delete"'+
        'data-uk-modal="{target:\'#delete_goods_panel\',bgclose:false}"><i class="uk-icon-trash"></i> 删除</button>'+
        '</td>'+
        '</tr>';
		 $(".data-table tbody").append(str);
		 str="";
		 $("#goods_tbody tr:last").data("good",this);
	});
	
}
//获取新增商品时商品类型
function getTypeList(){
	$.ajax({
		type:"post",
		url:project_name+"/type/show.do",
		dataType:"json",
		success:function(jo){
	    	var data = jo.RESULT_CONTENT.typeList;
	    	$("#goods_type").append('<option value="000">全部</option>');
	    	$(data).each(function(){
	    		$("#goods_type_add").append('<option value="'+this.type_id+'">'+this.type_name+'</option>');
	    		$("#goods_type").append('<option value="'+this.type_id+'">'+this.type_name+'</option>');
	    		$("#goods_type_add option:last").data("type",this);
	    		});
	     }
	});
}

//新添商品
function addGoods(type_id,goods_name,goods_price){
	var Data={
			"type_id":type_id,
			"goods_name":goods_name,
			"goods_price":goods_price
	}
	$.ajaxFileUpload({
		url:project_name+"/goods/addGoods.do",
		secureuri:false,  //是否启用安全提
 	    fileElementId: 'upLoadFile_add',
 	    dataType:"text",
 	    data:Data,
 	    success:function(jo){
 		   //因为返回格式的问题  必须对返回的数据进行处理  否则无法捕获
 		  jo = parseJson(jo);
	   	  if(jo.REQUEST_SUCCESS=="1"){
	   		findGood(1);
	   	  }else if(jo.ERROR_CODE=="2"){
	   		  alert(jo.RESULT_MESSAGE);
	   	  }else{
	   		 alert(jo.RESULT_MESSAGE);
	   	  }
  		}
	});
}
//转换文件上传返回JSON的格式
function parseJson(data){
	  var start = data.indexOf(">"); 
	    if(start != -1) {  
	      var end = data.indexOf("<", start + 1);  
	      if(end != -1) {  
	        data = data.substring(start + 1, end);  
	       }  
	    }  
	   data = JSON.parse(data);
	   return data;
}
function checkType(obj){
	var array = new Array('jpg', 'png');  //可以上传的文件类型  
    if (obj.value == '') {   
        return true;  
    }  
    else {  
        var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）  
        var isExists = false;  
        for (var i in array) {  
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {  
                isExists = true;  
                return true;  
            }  
        }  
        if (isExists == false) {  
        	obj.value = null;  
            alert("请上传.jpg或.png格式图片");  
            return false;  
        }  
        return false;
    }
}
	//检查文件大小
function findSize(obj)
{	
  var size = (obj.files[0].size/1024).toFixed(0);
   if(size>=10000){
	  alert("文件太大");
	  obj.value = null;  
  }
}
//检查是否为空String 
function checkString(value){
		var str = value;
        if(str==""||str==null){
          alert("值不能为空！");
          return false;
        }else{
    	    return true;
        }
}

function checkNum(value){
	 var reg = new RegExp("^[1-9][0-9]*.[0-9]{2}$");
     if(!reg.test(value)){
   	  	alert("价格格式为XX.XX,且首位不能为0");
         return false;
     }else{
   	  return true;
     }
}