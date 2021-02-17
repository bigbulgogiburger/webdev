package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myexception.ExceptionPrintList;
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
			String name = request.getParameter("name");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String address = request.getParameter("address");
			String detail_address = request.getParameter("detail_address");
			String postcode = request.getParameter("postcode");
			int groupnum= Integer.parseInt(request.getParameter("groupNum"));
			
			
			MemberVO member= new MemberVO(name,phone1,phone2,phone3,address,groupnum,id,detail_address,postcode);
			member.setMemberNum(memberNum);
			ExceptionPrintList exception = new ExceptionPrintList();
			
			
			request.setAttribute("name", name);
			if(name.equals("")) {
				request.setAttribute("member", member);
				request.setAttribute("nameMsg", "이름을 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
				disp.forward(request, response);
			}else {
				String phonenumber = phone1+phone2+phone3; 
				if(exception.isAlreadyStored(phonenumber, member)) {
					request.setAttribute("member", member);
					request.setAttribute("phoneMsg", "이미 저장된 번호입니다.");
					RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
					disp.forward(request, response);
				}else if(exception.isNotNumber(phonenumber)) {
					request.setAttribute("member", member);
					request.setAttribute("phoneMsg", "올바른 숫자를 입력해주세요");
					RequestDispatcher disp = request.getRequestDispatcher("memberModifyForm.jsp");
					disp.forward(request, response);
				}else if(exception.isNotCorrectNumber(phonenumber)) {
					request.setAttribute("member", member);
					request.setAttribute("phoneMsg", "11자리의 숫자를 입력해주세요");
					RequestDispatcher disp = request.getRequestDispatcher("memberModify.jsp");
					disp.forward(request, response);
				}else {
			
					request.setCharacterEncoding("utf-8");
					ServiceMember sMember = new ServiceMember();
					
					member.setName(request.getParameter("name"));
					member.setPhone1(request.getParameter("phone1"));
					member.setPhone2(request.getParameter("phone2"));
					member.setPhone3(request.getParameter("phone3"));
					member.setGroupnum(Integer.parseInt(request.getParameter("groupNum")));
					member.setDetail_address(request.getParameter("detail_address"));
					member.setPostcode(request.getParameter("postcode"));
					System.out.println(Integer.parseInt(request.getParameter("groupNum")));
					member.setAddress(request.getParameter("address"));
					member.setMemberNum(memberNum);
					sMember.updateMember(member);
					
					response.sendRedirect("MainServlet");
				}
			}
		}
	}
}
