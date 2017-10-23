
/* 
 用途：检查输入对象的值是否符合E-Mail格式 
 输入：str 输入的字符串 
 返回：如果通过验证返回true,否则返回false 
 
 */
function isEmail(str) {
    var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
    if (myReg.test(str))
        return true;
    return false;
}
/* 
 用途：检查输入对象的值是否符合 字母，数字，和下划线组成的 用户名格式 
 输入：str 输入的字符串 
 返回：如果通过验证返回true,否则返回false 
 
 */
function isAccountName(str) { 
    var bResult = true;
    //非法字符串的验证
    var myReg = "^[0-9a-zA-Z\_]+$";
    var patt2 = new RegExp(myReg);
    bResult = patt2.test(str);
  
    if (!bResult)
    { 
        return bResult;
    }
//格式验证
    var patt1 = new RegExp("^[a-zA-Z]{1}[\W\w]{5,15}$");
    bResult = patt1.test(str); 
    return bResult; 
}

/**
 * 用途，验证手机号是否符合，默认是验证中国手机号码
 * @param {type} str
 * @returns {Boolean}
 */
function isMobile_China(str)
{
    var patt1 = new RegExp("1[0-9]{2}[0-9]{4}[0-9]{4}$");
    var bResult = patt1.test(str);
   
    return  bResult;
}

function isPassword(str)
{
    var patt1 = new RegExp("^[a-zA-Z0-9]{8,15}$");
    var bResult = patt1.test(str);

    return  bResult;
}

function isVerificationCode(str) {
    var patt1 = new RegExp("^[a-zA-Z0-9]{4,6}$");
    var bResult = patt1.test(str);

    return  bResult;
}
function isNickname(str) {
    var patt1 = new RegExp("^([\u4E00-\u9FA5a-zA-Z0-9]){1,20}$");
    var bResult = patt1.test(str);

    return  bResult;
}
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}