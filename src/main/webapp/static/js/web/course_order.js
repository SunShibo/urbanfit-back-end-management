$(function(){
    initCourseDistrict();
    $("#submitorder").click(submitorder);
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");
})

function submitorder(){
    //alert(aa);
    var data = {
        courseImageUrl:aa,
        courseId:['courseId'],
        courseDistrict:$("#district").val(),
        courseName:$('#name').text(),
        coursePrice  : $('#price').text(),
        text  : $(".text").text(),
    }

    $.ajax({
        type:"post",
        url:"list",
        dataType: "json",
        data: data,
        success: function(msg){
            //console.log(msg);
            var html = '';
            var html1 = '';
            if(code == 1){
                $.each(msg.data.lstCourse,function(k,v){
                    html +='<div class="sellimg">';
                    html +='<img src="${baseUrl}${course.courseImageUrl}" style="width:440px; height:280px;">';
                    html +='</div>';
                    html +='<ul class="sellbox">';
                    html +='<li><h1>赛法斗-<span id="name">${course.courseName}</span></h1></li>';
                    html +='<li>';
                    html +='<p><img src="../static/img/yang.jpg" width="16" height="16">价&emsp;&emsp;格：<span id="price">${course.coursePrice}元</span></p>';
                    html +='</li>';
                    html +='<li>';
                    html +='<p><img src="../static/img/zhi.jpg" width="15" height="20">上课地域：</p>';
                    html +='<div class="select">';
                    html +='<input type="hidden" name="courseDistrict" id="district" value="${course.courseDistrict}">';
                    html +='<div id="city_info">';
                    html +='<select class="prov" id="s_province" name="s_province"></select>&nbsp;&nbsp;';
                    html +='<select class="city" id="s_city" name="s_city" ></select>&nbsp;&nbsp;';
                    html +='<select class="dist" id="s_county" name="s_county"></select>';
                    html +='</div>';
                    html +='</div>';
                    html +='</li>';
                    html +='<li>';
                    html +='<a href="javascript:void(0);" id="A_join_course">我要报名</a>';
                    html +='</li>';
                    html +='</ul>';
                    html1 +='<p>${course.introduce}</p>';
                })
                $('.sell').html(html);
                $('.text').html(html);
            }else{
                alert('参数有误');
            }
        }
    });
}







