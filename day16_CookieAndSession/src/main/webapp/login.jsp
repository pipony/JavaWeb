<%--实现案例：带验证码的登录界面--%>
<%--案例需求：--%>
<%--1. 访问带有验证码的登录页面login.jsp--%>
<%--2. 用户输入用户名，密码以及验证码。--%>
<%--   * 如果用户名和密码输入有误，跳转登录页面，提示:用户名或密码错误--%>
<%--   * 如果验证码输入有误，跳转登录页面，提示：验证码错误--%>
<%--   * 如果全部输入正确，则跳转到主页success.jsp，显示：用户名,欢迎您--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <script>
        // 实现“点击验证码图片切换验证码的功能”
        window.onload = function (){
            document.getElementById("img").onclick = function (){
                this.src = "/day16/checkCodeServlet?time="+new Date().getTime();
            }
        }
    </script>
    <style>
        div{
            color: red;
        }
    </style>
</head>
<body>
<form action="/day16/LoginServlet" method="POST">
    <span>用户名</span>
    <input type="text" name="username"><br/>
    <span>密码</span>
    <input type="password" name="password"><br/>
    <span>验证码</span>
    <input type="text" name="code"><br/>
    <img id="img" src="/day16/checkCodeServlet"><br/>  <%--验证码图片的路径是那个生成验证码的servlet --%>
    <button type="submit">登录</button>
</form>
<%--显示错误提示信息--%>
<div><%=request.getAttribute("cc_error") == null ? "": request.getAttribute("cc_error")%></div>
<div><%=request.getAttribute("login_error") == null ? "": request.getAttribute("login_error")%></div>
</body>
</html>
