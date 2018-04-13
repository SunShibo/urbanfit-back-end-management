$(function(){
    initCourseDistrict();
    $("#A_join_course").click(joinCourse);
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");
})

function initCourseDistrict(){
    var courseDistrict = $("input[type='courseDistrict']").val();
    if(courseDistrict != ""){

    }
}

function joinCourse(){

}

