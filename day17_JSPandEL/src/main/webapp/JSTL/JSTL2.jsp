<%@ page import="com.example.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
实现案例：在request域中有一个存有User对象的List集合。需要使用jstl+el将list集合数据展示到
jsp页面的表格table中
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息展示</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%--生成两个用户--%>
<%
    User user1 = new User(1, "cindy", 24);
    User user2 = new User(2, "alice", 22);
    List userList = new ArrayList();
    userList.add(user1);
    userList.add(user2);
    request.setAttribute("userList", userList);
%>
<table>
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>年龄</th>
    </tr>
    <%--使用JSTL的foreach标签遍历list--%>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
