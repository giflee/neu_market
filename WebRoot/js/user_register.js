$(function(){
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	$("#register_auth_code").attr("src","/"+context+"/pre/showImage.do?time="+new Date().getTime()).click(function(){
		 $(this).attr("src","/"+context+"/pre/showImage.do?time="+new Date().getTime());

	 });
});
var emailTag=false;
function verify_user_email(){
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	 var emailPattern = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	 var email=$("#register_user_email").val();
	 if(email==""){
		 $("#lable_register_user_email").attr("class","error-text");
		 $("#lable_register_user_email").text("邮箱不能为空");
		 $("#register_user_email").attr("class","user-name error");
		 emailTag=false;
	 }else if (!emailPattern.test(email)){
		 $("#lable_register_user_email").attr("class","error-text");
		 $("#lable_register_user_email").text("邮箱格式错误");
		 $("#register_user_email").attr("class","user-name error");
		 emailTag=false;
	 }else{
		 
	 }
		
}


var pwdTag=false;
function verify_password(){
	var passwordPattern= /^[a-zA-Z0-9]\w{5,17}$/;
	var password=$("#register_user_password").val();
	if(password==""){
		$("#lable_register_user_password").attr("class","error-text");
		 $("#lable_register_user_password").text("密码不能为空");
		 $("#register_user_password").attr("class","password error");
		 pwdTag=false;
	}else if(!passwordPattern.test(password)){
		$("#lable_register_user_password").attr("class","error-text");
		 $("#lable_register_user_password").text("密码格式错误");
		 $("#register_user_password").attr("class","password error");
		 pwdTag=false;
	}else{
		$("#lable_register_user_password").attr("class","");
		 $("#lable_register_user_password").text("密码");
		 $("#register_user_password").attr("class","password");
		 pwdTag=true;
	}
	
}

var pwd2Tag=false;
function verify_password_two(){
	var password_two=$("#register_user_password_two").val();
	var password=$("#register_user_password").val();
	if(password==password_two){
		$("#lable_register_user_password_two").attr("class","");
		 $("#lable_register_user_password_two").text("确认密码");
		 $("#register_user_password_two").attr("class","password");
		 pwd2Tag=true;
	}else{
		$("#lable_register_user_password_two").attr("class","error-text");
		 $("#lable_register_user_password_two").text("两次输入密码不一致");
		 $("#register_user_password_two").attr("class","password error");
		 pwd2Tag=false;
	}
	
}

var autncodeTag=false;
function verify_register_authcode(){
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	var session_authCode="";
	var authcode=$("#register_authcode_input").val();
	$.ajax({
		type : "POST",
		url :"/"+context+ "/pre/getCode.do",
		dataType : "json",
		success : function(jo) {
				session_authCode=jo.RESULT_CONTENT.code;
				if(session_authCode==authcode){
					$("#label_register_authcode_input").attr("class","title");
					$("#label_register_authcode_input").text("验证码");
					$("#register_authcode_input").attr("class","text");
					autncodeTag=true;
				}else{
					$("#label_register_authcode_input").attr("class","title error-text");
					$("#label_register_authcode_input").text("验证码错误,请重新输入");
					$("#register_authcode_input").attr("class","code error");
					autncodeTag=false;
				}
		}
	});
}

function user_register(){	
//	var urlpath=window.location.pathname;
//	var path=urlpath.split("/");
//	var context=path[1];
	var name=$("#register_user_name").val();
	var email=$("#register_user_email").val();
	var password=$("#register_user_password").val();
//	var state = [];
//	$('input[name="agree_register_item"]:checked').each(function() {
//		state.push($(this).val());
//	});
//	verify_password_two();
//	verify_user_email();
//	verify_password();
//	verify_register_authcode();
//if(emailTag&&pwdTag&&pwd2Tag&&autncodeTag&&state=="true"){
	var Data = {
			"name":name,
			"email" : email,
			"pwd" : password,
		};
		$.ajax({
			type : "POST",
			data : Data,
			datatype : "json",
			url :"/Shop/pre/register.do",
			success : function(jo) {
				if (jo.REQUEST_SUCCESS == 1) {
					window.location.href = "userlogin.html";
				} else {
					$("#admin_login_failure").text(jo.RESULT_MESSAGE)
				}
			}
		});
//}	
}

