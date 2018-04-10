<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8"/>
  <title>重置密码</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/update_password.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>重置密码1111111</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox user">
                <jsp:include page="client_common.jsp"/>
                <div class="user_box">
                    <h1>我的信息</h1>
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
                            <a href="javascript:void(0);" class="next">下一步</a>
                        </li>
                    </ul>
                    <ul class="user_boxul user_boxul0 ">
                        <li>
                            <span>密&emsp;&emsp;码</span>
                            <input type="text" value="" placeholder="请输入您的密码" class="input" id="pwd">
                            <p id="pwdmsg"></p>
                        </li>
                        <li>
                            <span>密码确认</span>
                            <input type="text" value="" placeholder="请再次输入您的密码" class="input" id="cpwd">
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
