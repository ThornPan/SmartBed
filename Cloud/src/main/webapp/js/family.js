/**
 * Created by Thorn on 2016/8/3.
 */
function showFamilyInfo(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getFamilyInfo',
        data:{},
        dataType:'json',
        success:function(msg){
            console.log(msg);
            /*$("#family_id").html(msg.id);*/
            $("#family_name").html(msg.name);
            var phone = msg.phone.substring(0,3) + "****" + msg.phone.substring(7,11);
            $("#family_phone").html(phone);
        }
    })
}

function refreshUserList(){
    setInterval("getFamilyUserList()",1000);
}

function getFamilyUserList(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getFamilyUserList',
        data:{},
        dataType:'json',
        success:function(msg){
            console.log(msg);
            var count=msg.length;
            $("#userList_body").empty();
            for(var i=0;i<count;i++){
                var para = document.createElement("tr");
                if(msg[i].status==1){
                    $(para).attr("class","success");
                }else if(msg[i].status==2){
                    $(para).attr("class","danger");
                }else if(msg[i].status==3){
                    $(para).attr("class","warning");
                }

                var node = document.createElement("td");
                var content= document.createElement("a");
                content.innerHTML=msg[i].id;
                $(content).attr("class","userList_id");
                $(node).append(content);
                $(para).append(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg[i].name);
                node.appendChild(content);
                para.appendChild(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg[i].sex);
                node.appendChild(content);
                para.appendChild(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg[i].age);
                node.appendChild(content);
                para.appendChild(node);

                var element = document.getElementById("userList_body");
                element.appendChild(para);
            }
            $(".userList_id").click(function () {
                window.open(getUrl()+"/user/UserIndex.html?"+$(this).html());
            })
        }
    })
}

function addBind(){
    var bindId = $("#addBind_id").val();
    var bindPw = $("#addBind_pw").val();
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/addBind',
        data:{id:bindId,pw:bindPw},
        success:function(msg){
            if(msg=="success"){
                alert("成功");
            } else {
                alert("失败");
            }
        }
    })
}