<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title></title>
</head>
<body>
    <div class="route">
        <span>关于我们</span>
        <div class="navbox1">
            <img src="../static/img/navbg.jpg" alt="">
            <ul id="myTab" class="navboxul">
                <li id="about_company" class="active"><a href="/about/company" data-toggle="tab">公司介绍</a></li>
                <%--<li id="about_store"><a href="/store/list" data-toggle="tab">俱乐部</a></li>--%>
                <li id="about_contact"><a href="/about/contact" data-toggle="tab">联系我们</a></li>
                <li id="about_join"><a href="/about/join" data-toggle="tab">合作伙伴</a></li>
            </ul>
        </div>
    </div>
</body>