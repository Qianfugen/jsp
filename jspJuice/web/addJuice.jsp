<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.polo.Juice" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="addJuiceServlet" method="post" enctype="multipart/form-data">
        <input type="text" name="id" readonly="readonly" type="hidden"><p></p>
        序号:<input type="text" name="code"><p></p>
        名称:<select name="name">
                <c:forEach items="${juiceList}" var="ju"><option value="${ju.name}">${ju.name}</option></c:forEach>
            </select>
        <p></p>
        价格:<input type="text" name="price"><p></p>
        数量:<input type="text" name="amount"><p></p>
        购入时间:<input type="date" name="buyTime"><p></p>
        图片:<input type="file" name="pic"><p></p>
        <input type="submit" name="add" value="添加" onclick="return confirm('确认添加?!')">
    </form>
</body>
</html>
