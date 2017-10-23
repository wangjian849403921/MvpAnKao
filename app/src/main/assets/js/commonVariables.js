//变量
var date = new Date();
var currentYear = date.getFullYear();
var currentMonth = date.getMonth() + 1;
var currentDay = date.getDate();
var currentDateStr = judgeTimeStr(currentYear,currentMonth,currentDay);

var monthCount = currentMonth;//用于记录月份选择
var yearCount = currentYear;//用于记录年份份选择
var currentCalendar;
var monthArr = ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
//年份选择按钮
var calendarYearBtn = document.getElementsByClassName("calendarYearBtnBox")[0];
//天数选择框遮罩
var daySelectShadow = document.getElementsByClassName("daySelectShadow")[0];
//年份
var yearBox = document.getElementById("calendarSelectYearBox");
var currentMonthDom = document.getElementById("monthNum");
var currentYearDom = document.getElementById("currentYear");
//月份改变
var selectMonthLeftBtn = document.getElementById("selectMonthLeftBtn");
var selectMonthRightBtn = document.getElementById("selectMonthRightBtn");
//天数列表
var dayList = document.getElementById("dayList");
var selectDayBtn = document.getElementById("selectDayBtn");
//天数
var selectDays = document.getElementById("selectDays");
var selectDaysBox = document.getElementById("selectDaysBox");
var dayScrollList;
var startTimeDom = document.getElementById("startTime");
var endTimeDom = document.getElementById("endTime");

//选择预约类型
var chooseTypeBox = document.getElementsByClassName("chooseTypeBox")[0];
//预约内容
var describeBox = document.getElementsByClassName("describeBox")[0];
//预约单位
var orderCompanyBox = document.getElementsByClassName("orderCompanyBox")[0];
//预约人姓名
var orderPerson = document.getElementsByClassName("orderPersonBox")[0];
//预约人电话
var orderPhone = document.getElementsByClassName("orderPhone")[0];
var defDescribeContent = "请对您的预约进行简要描述";
var defOrderPerson = "预约人姓名";
var defOrderCompany = "预约单位";
var IP = "http://222.185.56.218:8090/zhengsu-platform-api/api/Experiment/order";//预约的IP地址
var BSuccessCode = 200;
var body = document.getElementsByTagName("body")[0];
var getDataIP = "http://222.185.56.218:8090/zhengsu-platform-api/api/Experiment/requestExperimentOrderClient?";
var typeListIP = "http://222.185.56.218:8090/zhengsu-platform-api/api/Experiment/ExperimentType";

var promptBtn = document.getElementById("promptBtn");
//预约描述
