<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>认证查询</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/coach_auth.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>认证查询</span>
            </div>
        </div>
        <div class="attestion">
            <img src="../static/img/bg1.jpg" alt="">
            <div class="baoming">
                <div class="baoming1">
                    <div class="baomingform">
                        <div class="baoming2">
                            <h1>教练认证查询</h1>
                            <div>
                                <ul>
                                    <li>
                                        <span>教练姓名:</span>
                                        <input type="text" name="coachName">
                                    </li>
                                    <li>
                                        <span>教练证号码:</span>
                                        <input type="text" name="coachCardNum">
                                    </li>
                                </ul>
                                <a href="javascript:void(0);" id="A_query_auth">查询 </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
