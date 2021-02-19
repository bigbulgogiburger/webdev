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
<link rel="stylesheet" href="joinForm.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Hammersmith+One&display=swap"
	rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<title>회원가입</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
<script
     src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js";>
     </script>


</head>
<body>
<script type="text/javascript">
     function idCheck(){
          //jQuery에서 선택자역할
          var idStr = $("#id").val();
          
          $.ajax({
        	  async : false,
              url : "IdCheckServlet?id="+idStr,
              success : function(data){
            	  
                   if(data == "success"){
                        $("#result").text("사용가능한 아이디입니다.");
                   }else if(data == "fail"){
                        $("#result").text("중복된 아이디입니다.");
                   }
              }
          });
     }
</script>



	<form action="JoinServlet" method="post">
	<div class="mb-3 search-id mx-auto ">
		<div class="name">
		이름 : <input type="text" class="form-control" width="100px" placeholder="이름을 입력해주세요" name="name" size="10" value="${join.name }"/>${nameMsg }<br/>
		
		아이디 : <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력해주세요" size="10" value="${join.id }"/>
		<button type="button" name="id" placeholder="아이디를 입력해주세요" size="10" onclick="idCheck()">중복확인</button>
		<br>${idMsg }
		<span id="result"></span><br>
		비밀번호 : <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" name="pw" size="10"/>${pwMsg }<br/>
		
		연락처 :<br>
		<select  name="phone1">
		
		<option class="cod-md-12" value="010">010</option>
		<option class="cod-md-12" value="011">011</option>
		<option class="cod-md-12" value="017">017</option>
		</select>
		-
		<input type="text" name="phone2" size="4"  value="${member.phone2 }"/>
		-
		<input type="text" name="phone3" size="4" value="${member.phone3 }"/>${phoneMsg }
		<br/>
		<br>
		<br>
		<input type="text" id="sample6_postcode" name="postcode" placeholder="우편번호" value="${member.postcode }">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<br><input type="text" id="sample6_address" name="address" placeholder="주소" value="${member.address }"><br>
		<br><input type="text" id="sample6_detailAddress" name="detail_address" placeholder="상세주소" value="${member.detail_address }">
		<input type="text" id="sample6_extraAddress" placeholder="참고항목">
		</div>
		
		<br>
		<br>
		<br>
		<input type="submit" value="회원가입" /> 
		</div>
	</form>

</body>
</html>