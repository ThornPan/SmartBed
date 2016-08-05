/*window.onload = function () {

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

};*/

function getUrl(){
	var urlRemote="http://www.znhlc.xyz:8080/smartbed";
	var urlLocalMvn="http://localhost:8080/smartbed";
	var urlLocalIde="http://localhost:8080";
	return urlLocalIde;
}

function dataConvert(timestamp){
	var timestamp =timestamp;
	var d = new Date(timestamp*1);    //根据时间戳生成的时间对象

	var yr = d.getFullYear();

	var mt = d.getMonth() + 1;
	if(mt < 10) mt = "0" + mt;

	var day = d.getDate();
	if(day < 10) day = "0" + day;

	var hr = d.getHours();
	if(hr < 10) hr = "0" + hr;

	var min = d.getMinutes();
	if(min < 10) min = "0" + min;

	var sec = d.getSeconds();
	if(sec < 10) sec = "0" + sec;

	var date = yr + "-" + mt + "-" + day + " " + hr + ":" + min + ":" +sec;
	/*var date = (d.getFullYear()) + "-" +
		(d.getMonth() + 1) + "-" +
		(d.getDate()) + " " +
		(d.getHours()) + ":" +
		(d.getMinutes()) + ":" +
		(d.getSeconds());*/
	return date;
}

function login(){
	var userid=document.getElementById("txtUserName").value;
	var userpw=document.getElementById("txtPassword").value;
	var usertype=document.getElementById("userType").value;
	var source="website";
	console.log(userid+userpw+usertype);
	if(userid=="Username"||userpw=="Password"){
		alert("请填写账号")
	}else{
		$.ajax({
			type: "POST",
			url:getUrl()+'/api/login',
			data:{userId: userid,userPw:userpw,userType:usertype,source:source},
			success: function(msg){
				console.log(msg);
				if(msg=="success"&&usertype=="user"){
					window.location.href=getUrl()+"/user/UserIndex.html";
				}else if(msg=="success"&&usertype=="family"){
					window.location.href="family/FamilyIndex.html";
				}else if(msg=="success"&&usertype=="admin"){
					window.location.href=getUrl()+"/admin/AdminIndex.html";
				}else {
					alert("fail");
				}
			}
		})
	}
}

function logout(){
	$.ajax({
		type:"POST",
		url:getUrl()+'/api/logout',
		data:{source:"website"},
		success:function(msg){
			if(msg=="success"){
				window.location.href=getUrl();
			}
		}
	})
}

