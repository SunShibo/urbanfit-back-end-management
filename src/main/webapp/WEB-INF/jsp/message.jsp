<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/menu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/message.js"></script>
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
            <div class="info">
                <div class="infoleft">
                    <!--轮播图start-->
                    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                        <!-- 轮播（Carousel）指标 -->
                        <ol class="carousel-indicators">
                            <%--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>--%>
                        </ol>
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner" role="listbox">
                            <%--<div class="item active">
                                <img src="../static/img/banner.jpg" alt="First slide">
                            </div>
                            <div class="item">
                                <img src="../static/img/banner.jpg" alt="Second slide">
                            </div>
                            <div class="item">
                                <img src="../static/img/banner.jpg" alt="Third slide">
                            </div>--%>
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
                            <li>
                                <a href="info_detail1.html">
                                    <div class="listimg">
                                        <img src="../static/img/king.jpg">
                                    </div>
                                    <div class="listtext">
                                        <h1>“我就是拳王”上海赛区激情开场<span>2018／3/14</span></h1>
                                        <p>经过一个寒冬的沉淀，“我就是拳王”新的赛季已然准备好热情开场。2018年第三届“我就是拳王”系列赛于3月14日-16日在上海世博展览馆上海IWF赛区火热开赛。作为“我就是拳王”2018赛季的开篇大作，汇集了来自四方的英雄豪杰，准备点燃这一季的上海。</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="info_detail2.html">
                                    <div class="listtext1">
                                        <h1>相约上海滩！3月14日2018赛季我就是拳王全国启动<span>2018／3/14</span></h1>
                                        <p>当全球功夫春晚&我就是拳王总决赛还在各电视台轮番热播之时，新赛季的赛事已经悄然启动了！我就是拳王将在3月14日-16日在上海世博展览馆开启2018赛季，目前报名工作已经全面展开。</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="info_detail.html">
                                    <div class="listimg">
                                        <img src="../static/img/suo.png">
                                    </div>
                                    <div class="listtext">
                                        <h1>每天训练要流10斤汗水这个搏击新星未来不可限量<span>2018／3/14</span></h1>
                                        <p>“金刚”李元坤，19岁，来自广州北胜蔡李佛代表队，在2017赛季我就是拳王的擂台上给人留下了非常深刻的印象，据了解，他每天训练都要流10斤汗水，这样刻苦的训练真是让人叹为观止，谁会怀疑他的未来呢？</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="info_detail3.html">
                                    <div class="listtext1">
                                        <h1>参赛必看！我就是拳王海选三关规则详解<span>2018／3/14</span></h1>
                                        <p>3月14日-16日，2018赛季的我就是拳王将在上海世博展览馆打响新赛季的揭幕战。各路怀揣功夫梦想的草根达人将再次踏上征程，向冠军荣耀发起冲击。今天我们向各位新朋友介绍一下海选赛三关的比赛规则。</p>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="info_detai4.html">
                                    <div class="listimg">
                                        <img src="../static/img/tu.jpg">
                                    </div>
                                    <div class="listtext">
                                        <h1>2018我就是拳王将在上海世博馆揭幕够胆你就来！<span>2018／3/14</span></h1>
                                        <p>当全球功夫春晚&我就是拳王总决赛还在各电视台轮番热播之时，新赛季的赛事已经悄然启动了！我就是拳王组委会确认，将在3月14日-16日在上海世博展览馆开启新赛季，目前报名工作已经全面展开。</p>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="inforight">
                    <ul class="module">
                        <%--<li>
                            <a href="#"><img src="../static/img/tu1.jpg" alt=""></a>
                        </li>
                        <li>
                            <a href="#"><img src="../static/img/tu1.jpg" alt=""></a>
                        </li>
                        <li>
                            <a href="#"><img src="../static/img/tu1.jpg" alt=""></a>
                        </li>
                        <li>
                            <a href="#"><img src="../static/img/tu1.jpg" alt=""></a>
                        </li>--%>
                    </ul>
                </div>
            </div>
            <!-- 分页 -->
            <div class="pagerbox">
                <ul class="pager">
                    <li class="previous"><a href="#">&larr; 上一页</a></li>
                    <li id="page">
                        <span><em id="number0">1</em>-30</span>
                    </li>
                    <li class="next"><a href="#">下一页 &rarr;</a></li>
                </ul>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>

