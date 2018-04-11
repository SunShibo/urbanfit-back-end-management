$(function() {
    $(".next").click(checkForm);
    $(".save").click(checkForm1);
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
    var vcode = $.trim($("#vcode").val());
    if(vcode.length != 6){
        $('#yzmmsg').text("请输入验证码");
        $("#vcode").focus();
        return;
    }else {
        $('#yzmmsg').text('');
    }
};

function checkForm1(){
    var pwd = $.trim($("#pwd").val());
    if(pwd == ''){
        $('#pwdmsg').text("请输入您的密码");
        $("#pwd").focus();
        return;
    }else{
        $('#pwdmsg').text('');
    }
    if(pwd.length < 6){
        $('#pwdmsg').text("登陆密码不能小于6位");
        $("#pwd").focus();
        return;
    }else{
        $('#pwdmsg').text('');
    }

    var cpwd = $.trim($("#cpwd").val());
    if(pwd != $.trim($("#cpwd").val())) {
        $('#cpwdmsg').text("两次密码不一致");
        $("#cpwd").focus();
        return;
    }else{
        $('#cpwdmsg').text('');
    }
    // 调用注册接口
    $.ajax({
        type : "post",
        url : "register",
        data : {"password" : pwd, "confirmPassword" : cpwd},
        dataType : "json",
        success : function (result, status){
            if(result.code == 2){
                $('#cpwdmsg').text('两次密码不一致');
                $("#cpwd").focus();
                return;
            }else if(result.code == -3){
                $('#yzmmsg').text('参数错误');
                $("#vcode").focus();
                return;
            }else if(result.code == 1){
                // 成功跳转下一步
                $('#cpwdmsg').text('');
                $('#yzmmsg').text('');
                window.location.href = "registerSuccess";
            }
        }
    });
}

//粗略验证手机号
function isMobile(mobile){
    var re = /^1[0-9]{10}$/;
    //var validCode=true;
    if(re.test(mobile))
        return true;
    else
        return false;
}