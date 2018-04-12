<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>众力飞特</title>
</head>
<body>
    <div class="top">
        <div class="logo">
            <div class="logobox">
                <a href="javascript:void(0);"><img src="../static/img/logo.png" alt=""></a>
                <c:if test="${empty currentClient}">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/client/toRegister">join</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/toLogin">log in</a></li>
                    </ul>
                </c:if>
                <c:if test="${not empty currentClient}">
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/client/detail">
                                <c:if test="${empty currentClient.name}">${currentClient.mobile}</c:if>
                                <c:if test="${not empty currentClient.name}">${currentClient.name}</c:if>
                            </a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/client/signOut">sign out</a></li>
                    </ul>
                </c:if>
            </div>
        </div>
        <div class="navbox">
            <nav class="nav">
                <ul>
                    <li id="menu_home"><a href="${pageContext.request.contextPath}/module/toHome">HOME</a></li>
                    <li id="menu_message"><a href="${pageContext.request.contextPath}/module/toMessage">活动资讯</a></li>
                    <li id="menu_match"><a href="${pageContext.request.contextPath}/about/match">赛法斗</a></li>
                    <li id="menu_course"><a href="course.html">课程介绍</a>
                        <div class="menubox1">
                            <a href="${pageContext.request.contextPath}/course/detail?courseId=1">成人课程</a>
                            <a href="${pageContext.request.contextPath}/course/detail?courseId=2">青少年课程</a>
                            <a href="${pageContext.request.contextPath}/course/detail?courseId=3">私教课程</a>
                            <a href="${pageContext.request.contextPath}/course/detail?courseId=4">特色课程</a>
                        </div>
                        <div class="menubox"></div>
                    </li>
                    <li id="menu_coach"><a href="${pageContext.request.contextPath}/coach/list">教练团队</a></li>
                    <li id="menu_about"><a href="${pageContext.request.contextPath}/about/company">关于我们</a>
                        <div class="menubox1">
                            <a href="${pageContext.request.contextPath}/about/company">公司介绍</a>
                            <a href="${pageContext.request.contextPath}/">门店查询</a>
                            <a href="${pageContext.request.contextPath}/about/contact">联系我们</a>
                            <a href="${pageContext.request.contextPath}/about/join">合作伙伴</a>
                        </div>
                        <div class="menubox"></div>
                    </li>
                    <li id="menu_auth"><a href="${pageContext.request.contextPath}/auth/authPage">认证查询</a></li>
                </ul>
                <div class="search">
                    <img src="../static/img/search.png" width="22" height="18">
                    <input type="text" value="" placeholder="Search">
                </div>
            </nav>
        </div>
    </div>
</body>
</html>
