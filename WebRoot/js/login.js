function login_admin() {
	$("#admin_login_failure").text("");
	var state = [];
	$('input[name="remenber_password"]:checked').each(function() {
		state.push($(this).val());
	});
	var account = $("#admin_login_account").val();
	var password = $("#admin_login_password").val();
	var urlpath=window.location.pathname;
	var path=urlpath.split("/");
	var context=path[1];
	/*
	if (state == "true") {
		$.cookie("rmbAmin", "true", {expires : 7}); // 存储一个带7天期限的 cookie
		$.cookie("account", account, {expires : 7}); // 存储一个带7天期限的 cookie
		$.cookie("password", password, {expires : 7}); // 存储一个带7天期限的 cookie
	} else {
		$.cookie("rmbAmin", "false", {expires : -1});
		$.cookie("account", '', {expires : -1});
		$.cookie("password", '', {expires : -1});
	}
	*/
	var Data = {
		"adminName" : account,
		"adminPwd" : password,
	};
	$.ajax({
		type : "POST",
		data : Data,
		datatype : "json",
		url : "admin/login.do",
		success : function(jsonObject) {
			if (jsonObject.REQUEST_SUCCESS == 1) {
				setCookie("adminName",account);
				window.location.href = "admin_main.html";
			} else {
				$("#admin_login_failure").text(jsonObject.RESULT_MESSAGE)
			}
		}
	});
}

function set_password() {
	if ($.cookie("rmbAmin") == "true") {
		$('input:checkbox').eq(0).attr("checked",true);
		$("#admin_login_account").val($.cookie("account"));
		$("#admin_login_password").val($.cookie("password"));
	}
}



