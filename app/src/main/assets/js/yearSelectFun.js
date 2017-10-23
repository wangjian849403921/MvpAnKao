//年份选择
//创建年份列表
function createYearList(){
    var begin = currentYear - 20;
    var end = currentYear + 50;
    for (var i = begin; i < end; i++){
        var li = document.createElement("li");
        yearBox.appendChild(li);
        li.setAttribute("id-year",i);
        var a = document.createElement("a");
        li.appendChild(a);
        a.innerHTML = i + "年";
        var div = document.createElement("div");
        li.appendChild(div);
        div.className = "border";
        if(i == currentYear){
            li.className = "current";
            a.className = "current";
        }
        li.onclick = function(){
            var parent = this.parentNode;
            var lies = parent.getElementsByTagName("li");
            var as = parent.getElementsByTagName("a");
            var currentA = this.getElementsByTagName("a")[0];
            removeArrClass(lies, "current");
            removeArrClass(as, "current");
            addClassName(this,"current");
            addClassName(currentA,"current");
            currentYearDom.innerHTML = currentA.innerHTML;
            yearCount = parseInt(this.getAttribute("id-year"));
            currentCalendar.year = yearCount;
            currentCalendar.month = monthCount;
            currentCalendar.reset();
            var calendarSelectYearBox = document.getElementsByClassName("calendarSelectYearBox")[0];
            calendarSelectYearBox.style.display = "none";
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
    }
}
//显示时间选择
function showSelectYearBox(e){
    e.stopPropagation();
    var calendarSelectYearBox = document.getElementsByClassName("calendarSelectYearBox")[0];
    calendarSelectYearBox.style.display = "block";
}