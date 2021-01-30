<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>자바 코드를 활용한 출력</h2>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	out.println("아이디 : " + id);
	out.println("비밀번호 : " + pw);
%>
<br/>
<h2>
표현식 사용한 출력
</h2>
아이디 : <%=id %><br/>
비밀번호 : <%=pw %>

<h2>
EL 사용한 출력
</h2>

아이디 : ${param.id }<br/>
비밀번호 : ${param.pw }<br/>

아이디 : ${param["id"]}<br/>
비밀번호 : ${param["pw"]}

<h2>저장소에 저장된 값 출력</h2>
application_scope : ${applicationScope.application_name }<br/>
session_scope : ${sessionScope.session_name }<br/>
requestScope : ${requestScope.request_name }<br/>
pageScope : ${pageScope.page_name }



</body>
</html>