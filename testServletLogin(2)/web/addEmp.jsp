<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.zl.pojo.Dept" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加员工</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="emp/addServlet" method="post" enctype="multipart/form-data">
        员工编号:<input type="text" name="empNo"><p>
        员工姓名:<input type="text" name="eName"><p>
        员工岗位:<select name="job">
                    <option value="销售">销售</option>
                    <option value="经理">经理</option>
                    <option value="董事长">董事长</option>
                    <option value="职员">职员</option>
                    <option value="研发">研发</option>
                </select><p>
        上级领导:<input type="text" name="mgr"><p>
        入职日期:<input type="date" name="hireDate"><p>
        月薪:<input type="text" name="sal"><p>
        奖金:<input type="text" name="comm"><p>
        部门:<select name="deptNo">
               <c:forEach items="${depts}" var="d">
                    <option value="${d.deptNo}">${d.dName}</option>
               </c:forEach>
            </select><p>
        头像:<input type="file" name="pic"><p>
        <input type="submit" value="添加">
    </form>
</body>
</html>