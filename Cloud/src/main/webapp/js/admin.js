/**
 * Created by Thorn on 2016/6/23.
 */

function showAdminInfo(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getAdminInfo',
        data:{},
        dataType:'json',
        success:function(msg){
            console.log(msg);
            document.getElementById("admin_name").innerHTML=msg.name;
            document.getElementById("admin_id").innerHTML=msg.id;
        }
    })
}

function refreshUserList(){
    setInterval("getUserList()",1000);
}

function getUserList(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getUserList',
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

$(document).ready(function(){

})
