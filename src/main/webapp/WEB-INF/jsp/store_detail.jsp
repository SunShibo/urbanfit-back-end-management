<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>门店详情</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link rel="stylesheet" type="text/css" href="/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript">
        $("li[id^='menu_']").removeClass();
        $("#menu_auth").addClass("on");
    </script>
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
        <form method="post" action="/course/list">
            <div class="center">
                <div class="shangkebox">
                    <h1>${store.storeName}介绍</h1>
                    <div class="shangkebox1">
                        <div class="shangleft">
                            <c:if test="${empty store.storeImageUrl}"><img src="../static/img/tupian.jpg"></c:if>
                            <c:if test="${not empty store.storeImageUrl}"><img src="${baseUrl}${store.storeImageUrl}"></c:if>
                        </div>
                        <div class="shangright">
                            <h2>${store.storeName}<p><img src="../static/img/zhi.jpg">${store.storeDistrict}${store.storeAddress}</p></h2>
                            <p>${store.introduce}</p>
                        </div>
                    </div>
                </div>
                <div class="clist">
                    <h1>课程信息</h1>
                    <ul>
                        <c:forEach items="${lstCourse}" var="course">
                            <li>
                                <div class="clistcourse">
                                    <img src="${baseUrl}${course.courseImageUrl}" width="256" height="156">
                                    <ul>
                                        <li><h1>${course.courseName}</h1></li>
                                        <li>
                                            <img src="../static/img/yang.jpg" width="16" height="16" style="top:8px;">
                                            <p>价&emsp;&emsp;格：<span>${course.coursePrice}元</span></p>
                                        </li>
                                        <li>
                                            <img src="../static/img/lei.jpg" width="17" height="17">
                                            <p>课程类型：
                                                <i>
                                                  <c:if test="${course.courseType == 1}">成人课程</c:if>
                                                  <c:if test="${course.courseType == 2}">青少年课程</c:if>
                                                  <c:if test="${course.courseType == 3}">私教课程</c:if>
                                                  <c:if test="${course.courseType == 4}">特色课程</c:if>
                                                </i>
                                            </p>
                                        </li>
                                    </ul>
                                </div>
                                <a href="/apiCourse/toDetail?courseId=${course.courseId}&storeId=${store.storeId}">我要报名</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </form>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
</html>
