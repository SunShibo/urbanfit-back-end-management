<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>公司介绍</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript">
        $(function (){
            $("li[id^='about_']").removeClass();
            $("li[id='about_company']").addClass("active");
            $("li[id^='menu_']").removeClass();
            $("#menu_about").addClass("on");
        })
    </script>
<body>
    <div class="content">
        <jsp:include page="../main.jsp"/>
        <div class="center">
            <jsp:include page="about_common.jsp"/>
            <div class="course">
                <div id="myTabContent" class="tab-content" style="width: 100%;padding-bottom:0;">
                    <div class="tab-pane fade in active company clear" id="company">
                        <div class="imgtitle">
                          <img src="../static/img/company.png">
                        </div>
                        <div class="companybox">
                          <h1>北京众力飞特体育产业发展有限责任公司</h1>
                          <p>北京众力飞特体育产业发展有限责任公司致力于赛法斗项目在全民健身领域的推广，是国际赛法斗联合会（FISav）和中国赛法斗联合会（Chinese Savate Federation）在国内唯一授权的全民推广商业合作伙伴，运营赛法斗项目的教学和训练机构；目前，已与各大健身和搏击俱乐部达成课程合作，授权教学点已达800家。除训练课程外，众力飞特在赛法斗装备、专向赛事组织等项目相关领域，亦有发展规划。</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp"/>
    </div>
</body>
</html>
