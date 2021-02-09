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
		String category = request.getParameter("category");
		String value = request.getParameter("value");
		if(name==null || id==null) {
//			로그인 하지 않은 사용자인 경우.
//			get방식으로 전달
			response.sendRedirect("LoginServlet");
		}else {
			if(value.equals("")) {
				String resultMsg="내용을 한글자 이상 입력해주세요";
				request.setAttribute("resultMsg", resultMsg);
				RequestDispatcher disp = request.getRequestDispatcher("main.jsp");
				disp.forward(request, response);
			}else {
				ServiceMember sMember= new ServiceMember();
				ArrayList<MemberVO> members = sMember.selectByNameId(category,value,id);
				if(members.size()==0) {
					String resultMsg="해당하는 회원이 없습니다.";
					request.setAttribute("resultMsg", resultMsg);
					RequestDispatcher disp = request.getRequestDispatcher("main.jsp");
					disp.forward(request, response);
				}else {
					request.setAttribute("members", members);
//					2.2 main.jsp로 포워딩
					RequestDispatcher disp = request.getRequestDispatcher("main.jsp");
					disp.forward(request, response);
				}
			}
			
			
			
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
