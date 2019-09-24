<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.polo.User" %>
<%@ page import="cn.qianfg.service.NewsService" %>
<%@ page import="cn.qianfg.polo.QueryNews" %>
<%@ page import="cn.qianfg.polo.FenYe" %>
<%@ page import="cn.qianfg.polo.News" %>
<%@ page import="cn.qianfg.service.impl.NewsServiceImpl" %>
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
            System.out.println("qTitle为空");
            qTitle="";
        }
        if(qContext==null){
            System.out.println("qContext为空");
            qContext="";
        }
        System.out.println("查询标题:" + qTitle+",查询内容:"+qContext);
        NewsService ns = new NewsServiceImpl();
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle(qTitle);
        queryNews.setqContext(qContext);
//        List<News> newsList = ns.queryNewsByLike(queryNews);
        /**
         * 处理分页对象
         */
        FenYe fy=new FenYe();
        System.out.println("方法调用前:"+fy);
        String pg=request.getParameter("pg");
        List<News> newsList=ns.queryNewsByFy(fy,pg,queryNews);
        System.out.println("方法调用后:"+fy);
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
                    <td name="pic"><img src="<%=news.getPic()%>"></td>
                    <td><a href="updateNews.jsp?id=<%=news.getId()%>">修改</a></td>
                    <td><a href="delete.jsp?id=<%=news.getId()%>" onclick="return confirm('确认删除?')">删除</a></td>
                </tr>
                <%}%>
            </table>
            <a href="addNews.jsp"><input type="button" value="添加新闻"></a>
            <input type="submit" value="全部删除" onclick="return confirm('确认全部删除?!!')">
        </form>
        <a href="main.jsp?pg=1&qTitle=<%=qTitle%>&qContext=<%=qContext%>" >首页</a>
        <%if(fy.getPage()==1){%>上一页<%}else{%>
        <a href="main.jsp?pg=<%=fy.getPage()-1%>&qTitle=<%=qTitle%>&qContext=<%=qContext%>">上一页</a><%}%>
        <%if(fy.getPage()>=fy.getPageCount()){%>下一页<%}else{%>
        <a href="main.jsp?pg=<%=fy.getPage()+1%>&qTitle=<%=qTitle%>&qContext=<%=qContext%>">下一页</a><%}%>
        <a href="main.jsp?pg=<%=fy.getPageCount()%>&qTitle=<%=qTitle%>&qContext=<%=qContext%>">尾页</a>
        &nbsp;当前第<%=fy.getPage()%>页&nbsp;共<%=fy.getPageCount()%>页
    </div>
</body>
</html>
