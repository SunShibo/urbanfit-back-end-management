<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/web/config.js"></script>
    <script type="text/javascript">
        $(function (){
            // 获取code值
            var code = getParamValue("code");
            var type = getParamValue("type");
            $.ajax({
                type : "post",
                url : baseUrl + "client/wechatWebRegister",
                data : {"code" : code},
                dataType : "json",
                success : function (result){
                    if(type == "loginPage"){      // 登录界面
                        window.location.href = projectUrl;
                    }else{          // 登录弹框界面
                        var courseId = getParamValue("courseId");
                        var storeId = getParamValue("storeId");
                        var detailId = getParamValue("detailId");
                        window.location.href = projectUrl + "course_join.html?courseId=" + courseId
                                + "&storeId=" + storeId + "&detailId=" + detailId;
                    }
                }
            })
        })

        function getParamValue(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    </script>
</head>
<body>
</body>
</html>
