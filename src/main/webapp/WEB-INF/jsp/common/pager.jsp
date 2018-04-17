<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>

<script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/common/pager.js"></script>
<%
  String pageSize = request.getParameter("pageSize");
  if (null == pageSize || "".equals(pageSize)) {
      pageSize = "10";
  }
  request.setAttribute("pageSize", pageSize);
%>
<pg:pager export="curPage=pageNumber" items="${param.totalRecord}" maxPageItems="${pageSize}" url="${param.url}">
    <div class="pagerbox">
        <ul class="pager">
            <pg:param name="keywords"/>
            <pg:prev>
                <li class="previous">
                    <a href="javascript:void(0);" name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
                        data-tosize="${pageSize}"><i></i>&nbsp;&nbsp;上一页&nbsp;&nbsp;</a>
                </li>
            </pg:prev>
            <li>
                <c:if test="${param.totalPage != 0 && param.totalPage != 1}">
                    <span>1 - ${param.totalPage}</span>
                </c:if>
            </li>
            <pg:next>
                <li class="next">
                    <a href="javascript:void(0); " name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
                        data-tosize="${pageSize}">&nbsp;&nbsp;下一页&nbsp;&nbsp;</a>
                </li>
            </pg:next>
        </ul>
    </div>
</pg:pager>