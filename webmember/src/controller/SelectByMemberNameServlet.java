package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceMember;
import vo.MemberVO;

/**
 * Servlet implementation class SelectByMemberNameServlet
 */
@WebServlet("/SelectByMemberNameServlet")
public class SelectByMemberNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectByMemberNameServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		String memberName = request.getParameter("memberName");
		if(name==null || id==null) {
//			로그인 하지 않은 사용자인 경우.
//			get방식으로 전달
			response.sendRedirect("LoginServlet");
		}else {
			ServiceMember sMember= new ServiceMember();
			ArrayList<MemberVO> memList = sMember.selectByNameId(memberName,id);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
