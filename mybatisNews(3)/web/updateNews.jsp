<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.service.NewsService" %>
<%@ page import="cn.qianfg.service.impl.NewsServiceImpl" %>
<%@ page import="cn.qianfg.pojo.News" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="updateNewsServlet" method="post" enctype="multipart/form-data">
        序列号:<input type="text" name="id" value="${news.id}" readonly="readonly"><p>
        新闻编号:<input type="text" name="newsId" value="${news.newsId}"><p>
        新闻标题:<input type="text" name="title" value="${news.title}"><p>
        新闻内容:<input type="text" name="context" value="${news.context}"><p>
        新闻记者:<input type="text" name="newsMan" value="${news.newsMan}"><p>
        新闻日期:<input type="date" name="newsDate" value="<fmt:formatDate value='${news.newsDate}' pattern='yyyy-MM-dd'/>"><p>
        新闻类型:<input type="text" name="typeId" value="${news.typeId}"><p>
        新闻图片:<img src="${news.pic}"><input type="file" name="pic" value="${news.pic}"><p>
        <input type="submit" value="修改" onclick="return confirm('确认修改?!!')">
    </form>
</body>
</html>
