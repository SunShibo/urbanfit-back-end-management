<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>登录成功</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/web/login.js"></script>
</head>
<body>
    <div class="content">
        <div class="join">
            <img src="../static/img/loginbg.jpg">
            <div class="loginbox">
                <div class="joinlogo">
                    <a href="index.html">
                        <img src="../static/img/logo.png">
                    </a>
                </div>
                <div class="login">
                    <img src="../static/img/logo1.png">
                    <h1>登录成功</h1>
                    <div class="success">
                        <img src="../static/img/yes.png">
                        <p>login success</p>
                        <a href="/module/toHome" class="next">返回首页</a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
