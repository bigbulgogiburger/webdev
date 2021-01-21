<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>사원등록</h1>
	<form action="EmpServlet" method="get">
	<table border="1">
		<tr>
		<td>
		사원번호 :
		</td> 
		
			<td>
			<input type="text" name="empno" size="10">
			</td>
		</tr>
		
		<tr>
			<td>
			사원명 : 
			</td> 
		
			<td>
			<input type="text" name="empnm" size="10"> 
			</td>
		</tr>
		
		<tr>
			<td>
			부서번호 : 
			</td>
			
			<td>
				<select name="deptno">
					<option value="001">기획부</option>
					<option value="002">영업부</option>
					<option value="003">인사부</option>
					<option value="004">총무부</option>
				</select> 
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" name="sex" value="남">남
				<input type="radio" name="sex" value="여">여
			</td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
			<!-- check박스는 배열로 받는다. -->
				<input type="checkbox" name="hobby" value="01">코딩
				<input type="checkbox" name="hobby" value="02">탱고
				<input type="checkbox" name="hobby" value="03">유튜브보기
			</td>
		</tr>
		
		<td colspan="2">
			<input type="submit" value="사원등록">
		</td>
	</table>
	</form>

</body>
</html>