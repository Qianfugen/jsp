<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.io.StringBufferInputStream" %>
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
<%
    String cName="";
    String cPwd="";
    Cookie[] cs=request.getCookies();
    //获取用户名和密码
    if(cs!=null) {
        for (Cookie c : cs) {
            if ("cName".equals(c.getName())) {
                cName = URLDecoder.decode(c.getValue(), "utf-8");
            }
            if ("cPwd".equals(c.getName())) {
                cPwd = URLDecoder.decode(c.getValue(), "utf-8");
            }
        }
    }

    //获取错误信息
    String errorInfo=request.getParameter("error");
    if(errorInfo!=null){
        errorInfo=URLDecoder.decode(errorInfo,"utf-8");
    }else{
        errorInfo="";
    }
%>
    <form action="verify.jsp" method="post">
        <div align="center">
            <div style="color: red"><%if(errorInfo!=null)out.print(errorInfo);%></div>
            用户名:<input type="text" name="username" value="<%=cName%>"></p>
            密码:<input type="password" name="pwd" value="<%=cPwd%>"></p>
            <input type="checkbox" name="isno" value="yes">&nbsp;十天内免登陆</p>
            <input type="submit" value="登录">
        </div>
    </form>
</body>
</html>
