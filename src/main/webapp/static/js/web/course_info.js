$(function(){
    initCourseDistrict();
    $("#A_join_course").click(joinCourse);
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");

    $("#city_info").citySelect({
        prov : store.provice,
        city : store.city,
        dist : store.district,
        //nodata: "none",
        required: false
    });

    $("#proviceId").change(joinCourse);
    $("#cityId").change(joinCourse);
    $("#districtId").change(joinCourse);
})

function initCourseDistrict(){
    var courseDistrict = $("input[type='courseDistrict']").val();
    if(courseDistrict != ""){

    }
}

function joinCourse(){
    document.forms[0].submit();
}

