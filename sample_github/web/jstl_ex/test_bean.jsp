<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean 
	id="student" 
	class="com.kbigdata.edu.Student"
	scope="page"/>
	
	<jsp:setProperty 
	property="name" 
	name="student"
	value="홍길동"/>
	
	<jsp:setProperty 
	property="age" 
	name="student"
	value="13"/>
	
	<jsp:setProperty 
	property="grade" 
	name="student"
	value="6"/>
	
	<jsp:setProperty 
	property="studentNum" 
	name="student"
	value="7"/>
	getProperty 잘 안씀.
	이름 : <jsp:getProperty property="name" name="student"/><br>
	나이 : <jsp:getProperty property="age" name="student"/><br>
	학년 : <jsp:getProperty property="grade" name="student"/><br>
	번호 : <jsp:getProperty property="studentNum" name="student"/><br>
	
<%-- 	<%
	com.kbigdata.edu.Student student = new com.kbigdata.edu.Student();
	out.println("이름 : "+student.getName());
	out.println("나이 : "+student.getAge());
	out.println("학년 : "+student.getGrade());
	out.println("번호 : "+student.getStudentNum());
	
	%> --%>
	
	이름 : ${student.name }<br>
	나이 : ${student.age }<br>
	학년 : ${student.grade }<br>
	번호 : ${student.studentNum }<br>
	
</body>
</html>