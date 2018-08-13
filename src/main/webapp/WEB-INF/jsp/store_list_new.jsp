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
            <div class="cbanner" style="position: relative;">
                <h2>上课地点</h2>
                <img src="../static/img/bannerbm.jpg">
            </div>
            <div class="ctitle">
                <div>众力飞特 > <a href="javascript:void(0);">上课地点</a></div>
            </div>
            <form method="post" action="/store/list">
                <div class="center">
                    <div class="cfilter">
                        <div class="ccourse">
                            <div class="caddr" style="margin-top: 0px;">
                                <span>俱乐部地址：</span>
                                <div id="city_info" class="cselect addrbox">
                                    <select class="prov" id="proviceId" name="provice"></select>&nbsp;&nbsp;
                                    <select class="city" id="cityId" name="city"></select>&nbsp;&nbsp;
                                    <select class="dist" id="districtId" name="district"></select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sklist">
                        <ul>
                            <c:forEach items="${lstStore}" var="store">
                                <li>
                                    <a href="/store/detail?storeId=${store.storeId}">
                                        <c:if test="${empty store.storeImageUrl}"><img src="../static/img/tupian.jpg"></c:if>
                                        <c:if test="${not empty store.storeImageUrl}"><img src="${baseUrl}${store.storeImageUrl}"></c:if>
                                        <h2>${store.storeName}</h2>
                                        <p>
                                            <img src="../static/img/zhi.jpg">
                                            <span>${store.storeDistrict}      ${store.storeAddress}</span>
                                        </p>
                                    </a>
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
            </form>
        <jsp:include page="../footer.jsp"/>
    </div>
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1274101114'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1274101114%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
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
