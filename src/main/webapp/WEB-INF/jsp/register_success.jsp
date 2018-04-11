<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>注册成功</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
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
                    <h1>注册成功</h1>
                    <div class="success">
                        <img src="../static/img/yes.png">
                        <p>join success</p>
                        <a href="index.html" class="next">返回首页</a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>