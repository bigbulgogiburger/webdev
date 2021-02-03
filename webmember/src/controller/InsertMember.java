package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceMember;
import vo.JoinVO;
import vo.MemberVO;

@WebServlet("/InsertMember")
public class InsertMember extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public InsertMember() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
		
		
		
		
	}
	private void doAction(HttpServletRequest request, HttpServletResponse response) {
		
		ServiceMember service = new ServiceMember();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		int groupnum= Integer.parseInt(request.getParameter("groupnum"));
		
		int rowcnt2 = service.insertJoin(new JoinVO(id,pw));
		System.out.println("rowcnt2");
		int rowcnt1 = service.insertMember(new MemberVO(name,phone1,phone2,phone3,address,groupnum));
		System.out.println("rowcnt1");
		
		if(rowcnt1+rowcnt2==2) {
			try {
				response.sendRedirect("insertOK.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
