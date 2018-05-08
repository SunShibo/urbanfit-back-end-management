<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>联系我们</title>
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
            $("li[id='about_contact']").addClass("active");
        })
    </script>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <jsp:include page="about_common.jsp"/>
            <div class="tab-pane fade contact clear" id="contact">
                <div class="imgtitle">
                    <img src="../static/img/contact.png">
                </div>
                <div class="contactbox">
                    <ul>
                        <li>
                            <h2>客户服务</h2>
                            <p>客服热线：4000-664-688</p>
                        </li>
                        <%--<li>
                            <h2>媒体垂询／公关传播</h2>
                            <p>客服热线：4000-664-688</p>
                        </li>
                        <li>
                            <h2>市场推广／品牌合作</h2>
                            <p>客服热线：4000-664-688</p>
                        </li>
                        <li>
                            <h2>认证店加盟</h2>
                            <p>客服热线：4000-664-688</p>
                            <p>(周一至周日9:00-12:00，13:30-18:00，法定节假日除外)</p>
                        </li>--%>
                        <li>
                            <h2>联系方式</h2>
                            <p>公司：北京众力飞特体育产业发展有限责任公司</p>
                            <p>地址：北京市朝阳区南郎家园18号恋日国际大厦806室</p>
                        </li>
                    </ul>
                    <div class="map">
                        <img src="../static/img/map.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>