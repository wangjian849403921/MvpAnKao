//功能函数
var userID = null;
(function() {
    var lastTime = 0;
    var vendors = ['webkit', 'moz'];
    for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
        window.cancelAnimationFrame = window[vendors[x] + 'CancelAnimationFrame'] ||    // Webkit中此取消方法的名字变了
            window[vendors[x] + 'CancelRequestAnimationFrame'];
    }

    if (!window.requestAnimationFrame) {
        window.requestAnimationFrame = function(callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16.7 - (currTime - lastTime));
            var id = window.setTimeout(function() {
                callback(currTime + timeToCall);
            }, timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };
    }
    if (!window.cancelAnimationFrame) {
        window.cancelAnimationFrame = function(id) {
            clearTimeout(id);
        };
    }
}());
//移除className
function removeClassName(node, classStr){
    var arr = [];
    if(node.className != ""){
        arr = node.className.trim().split(" ");
        //移除为空的字段
        for(var n = 0; n < arr.length; n++){
            if(arr[n] == ""){
                arr.splice(n, 1);
                if(n > 0){
                    n--;
                }
            }else {
                arr[n].trim();
                if(classStr == arr[n]){
                    arr.splice(n, 1);
                    n--;
                }
            }
        }
        node.className = "";
        for(var i = 0; i < arr.length; i++){
            node.className += arr[i] + " ";
        }
        node.className = node.className.trim();
    }else {
        return false;
    }
    arr = null;//回收内存
}
//给节点添加指定的 className
function addClassName(node, classStr){
    if(node.className == ""){
        node.className = classStr;
    }else {
        var arr;
        var flag = true;
        arr = node.className.trim().split(" ");
        for (var n = 0; n < arr.length; n++){
            if(arr[n] == ""){
                arr.splice(n, 1);
                if(n > 0){
                    n--;
                }
            }else if(arr[n] == classStr){
                flag = false;
            }
        }
        if(flag){
            node.className = "";
            for(var i = 0; i < arr.length; i++){
                node.className += arr[i] + " ";
            }
            node.className += classStr;
        }
        arr = null;
    }
}
//整体移除数组的指定 className
function removeArrClass(arr, className){
    for(var i = 0; i < arr.length; i++){
        removeClassName(arr[i], className);
    }
}
//隐藏弹框
function hideAlert(){
    var calendarSelectYearBox = document.getElementsByClassName("calendarSelectYearBox")[0];
    calendarSelectYearBox.style.display = "none";
    var chooseTypeTable = document.getElementsByClassName("chooseTypeTable")[0];
    chooseTypeTable.style.display='none';
}

