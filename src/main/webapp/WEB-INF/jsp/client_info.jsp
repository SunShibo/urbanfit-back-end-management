<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>我的信息</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/client_info.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>填写我的信息</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox user">
                <jsp:include page="client_common.jsp"/>
                <div class="user_box">
                    <h1>我的信息</h1>
                    <ul class="user_boxul">
                        <li>
                            <span>真实姓名</span>
                            <input type="text" name="name" value="${currentClient.name}" placeholder="请输入姓名" class="input">
                        </li>
                        <li>
                            <a href="javascript:void(0);" id="B_save">保存</a>
                        </li>
                      </ul>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
