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
import vo.JoinVO;
import vo.MemberVO;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
		
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession session = request.getSession();	
		request.setCharacterEncoding("utf-8");
		ServiceMember service = new ServiceMember();
		String name = request.getParameter("name");
		String id = (String)session.getAttribute("id");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		int groupnum= Integer.parseInt(request.getParameter("groupnum"));
		request.setAttribute("name", name);
		
		int rowcnt1 = service.insertMember(new MemberVO(name,phone1,phone2,phone3,address,groupnum,id));
		System.out.println("rowcnt1");
						
		if(rowcnt1==1) {
		RequestDispatcher disp = request.getRequestDispatcher("insertOK.jsp");
		disp.forward(request, response);
			
				
			}
	}

}