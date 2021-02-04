<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>

<form action="LoginServlet" method="post">

	아이디 :  <input type="text" name="id" size="10"/>
	비밀번호 : <input type="text" name="pw" size="10"/>
	    	<input type="submit" value="로그인"/>
</form>
<a href="JoinServlet">회원가입</a>
</body>
</html>