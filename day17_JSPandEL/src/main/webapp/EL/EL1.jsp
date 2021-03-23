<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--学习EL
1.运算
2.获取域对象--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL学习</title>
</head>
<body>
<%--学习运算--%>
${1+1}
${3>4}
${3<4 && 1==1}
<%
    //创建空对象
    List list = new ArrayList();
    //特别注意，由于EL只能获取域对象，所以还需要将list添加到域对象才行
    request.setAttribute("listAttr", list);
%>
${empty listAttr}
<%--学习获取值--%>
<%
    List list1 = new ArrayList();
    list1.add(1);
    list1.add(2);
    List list2 = new ArrayList();
    list2.add("aaa");
    list2.add("bbb");
    //分别加入pageContext和request域的list属性中
    pageContext.setAttribute("list", list1);
    request.setAttribute("list", list2);
%>
<br/>
${"pageScope.list "}
${pageScope.list}<br/>
${"requestScope.list "}
${requestScope.list}<br/>
${"list "}
${list}<br/>
${"list[0] "}
${list[0]}
</body>
</html>
