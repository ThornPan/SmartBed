/**
 * Created by Thorn on 2016/6/23.
 */
function getUserInfo(){
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getUserInfo',
        data:{},
        dataType:'json',
        success:function(msg){
            console.log(msg);
            document.getElementById("user_id").innerHTML=msg.id;
            document.getElementById("user_name").innerHTML=msg.name;
            $("#user_age").html(msg.age);
            $("#user_sex").html(msg.sex);
        }
    })
}