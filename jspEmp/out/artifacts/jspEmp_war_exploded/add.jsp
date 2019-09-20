<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="qianfg.cn.service.NewsService" %>
<%@ page import="qianfg.cn.service.impl.NewsServiceImpl" %>
<%@ page import="qianfg.cn.polo.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
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
        //获取添加页面属性值
        String newsId=request.getParameter("newsId");
        String title=request.getParameter("title");
        String context=request.getParameter("context");
        String newsMan=request.getParameter("newsMan");
        String newsDate=request.getParameter("newsDate");
        String typeId=request.getParameter("typeId");
        String pic=request.getParameter("pic");
        //封装成一个news对象
        News news=new News();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        if(newsId!=null && !"".equals(newsId)){
            news.setNewsId(Integer.valueOf(newsId));
        }
        if(title!=null && !"".equals(title)){
            news.setTitle(title);
        }
        if(context!=null && !"".equals(context)){
            news.setContext(context);
        }
        if(newsMan!=null && !"".equals(newsMan)){
            news.setNewsMan(newsMan);
        }
        if(newsDate!=null&&!"".equals(newsDate)){
            try {
                news.setNewsDate(sdf.parse(newsDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(typeId!=null && !"".equals(typeId)){
            news.setTypeId(Integer.valueOf(typeId));
        }
        if(pic!=null && !"".equals(pic)){
            news.setPic(pic);
        }
        //添加至数据库
        NewsService ns=new NewsServiceImpl();
        //如果添加成功,返回主页面
        int flag=ns.addNews(news);
        if(flag>0){
            response.sendRedirect("main.jsp");
        }else{
            out.print("添加失败");
        }

    %>
</body>
</html>
