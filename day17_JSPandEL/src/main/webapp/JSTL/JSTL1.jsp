<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  学习JSTL
  1.if
  2.choose
  3.foreach
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学习JSTL</title>
</head>
<body>
<%--1、if--%>
<c:if test="$<3<4>">
    <h1>It is true</h1>
</c:if>
<br/>
<br/>
<%--2、choose--%>
<%
    int x = 2;
    request.setAttribute("x", x);
%>
<c:choose>
    <c:when test="${x==1}">我是1</c:when>
    <c:when test="${x==2}">我是2</c:when>
    <c:when test="${x==3}">我是3</c:when>
    <c:otherwise>我啥都不是</c:otherwise>
</c:choose>
<br/>
<br/>
<%--3、foreach--%>
<c:forEach begin="1" end="10" step="1" var="i" varStatus="vs">
    <h3>${vs.index}</h3> ${i}
</c:forEach>
<%
    List list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    request.setAttribute("list", list);
%>
<br/>
<br/>
<c:forEach items="${list}" var="item" varStatus="vs">
    <h3>${vs.index}</h3> ${item}
</c:forEach>
</body>
</html>
