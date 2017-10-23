//天数选择事件
//创建天数列表
function createDayList(){
    var dayList = document.getElementById("dayList");
    for(var i = 0; i <= 26; i++){
        var li = document.createElement("li");
        if(i == 0){
            li.className = "top";
        }else if( i == 26){
            li.className = "bottom";
        }else if(i == 1){
            li.innerHTML = i;
            li.className = "current";
        }else if(i == 2){
            li.innerHTML = i;
            li.className = "scale1";
        }else{
            li.innerHTML = i;
            li.className = "scale2";
        }
        li.setAttribute("id-num",i);
        dayList.appendChild(li);
    }
}
//天数列表滚动条事件
function ScrollDayList(obj,standScale){
    this.obj = obj;
    this.standScale = standScale;
    this.dayNum = 1;
    this.dayList = null;
    this.timerID = null;
    this.animationID = null;
}
ScrollDayList.prototype = {
    constructor: ScrollDayList,
    getDayList: function(){
        this.dayList = this.obj.getElementsByTagName("li");
    },
    scrollEvent: function(){
        var that = this;
        that.obj.onscroll = function(e){
            e.stopPropagation();
            var scrollNum = that.obj.scrollTop;
            var num = Math.round(scrollNum / that.standScale);
            for(var i_0 = 0; i_0 < that.dayList.length; i_0++){
                var dayNum = parseInt(that.dayList[i_0].getAttribute("id-num"));
                if(dayNum == (num + 1)){
                    removeClassName(that.dayList[i_0],"scale1");
                    removeClassName(that.dayList[i_0],"scale2");
                    addClassName(that.dayList[i_0],"current");
                    that.dayNum = dayNum;
                    var preNum = i_0 - 1;
                    var prePreNum = i_0 - 2;
                    var nextNum = i_0 + 1;
                    var nextNextNum = i_0 + 2;
                    if(preNum >= 0){
                        removeClassName(that.dayList[preNum],"current");
                        removeClassName(that.dayList[preNum],"scale2");
                        addClassName(that.dayList[preNum],"scale1");
                    }
                    if(prePreNum >= 0){
                        removeClassName(that.dayList[prePreNum],"current");
                        removeClassName(that.dayList[prePreNum],"scale1");
                        addClassName(that.dayList[prePreNum],"scale2");
                    }
                    if(nextNum < that.dayList.length){
                        removeClassName(that.dayList[nextNum],"current");
                        removeClassName(that.dayList[nextNum],"scale2");
                        addClassName(that.dayList[nextNum],"scale1");
                    }
                    if(nextNextNum < that.dayList.length){
                        removeClassName(that.dayList[nextNextNum],"current");
                        removeClassName(that.dayList[nextNextNum],"scale1");
                        addClassName(that.dayList[nextNextNum],"scale2");
                    }
                }

            }
            clearInterval(that.timerID);
            cancelAnimationFrame(that.animationID);
            that.timerID = setInterval(function(){
                    var curScrollTop = that.obj.scrollTop;
                    if(scrollNum == curScrollTop){//滚动条停止运动
                        var curNum = Math.round(curScrollTop / that.standScale);
                        clearInterval(that.timerID);
                        that.animationID = scrollAnimation(that.obj,that.obj.scrollTop,curNum * that.standScale,0.3);
                    }
                },10);
        };
        that.obj.ontouchstart = function(e){
            e.stopPropagation();
        }
    },
    init: function(){
        this.getDayList();
        this.scrollEvent();
    }
};
//确认天数
function confirmDayNum(e){
    e.stopPropagation();
    body.style.overflow = "auto";
    selectDays.innerHTML = dayScrollList.dayNum;
    daySelectShadow.style.display = "none";
    //绘制日历背景,设置class
    var index = currentCalendar.index;
    var length;
    if(index + dayScrollList.dayNum > 35){
        length = 35;
    }else {
        length = index + dayScrollList.dayNum;
    }
    currentCalendar.needDays = dayScrollList.dayNum;
    var reg = /currentDay/;
    for(var i_0 = index; i_0 < length; i_0++){
        if(!reg.test(currentCalendar.calendarLies[i_0].className)){
            addClassName(currentCalendar.calendarLies[i_0], "current")
        }
    }
    if(dayScrollList.dayNum == 1){
        currentCalendar.endDateStr = currentCalendar.defDate;
    }else {
        var year = parseInt(currentCalendar.defDate.split("-")[0]);
        var month = parseInt(currentCalendar.defDate.split("-")[1]);
        var day = parseInt(currentCalendar.defDate.split("-")[2]);
        var curDays = getMonthDay(year,month);
        if(day + currentCalendar.needDays - 1 > curDays){
            if(month == 12){
                currentCalendar.endDateStr = judgeTimeStr(year + 1,1,day + currentCalendar.needDays - 1 - curDays);
            }else {
                currentCalendar.endDateStr = judgeTimeStr(year,month + 1,day + currentCalendar.needDays - 1 - curDays);
            }
        }else {
            currentCalendar.endDateStr = judgeTimeStr(year,month,day + currentCalendar.needDays - 1);
        }
    }
    var dataList = defData.appointList;
    getEndTimeStrAndNeedDays(dayScrollList.dayNum,dataList,currentCalendar.defDate,currentCalendar);
    selectDays.innerHTML = currentCalendar.needDays;
    currentCalendar.drawBg();
}
//显示天数选择
function showDaySelectBox(e){
    e.stopPropagation();
    daySelectShadow.style.display = "block";
    body.style.overflow = "hidden";
}
//选择天数时，给出最大可选时间，已确定结尾时间，传入选择的天数

