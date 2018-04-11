$(function (){
    $("li[id^='about_']").removeClass();
    $("li[id='about_store']").addClass("active");
    $("li[id^='menu_']").removeClass();
    $("#menu_about").addClass("on");

    $("#city_info").citySelect({
        prov : store.provice,
        city : store.city,
        dist : store.district,
        nodata: "none",
        required: false
    });

    $("#proviceId").change(queryStore);
    $("#cityId").change(queryStore);
    $("#districtId").change(queryStore);
})

function queryStore(){
    document.forms[0].submit();
}