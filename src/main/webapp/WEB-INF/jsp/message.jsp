<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/jwplayer-7.2.2/jwplayer.js"></script>
    <script type="text/javascript" src="/static/js/web/message_list.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>活动资讯</span>
                <div class="navbox1">
                    <img src="../static/img/navbg.jpg" alt="">
                </div>
            </div>
            <form action="clist" method="post">
                <input type="hidden" name="type" value="2">
                <div class="info">
                    <div class="infoleft">
                        <!--轮播图start-->
                        <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                            <!-- 轮播（Carousel）指标 -->
                            <ol class="carousel-indicators">
                                <c:forEach items="${lstBanner}" var="banner" varStatus="status">
                                    <c:if test="${status.index == 0}">
                                        <li data-target="#myCarousel" data-slide-to="${status.index}" class="active"></li>
                                    </c:if>
                                    <c:if test="${status.index != 0}">
                                        <li data-target="#myCarousel" data-slide-to="${status.index}"s></li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <!-- 轮播（Carousel）项目 -->
                            <div class="carousel-inner" role="listbox">
                                <c:forEach items="${lstBanner}" var="banner" varStatus="status">
                                    <c:if test="${status.index == 0}">
                                        <div class="item active">
                                            <a href="${banner.linkUrl}">
                                                <img src="${baseUrl}${banner.imageUrl}">
                                            </a>
                                        </div>
                                    </c:if>
                                    <c:if test="${status.index != 0}">
                                        <div class="item">
                                            <img src="${baseUrl}${banner.imageUrl}">
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <!-- 轮播（Carousel）导航 -->
                            <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                                <img src="../static/img/left.png" width="64" height="64">
                            </a>
                            <a class="carousel-control right" href="#myCarousel" data-slide="next">
                                <img src="../static/img/right.png" width="64" height="64">
                            </a>
                        </div>
                        <!--轮播图end-->
                        <!-- 列表 -->
                        <div class="list">
                            <ul id="listbox">
                                <c:forEach items="${lstMessage}" var="message" varStatus="status">
                                    <c:if test="${(status.index + 1) % 2 == 0}">
                                        <li>
                                            <a href="/message/toDetail?messageId=${message.messageId}">
                                                <div class="listtext1">
                                                    <h1>${message.title}<span><fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd"/></span></h1>
                                                    <p>${message.introduce}</p>
                                                </div>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:if test="${(status.index + 1) % 2 != 0}">
                                        <li>
                                            <a href="/message/toDetail?messageId=${message.messageId}">
                                                <div class="listimg">
                                                    <img src="${baseUrl}${message.thumbnails}">
                                                </div>
                                                <div class="listtext">
                                                    <h1>${message.title}<span><fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd"/></span></h1>
                                                    <p>${message.introduce}</p>
                                                </div>
                                            </a>
                                        </li>

                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="inforight">
                        <input type="hidden" value='${module.content}' name="moduleContent">
                        <ul class="module">
                            <li>
                                <a href="#">
                                    <img src="../static/img/guanggao.jpg">
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <img src="../static/img/guanggao.jpg">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="page clear">
                    <div class="pages">
                        <jsp:include page="./common/pager.jsp">
                            <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                            <jsp:param value="${pager.totalPage}" name="totalPage"/>
                            <jsp:param value="clist" name="url"/>
                        </jsp:include>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>

