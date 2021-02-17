<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>회원가입</title>
<script>
function idDupl(idMsg){
	if(idMsg !=null)
	alert(idMsg);
}
	
</script>
</head>
<body>
	<form action="JoinServlet" method="post">
	<div class="col-md-4">
		이름 : <input type="text" class="form-control" width="100px" placeholder="이름을 입력해주세요" name="name" size="10" value="${join.name }"/>${nameMsg }<br/>
		아이디 : <input type="text" class="form-control" name="id" placeholder="아이디를 입력해주세요" size="10" value="${join.id }"/><a href="IdCheckServlet" type="button">중복체크</a>${idMsg } <br/>
		비밀번호 : <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" name="pw" size="10"/>${pwMsg }<br/>
	</div>
		연락처 :<br> 
		<select name="phone1">
		
		<option value="010">010</option>
		<option value="011">011</option>
		<option value="017">017</option>
		</select>
		-
		<input type="text" name="phone2" size="4"  value="${member.phone2 }"/>
		-
		<input type="text" name="phone3" size="4" value="${member.phone3 }"/>${phoneMsg }
		<br/>
		주소 : <br>
		<input type="text" class="col-md-4" name="address" size="20" placeholder="주소를 입력해주세요" value="${member.address }"/>
		<br/>
		<input type="submit" value="추가" /> 
	</form>

</body>
</html>