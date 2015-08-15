function login_user() {
	var state = [];
	var account = $("#user_login_user_name").val();
	var password = $("#user_login_password").val();
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	var Data = {
		"name" : account,
		"pwd" : password,
	};
	$.ajax({
		type : "POST",
		data : Data,
		datatype : "json",
		url : "/Shop/pre/login.do",
		success : function(jsonObject) {
			if (jsonObject.REQUEST_SUCCESS == 1) {
				var userName=jsonObject.RESULT_CONTENT.user;
				setCookie("user",userName);
					window.location.href = "main.html";
					
			} else {
				$("#lable_user_login_password").attr("class","error-text");
				$("#lable_user_login_password").text(jsonObject.RESULT_MESSAGE);
				$("#user_login_password").attr("class","password error");
			}
		}
	});
}

var emailTag=false;
function verify_login_user_account(){
	 var emailPattern = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	 var email=$("#user_login_user_name").val();
	 if(email==""){
		 $("#lable_user_login_account").attr("class","error-text");
		 $("#lable_user_login_account").text("邮箱不能为空");
		 $("#user_login_user_name").attr("class","user-name error");
		 emailTag=false;
	 }else if(!emailPattern.test(email)){
		 $("#lable_user_login_account").attr("class","error-text");
		 $("#lable_user_login_account").text("邮箱格式错误");
		 $("#user_login_user_name").attr("class","user-name error");
		 emailTag=false;
	 }else{
		 $("#lable_user_login_account").attr("class","");
		 $("#lable_user_login_account").text("账号");
		 $("#user_login_user_name").attr("class","user-name");
		 emailTag=true;
	 }
}

var pwdTag;
function verify_login_user_password(){
	var passwordPattern= /^[a-zA-Z0-9]\w{5,17}$/;
	var password=$("#user_login_password").val();
	if(password==""){
		$("#lable_user_login_password").attr("class","error-text");
		 $("#lable_user_login_password").text("密码不能为空");
		 $("#user_login_password").attr("class","password error");
		 pwdTag=false;
	}else{
		$("#lable_user_login_password").attr("class","");
		 $("#lable_user_login_password").text("密码");
		 $("#user_login_password").attr("class","password");
		 pwdTag=true;
	}
	
}

function login(){
	//verify_login_user_account();
	//verify_login_user_password();
	//if(pwdTag&&emailTag){
		login_user();
	//}
}
/*function set_password() {
	if ($.cookie("rmbAmin") == "true") {
		$('input:checkbox').eq(0).attr("checked",true);
		$("#admin_login_account").val($.cookie("account"));
		$("#admin_login_password").val($.cookie("password"));
	}
}*/
