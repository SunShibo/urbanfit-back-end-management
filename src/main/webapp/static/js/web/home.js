$(function(){
    $("li[id^='menu_']").removeClass();
    $("#menu_home").addClass("on");
})

//banner图
$.ajax({
    type: "post",
    url: "/module/list",
    data:{"type": 1},
    dataType: "json",
    success:function(res){
        var html = '';
        var html_1 = '';
        var modulehtml = '';
        var num = 1;
        if(res.code == 1){
            var module = res.data.module;
            var banner = res.data.lstBanner;
            var baseUrl = res.data.baseUrl;  // 图片地址前缀
            // banner部分遍历
            if(banner != ""){
                $.each(banner, function (i, n){
                    if(num == 1){
                        html += '<div class="item active">';
                        html += '<a href="'+n.linkUrl+'"><img src="'+baseUrl+n.imageUrl+'" alt="'+n.title+'"></a>';
                        html += '</div>';
                        html_1 += '<li data-target="#myCarousel" data-slide-to="'+n.sort+'" class="active"></li>';
                    }else{
                        html += '<div class="item">';
                        html += '<a href="'+n.linkUrl+'"><img src="'+baseUrl+n.imageUrl+'" alt="'+n.title+'"></a>';
                        html += '</div>';
                        html_1 += '<li data-target="#myCarousel" data-slide-to="'+n.sort+'"></li>';
                    }
                    num++;
                })
            }

            $(".carousel-inner").html(html);
            $(".carousel-indicators").html(html_1);
            $(".module").html(modulehtml);
        }else{
            alert("接口请求错误");
        }

    }
});