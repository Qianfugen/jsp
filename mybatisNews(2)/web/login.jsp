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
    <title>登录页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="loginServlet" method="post">
        <div align="center">
            <div style="color: red">${URLDecoder.decode(errorInfo)}</div>
            用户名:<input type="text" name="username" value="${cookie.cName.value}"></p>
            密码:<input type="password" name="pwd" value="${cookie.cPwd.value}"></p>
            <input type="checkbox" name="isno" value="yes">&nbsp;十天内免登陆</p>
            <input type="submit" value="登录">
        </div>
    </form>
</body>
</html>
