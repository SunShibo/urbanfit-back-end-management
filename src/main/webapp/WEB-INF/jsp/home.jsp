<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/menu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/home.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="index">
            <div class="banner">
                <!--轮播图start-->
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner" role="listbox">
                          <div class="item active">
                              <img src="../static/img/banner1.jpg" alt="First slide">
                          </div>
                          <div class="item">
                              <img src="../static/img/banner1.jpg" alt="Second slide">
                          </div>
                          <div class="item">
                              <img src="../static/img/banner1.jpg" alt="Third slide">
                          </div>
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
            </div>
            <div class="index1">
                <div class="index1left">
                  <div class="index1left1">
                          <span>
                              <img src="../static/img/news.png">
                              新闻动态
                          </span>
                    <img src="../static/img/yellow1.jpg">
                  </div>
                  <div class="index1text">
                    <h1><a href="info_detail1.html">我就是拳王-<br>上海赛区激情开场</a></h1>
                    <div>
                      <a href="info_detail1.html">
                        <span><img src="../static/img/king.jpg"></span>
                        <p>经过一个寒冬的沉淀，“我就是拳王”新的赛季已然准备好热情开场。2018年第三届“我就是拳王”系列赛于3月14日-16日在上海世博展览馆上海IWF赛区火热开赛。作为“我就是拳王”2018赛季的开篇大作，汇集了来自四方的英雄豪杰，准备点燃这一季的上海。</p></a>
                    </div>
                    <ul>
                      <li><a href="info_detail1.html">“我就是拳王”上海赛区激情开场</a></li>
                      <li><a href="info_detail.html">每天训练要流10斤汗水这个搏击新星未来不可限量每天训练要流10斤汗水这个搏击新星未来不可限量</a></li>
                      <li><a href="info_detail.html">法国赛法斗联合会主席、赛法斗国际联合会</a></li>
                    </ul>
                    <a href="#">MORE</a>
                  </div>
                </div>
                <div class="index1right">
                  <div class="index1right1">
                    <img src="../static/img/yellow.jpg">
                          <span>
                              <img src="../static/img/match.png">
                              赛事前沿
                          </span>
                  </div>
                  <img src="../static/img/right.jpg">
                </div>
            </div>
            <div class="indexmap">
                <span><img src="../static/img/store1.png"></span>
                <div class="indexmap1">
                    <div class="chinamap">
                        <img src="../static/img/chinamap.jpg">
                    </div>
                    <div class="addr">
                        <img src="../static/img/logoblack.png">
                    </div>
                </div>
            </div>
            <div class="train">
                <img src="../static/img/peoplebg.jpg">
                <div class="trainbox">
                    <img src="../static/img/train.png">
                    <div class="trainboxul">
                        <img src="../static/img/curriculum.png">
                        <ul>
                            <li>
                                <img src="../static/img/quan1.png">
                                <span>少儿赛法斗</span>
                            </li>
                            <li>
                                <img src="../static/img/quan2.png">
                                <span>健身赛法斗</span>
                            </li>
                            <li>
                                <img src="../static/img/quan3.png">
                                <span>教练员课程</span>
                            </li>
                            <li>
                                <img src="../static/img/quan4.png">
                                <span>特色课程</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
