<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Cookie[] cookie = request.getCookies();

for(int i = 0 ; i<cookie.length;i++){
	String id = cookie[i].getValue();
		if(id!=null&&id.equals("abcde")){
			cookie[i].setMaxAge(0);
			response.addCookie(cookie[i]);
	
		}
	}
response.sendRedirect("loginForm.jsp");
%>