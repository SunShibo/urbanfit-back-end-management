<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>北京众力飞特体育产业发展有限公司 | 赛法斗培训 | 赛法斗| 搏击培训</title>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
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
                    <h1><a href="http://www.urbanfit.cn/message/toDetail?messageId=14">燃动周末，<br>赛法斗体验课完美收官！</a></h1>
                    <div>
                        <a href="http://www.urbanfit.cn/message/toDetail?messageId=14" class="clear">
                            <span><img src="../static/img/sai.jpg"></span>
                            <p>孩子能健康成长就是家长最大的心愿，众力飞特针对青少年身体特点和传统赛法斗既有的动作体系设计了一套合理有效的课程体系。既保留了原汁原味法式绅士搏击文化，也让孩子们在玩乐时得到最科学的锻炼。</p></a>
                        <em>2018-06-14</em>
                    </div>
                    <ul>
                        <li>
                            <a href="#">
                                <img src="../static/img/pto1.jpg" width="82" height="82">
                                <div class="text">
                                    <h2>点燃你的周末！法式拳击体验课火热来袭！</h2>
                                    <p>热浪一波一波来袭，整个北京进入了炙烤模式孩子们每天背着沉重的书包回到家，在空调和冰棍的陪伴下写完作业，睡前又一头扎入手机和电脑的世界中；白天懒懒不想动，到了晚上却又睡不着，无穷无尽的精力不知道如何安放，应该是属于奔跑流汗的盛夏时段，不应该如此浪费</p>
                                </div></a>
                            <em>2018-06-06</em>
                        </li>
                        <li><a href="#">
                            <img src="../static/img/pto2.jpg" width="82" height="82">
                            <div class="text">
                                <h2>首战！小小勇士也要练就一颗勇敢的心!</h2>
                                <p>5月20日下午，由众力飞特举办的少儿赛法斗公开课于成长基石新天地店顺利举办。现场，小小选手们斗志昂扬，一招一式都尽显绅士风采。场内家长也跃跃欲试，迫不及待想体验一下这来自法国的贵族搏击！</p>
                            </div></a>
                            <em>2018-05-29</em>
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
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274101114'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274101114%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
