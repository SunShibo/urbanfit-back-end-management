<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程报名|赛法斗培训||儿童搏击课|少儿赛法斗课程-众力飞特</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="content">
    <jsp:include page="../main.jsp"/>
    <div class="cbanner" style="position: relative;">
        <h2>课程介绍</h2>
        <img src="../static/img/cbanner.jpg">
    </div>
    <div class="ctitle">
        <div>众力飞特 > <a href="javascript:void(0);">课程报名</a></div>
    </div>
    <form method="post" action="/course/list">
        <div class="center">
            <div class="cfilter">
                <div class="ccourse">
                    <span>课程类型：</span>
                    <ul>
                        <li><a <%--class="on"--%> name="A_query_courseType" href="javascript:void(0);" data-type="">全部</a></li>
                        <li><a name="A_query_courseType" href="javascript:void(0);" data-type="1">成人课程</a></li>
                        <li><a name="A_query_courseType" href="javascript:void(0);" data-type="2">青少年课程</a></li>
                        <li><a name="A_query_courseType" href="javascript:void(0);" data-type="3">私教课程</a></li>
                        <li><a name="A_query_courseType" href="javascript:void(0);" data-type="4">特色课程</a></li>
                        <input type="hidden" name="courseType" value="${courseType}">
                    </ul>
                </div>
                <div class="caddr">
                    <span>俱乐部地址：</span>
                    <div id="city_info" class="cselect">
                        <select class="prov" id="proviceId" name="provice"></select>&nbsp;&nbsp;
                        <select class="city" id="cityId" name="city"></select>&nbsp;&nbsp;
                        <select class="dist" id="districtId" name="district"></select>
                    </div>
                </div>
            </div>
            <div class="clist">
                <ul>
                    <c:forEach items="${lstCourse}" var="course">
                        <li>
                            <div class="clistcourse">
                                <c:if test="${empty course.courseImageUrl}">
                                    <img src="../static/img/tu5.jpg" width="256" height="156">
                                </c:if>
                                <c:if test="${not empty course.courseImageUrl}">
                                    <img src="${baseUrl}${course.courseImageUrl}" width="256" height="156">
                                </c:if>
                                <ul>
                                    <li><h1>${course.courseName}</h1></li>
                                    <li>
                                        <img src="../static/img/yang.jpg" width="16" height="16" style="top:8px;">
                                        <p>价&emsp;&emsp;格：<span>${course.coursePrice}元</span></p>
                                    </li>
                                    <li>
                                        <img src="../static/img/ju.jpg" width="16" height="16">
                                        <p>俱&nbsp;乐&nbsp;部&nbsp;：<i>${course.storeName}</i></p>
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
                                    <li>
                                        <img src="../static/img/zhi.jpg" width="15" height="20">
                                        <p>所属地区：<i>${course.storeDistrict}</i></p>
                                    </li>
                                </ul>
                            </div>
                            <a href="javascript:void(0);" id="A_join_course_${course.courseId}"
                               data-courseid="${course.courseId}">我要报名</a>
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

    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/web/course_list.js"></script>
    <script type="text/javascript">
        var course = {
            courseType : '${courseType}',
            "provice" : '${provice}',
            "city" : '${city}',
            "district" : '${district}'
        };
        $(function(){
            $('.ccourse ul li a').click(function(){
                $('.ccourse ul li a').removeClass('on');
                $(this).addClass('on');
            })
        })
    </script>
</div>
