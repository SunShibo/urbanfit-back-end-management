$(function() {
    $(".next").click(checkForm);
});

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
                // 取出上次访问的URL
                var lastURL = $.cookie('lastURL') ;
                if (lastURL == null || lastURL == "") {
                    window.location.href = "/module/toHome";
                    return ;
                }
                window.location.href = lastURL ;
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