function getEndTimeStrAndNeedDays(days,dataList,startTimeStr,calendar){
    //遍历所有的起点时间，获取最近的起点时间
    //计算最大可能的结尾时间字符串
    var endTimeStr;
    var startDateArr = startTimeStr.split("-");
    var year = startDateArr[0];
    var month = startDateArr[1];
    var day = startDateArr[2];
    var monthMaxDays = getMonthDay(year,month);
    if(day + days - 1 > monthMaxDays){
        if(month == 12){
            endTimeStr = judgeTimeStr(year + 1,1,day + days - 1 - monthMaxDays);
        }else {
            endTimeStr = judgeTimeStr(year,month + 1,day + days - 1 - monthMaxDays)
        }
    }else {
        endTimeStr = judgeTimeStr(year,month,day + days - 1);
    }
    for(var i = 0; i < dataList.length; i++){
        var curTimeStr = translateTimeToStr(dataList[i].startTime);
        if(startTimeStr < curTimeStr && curTimeStr < endTimeStr){
            endTimeStr = curTimeStr;
        }
    }
    //将结束日期向前推一天
    var endDateArr = endTimeStr.split("-");
    var endYear = endDateArr[0];
    var endMonth = endDateArr[1];
    var endDay = endDateArr[2];
    if(endDay == 1){
        if(endMonth == 1){
            endYear -= 1;
            endTimeStr = judgeTimeStr(endYear,12,31);
        }else {
            endTimeStr = judgeTimeStr(endYear,endMonth - 1,getMonthDay(endYear,endMonth - 1));
        }
    }else {
        endTimeStr = judgeTimeStr(endYear,endMonth,endDay - 1);
    }
    var needDays = getBetweenDays(startTimeStr,endTimeStr);
    if(days > needDays){
        promptMessage("所选时间已被预约，请重新选择！！！");
        calendar.needDays = needDays;
        calendar.endDateStr = endTimeStr;
    }
    startDateArr = null;
}
//根据两个字符串时间，计算出时间间隔
function getBetweenDays(startTimeStr,endTimeStr){
    var startDateArr = startTimeStr.split("-");
    var endDateArr = endTimeStr.split("-");
    var startTime = new Date(parseInt(startDateArr[0]),parseInt(startDateArr[1]) - 1,parseInt(startDateArr[2]));
    var endTime = new Date(parseInt(endDateArr[0]),parseInt(endDateArr[1]) - 1,parseInt(endDateArr[2]));
    startDateArr = null;
    endDateArr = null;
    return (endTime - startTime) / 86400000 + 1;
}