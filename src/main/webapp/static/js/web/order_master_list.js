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
})

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
                $("#detailCoursePrice").text(result.data.couponPrice);
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
                    status = "已退款";
                }else if(result.data.status == 3){
                    status = "系统自动取消";
                }
                $("#detailStatus").text(status);
                if(result.data.couponNum != ""){
                    $("#couponDiv").show();
                    $("#couponPrice").text(result.data.couponPrice);
                    $("#couponName").text(result.data.couponName);
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
            alert(result.data);
            alert(result.code);
            if(result.code == 1){
                $('body').html(result.data);
                $("form").attr("target", "_blank");
            }else{
                alert('参数有误');
            }
        }
    })
}