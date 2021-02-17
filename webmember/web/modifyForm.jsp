<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
</head>
<body>
<!-- 수정 입력할 수 있는 폼 -->

<form action="ModifyServlet" method="post">
	이름 : <input type="text" name="name" size="10" placeholder="이름을 입력해주세요" value="${member.name }"/><br/>
		아이디 : ${id }<br/>
		비밀번호 : <input type="password" name="pw" placeholder="비밀번호를 재입력해주세요" size="10"/><br/>
		전화번호 : 
		<select name="phone1">
		<option value="010">010</option>
		<option value="011">011</option>
		<option value="016">016</option>
		<option value="017">017</option>
		</select>
		-
		<input type="text" name="phone2" size="4" value="${member.phone2 }">
		-
		<input type="text" name="phone3" size="4" value="${member.phone3 }">
		<br/>
		
		주소 : <input type="text" name="address" size="20" placeholder="주소를 입력해주세요" value="${member.address }">
		<br/>

		<br/>
		<input type="submit" value="수정하기"/>
</form>
</body>
</html>