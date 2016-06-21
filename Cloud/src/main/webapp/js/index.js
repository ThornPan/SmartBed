window.onload = function () {

	var login = $().getId('login');
	var screen = $().getId('screen');
	login.center(450, 357);
	$().resize(function () {
		login.center(450, 357);
		if (login.css('display') == 'block') {
			screen.lock();
		}
	});
	$().getClass('login').click(function () {
		login.css('display', 'block');
		screen.lock();
	});
	$().getClass('close').click(function () {
		login.css('display', 'none');
		screen.unlock();
	});

};

function login(){
	var userid=document.getElementById("txtUserName").value;
	var userpw=document.getElementById("txtPassword").value;
	var usertype=document.getElementById("userType").value;
	if(userid=="Username"||userpw=="Password"){
		alert("请填写账号")
	}else{
		$.ajax({
			type: "POST",
			url:'/Login',
			data:{action:"login",id: userid,pw:userpw,type:usertype},
			success: function(msg){
				console.log(msg);
				if(msg=="1"&&usertype=="STUDENT"){
					window.location.href="student/index.html";
				}else if(msg=="1"&&usertype=="TEACHER"){
					window.location.href="teacher/index.html";
				}else if(msg=="1"&&usertype=="ADMIN"){
					window.location.href="admin/index.html";
				}else {
					alert("fail");
				}
			}
		})
	}
}

