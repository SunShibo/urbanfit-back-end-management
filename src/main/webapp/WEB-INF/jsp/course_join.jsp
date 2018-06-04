<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程报名</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/course_join.js"></script>
    <script type="text/javascript">
        var courseId = '${courseId}';
        var storeId = '${storeId}';
        var detailId = '${detailId}';
    </script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
              <span>填写订单信息</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox">
                <div class="order">
                    <h1><img src="../static/img/ling.png">学生信息：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>学生姓名<i>*</i></span>
                            <input type="text" name="childrenName" value="" placeholder="请输入您的姓名" class="input" id="name">
                        </div>
                        <div class="orderinput">
                            <span>手机号码<i>*</i></span>
                            <input type="text" name="clientMobile" placeholder="请输入您的姓名" class="input" id="phone">
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">课程信息：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>课程名称&nbsp;&nbsp;&nbsp;</span>
                            <input type="text" class="input1" id="courseName" readonly="readonly">
                        </div>
                        <div class="orderinput">
                            <span>课程价格</span>
                            <input type="text" class="input1" id="coursePrice" readonly="readonly">
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>俱乐部名称</span>
                            <input type="text" class="input1" readonly="readonly" id="storeName" style="width: 500px;"/>
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>俱乐部地址</span>
                            <input type="text" class="input1" readonly="readonly" id="storeAddress" style="width: 500px;"/>
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>课程规格&nbsp;&nbsp;&nbsp;</span>
                            <input type="text" class="input1" id="courseSize" readonly="readonly" style="width: 500px;">
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">支付信息：</h1>
                    <div class="order2">
                        <div class="radio seleted"  seleted-value="alipay">
                            <img src="../static/img/radio1.png" class="radioimg">
                            <span><img src="../static/img/zhifubao.jpg"></span>
                        </div>
                        <div class="radio" seleted-value="wechatpay">
                            <img src="../static/img/radio.png" class="radioimg"  >
                            <span><img src="../static/img/weixin1.jpg"></span>
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">优惠码：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>优 惠 码</span>
                            <input type="text" name="couponNum" placeholder="请输入您的优惠码" class="input" id="ma">
                            <a href="javascript:void(0);" id="change">立即兑换</a>
                            <em id="changebtn">修改</em>
                            <p id="couponName">优惠码优惠信息显示（全场5折）</p>
                        </div>
                    </div>
                </div>
                <div class="order" style="margin-bottom: 0; display: none;" id="couponDiv">
                    <h1><img src="../static/img/ling.png">课程价格：</h1>
                    <ul>
                        <li>课程价格<span id="price">￥${course.coursePrice}</span></li>
                        <li>优&emsp;&emsp;惠<span>-￥<label id="couponprice"></label></span></li>
                        <li>应付总额<span class="on">￥<label id="payPrice"></label></span></li>
                        <input type="hidden" name="coursePrice" value="${course.coursePrice}">
                    </ul>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">备注：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>备注</span>
                            <input type="text" class="input" id="remarks" name="remarks">
                        </div>
                    </div>
                </div>
                <div class="submit">
                    <input type="hidden" name="courseId" value="${courseId}" id="courseId">
                    <input type="hidden" name="storeId" value="${storeId}" id="storeId">
                    <input type="hidden" name="detailId" value="${detailId}" id="detailId">
                    <a href="javascript:void(0);" id="A_submit_order">提交订单</a>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
