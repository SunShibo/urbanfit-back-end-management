$(function (){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");
})

//banner图及模块
/*$.ajax({
    type: "post",
    url: "list",
    data:{"type": 2},
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
                    //alert(n.linkUrl);    // 链接地址
                    //alert(baseUrl+n.imageUrl);   // 图片地址
                    if(num == 1){
                     html += '<div class="item active">';
                     html += '<a href="'+n.linkUrl+'"><img src="'+baseUrl+n.imageUrl+'" alt="'+n.title+'"></a>';
                     html += '</div>';
                     html_1 += '<li data-target="#myCarousel" data-slide-to="'+n.sort+'" class="active"></li>';
                     }else{
                     html += '<div class="item">';
                     html += '<a href="'+n.linkUrl+'"><img src="'+n.imageUrl+'" alt="'+n.title+'"></a>';
                     html += '</div>';
                     html_1 += '<li data-target="#myCarousel" data-slide-to="'+n.sort+'"></li>';
                     }
                     num++;
                })
            }
            if(module != "" && module.content != ""){
                $.each(module.content, function(i, n){
                    //alert(n.title);    // 标题
                    //alert(n.linkUrl);   // 链接地址
                    //alert(n.imageUrl);   // 图片地址
                    //alert(n.remark);
                    modulehtml += '<li>';
                    modulehtml += '<a href="'+n.linkUrl+'">'+n.title+'</a>';
                    modulehtml += '</li>';
                });
            }
            $(".carousel-inner").html(html);
            $(".carousel-indicators").html(html_1);
            $(".module").html(modulehtml);
        }else{
            alert("接口请求错误");
        }

    }
});*/

//数据及分页
/*var pageNo=1;
$(document).ready(function(){
    $(".previous").click(function(){
        pageNo = pageNo- 1;
        if(pageNo == 1){
            $(this).addClass('disabled')
        }
        reloadPage();
    });

    $(".next").click(function(){
        pageNo += 1;
        if(pageNo == total){
            $(this).addClass('disabled')
        }
        reloadPage();
    });
});


function reloadPage(){
    $.ajax({
        type: "post",
        url : projectUrl + '/message/list',
        data:{'pageNo':pageNo,'pageSize':10},
        dataType: "json",
        success:function(res){
            var listbox = '';
            var Page = '';
            if(res.code == 1){
                var baseUrl0 = res.data.baseUrl;  // 图片地址前缀
                var lstMessage = res.data.lstMessage;
                    total = Math.ceil((res.data.totalRecord)/pageSize);  //计算总页数，获取总条数除以每页条数，小数位向上取整
                Page += '<span>'+pageNo+'-'+total+'</span>';    //把计算出来的当前页数与总页数塞回页面
                $.each(lstMessage,function(k,v){
                    alert(v.content);
                    if(k%2 ==0){
                        listbox += '<li>';
                        listbox += '<a href="'+v.messageId+'">';
                        listbox += '<div class="listimg">';
                        listbox += '    <img src="+baseUrl0+v.thumbnails+">';
                        listbox += '</div>';
                        listbox += '<div class="listtext">';
                        listbox += '    <h1>'+v.title+'<span>'+v.createTime+'</span></h1>';
                        listbox += '    <p>'+v.content+'</p>';
                        listbox += '</div>';
                        listbox += '</a>';
                        listbox += '</li>';
                    }else {
                        listbox += '<li>';
                        listbox += '<a href="'+v.messageId+'">';
                        listbox += '<div class="listtext1">';
                        listbox += '    <h1>'+v.title+'<span>'+v.createTime+'</span></h1>';
                        listbox += '    <p>'+v.content+'</p>';
                        listbox += '</div>';
                        listbox += '</a>';
                        listbox += '</li>';
                    }
                });
                $("#listbox").html(listbox);
            }else{
                alert("接口请求错误00");
            }

        }
    });
};
reloadPage();*/









