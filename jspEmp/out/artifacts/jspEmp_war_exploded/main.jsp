<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="qianfg.cn.service.NewsService" %>
<%@ page import="qianfg.cn.service.impl.NewsServiceImpl" %>
<%@ page import="qianfg.cn.polo.News" %>
<%@ page import="qianfg.cn.polo.User" %>
<%@ page import="qianfg.cn.polo.QueryNews" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>主页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <%
//        NewsService ns=new NewsServiceImpl();
//        List<News> newsList=ns.queryAll();
        //统一设置字符为"utf-8",防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName="";
        //获取登录页面数据,返回Object类型,需要强转
        User user = (User) session.getAttribute("loginUser");
        if(user!=null) {
            userName = user.getName();
        }

        //查询页面
        String qTitle=request.getParameter("qTitle");
        String qContext=request.getParameter("qContext");
        if(qTitle==null){
            qTitle="";
        }
        if(qContext==null){
            qContext="";
        }
        System.out.println("查询标题:" + qTitle+",查询内容:"+qContext);
        NewsService ns = new NewsServiceImpl();
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle(qTitle);
        queryNews.setqContext(qContext);
        List<News> newsList = ns.queryNewsByLike(queryNews);

    %>
    <div align="center">
        <form action="main.jsp" method="get">
            <h1>欢迎<%=userName%>登录</h1>
            <a href="login.jsp">退出系统</a>
            <p></p>
            新闻标题:<input type="text" name="qTitle" value="<%=qTitle%>">
            新闻内容:<input type="text" name="qContext" value="<%=qContext%>">
            <input type="submit" value="搜索" name="search">
        </form>
        <form action="delete.jsp" method="post">
            <table border="1" bordercolor="red">
                <tr>
                    <td><input type="checkbox" id="allbox">全选</td>
                    <td>新闻编号</td>
                    <td>新闻标题</td>
                    <td>新闻内容</td>
                    <td>新闻记者</td>
                    <td>新闻日期</td>
                    <td>新闻类型</td>
                    <td>新闻图片</td>
                    <td>修改</td>
                    <td>删除</td>
                </tr>

                <%for(News news:newsList){%>
                <tr>
                    <td><input type="checkbox" name="id" value="<%=news.getId()%>"></td>
                    <td name="newsId"><%=news.getNewsId()%></td>
                    <td name="title"><%=news.getTitle()%></td>
                    <td name="context"><%=news.getContext()%></td>
                    <td name="newsMan"><%=news.getNewsMan()%></td>
                    <td name="newsDate"><%=news.getNewsDate()%></td>
                    <td name="typeId"><%=news.getTypeId()%></td>
                    <td name="pic"><%=news.getPic()%></td>
                    <td><a href="updateNews.jsp?id=<%=news.getId()%>">修改</a></td>
                    <td><a href="delete.jsp?id=<%=news.getId()%>" onclick="return confirm('确认删除?')">删除</a></td>
                </tr>
                <%}%>
            </table>
            <a href="addNews.jsp"><input type="button" value="添加新闻"></a>
            <input type="submit" value="全部删除" onclick="return confirm('确认全部删除?!!')">
        </form>
    </div>
</body>
</html>
