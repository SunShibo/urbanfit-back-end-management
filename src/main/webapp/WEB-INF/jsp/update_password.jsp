<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8"/>
  <title>重置密码</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link rel="stylesheet" type="text/css" href="/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/menu.js"></script>
  <script type="text/javascript" src="/static/js/web/update_password.js"></script>
</head>
<style>
    ::-webkit-input-placeholder { color:#c6b98e; }
    ::-moz-placeholder { color:#c6b98e; } /* firefox 19+ */
    :-ms-input-placeholder { color:#c6b98e; } /* ie */
    input:-moz-placeholder { color:#c6b98e; }
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px #fff inset;
        -webkit-text-fill-color: #555;
    }
</style>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>会员中心</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox user">
                <jsp:include page="client_common.jsp"/>
                <div class="user_box">
                    <h1>重置密码</h1>
                    <ul class="user_boxul">
                        <li>
                            <span>手机号码</span>
                            <input type="text" placeholder="请输入手机号" class="input" id="phone">
                            <p id="phonemsg"></p>
                        </li>
                        <li>
                            <span>验 证 码</span>
                            <input type="text" value="" placeholder="验证码" class="input input1" id="vcode">
                            <i id="sendcode">获取验证码</i>
                            <p id="yzmmsg"></p>
                        </li>
                        <li>
                            <span>密&emsp;&emsp;码</span>
                            <input type="password" value="" placeholder="请输入您的密码" class="input" id="pwd">
                            <p id="pwdmsg"></p>
                        </li>
                        <li>
                            <span>密码确认</span>
                            <input type="password" value="" placeholder="请再次输入您的密码" class="input" id="cpwd">
                            <p id="cpwdmsg"></p>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="save">保存</a>
                        </li>
                    </ul>
                    <div class="wan">
                        <img src="../static/img/yes.png">
                        <p>密码重置成功</p>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
