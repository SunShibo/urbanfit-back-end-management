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
})

// 初始化订单状态
function initOrderMasterStatus() {
    $("select[name='status']").val(status);
}

function queryOrderMaster(){
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
            }
        }
    });
}