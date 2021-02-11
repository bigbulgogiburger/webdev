<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="stylesheet" href="loginForm.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Hammersmith+One&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="bombook">
	<h1>
		<span>B</span><span>O</span><span>M</span><span>B</span><span>O</span><span>O</span><span>K</span>
	</h1>
	<form action="LoginServlet" method="post">
		<div class="mb-3 search-id mx-auto">
			<input type="text" class="form-control" id="exampleInputEmail1"
				name="id" placeholder="아이디를 입력하세요">
				${idMsg }
		</div>
		<div class="mb-3 search-pw mx-auto">
			<input type="password" class="form-control" name="pw"
				placeholder="비밀번호를 입력하세요">
				${pwMsg }
		</div>

		<div class="submit">
			<button type="submit" class="btn btn-secondary">Submit</button>
		</div>

	</form>


	<form action="JoinServlet">
		<div class="joinus">
			<button type="submit" class="btn btn-secondary">JoinUs</button>
		</div>
	</form>
</div>

</body>
</html>