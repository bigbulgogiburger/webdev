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


@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		session =>id 추출
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id.equals("")) {
//		로그인이 안된상태 =>MainServlet으로 보낸다.=> loginForm.jsp
			response.sendRedirect("MainServlet");
		}else {
//			select by id
			ServiceMember mService = new ServiceMember();
//			request에 select한 결과를 담는다.
			MemberVO member= mService.selectById(id);//아이디는 session에서 추출한 아이디.
			request.setAttribute("member", member);
//			modifyForm.jsp forward
			RequestDispatcher disp = request.getRequestDispatcher("modifyForm.jsp");
			disp.forward(request, response);
				
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request =>사용자가 입력한 정보 추출
//		post로 들어오면 반드시 setcharacterEncoding해야한다.
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		
//		비밀번호 확인
		if(pw.equals("")) {
//			비밀번호를 입력하지 않은경우
			doGet(request,response);
		}else {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			ServiceMember mService= new ServiceMember();
//			id : session에서 가져옴 pw : 사용자가 입력한비밀번호.
			int memberNum = mService.selectByIdPw(id,pw);
			if(memberNum !=0) {
//				비밀번호가 정상적으로 입력된 경우=>정상적으로 수정(update).
				MemberVO mem = new MemberVO();
				mem.setName(name);
				mem.setId(id);
				mem.setPhone1(phone1);
				mem.setPhone2(phone2);
				mem.setPhone3(phone3);
				mem.setAddress(address);
				mem.setMemberNum(memberNum);
				mService.updateMember(mem);
				response.sendRedirect("MainServlet");
			}else{
				
				doGet(request, response);
			}
			
		}
		
//		ok인 경우
//		ok가 아닌 경우
	
	}

}
