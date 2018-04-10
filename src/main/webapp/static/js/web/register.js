$(function() {
    $(".next").click(checkForm);
    $('#sendcode').click(sendVcode);
});

//获取短信验证
function sendVcode(){
    var _this  = $(this);
    var phone = $('#phone').val();
    if(!isMobile(phone)){
        $('#phonemsg').text('请输入正确的手机号');
        $("#phone").focus();
        return;
    }else{
        $('#phonemsg').text('');
    }

    var validCode=true;
    var time=59;
    var code=$('.yzm');
    if (validCode) {
        validCode=false;
        var t=setInterval(function(){
            code.html('获取中'+time+'S');
            //code.addClass("msgs1");
            time--;
            if(time==0){
                clearInterval(t);
                code.html("重新获取");
                validCode=true;
                //code.removeClass("msgs1");
            }
        },1000)
    }
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
    // 调用注册接口
    $.ajax({
        type : "post",
        url : "register",
        data : {"mobile" : mobile, "password" : pwd, "confirmPassword" : cpwd},
        dataType : "json",
        success : function (result, status){
            console.log(result);
            if(result.code == 0){
                $('#phonemsg').text('手机号码已存在');
                $("#phone").focus();
                return;
            }else if(result.code == 2){
                $('#pwdmsg').text('两次密码输入不正确');
                $("#pwd").focus();
                return;
            }else{
                // 登录成功跳转页面
                $('#phonemsg').text('');
                $('#pwdmsg').text('');
                window.location.href = "loginSuccess";
            }
        }
    });
};

//粗略验证手机号
function isMobile(mobile){
    var re = /^1[0-9]{10}$/;
    var validCode=true;
    if(re.test(mobile))
        return true;
    else
        return false;
}