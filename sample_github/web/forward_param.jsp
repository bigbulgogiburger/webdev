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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		out.println("아이디 : "+id );
		out.println("비밀번호 : "+pw+"<br>");
	%>
	
	<h1>forward_param.jsp 페이지입니다.</h1>
	아이디 : <%=id %> 
	비밀번호 : <%=pw %><br>
	아이디 : ${param.id }
	비밀번호 : ${param.pw }
</body>
</html>