<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form action="/day19/loginServlet" method="post">
    <span>用户名</span>
    <input type="text" name="username">
    <span>密码</span>
    <input type="password" name="password"><br/>
    <button type="submit">立即登录</button>
    <div>${requestScope.msg}</div>
</form>
</body>
</html>
