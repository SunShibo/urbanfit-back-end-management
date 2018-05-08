<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>微信支付</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/course_wechat_pay.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>收 银 台</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox">
                <div class="pay1">
                    <p>订单提交成功，请您尽快付款！</p>
                    <span>
                        应款金额：<i>${order.payPrice}元</i><br>
                        请您在提交订单后30分钟内完成支付，否则订单会自动取消。
                    </span>
                </div>
                <div class="pay2">
                    <h1><img src="../static/img/weixin.jpg"></h1>
                    <div class="weima">
                        <input type="hidden" name="orderNum" value="${orderNum}">
                        <img src="${baseUrl}${wechatPayQr}">
                        <div>
                          <img src="../static/img/icon.jpg" width="44" height="44">
                          <p>请使用微信扫描<br>二维码以完成支付</p>
                        </div>
                    </div>
                    <div class="phone">
                        <img src="../static/img/iphone.jpg">
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
