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
                <a href="index.html"><img src="../static/img/logo.png" alt=""></a>
                <ul>
                    <li><a href="join.html">join</a></li>
                    <li><a href="login.html">log in</a></li>
                </ul>
            </div>
        </div>
        <div class="navbox">
            <nav class="nav">
                <ul>
                    <li><a href="index.html">HOME</a></li>
                    <li><a href="info.html">活动资讯</a></li>
                    <li><a href="match.html">赛法斗</a></li>
                    <li><a href="course.html">课程介绍</a>
                        <div class="menubox1">
                            <a href="course.html#adult">成人课程</a>
                            <a href="course.html#juvenile">青少年课程</a>
                            <a href="course.html#personal">私教课程</a>
                            <a href="course.html#characteristic">特色课程</a>
                        </div>
                        <div class="menubox"></div>
                    </li>
                    <li class="on"><a href="${pageContext.request.contextPath}/coach/list">教练团队</a></li>
                    <li><a href="${pageContext.request.contextPath}/about/company">关于我们</a>
                        <div class="menubox1">
                            <a href="${pageContext.request.contextPath}/about/company">公司介绍</a>
                            <a href="${pageContext.request.contextPath}/">门店查询</a>
                            <a href="${pageContext.request.contextPath}/about/contact">联系我们</a>
                            <a href="${pageContext.request.contextPath}/about/join">合作伙伴</a>
                        </div>
                        <div class="menubox"></div>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/auth/authPage">认证查询</a></li>
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
