$(function (){
    uploadHeadPortrait();
})

function uploadHeadPortrait(){
    var button = $("#uploadHeadPortrait"), interval;
    new AjaxUpload(button, {
        action: "/client/uploadHeadPortrait",
        type:"post",
        name: 'myFile',
        responseType : 'json',
        onSubmit: function(file, ext) {
            if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                alert("您上传的图片格式不对，请重新选择！");
                return false;
            }
        },
        onComplete: function(file, response) {
            if(response.code != 1) {
                alert(response.msg);
                return ;
            }else{
                var resultData = response.data;
                $("#uploadHeadPortrait").attr("src", resultData.baseUrl + resultData.headPortrait);
            }
        }
    });
}