<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% System.out.println(request.getAttribute("name")); %>
${name }님 잘했어요~
<a href="insertForm.jsp">전화번호부 회원추가</a>
</body>
</html>