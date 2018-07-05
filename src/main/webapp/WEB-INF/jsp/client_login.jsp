<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>登录</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
    <script type="text/javascript" src="/static/js/web/client_login.js"></script>
</head>
<body>
    <div class="content">
        <div class="join">
            <div class="loginbox">
                <div class="login" style="margin-top:20px">
                    <img src="../static/img/logo1.png">
                    <h1>会员登录</h1>
                    <ul>
                        <li>
                            <span><img src="../static/img/user.png">帐号</span>
                            <input type="text" value="" placeholder="请输入您的手机号" class="input" id="phone">
                            <p id="phonemsg"></p>
                        </li>
                        <li>
                            <span><img src="../static/img/passwd.png">密码</span>
                            <input type="password" value="" placeholder="输入您的密码" class="input" id="pwd">
                            <p id="pwdmsg"></p>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="next" id="A_login">登录</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
