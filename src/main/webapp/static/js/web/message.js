$(function (){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");

})

$.ajax({
    type: "post",
    url:"projectUrl",
    data:{'type':2},
    dataType: "json",
    success:function(res){
        //alert(res.data.url);
        var html = '';
        var html_1 = '';
        var num = 1;
        if(res.code == 1){
            $.each(res.data,function(k,v){
                if(num == 1){
                    html += '<div class="item active">';
                    html += '<a href="#"><img src="../static/img/banner.jpg" alt="First slide"></a>';
                    html += '</div>';
                    html_1 += '<li data-target="#myCarousel" data-slide-to="'+v.id+'" class="active"></li>';
                }else{
                    html += '<div class="item">';
                    html += '<a href="#"><img src="../static/img/banner.jpg" alt="First slide"></a>';
                    html += '</div>';
                    html_1 += '<li data-target="#myCarousel" data-slide-to="'+v.id+'"></li>';
                }
                num++;
            });

            $(".carousel-inner").html(html);
            $(".carousel-indicators").html(html_1);
        }else{
            alert("接口请求错误");
        }

    }
});


/*
var number = $('#span').html();
alert(number);

$.ajax({
    type: "post",
    url:"cmessage/list",
    data:{'pageNo':},
    dataType: "json",
    success:function(res){
        //alert(res.data.url);
        var html = '';
        var html_1 = '';
        var num = 1;
        if(res.code == 1){
            $.each(res.data,function(k,v){
                if(num == 1){
                    html += '<div class="item active">';
                    html += '<a href="#"><img src="../static/img/banner.jpg" alt="First slide"></a>';
                    html += '</div>';
                    html_1 += '<li data-target="#myCarousel" data-slide-to="'+v.id+'" class="active"></li>';
                }else{
                    html += '<div class="item">';
                    html += '<a href="#"><img src="../static/img/banner.jpg" alt="First slide"></a>';
                    html += '</div>';
                    html_1 += '<li data-target="#myCarousel" data-slide-to="'+v.id+'"></li>';
                }
                num++;
            });

            $(".carousel-inner").html(html);
            $(".carousel-indicators").html(html_1);
        }else{
            alert("接口请求错误");
        }

    }
});
*/




/*
$(function(){
    test("", function(){
        var element = $('#bp-element');
        options = {
            bootstrapMajorVersion:3, //对应的bootstrap版本
            currentPage: ${currentPage }, //当前页数，这里是用的EL表达式，获取从后台传过来的值
            numberOfPages: 5, //每页页数
            totalPages:${totalPages }, //总页数，这里是用的EL表达式，获取从后台传过来的值
            shouldShowPage:true,//是否显示该按钮
            itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            //点击事件
            onPageClicked: function (event, originalEvent, type, page) {
                location.href = "/self?event=toUserOperaLog&page=" + page;
            }
        };
        element.bootstrapPaginator(options);
    })
});
*/



