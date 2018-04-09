<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8"/>
  <title>我的订单</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/client_info.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>填写我的信息</span>
            </div>
        </div>
        <div class="pay">
            <div class="paybox user">
                <jsp:include page="client_common.jsp"/>
                <div class="user_box">
                    <h1>我的订单</h1>
                    <div class="screen">
                        <ul>
                            <li>
                                <span>订单号</span>
                                <input type="text" value="" placeholder="请输入您的订单号">
                            </li>
                            <li>
                                <span>订单状态</span>
                                <select>
                                    <option value="">状态</option>
                                    <option value="">未支付</option>
                                    <option value="">已支付</option>
                                </select>
                            </li>
                            <li>
                                <a href="#">搜索</a>
                            </li>
                        </ul>
                    </div>
                    <table cellpadding="0" cellspacing="0" class="table">
                        <tr>
                            <th>票种</th>
                            <th>课程名称</th>
                            <th>价格</th>
                            <th>学生姓名</th>
                            <th>订单日期</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <tr>
                            <td>73749857806</td>
                            <td>成人课程</td>
                            <td><span>￥55.00</span></td>
                            <td>设计师</td>
                            <td>2019-10-10  10:00:00</td>
                            <td><i>未支付</i></td>
                            <td><a href="#">支付</a><a href="#">查看</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
