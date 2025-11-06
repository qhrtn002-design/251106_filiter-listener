<%-- JSP를 방문 시 자동으로 세션이 만들어지는 걸 방지 --%>
<%-- <%@ page session="false" %> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카운터</title>
</head>
<body>
<p>접속을 카운트합니다</p>
<%-- session -> request에 담아주기 때문에 이렇게 꺼내서 쓸 수 있음 --%>
<p>세션 : <%= (Integer) session.getAttribute("counter") %></p>
<%--    <%--%>
<%--        int cookieCounter = 0;--%>
<%--        for (Cookie c : request.getCookies()) {--%>
<%--            if(c.getName().equals("counter")) {--%>
<%--                cookieCounter = Integer.parseInt(c.getValue());--%>
<%--                break;--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
<%--    <p>쿠키 : <%= cookieCounter %></p>--%>
<p>쿠키 : <%= request.getAttribute("counter") %></p>
</body>
</html>