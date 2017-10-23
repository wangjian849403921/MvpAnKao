//提交预约
function sendDate(){
    var type = chooseTypeBox.getAttribute("id-type");
    if(type == 0){
        promptMessage("请选择预约类型!!!");
        return false;
    }
    if (describeBox.innerHTML == "" || describeBox.innerHTML == defDescribeContent){
        promptMessage("请输入预约内容!!!");
        return false;
    }
    if(!isNickname(orderCompanyBox.value) || orderCompanyBox.value == defOrderCompany){
        promptMessage("请填入正确的公司!!!");
        return false;
    }
    if(orderPerson.value == defOrderPerson || !isNickname(orderPerson.value)){
        promptMessage("请输入正确的姓名格式!!!");
        return false;
    }
    if(!isMobile_China(orderPhone.value)){
        promptMessage("请输入正确的手机号!!!");
        return false;
    }
    var startTime = startTimeDom.innerHTML;
    var endTime = endTimeDom.innerHTML;
    $.ajax({
        url: IP,
        data : {
            "experimentUserId": userID,
            "experimentTypeIdfk": type,
            "experimentNote": describeBox.innerHTML,
            "startTime":startTime + " 00:00:00",
            "endTime":endTime + " 23:59:59",
            "experimentCompany": orderCompanyBox.value,
            "experimentPhone": orderPhone.value,
            "experimentUserName": orderPerson.value
        },
        dataType : 'json',
        type:'post',
        success: function(data){
            if(data.code == BSuccessCode){
                promptMessage("预约成功!!!");
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            promptMessage("预约失败!!!");
        }
    });
}