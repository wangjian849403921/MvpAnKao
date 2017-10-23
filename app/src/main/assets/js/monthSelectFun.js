//月份减
function minusMonth(){
    if(monthCount > 1){
        monthCount--;
    }else {
        monthCount = 12;
        yearCount--;
    }
    monthNum.innerHTML = monthArr[monthCount - 1];
    var lies = yearBox.getElementsByTagName("li");
    var as = yearBox.getElementsByTagName("a");
    removeArrClass(lies, "current");
    removeArrClass(as, "current");
    for(var i_0 = 0; i_0 < lies.length; i_0++ ){
        var year = parseInt(lies[i_0].getAttribute("id-year"));
        if(year == yearCount){
            addClassName(lies[i_0],"current");
            addClassName(as[i_0],"current");
            currentYearDom.innerHTML = as[i_0].innerHTML;
        }
    }

    currentCalendar.year = yearCount;
    currentCalendar.month = monthCount;
    currentCalendar.reset();
    //currentCalendar.drawDefBg(data);
    var buffer = document.getElementById("bufferBox");
    buffer.style.display = "block";
    var preYear = yearCount;
    var preMonth = monthCount;
    var preDay = 1;
    if(preMonth == 1){
        preMonth = 12;
        preYear--;
    }else {
        preMonth--;
    }
    var startTime = judgeTimeStr(preYear,preMonth,preDay) + " 00:00:00";
    var endTime;
    var nextMonth = monthCount;
    var nextDay;
    var nextYear = yearCount;
    if(nextMonth == 12){
        nextMonth = 1;
        nextYear += 1;
    }else {
        nextMonth++;
    }
    nextDay = getMonthDay(nextYear,nextMonth);
    endTime = judgeTimeStr(nextYear,nextMonth,nextDay) + " 00:00:00";
    getData(startTime,endTime,currentCalendar);
}
//月份加
function addMonth(){
    if(monthCount < 12){
        monthCount++;
    }else {
        monthCount = 1;
        yearCount++;
    }
    monthNum.innerHTML = monthArr[monthCount - 1];
    var lies = yearBox.getElementsByTagName("li");
    var as = yearBox.getElementsByTagName("a");
    removeArrClass(lies, "current");
    removeArrClass(as, "current");
    for(var i_0 = 0; i_0 < lies.length; i_0++ ){
        var year = parseInt(lies[i_0].getAttribute("id-year"));
        if(year == yearCount){
            addClassName(lies[i_0],"current");
            addClassName(as[i_0],"current");
            currentYearDom.innerHTML = as[i_0].innerHTML;
        }
    }
    currentCalendar.year = yearCount;
    currentCalendar.month = monthCount;
    currentCalendar.reset();
    var buffer = document.getElementById("bufferBox");
    buffer.style.display = "block";
    //上个月的时间
    var preYear = yearCount;
    var preMonth = monthCount;
    var preDay = 1;
    if(preMonth == 1){
        preMonth = 12;
        preYear--;
    }else {
        preMonth--;
    }
    var startTime = judgeTimeStr(preYear,preMonth,preDay) + " 00:00:00";
    //下个月的时间
    var endTime;
    var nextMonth = monthCount;
    var nextDay;
    var nextYear = yearCount;
    if(nextMonth == 12){
        nextMonth = 1;
        nextYear += 1;
    }else {
        nextMonth++;
    }
    nextDay = getMonthDay(nextYear,nextMonth);
    endTime = judgeTimeStr(nextYear,nextMonth,nextDay) + " 00:00:00";
    getData(startTime,endTime,currentCalendar);
}