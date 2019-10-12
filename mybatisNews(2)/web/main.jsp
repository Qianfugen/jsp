<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.pojo.User" %>
<%@ page import="cn.qianfg.service.NewsService" %>
<%@ page import="cn.qianfg.pojo.QueryNews" %>
<%@ page import="cn.qianfg.pojo.FenYe" %>
<%@ page import="cn.qianfg.pojo.News" %>
<%@ page import="cn.qianfg.service.impl.NewsServiceImpl" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div align="center">
        <form action="newsMainServlet" method="get">
            <h1>欢迎${loginUser.name}登录</h1>
            <a href="login.jsp">退出系统</a>
            <p></p>
            新闻标题:<input type="text" name="qTitle" value="${queryNews.qTitle}">
            新闻内容:<input type="text" name="qContext" value="${queryNews.qContext}">
            <input type="submit" value="搜索" name="search">
        </form>
        <form action="deleteNewsServlet" method="post">
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
                <c:forEach items="${newsList}" var="news">
                <tr>
                    <td><input type="checkbox" name="id" value="${news.id}"></td>
                    <td name="newsId">${news.newsId}</td>
                    <td name="title">${news.title}</td>
                    <td name="context">${news.context}</td>
                    <td name="newsMan">${news.newsMan}</td>
                    <td name="newsDate"><fmt:formatDate value='${news.newsDate}' pattern='yyyy-MM-dd'/></td>
                    <td name="typeId">${news.typeId}</td>
                    <td name="pic"><img src="${news.pic}"></td>
                    <td><a href="toUpdateNewsServlet?id=${news.id}">修改</a></td>
                    <td><a href="deleteNewsServlet?id=${news.id}" onclick="return confirm('确认删除?')">删除</a></td>
                </tr>
                </c:forEach>
            </table>
            <a href="toAddNewsServlet"><input type="button" value="添加新闻"></a>
            <input type="submit" value="全部删除" onclick="return confirm('确认全部删除?!!')">
        </form>

        <!--pg不用放进url,因为要变-->
        <c:url var="url" value="newsMainServlet">
            <c:param name="qTitle" value="${query.qTitle}"></c:param>
            <c:param name="qContext" value="${query.qContext}"></c:param>
        </c:url>

        <a href="${url}&pg=1">首页</a>
        <c:choose>
            <c:when test="${fy.page<=1}">上一页</c:when>
            <c:otherwise><a href="${url}&pg=${fy.page-1}">上一页</a></c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${fy.pageCount}" var="i">
            <a href="${url}&pg=${i}">${i}</a>
        </c:forEach>
        <c:choose>
            <c:when test="${fy.page>=fy.pageCount}">下一页</c:when>
            <c:otherwise><a href="${url}&pg=${fy.page+1}">下一页</a></c:otherwise>
        </c:choose>
        <a href="${url}&pg=${fy.pageCount}">尾页</a>
        <span>共${fy.pageCount}页</span>&nbsp;<span>当前${fy.page}页</span>
    </div>
</body>
</html>
