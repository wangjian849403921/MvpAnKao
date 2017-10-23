//获取网络数据
var flag = true;
function getData(startTime,endTime,calendar){
    $.ajax({
        url: getDataIP + "startTime=" + startTime + "&endTime=" + endTime,
        data: {},
        dataType : 'json',
        type:'post',
        success: function(data){
            if(data.code == BSuccessCode){
                var buffer = document.getElementById("bufferBox");
                buffer.style.display = "none";
                calendar.drawDefBg(data.object);
                defData = data.object;
                //第一次加载数据的时候，判断默认选择的天数是不是被选中，如果被选中，进行后移
                if(flag){
                    flag = false;
                    var selectArr = getTimeStrArr(defData);
                    var breakFlag = false;
                    for(var i = calendar.index; i < 35; i++){
                        var timeStr = calendar.calendarLies[calendar.index].getAttribute("id-date");
                        for(var j = 0; j < selectArr.length; j++){
                            if(timeStr == selectArr[j]){
                                calendar.index++;
                                break;
                            }else if(j == selectArr.length - 1 && timeStr != selectArr[j]){
                                breakFlag = true;
                                break;
                            }
                        }
                        if(breakFlag){
                            calendar.curDateStr = calendar.calendarLies[calendar.index].getAttribute("id-date");
                            calendar.selectDateStr = calendar.calendarLies[calendar.index].getAttribute("id-date");
                            calendar.endDateStr = calendar.calendarLies[calendar.index].getAttribute("id-date");
                            calendar.defDate = calendar.calendarLies[calendar.index].getAttribute("id-date");
                            break
                        }
                    }
                    initFlag = true;
                    calendar.drawBg();
                    getTypeListData();
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            //alert(XMLHttpRequest);
            //alert(textStatus);
            //alert(errorThrown);
            promptMessage("无网络!!!");
        }
    });
}
//获取预约类型列表
function getTypeListData(){
    $.ajax({
        url: typeListIP,
        data: {},
        dataType : 'json',
        type:'post',
        success: function(data){
            if(data.code == BSuccessCode){
                var typeList = document.getElementsByClassName("typeList")[0];
                var list = data.object;
                while (typeList.hasChildNodes()){
                    typeList.removeChild(typeList.firstChild);
                }
                for(var i = 0; i < list.length; i++){
                    var li = document.createElement("li");
                    li.setAttribute("id-type",list[i].id);
                    typeList.appendChild(li);
                    var p = document.createElement("p");
                    p.innerHTML = list[i].experimentTypeName;
                    li.appendChild(p);
                    var a = document.createElement("a");
                    a.className = "line1";
                    li.appendChild(a);
                    li.onclick = function(e){
                        e.stopPropagation();
                        var p = this.getElementsByTagName("p")[0];
                        chooseTypeBox.innerHTML = p.innerHTML;
                        var parent = this.parentNode;
                        var lis = parent.getElementsByTagName("li");
                        var ps = parent.getElementsByTagName("p");
                        removeArrClass(lis, "current");
                        removeArrClass(ps, "current");
                        addClassName(this, "current");
                        addClassName(p, "current");
                        var chooseTypeTable = document.getElementsByClassName("chooseTypeTable")[0];
                        chooseTypeTable.style.display='none';
                        var type = this.getAttribute("id-type");
                        chooseTypeBox.setAttribute("id-type",type);
                    }
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            //alert(XMLHttpRequest);
            //alert(textStatus);
            //alert(errorThrown);
            promptMessage("无网络!!!");
        }
    });
}