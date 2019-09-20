<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.zl.service.EmpService" %>
<%@ page import="com.zl.service.impl.EmpServiceImpl" %>
<%@ page import="com.zl.pojo.Emp" %>
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
        String empNo=request.getParameter("empNo");
        EmpService es=new EmpServiceImpl();
        Emp emp=new Emp();
        if(empNo!=null &&!"".equals(empNo)){
            emp=es.queryEmpById(new Integer(empNo));
        }
    %>
    <form action="update.jsp" method="post">
        员工编号:<input type="text" name="empNo" readonly="readonly" value="<%=emp.getEmpNo()%>"><p>
        员工姓名:<input type="text" name="eName" value="<%=emp.geteName()%>"><p>
        员工岗位:<select name="job">
                    <option value="销售" <%if("销售".equals(emp.getJob())){%>selected="selected"<%}%>>销售</option>
                    <option value="经理" <%if("经理".equals(emp.getJob())){%>selected="selected"<%}%>>经理</option>
                    <option value="董事长" <%if("董事长".equals(emp.getJob())){%>selected="selected"<%}%>>董事长</option>
                    <option value="职员" <%if("职员".equals(emp.getJob())){%>selected="selected"<%}%>>职员</option>
                    <option value="研发" <%if("研发".equals(emp.getJob())){%>selected="selected"<%}%>>研发</option>
                </select><p>
        上级领导:<input type="text" name="mgr" value="<%if(emp.getMgr()!=null){%><%=emp.getMgr()%><%}%>"><p>
        入职日期:<input type="date" name="hireDate" value="<%=emp.getHireDate()%>"><p>
        月薪:<input type="text" name="sal" value="<%=emp.getSal()%>"><p>
        奖金:<input type="text" name="comm" value="<%=emp.getComm()%>"><p>
        部门:<select name="deptNo">
                <option value="10" <%if(emp.getDeptNo()==10){%>selected="selected"<%}%>>会计部</option>
                <option value="20" <%if(emp.getDeptNo()==20){%>selected="selected"<%}%>>科研部</option>
                <option value="30" <%if(emp.getDeptNo()==30){%>selected="selected"<%}%>>销售部</option>
                <option value="40" <%if(emp.getDeptNo()==40){%>selected="selected"<%}%>>管理部</option>
            </select><p>
        <input type="submit" value="修改">
    </form>
</body>
</html>