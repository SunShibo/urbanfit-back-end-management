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
        <form id="clientForm" method="post">
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
                                <span>昵称</span>
                                <input type="text" name="nickname" value="${currentClient.nickname}" placeholder="请输入昵称" class="input">
                            </li>
                            <li>
                                <span>性别</span>
                                <input type="radio" name="gender" value="0" style="width: auto;height:54px;line-height:54px">&nbsp;&nbsp;
                                <label style="height:54px;line-height:54px">男</label>
                                <input type="radio" name="gender" value="1" style="width: auto;height:54px;line-height:54px">&nbsp;&nbsp;
                                <label style="height:54px;line-height:54px">女</label>
                            </li>
                            <li>
                                <span>电子邮箱</span>
                                <input type="text" name="email" value="${currentClient.email}" placeholder="请输入电子邮箱" class="input">
                            </li>
                            <li>
                                <a href="javascript:void(0);" id="B_save">保存</a>
                            </li>
                          </ul>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
