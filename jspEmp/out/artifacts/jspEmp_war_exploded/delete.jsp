<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="qianfg.cn.service.NewsService" %>
<%@ page import="qianfg.cn.service.impl.NewsServiceImpl" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>删除页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <%
        String[] ids=request.getParameterValues("id");
        NewsService ns=new NewsServiceImpl();
        int flag=ns.deleteNews(ids);
        if(flag>0){
            response.sendRedirect("main.jsp");
        }else {
            out.print("删除失败!");
        }
    %>
</body>
</html>
