package com.kbigdata.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String result = "";
		if(id.equals("hong")&&pw.equals("1234")) {
			result="get 정상 로그인";
		}else {
			result="get 아이디나 비밀번호가 틀렸습니다.";
		}
		response.setContentType("text/html ; charset=utf-8");
		PrintWriter client = response.getWriter();
		client.print(result);
		client.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String result = "";
		if(id.equals("hong")&&pw.equals("1234")) {
			result="post 정상 로그인";
		}else {
			result="post 아이디나 비밀번호가 틀렸습니다..";
		}
		response.setContentType("text/html ; charset=utf-8");
		PrintWriter client = response.getWriter();
		client.print(result);
		client.close();
	}

}
