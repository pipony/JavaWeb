<%@ page import="com.example.domain.User" %><%--
  登录成功界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<h1>欢迎您</h1>
<%
    User user = (User) request.getSession().getAttribute("user");
    String username = user.getUsername();
%>
<%=username%>
</body>
</html>
