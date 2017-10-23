//将预约数据设置为全局变量，每次拉取数据时进行赋值
var defData;
var initFlag = false;
var weekArr = ["日","一","二","三","四","五","六"];
function CreateWeek(weekID,weekArr){
    this.weekID = weekID;
    this.obj = null;
    this.weekArr = weekArr;
}
CreateWeek.prototype = {
    constructor: CreateWeek,
    createWeek: function(){
        this.obj = document.getElementById(this.weekID);
        for(var i_0 = 0; i_0 < this.weekArr.length; i_0++){
            var weekDay = document.createElement("li");
            weekDay.innerHTML = this.weekArr[i_0];
            if(i_0 == 6){
                weekDay.className = "lastCol";
            }
            this.obj.appendChild(weekDay);
        }
    },
    init: function(){
        this.createWeek();
    }
};
//获取某月的天数，传参(年份，月份)，返回天数--月份为 1 -- 12
function getMonthDay(year,month){
    if(month <= 0){
        return false;
    }
    var date = new Date(year,month,0);
    return date.getDate();
}
//获取某月第一天是周几 month: 1--12
function getFirstDayAsWeek(year,month){
    if(month < 1){
        return false;
    }
    var mon = month - 1;
    var date = new Date(year,mon,1);
    return date.getDay();
}
function CreateMonthDay(year,month,calendarID,width,height,drawBoxID,defDrawBoxID,drawHeight,defDate,startTimeDom,endTimeDom){
    this.obj = null;
    this.year = year;
    this.month = month;
    this.calendarID = calendarID;
    this.days = 0;
    this.currentDay = 0;
    this.currentMonth = 0;
    this.currentYear = 0;
    this.calendarLies = null;
    this.width = width;
    this.height = height;
    this.drawBoxID = drawBoxID;
    this.defDrawBoxID = defDrawBoxID;
    this.drawBox = null;
    this.defDrawBox = null;
    this.drawHeight = drawHeight;
    this.defDate = defDate;
    this.startTimeDom = startTimeDom;
    this.endTimeDom = endTimeDom;
    this.curDateStr = null;
    this.selectDateStr = null;
    this.endDateStr = defDate;
    this.defAppointData = null;//已预约的数据
    this.index = 0;
    this.needDays = 1;
    this.unSelectArr = [];
}
CreateMonthDay.prototype = {
    constructor: CreateMonthDay,
    getObj: function(){
        this.obj = document.getElementById(this.calendarID);
        this.drawBox = document.getElementById(this.drawBoxID);
        this.defDrawBox = document.getElementById(this.defDrawBoxID);
    },
    createCalendarLies: function(){
        removeAllChild(this.obj);
        var left = 0,top = 0;
        for(var i_0 = 0; i_0 < 35; i_0++){
            var day = document.createElement("li");
            if(i_0 >= 28 && (i_0 + 1) % 7 != 0){
                day.className = "bottom";
            }else if(i_0 == 34){
                day.className = "lastCol bottom";
            }else if((i_0 + 1) % 7 == 0){
                day.className = "lastCol";
            }
            day.setAttribute("id-left",left);
            day.setAttribute("id-top",top);
            if((i_0 + 1) % 7 == 0){
                left = 0;
                top += this.height;
            }else {
                left += this.width;
            }
            this.obj.appendChild(day);
        }
        this.calendarLies = this.obj.getElementsByTagName("li");
    },
    getTimeParam: function(){
        this.days = getMonthDay(this.year,this.month);
        var date = new Date();
        this.currentDay = date.getDate();
        this.currentMonth = date.getMonth() + 1;
        this.currentYear = date.getFullYear();
        this.curDateStr = this.currentYear + "_" + this.currentMonth + "_" + this.currentDay;
    },
    setMonthDate: function(){
        //获取某月的第一天
        //今天的index
        var index = 0;
        var flag = false;
        var currentWeek = getFirstDayAsWeek(this.year,this.month);
        var currentDay = judgeTimeStr(this.currentYear,this.currentMonth,this.currentDay);
        var dayLength;
        if(this.days + currentWeek > 35){
            dayLength = 35;
        }else {
            dayLength = this.days + currentWeek;
        }
        for (var i_1 = currentWeek; i_1 < dayLength; i_1++){
            this.calendarLies[i_1].innerHTML = (i_1 - currentWeek + 1);
            var curDate = judgeTimeStr(this.year,this.month,i_1 - currentWeek + 1);
            this.calendarLies[i_1].setAttribute("id-date",curDate);
            if(i_1 - currentWeek + 1 == 1){
                var curMon = document.createElement("div");
                curMon.className = "monthFirstDay";
                curMon.innerHTML = this.month + "月";
                this.calendarLies[i_1].appendChild(curMon);
            }
            if(currentDay == curDate){
                addClassName(this.calendarLies[i_1],"currentDay");
                var curDay = document.createElement("div");
                curDay.className = "currentMonthDay";
                curDay.innerHTML = "今天";
                index = i_1;
                flag = true;
                this.calendarLies[i_1].appendChild(curDay);
            }else if(this.year == this.currentYear && this.month == this.currentMonth && (i_1 - currentWeek + 1) > this.currentDay){
                addClassName(this.calendarLies[i_1],"currentMonthDay");
            }else if(this.year == this.currentYear && this.month > this.currentMonth){
                addClassName(this.calendarLies[i_1],"currentMonthDay");
            }else if(this.year > this.currentYear){
                addClassName(this.calendarLies[i_1],"currentMonthDay");
            }
        }
        //获取前一个月的天数
        var preMonth,year,preMonthDay;
        if(this.month == 1){
            preMonth = 12;
            year = this.year - 1;
        }else {
            preMonth = this.month - 1;
            year = this.year;
        }
        preMonthDay = getMonthDay(year,preMonth);
        //设置前一月的时间
        if(currentWeek > 0){
            for (var i_2 = currentWeek - 1; i_2 >= 0; i_2--){
                var preDate = judgeTimeStr(year,preMonth, preMonthDay);
                this.calendarLies[i_2].setAttribute("id-date",preDate);
                this.calendarLies[i_2].innerHTML = (preMonthDay--);
                if(currentDay == preDate){
                    addClassName(this.calendarLies[i_2],"currentDay");
                }
            }
        }
        //设置下个月
        var nextMonthDay = 1;
        var nextMonth;
        if(this.month == 12){
            nextMonth = 1;
            year = this.year + 1;
        }else {
            nextMonth = this.month + 1;
            year = this.year;
        }
        if(this.days + currentWeek < 35){
            for (var i_3 = this.days + currentWeek; i_3 < 35; i_3++){
                var nextDate =  judgeTimeStr(year,nextMonth,nextMonthDay);
                this.calendarLies[i_3].setAttribute("id-date",nextDate);
                this.calendarLies[i_3].innerHTML = (nextMonthDay);
                if(nextMonthDay == 1){//下个月1号，添加月份标记
                    var mon = document.createElement("div");
                    mon.className = "monthFirstDay";
                    mon.innerHTML = nextMonth + "月";
                    this.calendarLies[i_3].appendChild(mon);
                }
                nextMonthDay++;
                if(currentDay == nextDate){
                    addClassName(this.calendarLies[i_3],"currentDay");
                }
            }
        }
        //默认选择的时间
        removeArrClass(this.calendarLies,"current");
        var reg = /currentDay/;
        for(var n = 0; n < this.calendarLies.length; n++){
            var currDate = this.calendarLies[n].getAttribute("id-date");
            if(currDate == this.defDate){
                if(!reg.test(this.calendarLies[n].className)){
                    addClassName(this.calendarLies[n],"current");
                }
                var div1 = document.createElement("div");
                div1.className = "currentDrawStyle";
                div1.style.width = this.drawHeight + "px";
                var liLeft = parseInt(this.calendarLies[n].getAttribute("id-left"));
                var liTop = parseInt(this.calendarLies[n].getAttribute("id-top"));
                var left = (this.width - this.drawHeight) / 2 + liLeft + 10,
                    top = (this.height - this.drawHeight) / 2 + liTop;
                this.selectDateStr = currDate;
                this.startTimeDom.innerHTML = currDate;
                this.endTimeDom.innerHTML = currDate;
                removeDesignatedDom("selectEndPoint","selectStartPoint");
                appendDomToParent(this.calendarLies[n],"div","selectStartPoint");
                this.index = n;
                var row1 = parseInt((this.index) / 7);
                var count;
                if(this.index + this.needDays > 34){
                    count = 34;
                }else {
                    count = this.index + this.needDays - 1;
                }
                var row2 = parseInt(count / 7);
                div1.style.top = top + "px";
                div1.style.left = left + "px";

                if(row1 == row2){
                    div1.style.width = ((count - this.index) * this.width + this.drawHeight) + "px"
                }else if(row1 < row2){
                    var index1 = 0, index2 = 0;//index1记录 row1 行的最后一个元素,index2 用存储最后一行的首元素的索引值
                    for(var i_0 = this.index; i_0 < 35; i_0++){
                        if((i_0 + 1) % 7 == 0){
                            index1 = i_0;
                            break;
                        }
                    }
                    div1.style.width = ((index1 - this.index) * this.width + this.drawHeight) + "px";
                    var betweenNum = Math.floor((count - index1 - 1) / 7);
                    createDrawDom(this.drawBox,"div","currentDrawStyle",betweenNum,this.width,this.height,7,10,top + this.height,this.drawHeight);
                    var div2 = document.createElement("div");
                    index2 = index1 + betweenNum * 7;
                    div2.className = "currentDrawStyle";
                    div2.style.width = (this.width * (count - index2) -(this.width - this.drawHeight)) + "px";
                    div2.style.left = (10 + (this.width - this.drawHeight) / 2) + "px";
                    div2.style.top = ((betweenNum + 1) * this.height + top) + "px";
                    this.drawBox.appendChild(div2);
                }
                this.drawBox.appendChild(div1);
                var curCount = 0;
                if(this.index + this.needDays > 35){
                    curCount = 35;
                }else {
                    curCount = this.index + this.needDays;
                }
                for(var p = this.index; p < curCount; p++){
                    if(!reg.test(this.calendarLies[p])){
                        addClassName(this.calendarLies[p],"current");
                    }
                }
                break;
            }else if((currDate > this.defDate) && n == 0){
                while(this.drawBox.hasChildNodes()){
                    this.drawBox.removeChild(this.drawBox.firstChild);
                }
                var nextIndex1 = 0, nextIndex2 = 0;
                for(var x = 0; x < 35; x++){
                    var neDate = this.calendarLies[x].getAttribute("id-date");
                    if(neDate == this.endDateStr){
                        nextIndex2 = x;
                        break;
                    }
                }
                var row = Math.floor((nextIndex2 - nextIndex1) / 7);
                var top2 = (this.height - this.drawHeight) / 2;
                createDrawDom(this.drawBox,"div","currentDrawStyle",row,this.width,this.height,7,10,top2,this.drawHeight);
                var div4 = document.createElement("div");
                div4.style.background = "#1bbc9b";
                div4.style.position = "absolute";
                div4.style.borderRadius =  (this.drawHeight / 2) + "px";
                if(nextIndex2 != 0){
                    div4.style.width = (this.width * (nextIndex2 + 1 - (row * 7)) -(this.width - this.drawHeight)) + "px";
                }
                div4.style.height =  this.drawHeight + "px";
                div4.style.left = (10 + (this.width - this.drawHeight) / 2) + "px";
                div4.style.top = ((row) * this.height + top2) + "px";
                this.drawBox.appendChild(div4);
                for(var m = 0; m < nextIndex2; m++){
                    addClassName(this.calendarLies[m],"current");
                }
                break;
            }
        }
        this.startTimeDom.innerHTML = this.selectDateStr;
        this.endTimeDom.innerHTML = this.endDateStr;
        for(var i_6 = 0; i_6 < this.calendarLies.length; i_6++){
            var dateStr = this.calendarLies[i_6].getAttribute("id-date");
            if(dateStr == this.endDateStr){
                removeDesignatedDom("","selectEndPoint");
                appendDomToParent(this.calendarLies[i_6],"div","selectEndPoint");
                break;
            }
        }
    },
    drawSelectDay: function(){
        var that = this;
        that.obj.ontouchstart = function(e){
            if(defData != undefined){
                that.unSelectArr = getTimeStrArr(defData);
            }
            var touch = e.touches[0];
            var x0 = touch.clientX - that.obj.getBoundingClientRect().left;
            var y0 = touch.clientY - that.obj.getBoundingClientRect().top;
            var div1 = document.createElement("div");
            div1.style.position = "absolute";
            div1.style.background = "#1bbc9b";
            div1.style.borderRadius =  (that.drawHeight / 2) + "px";
            div1.style.width = that.drawHeight + "px";
            div1.style.height =  that.drawHeight + "px";
            var left = (that.width - that.drawHeight) / 2, top = (that.height - that.drawHeight) / 2;
            var reg = /currentDay/;
            for(var j_0 = 0; j_0 < that.calendarLies.length; j_0++){
                var left1 = parseInt(that.calendarLies[j_0].getAttribute("id-left"));
                var top1 = parseInt(that.calendarLies[j_0].getAttribute("id-top"));
                if((left1 <= x0 && x0 <= left1 + that.width) && (top1 <= y0 && y0 <= top1 + that.height)){
                    left = left1 + (that.width - that.drawHeight) / 2;
                    top = top1 + (that.height - that.drawHeight) / 2;
                    var curTimeStr = that.calendarLies[j_0].getAttribute("id-date");
                    var curTimeArr = curTimeStr.split("-");
                    var year = parseInt(curTimeArr[0]);
                    var month = parseInt(curTimeArr[1]);
                    var day = parseInt(curTimeArr[2]);
                    if(year < that.currentYear){
                        return false;
                    }else if(year == that.currentYear && month < that.currentMonth){
                        return false;
                    }else if(year == that.currentYear && month == that.currentMonth && day < that.currentDay){
                        return false;
                    }
                    for(var j_2 = 0; j_2 < that.unSelectArr.length; j_2++){
                        if(curTimeStr == that.unSelectArr[j_2]){
                            return false;
                        }
                    }

                    removeArrClass(that.calendarLies,"current");

                    //根据需要选择的天数，添加class
                    var length;
                    if(j_0 + that.needDays > 35){
                        length = 35;
                    }else {
                        length = j_0 + that.needDays;
                    }
                    for(var j_1 = j_0; j_1 < length; j_1++){
                        if(!reg.test(that.calendarLies[j_1].className)){
                            addClassName(that.calendarLies[j_1],"current");
                        }
                    }
                    that.defDate = that.selectDateStr = that.calendarLies[j_0].getAttribute("id-date");
                    that.startTimeDom.innerHTML = that.selectDateStr
                    removeDesignatedDom("selectEndPoint","selectStartPoint");
                    appendDomToParent(that.calendarLies[j_0],"div","selectStartPoint");
                    that.index = j_0;
                }
            }
            removeAllChild(that.drawBox);
            div1.style.top = top + "px";
            div1.style.left = left + 10 + "px";
            var row1 = parseInt((that.index) / 7);
            var count;
            if(that.index + that.needDays > 34){
                count = 34;
            }else {
                count = that.index + that.needDays - 1;
            }
            var row2 = parseInt(count / 7);
            if(row1 == row2){
                div1.style.width = ((count - that.index) * that.width + that.drawHeight) + "px"
            }else if(row1 < row2){
                var index1 = 0, index2 = 0;//index1记录 row1 行的最后一个元素,index2 用存储最后一行的首元素的索引值
                for(var i_0 = that.index; i_0 < 35; i_0++){
                    if((i_0 + 1) % 7 == 0){
                        index1 = i_0;
                        break;
                    }
                }
                div1.style.width = ((index1 - that.index) * that.width + that.drawHeight) + "px";
                var betweenNum = Math.floor((count - index1 - 1) / 7);
                createDrawDom(that.drawBox,"div","currentDrawStyle",betweenNum,that.width,that.height,7,10,top + that.height,that.drawHeight);
                var div2 = document.createElement("div");
                index2 = index1 + betweenNum * 7;
                div2.className = "currentDrawStyle";
                div2.style.width = (that.width * (count - index2) -(that.width - that.drawHeight)) + "px";
                div2.style.left = (10 + (that.width - that.drawHeight) / 2) + "px";
                div2.style.top = ((betweenNum + 1) * that.height + top) + "px";
                that.drawBox.appendChild(div2);
            }
            that.drawBox.appendChild(div1);

            var year2 = parseInt(that.defDate.split("-")[0]);
            var month2 = parseInt(that.defDate.split("-")[1]);
            var day2 = parseInt(that.defDate.split("-")[2]);
            var curDays = getMonthDay(year2,month2);
            if(day2 + that.needDays - 1 > curDays){
                if(month2 == 12){
                    that.endDateStr = judgeTimeStr(year2,1,day2 + that.needDays - 1 - curDays);
                    that.endTimeDom.innerHTML = that.endDateStr;
                }else {
                    that.endDateStr = judgeTimeStr(year2,month2 + 1,day2 + that.needDays - 1 - curDays);
                    that.endTimeDom.innerHTML = that.endDateStr;
                }
            }else {
                that.endDateStr = judgeTimeStr(year2,month2,day2 + that.needDays - 1);
                that.endTimeDom.innerHTML = that.endDateStr;
            }
            for(var i_6 = 0; i_6 < that.calendarLies.length; i_6++){
                var dateStr = that.calendarLies[i_6].getAttribute("id-date");
                if(dateStr == that.endDateStr){
                    removeDesignatedDom("","selectEndPoint");
                    appendDomToParent(that.calendarLies[i_6],"div","selectEndPoint");
                    break;
                }
            }

            if(defData != undefined){
                getEndTimeStrAndNeedDays(that.needDays,defData.appointList,that.selectDateStr,that);
                that.drawBg();
                selectDays.innerHTML = that.needDays;
            }
        }
    },
    drawBg: function(){
        var that = this;
        var flag = false;
        var startFlag = true;
        for(var i_5 = 0; i_5 < 35; i_5++){
            var curDate = that.calendarLies[i_5].getAttribute("id-date");
            if(curDate == that.defDate || curDate == that.endDateStr){
                flag = true;
                break;
            }
            if(i_5 == 0 && curDate > that.defDate){
                startFlag = false;
            }
        }
        removeArrClass(this.calendarLies,"current");
        removeAllChild(that.drawBox);
        if(flag){
            if(startFlag){//起点在显示的月内
                var div1 = document.createElement("div");
                var reg = /currentDay/;
                div1.className = "currentDrawStyle";
                div1.style.width = that.drawHeight + "px";
                var row1 = parseInt((that.index) / 7);
                var count;
                if(that.index + that.needDays > 34){
                    count = 34;
                }else {
                    count = that.index + that.needDays - 1;
                }
                var row2 = parseInt(count / 7);
                var left = (that.width - that.drawHeight) / 2 + (that.index % 7) * that.width, top = (that.height - that.drawHeight) / 2 + row1 * that.height;
                div1.style.top = top + "px";
                div1.style.left = left + 10 + "px";
                if(row1 == row2){
                    div1.style.width = ((count - that.index) * that.width + that.drawHeight) + "px"
                }else if(row1 < row2){
                    var index1 = 0, index2 = 0;//index1记录 row1 行的最后一个元素,index2 用存储最后一行的首元素的索引值
                    for(var i_0 = that.index; i_0 < 35; i_0++){
                        if((i_0 + 1) % 7 == 0){
                            index1 = i_0;
                            break;
                        }
                    }
                    div1.style.width = ((index1 - that.index) * that.width + that.drawHeight) + "px";
                    var betweenNum = Math.floor((count - index1 - 1) / 7);
                    createDrawDom(that.drawBox,"div","currentDrawStyle",betweenNum,that.width,that.height,7,10,top + that.height,that.drawHeight);
                    var div2 = document.createElement("div");
                    index2 = index1 + betweenNum * 7;
                    div2.className = "currentDrawStyle";
                    div2.style.width = (that.width * (count - index2) -(that.width - that.drawHeight)) + "px";
                    div2.style.left = (10 + (that.width - that.drawHeight) / 2) + "px";
                    div2.style.top = ((betweenNum + 1) * that.height + top) + "px";
                    that.drawBox.appendChild(div2);
                }
                for(var i = that.index; i < count + 1; i++){
                    if(!reg.test(that.calendarLies[i].className)){
                        addClassName(that.calendarLies[i],"current");
                    }
                }
                that.drawBox.appendChild(div1);

                var year2 = parseInt(that.defDate.split("-")[0]);
                var month2 = parseInt(that.defDate.split("-")[1]);
                var day2 = parseInt(that.defDate.split("-")[2]);
                var curDays = getMonthDay(year2,month2);
                if(day2 + that.needDays - 1 > curDays){
                    if(month2 == 12){
                        that.endDateStr = judgeTimeStr(year2 + 1,1,day2 + that.needDays - 1 - curDays);
                        that.endTimeDom.innerHTML = that.endDateStr;
                    }else {
                        that.endDateStr = judgeTimeStr(year2,month2 + 1, day2 + that.needDays - 1 - curDays);
                        that.endTimeDom.innerHTML = that.endDateStr;
                    }
                }else {
                    that.endDateStr = judgeTimeStr(year2,month2, day2 + that.needDays - 1);
                    that.endTimeDom.innerHTML = that.endDateStr;
                }
            }else {//起点在上一月
                var nextIndex1 = 0, nextIndex2 = 0;
                for(var x = 0; x < 35; x++){
                    var neDate = this.calendarLies[x].getAttribute("id-date");
                    if(neDate == this.endDateStr){
                        nextIndex2 = x;
                        break;
                    }
                }
                var row = Math.floor((nextIndex2 - nextIndex1) / 7);
                var top2 = (this.height - this.drawHeight) / 2;
                createDrawDom(this.drawBox,"div","currentDrawStyle",row,this.width,this.height,7,10,top2,this.drawHeight);
                var div4 = document.createElement("div");
                div4.className = "currentDrawStyle";
                div4.style.width = (this.width * (nextIndex2 - (row * 7)) -(this.width - this.drawHeight)) + "px";
                div4.style.left = (10 + (this.width - this.drawHeight) / 2) + "px";
                div4.style.top = ((row) * this.height + top2) + "px";
                this.drawBox.appendChild(div4);
                for(var m = 0; m < nextIndex2; m++){
                    addClassName(this.calendarLies[m],"current");
                }
            }

        }
        for(var i_6 = 0; i_6 < this.calendarLies.length; i_6++){
            var dateStr = this.calendarLies[i_6].getAttribute("id-date");
            if(dateStr == this.endDateStr){
                removeDesignatedDom("","selectEndPoint");
                appendDomToParent(that.calendarLies[i_6],"div","selectEndPoint");
                if(initFlag){
                    initFlag = false;
                    removeDesignatedDom("selectStartPoint","");
                    appendDomToParent(that.calendarLies[i_6],"div","selectStartPoint");
                    that.startTimeDom.innerHTML = this.endDateStr;
                }
                break;
            }
        }
    },
    drawDefBg: function(data){
        removeAllChild(this.defDrawBox);
        var list = data.appointList;
        for(var i = 0; i < list.length; i++){
            //拼接起始时间字符串
            var startTimeStr = translateTimeToStr(list[i].startTime);
            //拼接结束字符串
            var endTimeStr = translateTimeToStr(list[i].endTime);
            //遍历日历节点，进行预约绘图计算
            if(judgeTimeRange(this.calendarLies, startTimeStr, endTimeStr)){
                var startIndex = getIndex(this.calendarLies,startTimeStr);
                var endIndex = getIndex(this.calendarLies,endTimeStr);
                //计算所需行数，从而绘制矩形
                var startRow = Math.ceil((startIndex + 1)/ 7);
                var endRow = Math.ceil((endIndex + 1)/ 7);
                var top = (startRow - 1) * this.height + (this.height - this.drawHeight) / 2;
                var startLeft = parseInt(this.calendarLies[startIndex].getAttribute("id-left")) + 10;
                var startTop = parseInt(this.calendarLies[startIndex].getAttribute("id-top")) + (this.height - this.drawHeight) / 2;
                var endLeft = parseInt(this.calendarLies[endIndex].getAttribute("id-left")) + 10;
                var endTop = parseInt(this.calendarLies[endIndex].getAttribute("id-top")) + (this.height - this.drawHeight) / 2;
                if(startRow == endRow){//同一行
                    var num1 = endIndex - startIndex + 1;
                    createDrawDom(this.defDrawBox,"div","preDrawStyle",1,this.width,this.height,num1,startLeft,top,this.drawHeight);
                }else {//不同行
                    //先进行第一行绘制，确定个数
                    var firstNum;
                    if(endRow - startRow > 0 && startRow == 0){
                        top = (this.height - this.drawHeight) / 2;
                        firstNum = (startRow + 1) * 7 - startIndex;
                    }else {
                        firstNum = startRow * 7 - startIndex;
                    }

                    createDrawDom(this.defDrawBox,"div","preDrawStyle",1,this.width,this.height,firstNum,startLeft,top,this.drawHeight);
                    //确定中间行的行数
                    var rows = endRow - startRow - 1;
                    createDrawDom(this.defDrawBox,"div","preDrawStyle",rows,this.width,this.height,7,10,top + this.height,this.drawHeight);
                    //绘制最后行
                    var lastRowFirstIndex = (endRow - 1) * 7;
                    if(endRow - startRow > 0 && startRow == 0){
                        lastRowFirstIndex = (endRow) * 7;
                    }else {
                        lastRowFirstIndex = (endRow - 1) * 7;
                    }

                    console.log(lastRowFirstIndex);
                    var lastNum = endIndex - lastRowFirstIndex + 1;
                    createDrawDom(this.defDrawBox,"div","preDrawStyle",1,this.width,this.height,lastNum,10,top + (endRow - startRow) * this.height,this.drawHeight);
                }
                var icon = data.prefix + list[i].icon;
                createIconDom(this.defDrawBox,"div","iconBox",icon,startTop + 30,startLeft + 10);
                createIconDom(this.defDrawBox,"div","iconBox",icon,endTop + 30,endLeft + 10);
                console.log(i + startTimeStr + "--startIndex : " + startIndex + " startRow: " +startRow);
                console.log(i + endTimeStr + "--endIndex: " + endIndex + " endRow: " + endRow);
            }

        }
    },
    reset: function(){
        if(("ontouchend" in document) ? true : false){
            this.obj.ontouchstart = null;
            this.obj.ontouchmove = null;
            this.obj.ontouchend = null;
        }else {
            this.obj.onmousedown = null;
            this.obj.onmousemove = null;
            this.obj.onmouseup = null;
        }
        this.createCalendarLies();
        this.getTimeParam();
        this.setMonthDate();
        this.drawSelectDay();
        removeAllChild(this.drawBox);
        removeAllChild(this.defDrawBox);
        this.setMonthDate();
    },
    init: function(){
        this.getObj();
        this.createCalendarLies();
        this.getTimeParam();
        this.setMonthDate();
        this.drawSelectDay();
    }
};
//传入时间字符，与节点进行比较，如果时间字符串小于第一个节点的时间，则返回 0
//如果大于最后一个节点的时间，则返回节点长度 length - 1，否则正常返回
function getIndex(domArr,timeStr){
    for(var i = 0; i < domArr.length; i++){
        var curTimeStr = domArr[i].getAttribute("id-date");
        if(i == 0){
            if(timeStr < curTimeStr){
                return 0;
            }
        }else if(i == domArr.length - 1){
            if(timeStr > curTimeStr){
                return (domArr.length - 1);
            }
        }else {
            if(timeStr == curTimeStr){
                return i;
            }
        }
    }
    return 0;
}
//创建节点，并添加指定样式
function createDrawDom(parentDom,domName,className,row,width,heigth,num,defLeft,defTop,drawHeight){
    for(var i = 0; i < row; i++){
        var node = document.createElement(domName);
        node.className = className;
        node.style.width = (width * num - (width - drawHeight)) + "px";
        node.style.left = (defLeft + (width - drawHeight) / 2) + "px";
        node.style.top = (i * heigth + defTop) + "px";
        parentDom.appendChild(node);
    }
}
function createIconDom(parentNode,domName,className,background,top,left){
    var node = document.createElement(domName);
    node.className = className;
    node.style.background = "url(" + background + ")";
    node.style.top = top + "px";
    node.style.left = left + "px";
    node.style.backgroundSize = "cover";
    parentNode.appendChild(node);
}
//判断预约结束时间是否在本月之前,或者开始日期是否在本月之后
//flag 为起点或结束点标志，0 表示起点， 1 表示结束点
function judgeTimeRange(domList,startTimeStr,endTimeStr){
    var curStartTimeStr = domList[0].getAttribute("id-date");
    var curLastTimeStr = domList[domList.length - 1].getAttribute("id-date");
    if((curStartTimeStr <= startTimeStr && startTimeStr <= curLastTimeStr) || (curStartTimeStr <= endTimeStr && endTimeStr <= curLastTimeStr)){
        return true;
    }
    return false;
}
//当获取预约数据时，重置起点，以防时间被选取
function resetStartSelect(calendar,startTimeStr,endTimeStr,needDay){
    calendar.selectDateStr = startTimeStr;
    calendar.defDate = startTimeStr;
    calendar.curDateStr = startTimeStr;
    calendar.endDateStr = endTimeStr;
    calendar.needDays = needDay;
    calendar.setMonthDate();
    calendar.drawBg();
}