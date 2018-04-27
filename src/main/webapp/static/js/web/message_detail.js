$(function (){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");

})


$.ajax({
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
            //var content = res.data.module.content;
            //var baseUrl = res.data.baseUrl;  // 图片地址前缀
            console.log(module);
            if(module != "" && content != ""){
                $.each(content, function(i, n){
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
});

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

$.ajax({
    type: "post",
    url: "detail",
    data:{"messageId": canshu['messageId']},
    dataType: "json",
    success:function(res){
        var html = '';
        if(res.code == 1){
            var module = res.data.module;
            var baseUrl0 = res.data.baseUrl;  // 图片地址前缀
            var activityMessage = res.data.activityMessage;
            if(activityMessage != ""){
                $.each(activityMessage, function(k, v){
                    html += '<h1>'+v.title;
                    html += '<span>'+v.createTime+'</span>';
                    html += '</h1>';
                    html += '<div>';
                    html += '<p>'+v.content+'</p>';
                    html += '</div>';
                });
            }
            $(".info_detail").html(html);
        }else if(res.code == 0){
            alert("查询不到数据");
        }else{
            alert("接口请求错误11")
        }

    }
});