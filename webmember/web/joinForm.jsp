<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="JoinServlet" method="post">
		이름 : <input type="text" name="name" size="10" value="${join.name }"/>${nameMsg }<br/>
		아이디 : <input type="text" name="id" size="10" value="${join.id }"/>${idMsg }<br/>
		비밀번호 : <input type="password" name="pw" size="10"/>${pwMsg }<br/>
		연락처 : <select name="phone1">
		<option value="010">010</option>
		<option value="011">011</option>
		<option value="017">017</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${member.phone2 }"/>
		-
		<input type="text" name="phone3" size="4" value="${member.phone3 }"/>${phoneMsg }
		<br/>
		주소 : <input type="text" name="address" size="20" value="${member.address }"/>
		<br/>
		<input type="submit" value="추가" /> 
	</form>

</body>
</html>