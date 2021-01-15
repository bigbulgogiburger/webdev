<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- form은 편지봉투 action은 받을사람 주소(요청 URL-class) 나머지는 편지내용  -->
<form action="login" method="get">
아이디 : <input type="text" name="id"><br> 
비밀번호 :<input type="password" name="pw"><br>
<input type="submit" value="로그인">
</form>
</body>
</html>