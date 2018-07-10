$(function (){
    $("li[name^='client_']").removeClass();
    $("#client_order").addClass("on");
    $("#div_order_detail").hide();

    initOrderMasterStatus();
    $("#B_search").click(queryOrderMaster);
    $("select[name='status']").change(queryOrderMaster);
    $("a[id^='A_order_detail_']").click(queryOrderMasterDetail);

    $(".close").click(function(){
        $(this).parents('.kuang').hide();
    });

    $("#closeOrderDetail").click(function(){
        $(this).parents('.kuang').hide();
    });
    // 订单支付
    $("a[id^='A_pay_order_']").click(choosePayment);
    $("#A_payOrder").click(payOrderMasterAgain);

    $(".radio").click(function () {
        $(".radio").each(function (i, v) {
            $(this).find('.radioimg').attr('src', '../static/img/radio.png');
            $(this).removeClass("seleted");
        });
        $(this).find('.radioimg').attr('src', '../static/img/radio1.png');
        $(this).addClass('seleted');
    });
    // 申请退款
    $("a[id^='A_back_money_']").click(function (){
        var orderNum = $(this).data("ordernum");
        openApplyBackMoneyLayer(orderNum);
    });
    $("#B_apply_back_money").click(applyBackMoney);
})

function openApplyBackMoneyLayer(orderNum){
    $("input[name='applyOrderNum']").val(orderNum);
    layer.open({
        title : '申请退款',
        type: 1,
        content : $("#applyBackMoneyDiv"),//指定弹出DIV内容
        area: ['500px', '540px'],
        full: false
    });
}

function applyBackMoney(){
    var orderNum = $("input[name='applyOrderNum']").val();
    var reason = $("textarea[name='reason']").val();
    if(reason == ""){
        alert("请填写申请退款原因！");
        return ;
    }
    // 申请退款操作
    $.ajax({
        type : "post",
        url : "/order/applyBackMoney",    // 调用方法
        data : {"orderNum" : orderNum, "reason" : reason},   // 参数信息
        dataType : "json",
        success : function (result){      // 调用方法成功处理函数
            if(result.code != 1){
                alert(result.msg);
                return ;
            }else{     // 成功处理
                alert("提交申请成功");
                $("#applyBackSpan_" + orderNum + "").text("已申请");
            }
        }
    });
}


// 初始化订单状态
function initOrderMasterStatus() {
    $("select[name='status']").val(status);
}

function queryOrderMaster(){
    $("form").attr("action", "/order/list");
    document.forms[0].submit();
}

function queryOrderMasterDetail(){
    var orderNum = $(this).data("ordernum");
    $.ajax({
        type : "post",
        url : "detail",
        data : {"orderNum" : orderNum},
        dataType : "json",
        success : function(result, status){
            if(result.code != 1){
                alert(result.msg);
                return ;
            }else{
                $('.kuang').show();
                $('.kuang3').show();
                $('.kuang2').hide();
                // 给文本框赋值
                $("#detailOrderNum").text(result.data.orderNum);
                $("#detailCreateTime").text(result.data.createTime);
                $("#detailChildreName").text(result.data.childrenName);
                $("#detailClientMobile").text(result.data.clientMobile);
                $("#detailCourseDistrict").text(result.data.courseDistrict);
                $("#detailCourseName").text(result.data.courseName);
                $("#detailCoursePrice").text(result.data.price);
                $("#detailCourseType").text(result.data.courseTypeName);
                $("#detailStoreName").text(result.data.storeName);
                $("#detailStoreAddress").text(result.data.storeAddress);

                var payment = "支付宝";
                if(result.data.payment == 1){
                    payment = "微信";
                }
                $("#detailPayment").text(payment);
                $("#detailPayPrice").text(result.data.payPrice);
                var status = "未支付";
                if(result.data.status == 1){
                    status = "已支付";
                }else if(result.data.status == 2){
                    status = "申请退款";
                }else if(result.data.status == 3){
                    status = "系统自动取消";
                }else if(result.data.status == 4){
                    status = "退款成功"
                }else if(result.data.status == 5){
                    status = "申请退款失败"
                }
                $("#detailStatus").text(status);
                if(result.data.couponNum != ""){
                    $("#couponDiv").show();
                    $("#couponPrice").text(result.data.couponPrice);
                    $("#couponName").text(result.data.couponName);
                }
                if(result.data.remarks != ""){
                    $("#remarksDiv").show();
                    $("#remarks").text(result.data.remarks);
                }
            }
        }
    });
}

function choosePayment(){
    $("input[name='payOrderNum']").val($(this).data("ordernum"));
    $('.kuang').show();
    $('.kuang2').show();
    $('.kuang3').hide();
}

function payOrderMasterAgain(){
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
    var orderNum = $("input[name='payOrderNum']").val();
    var params = {"orderNum" : orderNum, "payment" : payWay};
    $.ajax({
        url : "/order/payAgain",
        type : "post",
        data : {"params" : JSON.stringify(params)},
        dataType : "json",
        success : function (result){
            if(result.code == 1){
                if(payWay == 0){   // 支付宝支付
                    $('body').html(result.data);
                    $("form").attr("target", "_blank");
                }else if(payWay == 1){  // 微信支付
                    var orderNum = result.data.orderNum;
                    var wechatPayQr = result.data.wechatPayQr;
                    window.location.href = "/order/wechatPay?orderNum=" + orderNum
                        + "&wechatPayQr=" + wechatPayQr;
                }
            }else{
                alert('参数有误');
            }
        }
    })
}