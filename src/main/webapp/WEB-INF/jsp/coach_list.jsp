<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练列表</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <style>
        ::-webkit-input-placeholder { color:#c6b98e; }
        ::-moz-placeholder { color:#c6b98e; } /* firefox 19+ */
        :-ms-input-placeholder { color:#c6b98e; } /* ie */
        input:-moz-placeholder { color:#c6b98e; }
    </style>

    <script type="text/javascript">
        $(function (){
            $("li[id^='menu_']").removeClass();
            $("#menu_coach").addClass("on");
        })
    </script>
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
                                            <h2>${coach.coachName}<span>${coach.coachTitle}</span></h2>
                                            ${coach.introduce}
                                        </div>
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
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
</html>
