<%@ page import="com.example.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <%--利用一个隐藏域使得id值可以在提交时一并加到request中--%>
            <input type="hidden" name="id" value="${findUser.id}">

            <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  value="${findUser.name}" readonly="readonly" placeholder="请输入姓名" />
          </div>

          <div class="form-group">
            <label>性别：</label>
<%--              <%--%>
<%--                  User user = (User) request.getAttribute("findUser");--%>
<%--                  String gender = user.getGender();--%>
<%--                  String address  = user.getAddress();--%>
<%--                  System.out.println("gender: "+gender);--%>
<%--                  System.out.println("address: "+address);--%>
<%--              %>--%>
              <%--注意不是test="${findUser.gender}== 'male'"--%>
              <c:if test="${findUser.gender == 'male'}">
                  <input type="radio" name="gender" value="male"  checked/>男
                  <input type="radio" name="gender" value="female"  />女
              </c:if>
              <c:if test="${findUser.gender == 'female'} ">
                  <input type="radio" name="gender" value="male"  />男
                  <input type="radio" name="gender" value="female"  checked/>女
              </c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${findUser.age}" placeholder="请输入年龄" />
          </div>

          <div class="form-group">
            <label for="address">籍贯：</label>
             <select name="address" class="form-control" >
                 <c:if test="${findUser.address == '广东'} ">
                     <option value="广东" selected>广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                 </c:if>
                 <c:if test="${findUser.address == '广西'} ">
                     <option value="广东">广东</option>
                     <option value="广西" selected>广西</option>
                     <option value="湖南">湖南</option>
                 </c:if>
                 <c:if test="${findUser.address == '湖南'}">
                     <option value="广东">广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南" selected>湖南</option>
                 </c:if>

            </select>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" value="${findUser.qq}" placeholder="请输入QQ号码"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" value="${findUser.email}" placeholder="请输入邮箱地址"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回"/>
             </div>
        </form>
        </div>
    </body>
</html>