<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title></title>
</head>
<body>
    <div class="user_nav">
        <div class="user_navbox">
            <img src="../static/img/head.jpg">
            <p>个人信息<br></p>
        </div>
        <ul class="user_nav1">
            <li>
                <a href="${pageContext.request.contextPath}/client/detail">
                    <img src="../static/img/my.png" class="img">
                    <img src="../static/img/my1.png" class="img1">
                    我的信息
                </a>
            </li>
            <li>
                <a href="reset.html">
                    <img src="../static/img/pwd.png" class="img">
                    <img src="../static/img/pwd1.png" class="img1">
                    重置密码
                </a>
            </li>
            <li class="on">
                <a href="user_order.html">
                    <img src="../static/img/order.png" class="img">
                    <img src="../static/img/order1.png" class="img1">
                    我的订单
                </a>
            </li>
        </ul>
    </div>
</body>
