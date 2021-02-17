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

@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("joinForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
		
		
		
		
	}
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		ServiceMember service = new ServiceMember();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		String detail_address = request.getParameter("detail_address");
		String postcode = request.getParameter("postcode");
		int groupnum= 4;
		JoinVO join = new JoinVO(id,pw,name);
		MemberVO member= new MemberVO(name,phone1,phone2,phone3,address,groupnum,id,detail_address,postcode);
		ExceptionPrintList exception = new ExceptionPrintList();
		if(name.equals("")) {
			request.setAttribute("member", member);
			request.setAttribute("join", join);
			request.setAttribute("nameMsg", "이름을 입력하지 않으셨습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
		}else if(id.equals("")) {
			request.setAttribute("member", member);
			request.setAttribute("join", join);
			request.setAttribute("idMsg", "아이디를 입력하지 않으셨습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
		}else if(pw.equals("")){
			request.setAttribute("member", member);
			request.setAttribute("join", join);
			request.setAttribute("pwMsg", "패스워드를 입력하지 않으셨습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
		}else {
//		id validation. 중복되는 아이디인지 아닌지.
//		true이면 중복 false면 중복아님
		
		if(service.idChecker(id)) {
			request.setAttribute("member", member);
			request.setAttribute("join", join);
			request.setAttribute("idMsg", "중복된 아이디입니다.");
			RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
			disp.forward(request, response);
		}else {
//			핸드폰 번호 validation 1. 11자리인지, 2. 숫자인지  3. 올바른 번호인지.
			String phonenumber = phone1+phone2+phone3; 
			if(exception.isAlreadyStored(phonenumber, member)) {
				request.setAttribute("member", member);
				request.setAttribute("join", join);
				request.setAttribute("phoneMsg", "이미 저장된 번호입니다.");
				RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
				disp.forward(request, response);
			}else if(exception.isNotNumber(phonenumber)) {
				request.setAttribute("member", member);
				request.setAttribute("join", join);
				request.setAttribute("phoneMsg", "올바른 숫자를 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
				disp.forward(request, response);
			}else if(exception.isNotCorrectNumber(phonenumber)) {
				request.setAttribute("member", member);
				request.setAttribute("join", join);
				request.setAttribute("phoneMsg", "11자리의 숫자를 입력해주세요");
				RequestDispatcher disp = request.getRequestDispatcher("joinForm.jsp");
				disp.forward(request, response);
			}else {
				int rowcnt2 = service.insertJoin(join);
				System.out.println("rowcnt2");
				int rowcnt1 = service.insertMember(member);
				System.out.println("rowcnt1");
				
				
				if(rowcnt1+rowcnt2==2) {
				request.setAttribute("name", name);
				RequestDispatcher disp = request.getRequestDispatcher("joinOK.jsp");
				disp.forward(request, response);
				
				}
			}
			
			
		}

	}
	}
}

