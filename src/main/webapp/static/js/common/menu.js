$(function(){
    $('.nav ul li').hover(function(){
        $(this).children('.menubox').slideDown(100);
        $(this).children('.menubox1').show();
    },function(){
        $(this).children('.menubox').slideUp("fast");
        $(this).children('.menubox1').hide();
    })
})
