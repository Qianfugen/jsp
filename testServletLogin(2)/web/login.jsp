<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLDecoder" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>登录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <font size="1" color="red">${param.error}</font>
    <form id="f" action="emp/loginServlet" method="post">
        用户名:<input id="name" type="text" name="name" value="${URLDecoder.decode(cookie.cName.value,"utf-8")}"><p>
        密码:<input id="pwd" type="password" name="pwd" value="${cookie.cPwd.value}"><p>
        <input type="checkbox" name="isno" value="yes">十天免登陆<p>
        <input type="submit" value="提交">
    </form>
</body>
</html>