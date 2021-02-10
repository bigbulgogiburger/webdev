<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 프로그램</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="stylesheet" href="main.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Hammersmith+One&display=swap"
	rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
</head>
<body>
	<h1>
		<span>P</span><span>H</span><span>O</span><span>N</span><span>E</span><span>B</span><span>O</span><span>O</span><span>K</span>
	</h1>
	<h3>${name }님의 회원목록</h3>
	<div class="membermenu">
	<a href="LogoutServlet">로그아웃</a>
	<a href="ModifyServlet">정보수정</a>
	<a href="MemberInsertServlet">연락처추가</a>
	<br />
	<br />
	</div>
	
	<form action="SelectByMemberNameServlet">
		<select name="category">
			<option value="name">이름</option>
			<option value="phonenumber">전화번호</option>
			<option value="address">주소</option>
		</select> <input type="text" name="value" size="10" /> <input type="submit"
			value="찾기">
	</form>
	<br /> ${resultMsg }
	<table class="table table-striped table-hover search-table">
		<tr>
			<th>이름</th>
			<th>연락처</th>
			<th>주소</th>
			<th>그룹</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${members }" var="member">
			<tr>
				<td>${member.name }</td>
				<td>${member.phone1 }- ${member.phone2 } - ${member.phone3 }</td>
				<td>${member.address }</td>
				<td>${member.groupName }</td>
				<td><a
					href="MemberModifyServlet?memberNum=${member.memberNum }">수정</a></td>
				<td><a href="DeleteServlet?memberNum=${member.memberNum }">삭제</a></td>
			</tr>
		</c:forEach>

	</table>

	<form action="MainServlet">
		<input type="submit" value="전체목록으로">
	</form>
</body>
</html>