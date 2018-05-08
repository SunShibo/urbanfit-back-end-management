<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>支付成功</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
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
                <div class="pay2 pay3">
                    <div class="pay3text">
                        <div class="complete">
                            <img src="../static/img/gou.png">
                            <p>恭喜您，支付成功！<span>我们会尽快处理您的订单</span></p>
                        </div>
                        <div class="payorder">
                          <a href="/module/toHome">返回首页</a>
                        </div>
                  </div>
              </div>
          </div>
      </div>
    </div>
</body>
