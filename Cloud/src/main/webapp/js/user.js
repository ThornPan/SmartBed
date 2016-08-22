/**
 * Created by Thorn on 2016/6/23.
 */
var recordCount;
var rowPerPage = 10;
var pageType = "";



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
    console.log(data);
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

function getUserMedicalHistoryList(curPage, pageSize){
    $("#medicalHistoryListBody").empty();
    $("#recordCount").html('0');
    $('#MainContent_AspNetPager_Msg').html(" <a disabled='disabled' style='margin-right:5px;'>← 上一页</a><span class='cpb' style='margin-right:5px;'>1</span><a disabled='disabled' style='margin-right:5px;'>下一页 →</a>");
    $('#CurrentPageSize').html(curPage);//页

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

function getParameter(){
    pageType = "parameter"
    getParameterList(1, rowPerPage);
}

function getParameterList(curPage, pageSize){
    $("#paraBody").empty();
    $("#recordCount").html('0');
    $('#MainContent_AspNetPager_Msg').html(" <a disabled='disabled' style='margin-right:5px;'>← 上一页</a><span class='cpb' style='margin-right:5px;'>1</span><a disabled='disabled' style='margin-right:5px;'>下一页 →</a>");
    $('#CurrentPageSize').html(curPage);//页

    var data=getUserIdPara(2);

    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getParameterList',
        data:{data:data,curPage:curPage,pageSize:pageSize},
        dataType:'json',
        success:function(msg){
            if(msg == null){
                $("#paraBody").html("<tr><td colspan='6' class='red'>未查询到数据！</td></tr>");
            } else if(msg != null){
                if(rowPerPage < msg.list.length){
                    var count = rowPerPage;
                } else {
                    var count = msg.list.length;
                }
                for(var i=0;i<count;i++){
                    var para=document.createElement("tr");
                    if(msg.list[i].danger==true){
                        $(para).attr("class","danger");
                    }else {
                        $(para).attr("class","success");
                    }

                    var btnode=document.createElement("td");
                    $(btnode).html(msg.list[i].bodyTemperature);
                    $(para).append(btnode);

                    var dbpnode=document.createElement("td");
                    $(dbpnode).html(msg.list[i].diastolicPressure);
                    $(para).append(dbpnode);

                    var sbpnode=document.createElement("td");
                    $(sbpnode).html(msg.list[i].systolicPressure);
                    $(para).append(sbpnode);

                    var abpnode=document.createElement("td");
                    $(abpnode).html(msg.list[i].averagePressure);
                    $(para).append(abpnode);

                    var bonode=document.createElement("td");
                    $(bonode).html(msg.list[i].bloodOxygen);
                    $(para).append(bonode);

                    var bgnode=document.createElement("td");
                    $(bgnode).html(msg.list[i].bloodGlucose);
                    $(para).append(bgnode);

                    var hrnode=document.createElement("td");
                    $(hrnode).html(msg.list[i].heartRate);
                    $(para).append(hrnode);

                    var atnode=document.createElement("td");
                    //$(atnode).html(dataConvert(msg.list[i].addTime));
                    $(atnode).html(msg.list[i].addTime);
                    $(para).append(atnode);

                    $("#paraBody").append(para);
                }

                recordCount = msg.recordCount;
                $("#recordCount").html(recordCount);

                var maxPage = recordCount % pageSize == 0 ? parseInt(recordCount / pageSize) : (parseInt(recordCount / pageSize) + 1);

                $('#MainContent_AspNetPager_Msg').html('');//分页链接
                var span = "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)' onclick='PageIndexClick(this)' id='TopPage' pageindex='0'>← 上一页</a>";
                var firstPage = 0;
                var pagecount = 0;//循环的次数
                if (maxPage > 10) {
                    pagecount = 10;
                } else {
                    pagecount = maxPage;
                }
                if (curPage > 10) {
                    pagecount = curPage;
                    var firstPage = curPage - 10;
                    if (firstPage >= 1) {
                        span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + firstPage + ",this)' >...</a>";
                    }
                }
                for (var j = firstPage + 1; j < pagecount + 1; j++) {
                    if (j == curPage) {
                        span += "<span class='cpb' style='margin-right: 5px; cursor: pointer;' onclick='CurPageSizeClick(" + j + ",this)'>" + j + "</span>";
                    } else {
                        span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + j + ",this)' >" + j + "</a>";
                    }
                }
                pagecount = pagecount + 1;
                if (maxPage >= pagecount) {
                    span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + pagecount + ",this)' >...</a>";
                }
                span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)' onclick='PageIndexClick(this)' id='NextPage' pageindex='0'>→ 下一页</a>";
                $('#MainContent_AspNetPager_Msg').html(span);


            }
        }
    })
}

