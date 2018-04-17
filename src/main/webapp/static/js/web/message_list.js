$(function(){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");
    initModule();
})

function initModule(){
    var moduleContent = $("input[name='moduleContent']").val();
    if(moduleContent != ""){
        var modulehtml = [];
        $.each(JSON.parse(moduleContent), function(i, n){
            modulehtml += '<li>';
            modulehtml += '<a href="'+n.linkUrl+'">'+n.title+'</a>';
            modulehtml += '</li>';
        });
        $(".module").html(modulehtml);
    }
}