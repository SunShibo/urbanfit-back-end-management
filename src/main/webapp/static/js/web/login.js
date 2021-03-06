$(function() {
    $(".next").click(checkForm);
    $("#A_wechat_login").click(wechatClientLogin);
});

function wechatClientLogin(){
    var redirectUrl = encodeURIComponent("http://client.test.urbanfit.cn/wechat_login_code.jsp?type=loginPage");
    window.location.href = "https://open.weixin.qq.com/connect/qrconnect?appid=wxcb74bd33bb3dece8" +
        "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_login&state=30723559788" +
        "&connect_redirect=1#wechat_redirect";
}

function checkForm(){
    var mobile = $.trim($("#phone").val());
    if(!isMobile(mobile)){
        $('#phonemsg').text('请输入正确的手机号');
        $("#phone").focus();
        return;
    }else{
        $('#phonemsg').text('');
    }
    var pwd = $.trim($("#pwd").val());
    if(pwd == ''){
        $('#pwdmsg').text("请输入您的密码");
        $("#pwd").focus();
        return;
    }else{
        $('#pwdmsg').text('');
    }
    // 调用登录接口
    $.ajax({
        type : "post",
        url : "login",
        data : {"mobile" : mobile, "password" : pwd},
        dataType : "json",
        success : function (result, status){
            if(result.code == 0){
                $('#phonemsg').text('手机号码不存在');
                $("#phone").focus();
                return;
            }else if(result.code == 2){
                $('#pwdmsg').text('密码输入不正确');
                $("#pwd").focus();
                return;
            }else{
                // 登录成功跳转页面
                $('#phonemsg').text('');
                $('#pwdmsg').text('');
                window.location.href = "/module/toHome";
            }
        }
    });
};


//粗略验证手机号
function isMobile(mobile){
    var re = /^1[0-9]{10}$/;
    //var validCode=true;
    if(re.test(mobile))
        return true;
    else
        return false;
}