<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>课程信息</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link rel="stylesheet" type="text/css" href="/static/css/common.css">
  <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/menu.js"></script>
  <script type="text/javascript" src="/static/js/web/course_detail.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="cbanner">
            <h2 id="courseNameH2"></h2><img src="../static/img/cbanner.jpg">
        </div>
        <div class="ctitle">
            <div>众力飞特 > 课程报名 > <label name="courseName"></label>详情</div>
        </div>
        <form method="post" action="/apiCourse/toJoin">
            <div class="center">
                <div class="cdetailbox">
                    <img src="../static/img/tu5.jpg" name="courseImageUrl" width="440" height="280">
                    <ul>
                        <li><h1 id="courseNameH1"></h1></li>
                        <li>
                            <img src="../static/img/yang.jpg" width="16" height="16" style="top:13px;">
                            <p>价&emsp;&emsp;格：<span><label id="coursePrice"></label>元</span></p>
                        </li>
                        <li>
                            <img src="../static/img/lei.jpg" width="17" height="17">
                            <p>课程类型：<i><label id="courseType"></label></i></p>
                        </li>
                        <li>
                            <img src="../static/img/zhi.jpg" width="15" height="20">
                            <p>所属地区：<i><label id="storeDistrict"></label></i></p>
                        </li>
                        <li>
                            <img src="../static/img/ju.jpg" width="16" height="16">
                            <p>俱&nbsp;乐&nbsp;部&nbsp;：<select id="courseStore"></select></p>
                            <input type="hidden" name="storeId">
                        </li>
                        <li>
                            <ul id="courseSizeDetail"></ul>
                            <input type="hidden" name="courseSizePrice">
                            <input type="hidden" name="sizeTypeIndex">
                            <input type="hidden" name="detailId">
                            <input type="hidden" name="courseId" value="${courseId}">
                        </li>
                        <li>
                            <%--<input type="button" id="B_join_course" class="joinButton"  value="我要报名">--%>
                            <a href="javascript:void(0);" id="B_join_course">我要报名</a>
                        </li>
                    </ul>
                </div>
                <div class="cdetaillist" id="courseDetailDiv"></div>
            </div>
        </form>
        <jsp:include page="../footer.jsp"/>
    </div>

    <script type="text/javascript">
      var courseId = '${courseId}';
    </script>
</body>
