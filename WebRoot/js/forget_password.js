$(function(){
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	$("#lookback_auth_code").attr("src","/"+context+"/pre/showImage.do?time="+new Date().getTime()).click(function(){
		 $(this).attr("src","/"+context+"/pre/showImage.do?time="+new Date().getTime());

	 });
});
var emailTag = false;
function verify_user_email() {
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	var emailPattern = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	var email = $("#look_back_user_email").val();
	if (email != "") {
			if (emailPattern.test(email)) {
				var Data = {
					"user_email" : email,
				};
				$.ajax({
					type : "POST",
					data : Data,
					datatype : "json",
					url : "/"+context+"/user/isUserEmailOnly",
					success : function(jsonObject) {
						if (jsonObject.REQUEST_SUCCESS == 1) {
							alert(jsonObject.REQUEST_SUCCESS);
							$("#lable_look_back_user_email").attr("class","error-text");
							$("#lable_look_back_user_email").text("不存在该用户");
							$("#look_back_user_email").attr("class","user-name error");
							emailTag = false;
						} else {
							$("#lable_look_back_user_email").attr("class", "");
							$("#lable_look_back_user_email").text("账号");
							$("#look_back_user_email").attr("class", "user-name");
							emailTag = true;
						}
					}
				});
			} else {
				$("#lable_look_back_user_email").attr("class", "error-text");
				$("#lable_look_back_user_email").text("邮箱格式错误");
				$("#look_back_user_email").attr("class", "user-name error");
				emailTag = false;
			}
	} else {
		$("#lable_look_back_user_email").attr("class", "error-text");
		$("#lable_look_back_user_email").text("邮箱不能为空");
		$("#look_back_user_email").attr("class", "user-name error");
		emailTag = false;
	}
}

var autncodeTag=false;
function verify_lookback_authcode(){
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	var session_authCode="";
	var authcode=$("#lookback_authcode_input").val();
	$.ajax({
		type : "POST",
		url : "/"+context+"/lmage/getCode",
		dataType : "json",
		success : function(jsonObject) {
			if (jsonObject.authcode!="error") {
				session_authCode=jsonObject.authcode;
				if(session_authCode==authcode){
					$("#label_lookback_authcode_input").attr("class","title");
					$("#label_lookback_authcode_input").text("验证码");
					$("#lookback_authcode_input").attr("class","text");
					autncodeTag=true;
				}else{
					$("#label_lookback_authcode_input").attr("class","title error-text");
					$("#label_lookback_authcode_input").text("验证码错误,请重新输入");
					$("#lookback_authcode_input").attr("class","code error");
					autncodeTag=false;
				}
			}else{
				$("#label_lookback_authcode_input").attr("class","title error-text");
				$("#label_lookback_authcode_input").text("验证码错误，请再试试");
				$("#lookback_authcode_input").attr("class","code error");
				autncodeTag=false;	
			}
		}
	});
}

function look_password() {
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	var account = $("#look_back_user_email").val();
	var Data = {
		"user_email" : account,
	};
	$.ajax({
		type : "POST",
		data : Data,
		datatype : "json",
		url : "/"+context+"/user/toupdateUserPassword",
		success : function(jsonObject) {
			if (jsonObject.REQUEST_SUCCESS == 1) {
				$("#lable_look_back_user_email").attr("class", "error-text");
				$("#lable_look_back_user_email").text("请登录你的邮箱，修改密码");
			} else {
				$("#lable_look_back_user_email").attr("class", "error-text");
				$("#lable_look_back_user_email").text("修改密码错误，请重试");
			}
		}
	});
}

function lookback_password() {
	verify_user_email();
	verify_lookback_authcode();
	if (emailTag&&autncodeTag) {
		look_password();
	}
}