//滚动条动画，传入节点对象，目标当前位置，目的地，
// 动画运行时间(秒)，返回值为 动画 id
function scrollAnimation(obj,curPosTop,desPosTop,time){
    var animationID;//需要返回的动画 ID
    var s = Math.abs(curPosTop - desPosTop);
    var t = time / 2;//用于计算加速度
    var a = s / (t * t);
    var startTime = new Date();//动画函数的起点时间
    var curTop = curPosTop;
    (function animation(){
        var curTime = new Date();//每次动画执行的时间
        if(curTop == desPosTop){
            cancelAnimationFrame(animationID);
        }else {
            if(curTop > desPosTop){//目标位置需要向上运动
                if((curTime - startTime) <= (t * 1000)){
                    curTop = curPosTop - 0.5 * a * Math.pow((curTime - startTime) / 1000,2);
                }else if(((t * 1000) < (curTime - startTime)) && ((curTime - startTime) <= (t * 1000 * 2))){
                    curTop = curPosTop - 0.5 * a * Math.pow(t,2) - a * t * ((curTime - startTime) / 1000 - t) + 0.5 * a * Math.pow((curTime - startTime) / 1000 - t,2)
                }else if((curTime - startTime) > (t * 1000 * 2)){
                    curTop = desPosTop;
                }
            }else {
                if((curTime - startTime) <= (t * 1000)){
                    curTop = curPosTop + 0.5 * a * Math.pow((curTime - startTime) / 1000,2);
                }else if(((t * 1000) < (curTime - startTime)) && ((curTime - startTime) <= (t * 1000 * 2))){
                    curTop = curPosTop + 0.5 * a * Math.pow(t,2) + a * t * ((curTime - startTime) / 1000 - t) - 0.5 * a * Math.pow((curTime - startTime) / 1000 - t,2)
                }else if((curTime - startTime) > (t * 1000 * 2)){
                    curTop = desPosTop;
                }
            }
            obj.scrollTop = curTop;
        }
        animationID = requestAnimationFrame(animation);
    })();
    return animationID;
}
//分割字符串，并且以指定的符号进行拼接，传入dom节点中
function setStr(str,splitStr,needStr,dom){
    var arr = str.split(splitStr);
    var year = arr[0];
    var mon = arr[1];
    var day = arr[2];
    if(mon < 10){
        mon = "0" + mon;
    }
    if(day < 10){
        day = "0" + day;
    }
    dom.innerHTML = year + needStr + mon + needStr + day;
    arr = null;
}
//向父节点添加子节点
function appendDomToParent(parentNode,domName,className){
    var node = document.createElement(domName);
    node.className = className;
    parentNode.appendChild(node);
}
//移除指定节点
function removeDesignatedDom(startDomClass,endDomClass){
    var startDom = document.getElementsByClassName(startDomClass);
    var endDom = document.getElementsByClassName(endDomClass);
    if(startDom.length){
        for(var i = 0; i < startDom.length; i++){
            var parent1 = startDom[i].parentNode;
            parent1.removeChild(startDom[i]);
        }
    }
    if(endDom.length){
        for(var j = 0; j < endDom.length; j++){
            var parent2 = endDom[j].parentNode;
            parent2.removeChild(endDom[j]);
        }
    }
}
//移除所有子节点
function removeAllChild(node){
    while (node.hasChildNodes()){
        node.removeChild(node.firstChild)
    }
}

//转换时间格式，传入时间戳，返回字符串
function translateTimeToStr(time){
    var timeStr;
    var date = new Date(time);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    timeStr = judgeTimeStr(year,month,day);
    return timeStr;
}
//拼接时间字符串
function judgeTimeStr(year,month,day){
    var m = month, d = day;
    if(m < 10){
        m = "0" + m;
    }
    if(d < 10){
        d = "0" + d;
    }
    return year + "-" + m + "-" + d;
}
//将预约列表中的时间转换成字符串，并且存储在数组中
function getTimeStrArr(data){
    var list = data.appointList;
    var arr = [];
    for(var i = 0; i < list.length; i++){
        var startDate = new Date(list[i].startTime);
        var startYear = startDate.getFullYear();
        var startMonth = startDate.getMonth() + 1;
        var startDay = startDate.getDate();
        var endDate = new Date(list[i].endTime);
        var endYear = endDate.getFullYear();
        var endMonth = endDate.getMonth() + 1;
        var endDay = endDate.getDate();
        var startTimeStr = judgeTimeStr(startYear,startMonth,startDay);
        var endTimeStr = judgeTimeStr(endYear,endMonth,endDay);
        var days = getBetweenDays(startTimeStr,endTimeStr);
        var maxDays = getMonthDay(startYear,startMonth);
        arr.push(startTimeStr);
        for(var j = 1; j < days; j++){
            if(startDay < maxDays){
                startDay++;
            }else {
                startDay = 1;
                if(startMonth == 12){
                    startMonth = 1;
                    startYear += 1;
                }else {
                    startMonth++;
                }
            }
            var timeStr = judgeTimeStr(startYear,startMonth,startDay);
            arr.push(timeStr);
        }
    }
    return arr;
}
function promptMessage(message){
    var promBox = document.getElementById("promptBox");
    promBox.style.display = "block";
    var prom = document.getElementById("promptContent");
    prom.innerHTML = message;
}
function hidePrompt(){
    var promBox = document.getElementById("promptBox");
    promBox.style.display = "none";
}
//显示隐藏预约描述的占位符
function describeBoxFocus(){
    if (describeBox.innerHTML == "请对您的预约进行简要描述"){
        describeBox.innerHTML = "";
    }
}
function describeBoxBlur(){
    if (describeBox.innerHTML == ""){
        describeBox.innerHTML = "请对您的预约进行简要描述";
    }
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
        userID = r[2];
    }

}