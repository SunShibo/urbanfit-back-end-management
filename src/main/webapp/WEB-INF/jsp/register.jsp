<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>注册</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/login.js"></script>
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
                    <h1>会员注册</h1>
                    <ul>
                      <li>
                          <span><img src="../static/img/user.png">手机号码</span>
                          <input type="text" value="" placeholder="请输入您的手机号" class="input" id="phone">
                          <p id="phonemsg"></p>
                      </li>
                      <li>
                          <span><img src="../static/img/passwd.png">验 证 码</span>
                          <input type="text" value="" placeholder="输入验证码" class="input input1" id="vcode">
                          <a href="#" class="yzm" id="sendcode">获取验证码</a>
                          <p id="yzmmsg"></p>
                      </li>
                      <li>
                          <a href="javascript:;" class="next">下一步</a>
                          <a href="login.html" class="loginbtn">登录</a>
                      </li>
                    </ul>
                </div>
            </div>
            <jsp:include page="../footer.jsp"/>
        </div>
    </div>
</body>