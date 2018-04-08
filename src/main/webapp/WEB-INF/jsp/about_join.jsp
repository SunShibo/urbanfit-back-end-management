<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>合作伙伴</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/menu.js"></script>
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
                    </ul>
                    <img src="../static/img/bg.jpg" alt="">
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>

