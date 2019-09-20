<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="qianfg.cn.service.NewsService" %>
<%@ page import="qianfg.cn.service.impl.NewsServiceImpl" %>
<%@ page import="qianfg.cn.polo.News" %>
<%@ page import="qianfg.cn.polo.QueryNews" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>搜索页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String qTitle=request.getParameter("qTitle");
        String qContext=request.getParameter("qContext");
        System.out.println("查询标题:" + qTitle+",查询内容:"+qContext);
        NewsService ns = new NewsServiceImpl();
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle(qTitle);
        queryNews.setqContext(qContext);
        List<News> newsList = ns.queryNewsByLike(queryNews);
    %>


    <div align="center">
        <h1 style="color: red;align-content: center">查询结果</h1>
        <table border="1" bordercolor="red">
            <tr>
                <td>新闻编号</td>
                <td>新闻标题</td>
                <td>新闻内容</td>
                <td>新闻记者</td>
                <td>新闻日期</td>
                <td>新闻类型</td>
                <td>新闻图片</td>
            </tr>

            <%for(News news:newsList){%>
            <tr>
                <td id=""><%=news.getNewsId()%></td>
                <td><%=news.getTitle()%></td>
                <td><%=news.getContext()%></td>
                <td><%=news.getNewsMan()%></td>
                <td><%=news.getNewsDate()%></td>
                <td><%=news.getTypeId()%></td>
                <td><%=news.getPic()%></td>
            </tr>
            <%}%>
        </table>
    </div>

</body>
</html>
