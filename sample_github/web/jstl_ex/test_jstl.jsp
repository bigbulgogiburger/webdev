<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
변수선언<br>
<%-- <c:set var="변수이름" value="값에 대입할 값"/> --%>
<c:set var="name" value="hong"/>
변수 값 출력
<%-- <c:out value="출력할 값"/> --%>

${name }
<br>
jstl : if 조건식<br>

<c:if test="${1+2==3 }">
	1 + 2 = 3
</c:if>
<c:if test="${1+2!=3 }">
	1 + 2 != 3
</c:if>

<br>
1부터 10까지 출력
<br>
<%
	for(int i =1;i<=10;i++){
		%>
		${i }&nbsp;&nbsp;
		<%
	}
%>
<br>

JSTL을 이용한 반식
<c:forEach var ="i" begin="1" end="10" step="1">
	${i } &nbsp;&nbsp;
</c:forEach>
<br>
</body>
</html>