function getAlarm(){
    pageType = "alarm"
    getAlarmList(1, rowPerPage);
}

function getAlarmList(curPage, pageSize){
    $("#alarmBody").empty();
    $("#recordCount").html('0');
    $('#MainContent_AspNetPager_Msg').html(" <a disabled='disabled' style='margin-right:5px;'>← 上一页</a><span class='cpb' style='margin-right:5px;'>1</span><a disabled='disabled' style='margin-right:5px;'>下一页 →</a>");
    $('#CurrentPageSize').html(curPage);//页

    var data=getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getAlarmList',
        data:{data:data,curPage:curPage,pageSize:pageSize},
        dataType:'json',
        success:function(msg) {
            if(msg == null){
                $("#paraBody").html("<tr><td colspan='6' class='red'>未查询到数据！</td></tr>");
            } else if(msg != null){
                if(rowPerPage < msg.list.length){
                    var count = rowPerPage;
                } else {
                    var count = msg.list.length;
                }
                for (var i = 0; i < count; i++) {
                    var para = document.createElement("tr");

                    var nonode = document.createElement("td");
                    $(nonode).html(i+1);
                    $(para).append(nonode);

                    var atnode = document.createElement("td");
                    $(atnode).html(msg.list[i].addTime);
                    $(para).append(atnode);

                    var tpnode = document.createElement("td");
                    $(tpnode).html(msg.list[i].type);
                    $(para).append(tpnode);

                    $("#alarmBody").append(para);
                }

                recordCount = msg.recordCount;
                $("#recordCount").html(recordCount);

                var maxPage = recordCount % pageSize == 0 ? parseInt(recordCount / pageSize) : (parseInt(recordCount / pageSize) + 1);

                $('#MainContent_AspNetPager_Msg').html('');//分页链接
                var span = "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)' onclick='PageIndexClick(this)' id='TopPage' pageindex='0'>← 上一页</a>";
                var firstPage = 0;
                var pagecount = 0;//循环的次数
                if (maxPage > 10) {
                    pagecount = 10;
                } else {
                    pagecount = maxPage;
                }
                if (curPage > 10) {
                    pagecount = curPage;
                    var firstPage = curPage - 10;
                    if (firstPage >= 1) {
                        span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + firstPage + ",this)' >...</a>";
                    }
                }
                for (var j = firstPage + 1; j < pagecount + 1; j++) {
                    if (j == curPage) {
                        span += "<span class='cpb' style='margin-right: 5px; cursor: pointer;' onclick='CurPageSizeClick(" + j + ",this)'>" + j + "</span>";
                    } else {
                        span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + j + ",this)' >" + j + "</a>";
                    }
                }
                pagecount = pagecount + 1;
                if (maxPage >= pagecount) {
                    span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)'  onclick='CurPageSizeClick(" + pagecount + ",this)' >...</a>";
                }
                span += "<a style='margin-right: 5px; cursor: pointer;' href='javascript:void(0)' onclick='PageIndexClick(this)' id='NextPage' pageindex='0'>→ 下一页</a>";
                $('#MainContent_AspNetPager_Msg').html(span);
            }
        }
    })
}

