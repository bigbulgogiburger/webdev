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
import vo.JoinVO;
import vo.MemberVO;

@WebServlet("/MemberInsertServlet")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("insertForm.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		session =>id 추출
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
//			로그인이 안된상태 =>MainServlet으로 보낸다.=> loginForm.jsp
				response.sendRedirect("MainServlet");
		}else {
			actionDo(request, response);
		}

		
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();	
		ServiceMember service = new ServiceMember();
		String name = request.getParameter("name");
		String id = (String)session.getAttribute("id");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		int groupnum= Integer.parseInt(request.getParameter("groupnum"));
		
		MemberVO member= new MemberVO(name,phone1,phone2,phone3,address,groupnum,id);
		ExceptionPrintList exception = new ExceptionPrintList();
		
		
		request.setAttribute("name", name);
		if(name.equals("")) {
			request.setAttribute("member", member);
			request.setAttribute("nameMsg", "이름을 입력해주세요");
			RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
			disp.forward(request, response);
		}else {
			String phonenumber = phone1+phone2+phone3; 
			if(exception.isAlreadyStored(phonenumber, member)) {
				request.setAttribute("member", member);
				request.setAttribute("phoneMsg", "이미 저장된 번호입니다.");
				RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
				disp.forward(request, response);
			}else if(exception.isNotNumber(phonenumber)) {
				request.setAttribute("member", member);
				request.setAttribute("phoneMsg", "올바른 숫자를 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
				disp.forward(request, response);
			}else if(exception.isNotCorrectNumber(phonenumber)) {
				request.setAttribute("member", member);
				request.setAttribute("phoneMsg", "11자리의 숫자를 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("insertForm.jsp");
				disp.forward(request, response);
			}else {
		
		
		int rowcnt1 = service.insertMember(new MemberVO(name,phone1,phone2,phone3,address,groupnum,id));
		System.out.println(rowcnt1);
						
		if(rowcnt1==1) {
		response.sendRedirect("MainServlet");
			
				
		}
	}

}
	}
}
