<%@ page import ="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String id= request.getParameter("id");
	String pw= request.getParameter("pw");
	String[] hobby= request.getParameterValues("hobby");
	String major = request.getParameter("major");

%>

이름 : <%=name %><br/>
아이디 : <%=id %><br/>
비밀번호 : <%=pw %><br/>
취미 : <%=Arrays.toString(hobby) %><br/>
전공 : <%=major %><br/>
</body>
</html>