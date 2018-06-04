<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>我的订单</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/order_master_list.js"></script>
    <script type="text/javascript">
        var status = '${status}';
    </script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>我的订单</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox user">
                <jsp:include page="client_common.jsp"/>
                <div class="user_box">
                    <form id="orderForm" method="post">
                        <h1>我的订单</h1>
                        <div class="screen">
                            <ul>
                                <li>
                                    <span>订单号</span>
                                    <input type="hidden" name="clientId" value="1">
                                    <input type="text" name="orderNum" value="${orderNum}" placeholder="请输入您的订单号">
                                </li>
                                <li>
                                    <span>订单状态</span>
                                    <select name="status" style="margin-left: 20px;">
                                        <option value="">状态</option>
                                        <option value="0">未支付</option>
                                        <option value="1">已支付</option>
                                        <option value="2">已退款</option>
                                        <option value="3">系统自动取消</option>
                                    </select>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="B_search">搜索</a>
                                </li>
                            </ul>
                        </div>
                        <table cellpadding="0" cellspacing="0" class="table">
                            <tr>
                                <th>订单号</th>
                                <th>课程名称</th>
                                <th>价格</th>
                                <th>学生姓名</th>
                                <th>订单日期</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${lstOrder}" var="order">
                                <tr>
                                    <td>${order.orderNum}</td>
                                    <td>${order.courseName}</td>
                                    <td><span>￥${order.payPrice}</span></td>
                                    <td>${order.childrenName}</td>
                                    <td>
                                        <fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <i id="orderStatus_${order.orderNum}">
                                            <c:if test="${order.status == 0}">未支付</c:if>
                                            <c:if test="${order.status == 1}">已支付</c:if>
                                            <c:if test="${order.status == 2}">已退款</c:if>
                                            <c:if test="${order.status == 3}">系统自动取消</c:if>
                                        </i>
                                    </td>
                                    <td>
                                        <c:if test="${order.status == 0}">
                                            <a href="javascript:void(0);" id="A_pay_order_${order.orderNum}" data-ordernum="${order.orderNum}">支付</a>
                                        </c:if>
                                        <a href="javascript:void(0);" id="A_order_detail_${order.orderNum}" data-ordernum="${order.orderNum}">查看</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="page clear">
                            <div class="pages">
                                <jsp:include page="./common/pager.jsp">
                                    <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                                    <jsp:param value="${pager.totalPage}" name="totalPage"/>
                                    <jsp:param value="list" name="url"/>
                                </jsp:include>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 弹框 -->
        <div class="kuang">
            <div class="kuang1">
                <div class="kuang2">
                    <h1>请选择付款方式<img src="../static/img/close1.png" class="close"></h1>
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
                    <input type="hidden" name="payOrderNum">
                    <a href="javascript:void(0);" id="A_payOrder">确定</a>
                </div>
                <div class="kuang3">
                    <h1>订单详情<img src="../static/img/close1.png" class="close"></h1>
                    <div class="kuangul">
                        <h2>订单编号：<label id="detailOrderNum"></label><span>提交时间：<label id="detailCreateTime"></label></span></h2>
                        <ul>
                            <li><h3>学生信息：</h3></li>
                            <li>学生姓名：<label id="detailChildreName"></label></li>
                            <li>手机号：<label id="detailClientMobile"></label></li>
                        </ul>
                        <ul>
                            <li><h3>课程信息：</h3></li>
                            <li>课程名称：<label id="detailCourseName"></label></li>
                            <li>课程类型：<label id="detailCourseType"></label></li>
                            <li>课程规格：<label id="detailCourseSize"></label></li>
                            <li>课程价格：￥<label id="detailCoursePrice"></label></li>
                            <li>俱乐部名称：<label id="detailStoreName"></label></li>
                            <li>俱乐部地址：<label id="detailStoreAddress"></label></li>
                        </ul>
                        <ul>
                            <li><h3>支付信息：</h3></li>
                            <li>支付方式：<label id="detailPayment"></label></li>
                            <li>支付额度：<span>￥<label id="detailPayPrice"></label></span></li>
                            <li>支付状态：<label id="detailStatus"></label></li>
                        </ul>
                        <ul id="couponDiv" style="display: none;">
                            <li><h3>优惠码：</h3></li>
                            <li><p>-￥ <label id="couponPrice"></label>&nbsp;&nbsp;<label id="couponName"></label></p></li>
                        </ul>
                        <ul id="remarksDiv" style="display: none;">
                            <li><h3>备注信息：</h3></li>
                            <li>备注：<label id="remarks"></label></li>
                        </ul>
                    </div>
                    <a href="javascript:void(0);" id="closeOrderDetail">确定</a>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
