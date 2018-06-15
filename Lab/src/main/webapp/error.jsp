<%@ page import="utility.Constants" %><%--
  Created by IntelliJ IDEA.
  User: czf
  Date: 2018/5/29
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String msg = (String) session.getAttribute(Constants.SESSION_ERROR);
%>
<%=msg%>
</body>
</html>
