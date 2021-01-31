<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>안녕하세요.</h1>
<form action="restore" method="post">
이름 : <input type="text" name="name" size="15"><br>
핸드폰번호 : <input type="text" name="phoneNumber" size="15"><br>
주소 : <input type="text" name="address" size="15"><br>
그룹 : <br>가족 <input type="radio" name="group" value="가족"><br>
친구 <input type="radio" name="group" value="친구"><br>
기타 <input type="radio" name="group" value="기타"><br>
<input type="submit" value="추가">
</form>

</body>
</html>