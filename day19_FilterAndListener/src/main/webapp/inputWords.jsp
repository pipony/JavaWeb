
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>输入一句话</title>
</head>
<body>
<h1>输入一行字吧 脏话也行</h1>
<form action="/day19/showWords.jsp" method="post">
    <input type="text" name="words">
    <button type="submit">提交</button>
</form>
</body>
</html>
