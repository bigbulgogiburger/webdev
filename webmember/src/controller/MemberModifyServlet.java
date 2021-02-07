package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceMember;
import vo.MemberVO;

@WebServlet("/MemberModifyServlet")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServiceMember sMember = new ServiceMember();
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			response.sendRedirect("MainServlet");
		}else {
			int memberNum=Integer.parseInt(request.getParameter("memberNum"));
			MemberVO member = sMember.selectByMemberNum(memberNum);
			request.setAttribute("member", member);
			session.setAttribute("memberNum", memberNum);
			RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
			disp.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String id = (String)session.getAttribute("id");
		int memberNum =(int)session.getAttribute("memberNum");
		session.removeAttribute("memberNum");
		
		if(id == null) {
			response.sendRedirect("MainServlet");
		}else {
		
		request.setCharacterEncoding("utf-8");
		MemberVO member = new MemberVO();
		ServiceMember sMember = new ServiceMember();
		
		member.setName(request.getParameter("name"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setGroupnum(Integer.parseInt(request.getParameter("groupNum")));
		System.out.println(Integer.parseInt(request.getParameter("groupNum")));
		member.setAddress(request.getParameter("address"));
		member.setMemberNum(memberNum);
		sMember.updateMember(member);
		
		response.sendRedirect("MainServlet");
	}
}

}
