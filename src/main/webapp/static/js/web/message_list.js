$(function(){
    $("li[id^='menu_']").removeClass();
    $("#menu_message").addClass("on");
    //initModule();
})

/*function initModule(){
    var moduleContent = $("input[name='moduleContent']").val();
    console.log(moduleContent);
    if(moduleContent != ""){
        var modulehtml = [];
        $.each(JSON.parse(moduleContent), function(i, n){
            modulehtml += '<li>';
            modulehtml += '<a href="'+n.linkUrl+'">'+n.imageUrl+'</a>';
            modulehtml += '</li>';
        });
        $(".module").html(modulehtml);
    }
}*/
