<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1>欢迎${loginUser.name}登陆</h1>
        <a href="emp/outServlet">退出</a>
        <p></p>
        ${fy.query.qEndHiredate}-------------------<p>
        <form action="emp/empMainServlet" method="get">
            姓名:<input type="text" name="qeName" value="${fy.query.qeName}">
            入职开始日期:<input type="date" name="qStartHiredate" value="<fmt:formatDate value='${fy.query.qStartHiredate}' pattern='yyyy-MM-dd'/>">
            入职结束日期:<input type="date" name="qEndHiredate" value="<fmt:formatDate value='${fy.query.qEndHiredate}' pattern='yyyy-MM-dd'/>">
            <input type="submit" value="搜索">
        </form>
        <form action="emp/deleteServlet" method="get" onsubmit="return confirm('是否全部删除!!!')">
            <table bordercolor="red" border="1">
                <tr>
                    <td><input type="checkbox" id="allbox">全选</td>
                    <td>员工编号</td>
                    <td>员工姓名</td>
                    <td>员工岗位</td>
                    <td>上级领导</td>
                    <td>入职日期</td>
                    <td>月薪</td>
                    <td>奖金</td>
                    <td>部门编号</td>
                    <td>头像</td>
                    <td>删除</td>
                    <td>修改</td>
                   <c:forEach items="${emps}" var="e">
                        <tr>
                            <td><input type="checkbox" name="empNo" value="${e.empNo}"></td>
                            <td>${e.empNo}</td>
                            <td>${e.eName}</td>
                            <td>${e.job}</td>
                            <td>${e.mgr}</td>
                            <td>${e.hireDate}</td>
                            <td>${e.sal}</td>
                            <td>${e.comm}</td>
                            <td>${e.deptNo}</td>
                            <td><img src="${e.pic}"/></td>
                            <td><a href="emp/deleteServlet?empNo=${e.empNo}" onclick="return confirm('是否确定删除!!!')">删除</a></td>
                            <td><a href="emp/toUpdateServlet?empNo=${e.empNo}">修改</a></td>
                        </tr>
                    </c:forEach>
                </tr>
            </table>
            <p></p>
            <input type="submit" value="全部删除">
            <a href="emp/toAddServlet"><input type="button" value="添加员工"></a>
        </form>

        <c:url var="url" value="emp/empMainServlet">
            <c:param name="qeName" value="${fy.query.qeName}"></c:param>
            <c:param name="qStartHiredate"><c:if test="${fy.query.qStartHiredate!=''}"><fmt:formatDate value='${fy.query.qStartHiredate}' pattern='yyyy-MM-dd'/></c:if></c:param>
            <c:param name="qEndHiredate"><c:if test="${fy.query.qEndHiredate!=''}"><fmt:formatDate value='${fy.query.qEndHiredate}' pattern='yyyy-MM-dd'/></c:if></c:param>
        </c:url>
        <a href="${url}&pg=1">首页</a>
        <c:choose>
            <c:when test="${fy.page==1}">
                上一页
            </c:when>
            <c:otherwise>
                <a href="${url}&pg=${fy.page-1}">上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${fy.pageCount}" var="i">
            <a href="${url}&pg=${i}">${i}</a>&nbsp;
        </c:forEach>

        <c:choose>
            <c:when test="${fy.page==fy.pageCount}">
                下一页
            </c:when>
            <c:otherwise>
                <a href="${url}&pg=${fy.page+1}">下一页</a>
            </c:otherwise>
        </c:choose>
        <a href="${url}&pg=${fy.pageCount}">尾页</a>
        &nbsp;当前第${fy.page}页&nbsp;共${fy.pageCount}页
    </div>

    <%
        request.setAttribute("test","req数据");
        session.setAttribute("test","session数据");
        application.setAttribute("test","app数据");
    %>

------------------------------------------EL以及JSTL测试-------------------------------------------------------------------<p>
    取request中数据:${requestScope.emps}<p>
    取session中数据:${sessionScope.loginUser}<p>
    取app中数据:${applicationScope.app}<p>
    简化写法:${test}<p><%--如果简化写法默认从最小的作用域开始搜索,如果找到该key输出内容,停止搜索--%>
    获取登录用户用户名:${loginUser.name}<p>
    获取集合中的元素:${emps[1].eName}<p>
    获取Cookie中的数据:${URLDecoder.decode(cookie.cName.value,"utf-8")}<p>
    运算:${fy.page==1}-----${fy.page==fy.pageCount}-----${fy.page+1}<p>
    三目运算符:${fy.page==1?'是第一页':'不是第一页...'}<p>

    <c:set var="but" value="<button>这是一个按钮</button>" scope="request"></c:set>
    ${but}
    <c:out value="${but}" escapeXml="false"></c:out><p>
    <c:remove var="but" scope="request"></c:remove>


    <c:if test="${fy.page==5}">
       相当于:if(page==5){健康撒谎打卡机还是咖啡店}<p>
    </c:if>
    <c:set var="score" value="85"></c:set>
    <c:choose>
        <c:when test="${score>=90}">
            优秀<p>
        </c:when>
        <c:when test="${score>=80}">
            良好<p>
        </c:when>
        <c:otherwise>
            不及格<p>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="1" end="10" var="i">
        ${i}&nbsp;
    </c:forEach>

    <c:forEach items="${emps}" var="e">
                ${e.eName}---><fmt:formatDate value="${e.hireDate}" pattern="yyyy年MM月dd日"></fmt:formatDate>&nbsp;
    </c:forEach>
</body>
</html>