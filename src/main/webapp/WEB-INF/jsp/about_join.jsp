<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>合作伙伴</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript">
        $(function (){
            $("li[id^='menu_']").removeClass();
            $("#menu_about").addClass("on");
            $("li[id^='about_']").removeClass();
            $("li[id='about_join']").addClass("active");
        })
    </script>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <jsp:include page="about_common.jsp"/>
            <div class="tab-pane fade cooperative" id="cooperative">
                <div class="imgtitle">
                    <img src="../static/img/cooperative.png">
                </div>
                <div class="cooperativebox clear">
                    <ul>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo1.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo2.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo10.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo6.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo7.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo9.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo11.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo4.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo3.jpg"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo12.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo13.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo14.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo15.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo16.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo17.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo18.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo19.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo200.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo21.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo22.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo23.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo24.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo25.png"></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><img src="../static/img/logo26.png"></a>
                        </li>
                    </ul>
                    <img src="../static/img/bg.jpg" alt="">
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>

