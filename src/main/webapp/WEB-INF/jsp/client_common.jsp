<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/web/client_common.js"></script>
</head>
<body>
    <div class="user_nav">
        <div class="user_navbox">
            <c:if test="${empty clientInfo.headPortrait}">
                <img src="../static/img/head.jpg" id="uploadHeadPortrait">
            </c:if>
            <c:if test="${not empty clientInfo.headPortrait}">
                <img src="${baseUrl}${clientInfo.headPortrait}" id="uploadHeadPortrait">
            </c:if>
            <p>个人信息<br></p>
        </div>
        <ul class="user_nav1">
            <li id="client_detail">
                <a href="/client/detail">
                    <img src="../static/img/my.png" class="img">
                    <img src="../static/img/my1.png" class="img1">
                    我的信息
                </a>
            </li>
            <li id="client_password">
                <a href="/client/toPassword">
                    <img src="../static/img/pwd.png" class="img">
                    <img src="../static/img/pwd1.png" class="img1">
                    重置密码
                </a>
            </li>
            <li id="client_order">
                <a href="/order/list">
                    <img src="../static/img/order.png" class="img">
                    <img src="../static/img/order1.png" class="img1">
                    我的订单
                </a>
            </li>
        </ul>
    </div>
</body>
