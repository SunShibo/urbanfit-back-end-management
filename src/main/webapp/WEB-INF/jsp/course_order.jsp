<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>课程报名</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/menu.js"></script>
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
                            <input type="text" name="childrenName" placeholder="请输入您的姓名" class="input">
                        </div>
                        <div class="orderinput">
                            <span>手机号码</span>
                            <input type="text" name="clientMobile" placeholder="请输入您的姓名" class="input">
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>上课区域</span>
                            <div class="select">
                                <div>
                                    <select id="s_province" name="s_province"></select>&nbsp;&nbsp;
                                    <select id="s_city" name="s_city" ></select>&nbsp;&nbsp;
                                    <select id="s_county" name="s_county"></select>
                                </div>
                            </div>
                            <input type="text" value="" placeholder="例如：北京市海淀区" class="input" style="width: 179px;">
                            <input type="hidden" name="courseDistrict">
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">支付信息：</h1>
                    <div class="order2">
                        <div class="radio">
                            <img src="../static/img/radio1.png" class="radioimg">
                            <span><img src="../static/img/zhifubao.jpg"></span>
                        </div>
                        <div class="radio">
                            <img src="../static/img/radio.png" class="radioimg">
                            <span><img src="../static/img/weixin1.jpg"></span>
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">课程信息：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>课程名称</span>
                            <input type="text" value="赛法斗-成人课程" class="input">
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>课程价格</span>
                            <input type="text" value="￥9999" class="input1">
                        </div>
                    </div>
                  </div>
                <div class="order">
                    <h1><img src="../static/img/ling.png">优惠码：</h1>
                    <div class="order1">
                        <div class="orderinput">
                            <span>优 惠 码</span>
                            <input type="text" name="couponNum" placeholder="请输入您的优惠码" class="input">
                            <a href="#">立即兑换</a>
                        </div>
                    </div>
                    <div class="order1">
                        <div class="orderinput">
                            <span>优 惠 码</span>
                            <input type="text" value="2343 5758 8669 6878" class="input">
                            <em>修改</em>
                            <p>优惠码优惠信息显示（全场5折）</p>
                        </div>
                    </div>
                </div>
                <div class="order" style="margin-bottom: 0;">
                    <h1><img src="../static/img/ling.png">课程价格：</h1>
                    <ul>
                        <li>课程价格<span>￥9999</span></li>
                        <li>优&emsp;&emsp;惠<span>-￥50</span></li>
                        <li>应付总额<span class="on">￥9949</span></li>
                    </ul>
                </div>
                <div class="submit">
                    <a href="#">提交订单</a>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
