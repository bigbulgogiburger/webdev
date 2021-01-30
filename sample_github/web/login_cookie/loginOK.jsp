<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	/* id/pw 확인*/
	if(id.equals("abcde")&&pw.equals("12345")){
		/*정상 쿠키 생성*/
		Cookie cookie = new Cookie("id",id);
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		response.sendRedirect("welcome.jsp");
		
	}else{
		response.sendRedirect("loginForm.jsp?msg=error");
	}

%>