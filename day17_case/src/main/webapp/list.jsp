<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.User" %><%--
  查询所有用户信息页面
  使用jstl的foreach遍历list集合生成表格
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        .disabled {
            pointer-events: none;
        }
    </style>
    <script>
        function deleteUser(id){
            //加一个删除确认框
            if(confirm("你确定要删除吗？")){
                //地址跳到DelUserServlet
                location.href = "${pageContext.request.contextPath}/delUserServlet?id="+id;
            }
        }
        window.onload = function (){
            //定义“删除选中”按钮的动作
            document.getElementById("delSelected").onclick = function (){
                if (confirm("确定要删除这些记录吗？")){
                    //先判断有无用户被选中，若没有被选中则不提交表单
                    let flag = false; //标记有用户被选中
                    let cbs = document.getElementsByName("uid");
                    for(var i = 0; i < cbs.length; i++){
                        if (cbs[i].checked){
                            flag = true;
                            break;
                        }
                    }
                    //若至少有一个用户被选中，则提交表单
                    if (flag){
                        document.getElementById("form").submit();
                    }
                }
            }
            //定义“全选”按钮被选中时的动作
            document.getElementById("firstCb").onclick = function (){
                //设置所有框的状态为“全选”按钮的状态
                let cbs = document.getElementsByName("uid");
                for(var i = 0; i < cbs.length; i++){
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputName3" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>

    <%--为啥要用form呢？因为我们在实现“删除选中”功能时，需要获取所有选中的行的id，此时用form可以自然支持它--%>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="user" varStatus="vs" >
            <tr>
                <td><input type="checkbox" name="uid" value="${user.id}"></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <%--注意下面两个按钮的href，需要获取要执行的用户id。后者还借助了js实现删除确认操作--%>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <%--“上一页”按钮--%>
            <c:if test="${pb.currentPage == 1}">
            <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage != 1}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>


            <c:forEach begin="1" end="${pb.totalPage}" var="i" >

                <%--如为当前页，则页码条的当前页码外观特殊--%>
                <c:if test="${pb.currentPage == i}">
                    <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                </c:if>

            </c:forEach>

            <%--“下一页”按钮--%>
            <c:if test="${pb.currentPage == pb.totalPage}">
            <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage != pb.totalPage}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>

            <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

        </ul>
    </nav>


</div>
</div>
</body>
</html>

