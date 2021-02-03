package service;

import dao.MemberDAO;
import vo.JoinVO;
import vo.MemberVO;

public class ServiceMember {
	
	public int insertMember(MemberVO member) {
		MemberDAO memberDAO = new MemberDAO(); 
		int rowcnt=0;
		rowcnt = memberDAO.insertMember(member);
		return rowcnt;
	}
	public int insertJoin(JoinVO join) {
		MemberDAO memberDAO = new MemberDAO(); 
		int rowcnt=0;
		rowcnt = memberDAO.insertJoin(join);
		return rowcnt;
	}
	public int searchJoin(String id, String pw) {
		MemberDAO memberDAO = new MemberDAO();
		String password = memberDAO.searchJoin(id);
		if(pw.equals(password)) {
			return 1;
		}
		return 0;
	}

}
