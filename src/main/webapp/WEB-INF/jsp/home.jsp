<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>北京众力飞特体育产业发展有限公司 | 赛法斗培训 | 赛法斗| 搏击培训</title>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">--
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/home.js"></script>
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
                    <h1><a href="#">我就是拳王-<br>上海赛区激情开场</a></h1>
                    <div>
                        <a href="#" class="clear">
                            <span><img src="../static/img/king.jpg"></span>
                            <p>经过一个寒冬的沉淀，“我就是拳王”新的赛季已然准备好热情开场。2018年第三届“我就是拳王”系列赛于3月14日-16日在上海世博展览馆上海IWF赛区火热开赛。作为“我就是拳王”2018赛季的开篇大作，汇集了来自四方的英雄豪杰，准备点燃这一季的上海。</p></a>
                        <em>2018-3-11</em>
                    </div>
                    <ul>
                        <li>
                            <a href="#">
                                <img src="../static/img/pto1.jpg" width="82" height="82">
                                <div class="text">
                                    <h2>参赛必看！我就是拳王海选三关规则详解</h2>
                                    <p>3月14日-16日，2018赛季的我就是拳王将在上海世博展览馆打响新赛季的揭幕战。各路怀揣功夫梦想的草根达人将再次踏上征程，向冠军发起冲击。今天我们向各位朋友介绍下海选三关的比赛规则。</p>
                                </div></a>
                            <em>2018-3-10</em>
                        </li>
                        <li><a href="#">
                            <img src="../static/img/pto2.jpg" width="82" height="82">
                            <div class="text">
                                <h2>机会来了，你准备好了吗！我就是拳王北京赛区15日开战</h2>
                                <p>有功夫梦想的朋友们注意了，2018我就是拳王北京赛区的比赛将在4月15日拉开大幕，目前报名工作已经全面启动，戴上你的拳套来探险吧！</p>
                            </div></a>
                            <em>2018-4-13</em>
                        </li>
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
                <div class="rightbox">
                    <img src="../static/img/right.jpg">
                    <ul>
                        <li class="one"><a href="#">2018我就是拳王山东站</a></li>
                        <li class="two"><a href="#">2018我就是拳王广东站</a></li>
                        <li class="three"><a href="#">2018我就是拳王北京站</a></li>
                        <li class="four"><a href="#">2018我就是拳王上海站</a></li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="indexmap">
            <span><img src="../static/img/store1.png"></span>
            <div class="indexmap1">
                <div class="chinamap">
                    <a href="/store/list"><img src="../static/img/chinamap1.jpg"></a>
                </div>
                <div class="addr">
                    <img src="../static/img/logoblack.png">
                    <h1>北京众力飞特体育产业发展有限责任公司</h1>
                    <p>北京众力飞特体育产业发展有限责任公司致力于赛法斗项目在全民健身领域的推广，是国际赛法斗联合会（FISav）和中国赛法斗联合会（Chinese Savate Federation）在国内唯一授权的全民推广商业合作伙伴，运营赛法斗项目的教学和训练机构；目前，已与各大健身和搏击俱乐部达成课程合作，授权教学点已达800家，如北京中健银座、石家庄超越健身俱乐部、黄金时代健身等等俱乐部。除训练课程外，众力飞特在赛法斗装备、专向赛事组织等项目相关领域，亦有发展规划。</p>
                    <a href="/store/list">MORE</a>
                </div>
            </div>
        </div>
        <div class="train">
            <img src="../static/img/peoplebg.jpg">
            <div class="trainbox">
                <img src="../static/img/train.png">
                <div class="trainboxul">
                    <img src="../static/img/curriculum1.png">
                    <ul>
                        <li>
                            <a href="/course/detail?courseId=2">
                                <img src="../static/img/shaoer.png">
                                <span>少儿赛法斗</span>
                            </a>
                        </li>
                        <li>
                            <a href="/course/detail?courseId=1">
                                <img src="../static/img/jianshen.png">
                                <span>健身赛法斗</span>
                            </a>
                        </li>
                        <li>
                            <a href="/course/detail?courseId=3">
                                <img src="../static/img/jiaolian.png">
                                <span>教练员课程</span>
                            </a>
                        </li>
                        <li>
                            <a href="/course/detail?courseId=4">
                                <img src="../static/img/tese.png">
                                <span>特色课程</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</div>
</body>
