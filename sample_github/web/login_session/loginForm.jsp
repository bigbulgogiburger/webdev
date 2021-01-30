<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 프로그램</title>
</head>
<body>
	<form action="loginOK.jsp" method="post">
		아이디:<input type="text" name="id" size="10"><br/>
		비밀번호:<input type="password" name="pw" size="10"><br/>
		
		<input type="submit" value="로그인"><br/>
		${param.msg }
	</form>

</body>
</html>