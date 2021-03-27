
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示输入的话</title>
</head>
<body>
<%
    String words = request.getParameter("words");
    request.setAttribute("words", words);
%>
${requestScope.words}
</body>
</html>