function getTurnList(){
    var data=getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getTurnList',
        data:{data:data},
        dataType:'json',
        success:function(msg) {
            var count = msg.length;
            $("#turnBody").empty();
            for (var i = 0; i < count; i++) {
                var para = document.createElement("tr");

                var nonode = document.createElement("td");
                $(nonode).html(i+1);
                $(para).append(nonode);

                var atnode = document.createElement("td");
                $(atnode).html(dataConvert(msg[i].addTime));
                $(para).append(atnode);

                $("#turnBody").append(para);
            }
        }
    })
}
function getMedicineList(){
    var data = getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getMedicineList',
        data:{data:data},
        dataType:'json',
        success:function(msg) {
            var count = msg.length;
            $("#medicineBody").empty();
            for (var i = 0; i < count; i++) {
                var para = document.createElement("tr");

                var atnode = document.createElement("td");
                $(atnode).html(dataConvert(msg[i].addTime));
                $(para).append(atnode);

                var mnnode = document.createElement("td");
                $(mnnode).html(msg[i].medicine);
                $(para).append(mnnode);

                var dnode = document.createElement("td");
                $(dnode).html(msg[i].dosage);
                $(para).append(dnode);

                $("#medicineBody").append(para);
            }
        }
    })
}

function getToiletList(){
    var data = getUserIdPara(2);
    $.ajax({
        type:"POST",
        url:getUrl()+'/api/getToiletList',
        data:{data:data},
        dataType:'json',
        success:function(msg) {
            var count = msg.length;
            $("#toiletBody").empty();
            for (var i = 0; i < count; i++) {
                var para = document.createElement("tr");

                var nonode = document.createElement("td");
                $(nonode).html(i+1);
                $(para).append(nonode);

                var atnode = document.createElement("td");
                $(atnode).html(dataConvert(msg[i].addTime));
                $(para).append(atnode);

                $("#toiletBody").append(para);
            }
        }
    })
}

//首页、上一页、下一页、尾页点击
function PageIndexClick(obj) {
    //当前第几页
    var CurrenPageSize = $.trim($('#CurrentPageSize').html());
    if (CurrenPageSize != '') {
        CurrenPageSize = parseInt(CurrenPageSize);
    }
    //id
    var type = $(obj).attr('id');
    //首页
    if (type == 'FirstPage') {
        CurrenPageSize = 1;
        //AjaxPage(CurrenPageSize, PageSize);
        $('#CurrentPageSize').html('1');
    }
    //上一页
    else if (type == 'TopPage') {
        if (CurrenPageSize > 1) {
            CurrenPageSize = CurrenPageSize - 1;
        } else {
            CurrenPageSize = 1;
        }
        if(pageType == "parameter"){
            getParameterList(CurrenPageSize,rowPerPage);
        }else if(pageType == "alarm"){
            getAlarmList(CurrenPageSize,rowPerPage);
        }
        //AjaxPage(CurrenPageSize, PageSize);
        $('#CurrentPageSize').html(CurrenPageSize);
    }
    //下一页
    else if (type == 'NextPage') {
        var size = CurrenPageSize + 1;
        var maxpage = recordCount % rowPerPage == 0 ? parseInt(recordCount / rowPerPage) : (parseInt(recordCount /rowPerPage) + 1);
        if (size <= maxpage) {
            CurrenPageSize = CurrenPageSize + 1
        }
        if(pageType == "parameter"){
            getParameterList(CurrenPageSize,rowPerPage);
        }else if(pageType == "alarm"){
            getAlarmList(CurrenPageSize,rowPerPage);
        }
        $('#CurrentPageSize').html(CurrenPageSize);
    }
    //尾页
    else if (type == 'LastPage') {
        CurrenPageSize = recordCount % rowPerPage == 0 ? parseInt(recordCount / rowPerPage) : (parseInt(recordCount /rowPerPage) + 1);
        //AjaxPage(CurrenPageSize, PageSize);
        $('#CurrentPageSize').html(CurrenPageSize);
    }
}

//页数点击
function CurPageSizeClick(curPage, obj) {
    $('#CurrentPageSize').html(curPage);
    if(pageType == "parameter"){
        getParameterList(curPage,rowPerPage);
    }else if(pageType == "alarm"){
        getAlarmList(curPage,rowPerPage);
    }
}