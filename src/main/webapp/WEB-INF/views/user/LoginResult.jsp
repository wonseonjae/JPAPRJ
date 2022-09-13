<%@ page import="kopo.poly.util.CmmUtil" %><%--
  Created by IntelliJ IDEA.
  User: data12
  Date: 2022-09-13
  Time: 오후 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String SS_USER_ID = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
  String res = CmmUtil.nvl((String) request.getAttribute("res"));

  String msg = "";

  if (res.equals("1")){
      msg = SS_USER_ID + "님 환영합니다.";
  } else if (res.equals("0")){
      msg = "아이디, 비밀번호가 일치하지 않습니다.";
  } else {
      msg = "시스템 오류로 실패하였습니다.";
  }
%>
<html>
<head>
    <title><%=msg%>></title>
</head>
<body>
    <%=msg%>
</body>
</html>
