
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>ararsr</h2>
<c:forEach var="member" items="${memList}">
	이름 : <c:out value="${member.getName()}"></c:out><br/>
	전화번호 : <c:out value="${member.getPhone1()}"></c:out> - 
		    <c:out value="${member.getPhone2()}"></c:out> - 
		    <c:out value="${member.getPhone3()}"></c:out> <br/>
	주소 :   <c:out value="${member.getAddress()}"></c:out> <br/>
	그룹 :   <c:out value="${member.getGroupName()}"></c:out>	<br/>    
</c:forEach>

</body>
</html>