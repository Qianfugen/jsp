<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLEncoder" %>
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
    <form action="loginServlet" method="post">
        <div align="center">
            <div style="color: red">${param.error}</div>
            <!--cookie用户名写解码,不支持中文-->
            用户名:<input type="text" name="userName" value="${URLDecoder.decode(cookie.cName.value,"utf-8")}"><p></p>
            密码:<input type="password" name="pwd" value="${cookie.cPwd.value}"><p></p>
            <input type="checkbox" name="isno" value="yes">&nbsp;十天内免登录<p></p>
            <input type="submit" value="登录">
        </div>
    </form>
</body>
</html>
