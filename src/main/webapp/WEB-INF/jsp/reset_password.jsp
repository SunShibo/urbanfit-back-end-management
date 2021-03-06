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
  <script type="text/javascript" src="/static/js/web/reset_password.js"></script>
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
                      <h1>重置密码</h1>
                      <ul>
                          <li>
                              <span><img src="../static/img/user.png">手机号码</span>
                              <input type="text" name="mobile" placeholder="请输入您的手机号" class="input" id="phone">
                              <p id="phonemsg"></p>
                          </li>
                          <li>
                              <span><img src="../static/img/passwd.png">验 证 码</span>
                              <input type="text" value="" placeholder="输入验证码" class="input input1" id="vcode">
                              <a href="javascript:;" class="yzm" id="sendcode">获取验证码</a>
                              <p id="yzmmsg"></p>
                          </li>
                          <li>
                              <span><img src="../static/img/user.png">密&emsp;&emsp;码</span>
                              <input type="password" value="" placeholder="请输入您的密码" class="input" id="pwd">
                              <p id="pwdmsg"></p>
                          </li>
                          <li>
                              <span><img src="../static/img/passwd.png">密码确认</span>
                              <input type="password" value="" placeholder="请再次输入您的密码" class="input" id="cpwd">
                              <p id="cpwdmsg"></p>
                          </li>
                          <li>
                              <a href="javascript:;" class="save">保存</a>
                              <a href="/client/toLogin" class="loginbtn">登录</a>
                          </li>
                      </ul>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274101114'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274101114%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
