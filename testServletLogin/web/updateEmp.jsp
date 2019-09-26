<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="emp/updateServlet" method="post" enctype="multipart/form-data">
        员工编号:<input type="text" name="empNo" readonly="readonly" value="${emp.empNo}"><p>
        员工姓名:<input type="text" name="eName" value="${emp.eName}"><p>
        员工岗位:<select name="job">
                    <option value="销售" <c:if test="${emp.job=='销售'}"> selected="selected"</c:if>>销售</option>
                    <option value="经理" <c:if test="${emp.job=='经理'}"> selected="selected"</c:if>>经理</option>
                    <option value="董事长" <c:if test="${emp.job=='董事长'}"> selected="selected"</c:if>>董事长</option>
                    <option value="职员" <c:if test="${emp.job=='职员'}"> selected="selected"</c:if>>职员</option>
                    <option value="研发" <c:if test="${emp.job=='研发'}"> selected="selected"</c:if>>研发</option>
                </select><p>
        上级领导:<input type="text" name="mgr" value="${emp.mgr}"><p>
        入职日期:<input type="date" name="hireDate" value="${emp.hireDate}"><p>
        月薪:<input type="text" name="sal" value="${emp.sal}"><p>
        奖金:<input type="text" name="comm" value="${emp.comm}"><p>
        部门:<select name="deptNo">
                <c:forEach items="${depts}" var="d">
                    <option value="${d.deptNo}" <c:if test="${emp.deptNo==d.deptNo}">selected="selected"</c:if>>${d.dName}</option>
                </c:forEach>
            </select><p>
        <img src="${emp.pic}">
        <input name="imgPic" type="hidden" value="${emp.pic}">
        头像:<input type="file" name="pic"><p>

        <input type="submit" value="修改">
    </form>
</body>
</html>