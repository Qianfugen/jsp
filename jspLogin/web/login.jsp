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
<%
    //拿出用户名密码
    String cName = "";
    String cPwd = "";
    Cookie[] cs = request.getCookies();
    if (cs != null) {
        for (Cookie c : cs) {
            if ("cName".equals(c.getName())) {
                cName = URLDecoder.decode(c.getValue(), "utf-8");
            }
            if ("cPwd".equals(c.getName())) {
                cPwd = c.getValue();
            }
        }
    }
%>
<form id="f" action="getValue.jsp" method="post" onload="login()">
    用户名:<input id="name" type="text" name="name" value="<%=cName%>">
    <p>
        密码:<input id="pwd" type="password" name="pwd" value="<%=cPwd%>">
    <p>
        爱好:<input type="checkbox" name="hobby" value="lq">篮球
        <input type="checkbox" name="hobby" value="java">java
    <p>
        <input type="checkbox" name="isno" value="yes">十天免登陆
    <p>
        <input type="submit" value="提交">
</form>
</body>
</html>
