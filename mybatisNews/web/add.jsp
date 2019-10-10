<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="cn.qianfg.service.NewsService" %>
<%@ page import="cn.qianfg.service.impl.FileUploadNewsImpl" %>
<%@ page import="cn.qianfg.service.FileUploadNews" %>
<%@ page import="cn.qianfg.service.impl.NewsServiceImpl" %>
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
<%--添加新闻的业务逻辑--%>
<%
    //设置字符为"utf-8"
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    NewsService es = new NewsServiceImpl();
    FileUploadNews fu = new FileUploadNewsImpl();
    if (ServletFileUpload.isMultipartContent(request)) {//判断是否为多部件表单
        //创建缓存对象
        DiskFileItemFactory dif = new DiskFileItemFactory();
        //上传核心工具类
        ServletFileUpload sfu = new ServletFileUpload(dif);
        //解析request请求
        List<FileItem> fs = sfu.parseRequest(request);
        int flag = es.addNews(fu.upload(fs));

        response.sendRedirect("main.jsp");
    } else {
        response.sendRedirect("addNews.jsp");
    }
%>
</body>
</html>
