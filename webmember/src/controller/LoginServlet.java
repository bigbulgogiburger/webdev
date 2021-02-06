package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceMember;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			response.sendRedirect("loginForm.jsp");
		}else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceMember serviceMember = new ServiceMember();
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		String name = serviceMember.searchJoin(id,pw);
		if(name !=null) {
			session.setAttribute("id", id);
			session.setAttribute("name",name);
			response.sendRedirect("MainServlet");
		}else {
			response.sendRedirect("loginForm.jsp");
		}
	}

}
