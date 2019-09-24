<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.service.NewsService" %>
<%@ page import="cn.qianfg.service.impl.NewsServiceImpl" %>
<%@ page import="cn.qianfg.polo.News" %>
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
    <%
        //设置字符为"utf-8"
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取添加页面属性值
        String strId=request.getParameter("id");
        if(strId==null){
            System.out.println("strId没找到");
        }
        //根据id查询news对象
        NewsService ns=new NewsServiceImpl();
        //id本身是从数据库获取,所以肯定能查到对象
        int id=Integer.valueOf(strId);
        News news=ns.queryNewsById(id);
        //获取添加页面属性值
        int newsId=news.getNewsId();
        String title=news.getTitle();
        String context=news.getContext();
        String newsMan=news.getNewsMan();
        Date newsDate=news.getNewsDate();
        int typeId=news.getTypeId();
        String pic=news.getPic();
    %>
    <form action="update.jsp?id=<%=id%>" method="post" enctype="multipart/form-data">
        序列号:<input type="text" name="id" value="<%=id%>" readonly="readonly"><p>
        新闻编号:<input type="text" name="newsId" value="<%=newsId%>"><p>
        新闻标题:<input type="text" name="title" value="<%=title%>"><p>
        新闻内容:<input type="text" name="context" value="<%=context%>"><p>
        新闻记者:<input type="text" name="newsMan" value="<%=newsMan%>"><p>
        新闻日期:<input type="date" name="newsDate" value="<%=newsDate%>"><p>
        新闻类型:<select name="typeId">
        <option value="1">社会</option>
        <option value="2">娱乐</option>
        <option value="3">生活</option>
        <option value="4">体育</option>
    </select><p>
        新闻图片:<input type="file" name="pic" value="<%=pic%>"><p>
        <input type="submit" value="修改" onclick="return confirm('确认修改?!!')">
    </form>
</body>
</html>
