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
        var modulehtml = '';
        if(res.code == 1){
            var module = res.data.module;
            var baseUrl = res.data.baseUrl;  // 图片地址前缀

            if(module != "" && module.content != ""){
                $.each(module.content, function(i, n){
                    //alert(n.title);    // 标题
                    //alert(n.linkUrl);   // 链接地址
                    modulehtml += '<li>';
                    modulehtml += '<a href="'+n.linkUrl+'">'+n.title+'</a>';
                    modulehtml += '</li>';
                });
            }
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
    data:{"messageId": canshu},
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
            alert("接口请求错误")
        }

    }
});