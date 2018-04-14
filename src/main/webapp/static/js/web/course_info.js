$(function(){
    initCourseDistrict();
    $("#A_join_course").click(joinCourse);
    $("li[id^='menu_']").removeClass();
    $("#menu_course").addClass("on");

    $("#city_info").citySelect({
        prov : store.provice,
        city : store.city,
        dist : store.district,
        nodata: "none",
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

//获取url中的参数
function GetRequest(){
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
//接收get参数
var canshu = GetRequest();
//alert(canshu['courseId']);

function joinCourse(){
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

