/**
 * Created by Thorn on 2016/6/23.
 */
function getUserIdPara(type){
    var data="user";
    if(type==1){
        var url=window.location.search;
    }else if(type==2){
        var url=window.parent.window.location.search;
    }
    if(url!=""){
        console.log(url);
        data=url.substr(1);
        console.log(data);
    }
    return data;
}

function getUserInfo(){
    var data=getUserIdPara(1);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getUserInfo',
        data:{data:data},
        dataType:'json',
        success:function(msg){
            document.getElementById("user_id").innerHTML=msg.id;
            document.getElementById("user_name").innerHTML=msg.name;
            $("#user_age").html(msg.age);
            $("#user_sex").html(msg.sex);
        }
    })
}

function getUserMedicalHistoryList(){
    var data=getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getMedicalHistoryList',
        data:{data:data},
        dataType:'json',
        success:function(msg){
            var count=msg.length;
            $("#medicalHistoryListBody").empty();
            for(var i=0;i<count;i++){
                var para=document.createElement("div");

                var timeRow=document.createElement("h4");

                timeRow.innerHTML=dataConvert(msg[i].addTime);
                $(para).append(timeRow);

                var contentRow=document.createElement("p");
                contentRow.innerHTML=msg[i].content;
                $(para).append(contentRow);

                var nextLine=document.createElement("br");
                $("#medicalHistoryListBody").append(nextLine);
                $("#medicalHistoryListBody").append(para);
            }
        }
    })
}

function getParameterList(){
    var data=getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getParameterList',
        data:{data:data},
        dataType:'json',
        success:function(msg){
            var count=msg.length;
            $("#paraBody").empty();
            for(var i=0;i<count;i++){
                var para=document.createElement("tr");
                if(msg[i].danger==true){
                    $(para).attr("class","danger");
                }else {
                    $(para).attr("class","success");
                }

                var btnode=document.createElement("td");
                $(btnode).html(msg[i].bodyTemperature);
                $(para).append(btnode);

                var dbpnode=document.createElement("td");
                $(dbpnode).html(msg[i].diastolicPressure);
                $(para).append(dbpnode);

                var sbpnode=document.createElement("td");
                $(sbpnode).html(msg[i].systolicPressure);
                $(para).append(sbpnode);

                var abpnode=document.createElement("td");
                $(abpnode).html(msg[i].averagePressure);
                $(para).append(abpnode);

                var bonode=document.createElement("td");
                $(bonode).html(msg[i].bloodOxygen);
                $(para).append(bonode);

                var bgnode=document.createElement("td");
                $(bgnode).html(msg[i].bloodGlucose);
                $(para).append(bgnode);

                var hrnode=document.createElement("td");
                $(hrnode).html(msg[i].heartRate);
                $(para).append(hrnode);

                var atnode=document.createElement("td");
                $(atnode).html(dataConvert(msg[i].addTime));
                $(para).append(atnode);

                $("#paraBody").append(para);
            }
        }
    })
}

function getTurnList(){

}