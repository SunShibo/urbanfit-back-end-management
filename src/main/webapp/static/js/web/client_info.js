$(function (){
    $("li[name^='client_']").removeClass();
    $("#client_detail").addClass("on");

    $("#B_save").click(updateClientInfo);
    initClientGender();     // 初始化性别
})

function initClientGender(){
    $("input[name='gender'][value='" + clientInfo.gender + "']").attr("checked",true);
}

function updateClientInfo(){
    var clientName = $("input[name='name']").val();
    if(clientName == ""){
        alert("真是姓名不能为空");
        return ;
    }
    var nickname = $("input[name='nickname']").val();
    if(nickname == ""){
        alert("昵称不能为空");
        return ;
    }
    var gender = $("input[name='gender']:checked").val();
    if(gender == ""){
        alert("请选择性别");
        return ;
    }
    var email = $("input[name='email']").val();
    if(email != ""){
        var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if(!emailReg.test(email)){
            alert("邮箱输入格式有误");
            return;
        }
    }
    // 修改信息
    $.ajax({
        type : "post",
        url : "update",
        data :  $("#clientForm").serialize(),
        dataType : "json",
        success : function (result, status){
            if(result.code != 1){
                alert(result.msg);
                return;
            }else{
                alert("修改用户信息成功");
            }
        }
    })
}