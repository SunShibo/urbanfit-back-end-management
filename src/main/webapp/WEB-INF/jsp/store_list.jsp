<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店信息</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <%--<jsp:include page="about_common.jsp"/>--%>
            <form method="post" id="storeForm" action="list">
                <div class="course">
                    <div id="myTabContent" class="tab-content" style="width: 100%;padding-bottom:0;">
                        <div class="tab-pane fade in active store clear" id="store">
                            <div class="storeimg">
                                <img src="../static/img/store.png">
                            </div>
                            <div class="storeright">
                                <div class="select">
                                    <div id="city_info">
                                        <select class="prov" id="proviceId" name="provice"></select>&nbsp;&nbsp;
                                        <select class="city" id="cityId" name="city"></select>&nbsp;&nbsp;
                                        <select class="dist" id="districtId" name="district"></select>
                                    </div>
                                </div>
                                <ul>
                                    <c:forEach items="${lstStore}" var="store">
                                        <li>
                                          <h3><img src="../static/img/ling.png">${store.storeName}</h3>
                                          <p>地址：${store.storeDistrict}${store.storeAddress}</p>
                                          <%--<p>电话：${store.mobile}</p>
                                          <p>联系：${store.contactName}</p>--%>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <div class="page clear">
                                    <div class="pages">
                                        <jsp:include page="./common/pager.jsp">
                                          <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                                          <jsp:param value="${pager.totalPage}" name="totalPage"/>
                                          <jsp:param value="list" name="url"/>
                                        </jsp:include>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/web/store_list.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript">
        var store = {
          "provice" : '${provice}',
          "city" : '${city}',
          "district" : '${district}'
        };
    </script>
</body>
