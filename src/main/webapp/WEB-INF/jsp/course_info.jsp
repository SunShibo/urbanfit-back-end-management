<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>课程信息</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/menu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/cityselect.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/web/course_info.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <jsp:include page="course_common.jsp"/>
            <div class="course">
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade coursetext active in" id="juvenile">
                        <div class="sell">
                            <div class="sellimg">
                                <img src="${baseUrl}${course.courseImageUrl}" style="width:440px; height:280px;">
                            </div>
                            <ul class="sellbox">
                                <li><h1>赛法斗-${course.courseName}</h1></li>
                                <li>
                                    <p><img src="../static/img/yang.jpg" width="16" height="16">
                                      价&emsp;&emsp;格：<span>${course.coursePrice}元</span></p>
                                </li>
                                <li>
                                    <p><img src="../static/img/zhi.jpg" width="15" height="20">
                                      上课地域：</p>
                                    <div class="select">
                                        <input type="hidden" name="courseDistrict" value="${course.courseDistrict}">
                                        <div id="city_info">
                                            <select class="prov" id="s_province" name="s_province"></select>&nbsp;&nbsp;
                                            <select class="city" id="s_city" name="s_city" ></select>&nbsp;&nbsp;
                                            <select class="dist" id="s_county" name="s_county"></select>
                                        </div>
                                  </div>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" id="A_join_course">我要报名</a>
                                </li>
                            </ul>
                        </div>
                        <div class="clear"></div>
                        <div class="text">${course.introduce}</div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
    <script type="text/javascript">
        var store = {
            "provice" : '${provice}',
            "city" : '${city}',
            "district" : '${district}'
        };
    </script>
</body>
