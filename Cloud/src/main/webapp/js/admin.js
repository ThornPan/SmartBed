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

function getUserList(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getUserList',
        data:{},
        dataType:'json',
        success:function(msg){
            console.log(msg);
            var count=msg.count;
            $("#userList_body").empty();
            for(var i=0;i<count;i++){
                var para = document.createElement("tr");

                var node = document.createElement("td");
                var content = document.createTextNode(msg.user[i].id);
                node.appendChild(content);
                para.appendChild(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg.user[i].name);
                node.appendChild(content);
                para.appendChild(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg.user[i].sex);
                node.appendChild(content);
                para.appendChild(node);

                var node = document.createElement("td");
                var content = document.createTextNode(msg.user[i].age);
                node.appendChild(content);
                para.appendChild(node);

                var element = document.getElementById("userList_body");
                element.appendChild(para);
            }
        }
    })
}
