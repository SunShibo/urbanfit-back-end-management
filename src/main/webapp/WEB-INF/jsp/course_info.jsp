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
    <script type="text/javascript" src="/static/js/web/course_info.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <jsp:include page="course_common.jsp"/>
            <div class="course">
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active coursetext" id="adult">
                        <c:if test="${empty course}">
                            <h1 style="margin-top: 50px;margin-left: 30%;">敬请期待...</h1>
                            <div class="baoming">
                                <div class="baomingform">
                                    <div class="baoming2">
                                        <h1>成人课程报名</h1>
                                        <div>
                                            <ul>
                                                <li>
                                                    <span>姓名:</span>
                                                    <input type="text" name="clientName">
                                                </li>
                                                <li>
                                                    <span>联系电话:</span>
                                                    <input type="text" name="clientMobile">
                                                </li>
                                            </ul>
                                            <a href="javascript:void(0);" id="A_join_waiting_course">报名</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty course}">
                        <div class="tab-pane fade coursetext active in" id="juvenile">
                            <div class="sell">
                                <div class="sellimg">
                                    <img src="${baseUrl}${course.courseImageUrl}" style="width:440px; height:280px;">
                                </div>
                                <ul class="sellbox">
                                    <li><h1>赛法斗-<span id="name">${course.courseName}</span></h1></li>
                                    <li>
                                        <p><img src="../static/img/yang.jpg" width="16" height="16">
                                          价&emsp;&emsp;格：<span id="price">${course.coursePrice}元</span></p>
                                    </li>
                                    <li>
                                        <p><img src="../static/img/zhi.jpg" width="15" height="20">
                                          上课地域：</p>
                                        <div class="select">
                                            <input type="hidden" name="courseDistrict" id="district" value="${course.courseDistrict}">
                                            <div id="city_info">
                                                <select id="s_province" name="s_province"></select>&nbsp;&nbsp;
                                                <select id="s_city" name="s_city" ></select>&nbsp;&nbsp;
                                                <select id="s_county" name="s_county"></select>
                                            </div>
                                      </div>
                                    </li>
                                    <li>
                                        <input type="hidden" name="courseId" value="${courseId}">
                                        <a href="javascript:void(0);" id="A_join_course">我要报名</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="clear"></div>
                            <div class="text">${course.introduce}</div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
    <script type="text/javascript">
        var courseId = '${courseId}';
    </script>
</body>
