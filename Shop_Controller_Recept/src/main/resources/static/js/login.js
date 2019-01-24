//进入页面后，通过ajax访问sso工程，认证是否登录
$(function(){
    $.ajax({
        url:"http://10.36.142.17:8087/sso/islogin",
        success:function(data){
            alert(data);
            //json对象 —> json字符串
            if(data != null){
                //登录
                $("#pid").html(data.name + "您好，欢迎来到<b>ShopCZ商城</b> <a href='http://10.36.142.17:8087/sso/logout'>注销</a>");
            } else {
                //未登录
                $("#pid").html("[<a href=\"javascript:login();\">登录</a>][<a href=\"\">注册</a>]");
            }
        },
        dataType:"jsonp",
        jsonpCallback: "islogin"
    });
});

//登录
function login(){
    //获取当前页面的url
    var returnURL = location.href;
    //编码
    returnURL = encodeURI(returnURL, "utf-8");
    //解决多个参数&符号替换问题,否则浏览器会以为只有一个参数
    returnURL = returnURL.replace("&", "%26");

    //跳转到tologin请求
    location.href="http://47.112.22.169:8087/sso/tologin?returnURL=" + returnURL;
}