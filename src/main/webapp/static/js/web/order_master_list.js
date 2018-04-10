$(function (){
    $("li[name^='client_']").removeClass();
    $("#client_order").addClass("on");

    initOrderMasterStatus();
    $("#B_search").click(queryOrderMaster);
    $("select[name='status']").change(queryOrderMaster);
})

// 初始化订单状态
function initOrderMasterStatus() {
    $("select[name='status']").val(status);
}

function queryOrderMaster(){
    document.forms[0].submit();
}