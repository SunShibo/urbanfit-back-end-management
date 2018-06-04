$(function (){
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");

    $("#city_info").distpicker({
        province : course.provice,
        city : course.city,
        district : course.district,
    });

    $("#proviceId").change(queryCourse);
    $("#cityId").change(queryCourse);
    $("#districtId").change(queryCourse);
    $("a[name='A_query_courseType']").click(function (){
        $("input[name='courseType']").val($(this).data("type"));
        queryCourse();
    });
    $("a[id^='A_join_course_']").click(redirectJoinCourse);
})

function queryCourse(){
    document.forms[0].submit();
}

function redirectJoinCourse(){
    var courseId = $(this).data("courseid");
    window.location.href = "/apiCourse/toDetail?courseId=" + courseId;
}