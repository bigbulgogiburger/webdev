<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	if(id.equals("abcde")&&pw.equals("12345")){
		Cookie cookie = new Cookie("id",id);
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		response.sendRedirect("welcome.jsp");
	}else{
		response.sendRedirect("loginForm.jsp?msg=error");
	}
%>