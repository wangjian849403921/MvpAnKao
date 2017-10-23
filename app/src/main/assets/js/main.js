//入口函数，页面加载完成后执行
window.onload = function(){
    //创建星期列表
    var week = new CreateWeek("week",weekArr);
    week.init();
    //创建日历
    currentCalendar = new CreateMonthDay(currentYear,currentMonth,"calendar",48,60,"drawBox","defDrawBox",30,currentDateStr,startTimeDom,endTimeDom);
    currentCalendar.init();
    //显示当前年月
    currentYearDom.innerHTML = currentYear + "年";
    currentMonthDom.innerHTML = monthArr[monthCount - 1];
    //创建年份列表--用于下拉框
    createYearList();
    //创建天数列表
    createDayList();
    //天数列表的选择
    dayScrollList = new ScrollDayList(dayList,45);
    dayScrollList.init();
    //年份下拉列表显示事件监听
    calendarYearBtn.addEventListener("click",showSelectYearBox,false);
    //月份减少事件
    selectMonthLeftBtn.addEventListener("click",minusMonth,false);
    //月份增加事件
    selectMonthRightBtn.addEventListener("click",addMonth,false);
    //隐藏弹框
    document.addEventListener("click",hideAlert,false);
    //时间选择按钮监听
    selectDayBtn.addEventListener("click",confirmDayNum,false);
    //显示天数选择
    selectDaysBox.addEventListener("click",showDaySelectBox,false);
    //显示隐藏预约类型
    chooseTypeBox.addEventListener("click",showOrHideChooseTypeTable,false);
    //关闭提示
    promptBtn.addEventListener("click",hidePrompt,false);
    //选择预约类型
    //selectType();
    //currentCalendar.drawDefBg(data);
    setTimeout(function(){
        getQueryString("userId");
        var preYear = currentYear;
        var preMonth = currentMonth;
        var preDay = 1;
        if(preMonth == 1){
            preMonth = 12;
            preYear--;
        }else {
            preMonth--;
        }
        var startTime = judgeTimeStr(preYear,preMonth,preDay) + " 00:00:00";
        var endTime;
        var month = currentMonth;
        var day;
        var year = currentYear;
        if(currentMonth == 12){
            month = 1;
            year += 1;
        }else {
            month++;
        }
        day = getMonthDay(year,month);
        endTime = judgeTimeStr(year,month,day) + " 00:00:00";
        getData(startTime,endTime,currentCalendar);
    },10);
    //显示隐藏描述文字
    describeBox.addEventListener("focus",describeBoxFocus,false);
    describeBox.addEventListener("blur",describeBoxBlur,false);

};