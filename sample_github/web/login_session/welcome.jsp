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
		String id = (String)session.getAttribute("id");
		if(id !=null&&id.equals("abcde")){
			out.println(id+"님 안녕하세요~"+"<br/>");
		}else{
			response.sendRedirect("loginForm.jsp");
		}
	%>
	
<a href="logout.jsp">로그아웃</a>
</body>
</html>