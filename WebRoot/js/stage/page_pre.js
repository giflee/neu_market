function login(){
	var name=$("#exampleInputEmail1").val();
	var pwd = $("#exampleInputPassword1").val();
	var Data = {
			"name" : name,
			"pwd" : pwd,
		};
	$.ajax({
		type : "POST",
		data : Data,
		datatype : "json",
		url : "/Shop/pre/login.do",
		success : function(jsonObject) {
			if (jsonObject.REQUEST_SUCCESS == 1) {
					window.location.href = "cart.html";
			} 
		}
	});
}

function buy(){
	var userName = getCookie("user");
	if(userName!=""&&userName!=null){
		location.href="cart.html";
	}else{
		location.href="userlogin.html";
	}
}


function logout(){
	$.ajax({
		type : "POST",
		datatype : "json",
		url : "/Shop/pre/logout.do",
		success : function(jsonObject) {
			if (jsonObject.REQUEST_SUCCESS == 1) {
					delCookie("user");
					window.location.href = "main.html";
			} 
		}
	});
}
