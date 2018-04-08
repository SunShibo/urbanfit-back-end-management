<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
</head>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <div class="route">
                <span>教练团队</span>
                <div class="navbox1">
                    <img src="../static/img/navbg.jpg" alt="">
                    <ul id="myTab" class="navboxul">
                        <li><a href="#beijing" data-toggle="tab">北京团队</a></li>
                    </ul>
                </div>
            </div>
            <div class="course">
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active coach" id="beijing">
                        <div class="team">
                            <img src="../static/img/beijing.png">
                        </div>
                        <div class="coachbox">
                            <ul>
                                <c:forEach items="${lstCoach}" var="coach">
                                    <li>
                                        <div class="coachimg">
                                            <img src="${baseUrl}${coach.headPortrait}">
                                        </div>
                                        <div class="coachtext">
                                            ${coach.introduce}
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                            <!-- 分页
                             <div class="pagerbox">
                                 <ul class="pager">
                                     <li class="previous"><a href="#">&larr; 上一页</a></li>
                                     <li>
                                         <span>1-30</span>
                                     </li>
                                     <li class="next"><a href="#">下一页 &rarr;</a></li>
                                 </ul>
                             </div> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
</html>
