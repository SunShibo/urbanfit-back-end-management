$(function (){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");

})


/*$.ajax({
    type: "post",
    url: "list",
    data:{"type": 2},
    dataType: "json",
    success:function(res){
        var modulehtml = '';
        if(res.code == 1){
            var module = res.data.module;
            //var content = res.data.module.content;
            //var baseUrl = res.data.baseUrl;  // 图片地址前缀
            console.log(res.data.module);
            if(module != "" && content != ""){
                alert(11);
                $.each(content, function(i, n){
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
});*/

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
            //alert(canshu['messageId']);
            var baseUrl0 = res.data.baseUrl;  // 图片地址前缀
            var activityMessage = res.data.activityMessage;
            //console.log(activityMessage);
            if(activityMessage != ""){
                html += '<h1>'+activityMessage.title;
                html += '<span>'+activityMessage.createTime+'</span>';
                html += '</h1>';
                html += '<div>';
                html += '<img src="'+baseUrl0+activityMessage.detailImage+'">';
                html += '<p>'+activityMessage.content+'</p>';
                html += '</div>';
            }
            $(".info_detail").html(html);
        }else if(res.code == 0){
            alert("查询不到数据");
        }else{
            alert("接口请求错误")
        }

    }
});