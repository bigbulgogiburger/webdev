package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceMember;
import vo.MemberVO;

/**
 * Servlet implementation class SelectAll
 */
@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectAll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServiceMember serviceMember = new ServiceMember();
		HttpSession session = request.getSession();
		ArrayList<MemberVO> memlist = serviceMember.selectAll((String)session.getAttribute("id"));
		System.out.println((String)session.getAttribute("id"));
		request.setAttribute("memList", memlist);
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		rd.forward(request, response);
	}

}
