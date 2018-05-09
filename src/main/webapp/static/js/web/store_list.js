$(function (){
    $("li[id^='about_']").removeClass();
    $("li[id='about_store']").addClass("active");
    $("li[id^='menu_']").removeClass();
    $("#menu_auth").addClass("on");

    $("#city_info").distpicker({
        province : store.provice,
        city : store.city,
        district : store.district,
    });

    $("#proviceId").change(queryStore);
    $("#cityId").change(queryStore);
    $("#districtId").change(queryStore);
})

function queryStore(){
    document.forms[0].submit();
}