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
	
	if(id.equals("abcde")&&pw.equals("12345")){
		session.setAttribute("id", id);
		response.sendRedirect("welcome.jsp");
		
	}else{
		response.sendRedirect("loginForm.jsp");
	}
	%>

</body>
</html>