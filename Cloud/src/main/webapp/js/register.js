// JavaScript Document

function adv_left_hidden(){
   document.getElementById('adv_left').style.display='none';
}

function adv_right_hidden(){
   document.getElementById('adv_right').style.display='none';
}

function divopen(){
document.getElementById('light').style.display='block';
document.getElementById('fade').style.display='block';
}

/*$(function(){
	//调用漂浮插件
	$("body").floatAd({
		url:'http://www.baidu.com'
	});
})*/

//表单验证
var flag = [false, false,false,false];

function showTips(_obj){
    tips = _obj.nextElementSibling;
    switch(_obj.name) {
        case "InputID":
            tips.innerHTML = "身份证号为15位或18位";
            tips.className = "tips grey";
            break;
        case "InputName":
            tips.innerHTML = "姓名由汉字和英文字母组成";
            tips.className = "tips grey";
            break;
        case "InputEmail":
            tips.innerHTML = "xxx@example.com";
            tips.className = "tips grey";
            break;
        case "InputRePwd":
            tips.innerHTML = "请再输入一次密码~";
            tips.className = "tips grey";
            break;
        case "InputPhone":
            tips.innerHTML = "手机~";
            tips.className = "tips grey";
            break;
        default:
            break;
    }
};

var checkInput = function(_obj){
    var val = _obj.value;
    tips = _obj.nextElementSibling;
    switch(_obj.name) {
        case "InputID":
            if(RegExp("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)").test(val)) {
                tips.innerHTML = "身份证号格式正确";
                tips.className = "tips green";
                flag[1] = true;
            } else {
                tips.innerHTML = "请按正确的格式输入身份证号";
                tips.className = "tips red";
                flag[1] = false;
            }
            break;
        case "InputName":
            if(RegExp("^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$").test(val)) {
                tips.innerHTML = "姓名格式正确";
                tips.className = "tips green";
                flag[0] = true;
            } else {
                tips.innerHTML = "请按正确的格式输入姓名";
                tips.className = "tips red";
                flag[0] = false;
            }
            break;
        /*case "InputEmail":
            if(RegExp("\\w+([-+._]\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").test(val)) {
                tips.innerHTML = "邮箱地址格式正确";
                tips.className = "tips green";
                flag[1] = true;
            } else {
                tips.innerHTML = "请输入正确的邮箱地址";
                tips.className = "tips red";
                flag[1] = false;
            }
            break;*/
        case "InputRePwd":
            if(document.getElementById("InputPwd").value==document.getElementById("InputRePwd").value) {
                tips.innerHTML = "两次密码一致";
                tips.className = "tips green";
                flag[2] = true;
            } else {
                tips.innerHTML = "两次密码不一致";
                tips.className = "tips red";
                flag[2] = false;
            }
            break;
        case "InputPhone":
            if(RegExp("^((([0-9]{3,4}-)?[0-9]{7,8})|(1\\d{10}))$").test(val)) {
                tips.innerHTML = "联系方式格式正确";
                tips.className = "tips green";
                flag[3] = true;
            } else {
                tips.innerHTML = "请输入正确的联系方式";
                tips.className = "tips red";
                flag[3] = false;
            }
            break;
        default:
            break;
    }
};

function beforeSubmit(){
    for(i = 0; i <= 3; i++) {
        if(!flag[i]) {
            alert("信息填写不正确");
            return false;
        }
    }
    alert("OK");
    return true;
};

function register(){
    console.log("注册");
    for(i = 0; i <= 3; i++) {
        if(!flag[i]) {
            alert("信息填写不正确");
            return false;
        }
    }
    var id = $("#InputID").val();
    var name = $("#InputName").val();
    var pw = $("#InputPwd").val();
    var phone = $("#InputPhone").val();
    //console.log(name+pw+phone);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/register',
        data:{id:id,name:name,pw:pw,phone:phone},
        success: function(msg){
            if(msg=="success"){
                alert("注册成功");
                $("#myform").reset();
            }else if(msg == "exist"){
                alert("用户已存在");
            }
        }
    })
}
