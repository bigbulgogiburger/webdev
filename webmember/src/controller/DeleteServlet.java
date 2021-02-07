package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceMember;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. 로그인 여부 확인.
//		1.1 Session 확인. ->로그인을 한 후에 이름, 아이디 저장. =>Session 저장.
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		
		if(name==null || id==null) {
//			로그인 하지 않은 사용자인 경우.
//			get방식으로 전달
			response.sendRedirect("LoginServlet");
		}else {
			int memberNum = Integer.parseInt(request.getParameter("memberNum"));
			System.out.println(memberNum);
			ServiceMember serviceMember = new ServiceMember();
			serviceMember.deleteMember(memberNum);
			response.sendRedirect("MainServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
