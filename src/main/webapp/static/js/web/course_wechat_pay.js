var interval;
$(function (){
    interval = setInterval(queryOrderMasterStatus, 2000);
})

function queryOrderMasterStatus(){
    var orderNum = $("input[name='orderNum']").val();
    $.ajax({
        url : "/order/orderDetail",
        type : "post",
        data : {"orderNum" : orderNum},
        dataType : "json",
        success : function (result){
            if(result.code == 1){
                if(result.data == 1){
                    window.clearInterval(interval);
                    window.location.href = "/order/paySuccess";
                }
            }else {
                alert(result.msg);
                return ;
            }
        }
    });
}