package com.kbigdata.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/emp/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request는 hashmap으로 저장한다. -> request는 저장소가 있다.
		request.setCharacterEncoding("utf-8");
		String empno = request.getParameter("empno");
		String empnm = request.getParameter("empnm");
		String deptno = request.getParameter("deptno");
		String sex = request.getParameter("sex");
		
		response.setContentType("text/html ; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.println("<h1>사원등록 정보<h1>");
		out.println("사원번호 : "+ empno);
		out.println("사원명 : "+ empnm);
		out.println("부서번호 : "+ deptno);
		out.println("성별 : "+sex);
		String[] hobby = request.getParameterValues("hobby");
		out.print("취미 : ");
		for(int i=0; i<hobby.length;i++) {
			out.print(hobby[i]+", ");
		}
		out.close();
	}

}
