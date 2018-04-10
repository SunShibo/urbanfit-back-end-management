<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/order_master_list.js"></script>
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
                <form id="orderForm" method="post" action="list">
                    <div class="user_box">
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
                                            <a href="javascript:void(0);" id="A_pay_order" data-ordernum="${order.orderNum}">支付</a>
                                        </c:if>
                                        <a href="javascript:void(0);" id="A_order_detail" data-ordernum="${order.orderNum}">查看</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
