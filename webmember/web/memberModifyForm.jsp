<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 프로그램</title>
</head>
<body>
<!-- 수정 입력할 수 있는 폼 -->

<form action="MemberModifyServlet" method="post">
	이름 : <input type="text" name="name" size="10" value="${member.name }"/><br/>
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
		
		주소 : <input type="text" name="address" size="20" value="${member.address }">
		<br/>
		그룹 : <select name="groupNum">
		<option value="1">가족</option>
		<option value="2">친구</option>
		<option value="3">기타</option>
		</select>
		<br/>
		<input type="submit" value="수정하기"/>
</form>
</body>
</html>