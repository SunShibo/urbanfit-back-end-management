$(function(){
    //initCourseDistrict();
    $("#submitorder").click(submitorder);
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");

    $('.input').focus(function(){
        $('.input').css("border-color","#ebebeb");
        $(this).css("border-color","#f6d332");
    })

    $(".radio").click(function(){
        $(".radio").each(function(i,v){
            $(this).find('.radioimg').attr('src','../static/img/radio.png');
            $(this).removeClass("seleted");
        });
        $(this).find('.radioimg').attr('src','../static/img/radio1.png');
        $(this).addClass('seleted');
    });

    $('#change').click(function(){
        var couponNum = $.trim($("#ma").val());
        if(couponNum == ''){
            alert('优惠码不能为空');
            $("#ma").focus();
            return;
        }

        $.ajax({
            type:"post",
            url:"/coupon/detail ",
            dataType: "json",
            data: {"couponNum" : couponNum},
            success: function(msg){
                console.log(msg);
                if(msg.code == 1){
                    $('#change').hide();
                    $('#changebtn').show();
                    $('#couponName').text(msg.data.couponName).show();
                }else{
                    alert('优惠码不存在');
                }
            }
        });
    })
})

function submitorder(){
    var payWay = "";
    $(".radio").each(function(i,v){
        if($(this).hasClass("seleted")){
            if ($(this).attr("seleted-value") == "alipay"){
                payWay = 0 ;
            }
            if ($(this).attr("seleted-value") == "wechatpay"){
                payWay = 1 ;
            }
        };
    });
    //alert(payWay);

    var name = $.trim($("#name").val());
    if(name == ''){
        alert('姓名不能为空');
        $("#name").focus();
        return;
    }
    var mobile = $.trim($("#phone").val());
    if(!isMobile(mobile)){
        alert('请输入正确的手机号');
        $("#phone").focus();
        return;
    }
    var params = {
        "childrenName":name,
        "clientMobile":mobile,
        "couponNum":$("#ma").val(),
        "courseId":$('#courseId').val(),
        "payment":payWay
    }
    $.ajax({
        type:"post",
        url:"/order/add",
        dataType: "json",
        data: {"params" : JSON.stringify(params)},
        success: function(msg){
            //console.log(msg);
            if(msg.code == 1){
                //window.location.href='';
                alert('成功');
            }else{
                alert('参数有误');
            }
        }
    });
    //粗略验证手机号
    function isMobile(mobile){
        var re = /^1[0-9]{10}$/;
        var validCode=true;
        if(re.test(mobile))
            return true;
        else
            return false;
    }
}







