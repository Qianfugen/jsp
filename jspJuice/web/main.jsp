<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.polo.User" %>
<%@ page import="cn.qianfg.polo.Query" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="cn.qianfg.polo.FenYe" %>
<%@ page import="cn.qianfg.service.JuiceService" %>
<%@ page import="cn.qianfg.service.impl.JuiceServiceImpl" %>
<%@ page import="cn.qianfg.polo.Juice" %>
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
<div align="center">
    <h1>欢迎${loginUser.name}登录</h1>
    <h1 style="color: red">果汁价目表</h1>
    <!--搜索栏-->
    <form action="juiceMainServlet" method="get">
        <!--name尽量跟juice的属性值一致,以防混淆-->
        品名:<input type="text" name="qName" value="${fy.query.qName}">
        <!--fmt转格式,回显-->
        开始日期:<input type="date" name="qStartDate" value="<fmt:formatDate value="${fy.query.qStartDate}" pattern="yyyy-MM-dd"/>">
        结束日期:<input type="date" name="qEndDate" value="<fmt:formatDate value="${fy.query.qEndDate}" pattern="yyyy-MM-dd"/>">
        <input type="submit" value="搜索">
    </form>
    <!--展示栏-->
    <form action="deleteJuiceServlet" method="post">
        <table border="1" bordercolor="red">
            <tr>
                <td><input type="checkbox" name="idbox">全选</td>
                <td>序号</td>
                <td>名称</td>
                <td>价格</td>
                <td>数量</td>
                <td>购入时间</td>
                <td>图片</td>
                <td>修改</td>
                <td>删除</td>
            </tr>
            <!--添加内容-->
            <c:forEach items="${juiceList}" var="ju">
                <tr>
                    <td><input type="checkbox" name="id" value="${ju.id}"></td>
                    <td name="code">${ju.code}
                    </td>
                    <td name="name">${ju.name}
                    </td>
                    <td name="price">${ju.price}
                    </td>
                    <td name="amount">${ju.amount}
                    </td>
                    <td name="buyTime">${ju.buyTime}
                    </td>
                    <td name="pic"><img src="${ju.pic}"/></td>
                    <td><a href="toUpdateJuiceServlet?id=${ju.id}">修改</a></td>
                    <td><a href="deleteJuiceServlet?id=${ju.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="toAddJuiceServlet"><input type="button" value="添加果汁"></a>
        <input type="submit" value="删除果汁" onclick="return confirm('确认删除?')">
    </form>

    <!--pg不用放进url,因为要变-->
    <c:url var="url" value="juiceMainServlet">
        <c:param name="qName" value="${fy.query.qName}"></c:param>
        <c:param name="qStartDate"><c:if test="${fy.query.qStartDate!=''}"><fmt:formatDate value='${fy.query.qStartDate}' pattern='yyyy-MM-dd'/></c:if></c:param>
        <c:param name="qEndDate"><c:if test="${fy.query.qEndDate!=''}"><fmt:formatDate value='${fy.query.qEndDate}' pattern='yyyy-MM-dd'/></c:if></c:param>
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
