package com.kbigdata.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormEx
 */
@WebServlet("/FormEx")
public class FormEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormEx() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernm = request.getParameter("nm");
		String userid = request.getParameter("id");
		String userpw = request.getParameter("pw");
		System.out.println("사용자가 입력한 이름 : "+usernm);
		System.out.println("사용자가 입력한 아이디 : "+userid);
		System.out.println("사용자가 입력한 아이디 : "+userpw);
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.print("이름 :" +usernm +"<br>");
		out.print("아이디 :" +userid +"<br>");
		out.print("비밀번호 :" +userpw +"<br>");
		out.print("<h1>로그인 성공<h1>");
	}

}
