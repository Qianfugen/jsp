<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="updateJuiceServlet" method="post" enctype="multipart/form-data">
        id:<input type="text" name="id" readonly="readonly" type="hidden" value="${ju.id}"><p></p>
        序号:<input type="text" name="code" value="${ju.code}"><p></p>
        名称:<select name="name">
        <c:forEach items="${juiceList}" var="juice"><option value="${juice.name}" <c:if test="(${juice.id}==${ju.id})>${juice.name}">selected="selected"</c:if>>${juice.name}</option></c:forEach>
    </select>
        <p></p>
        价格:<input type="text" name="price" value="${ju.price}"><p></p>
        数量:<input type="text" name="amount" value="${ju.amount}"><p></p>
        购入时间:<input type="date" name="buyTime" value="${ju.buyTime}"><p></p>
        图片:<input type="file" name="pic" value="${ju.pic}"><p></p>
        <input type="submit" name="add" value="修改" onclick="return confirm('确认修改?!')">
    </form>
</body>
</html>
