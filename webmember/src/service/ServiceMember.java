package service;

import java.util.ArrayList;

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
	public String searchJoin(String id, String pw) {
		MemberDAO memberDAO = new MemberDAO();
		JoinVO joinVO = memberDAO.searchJoin(id);
		if(pw.equals(joinVO.getPw())) {
			return joinVO.getName();
		}
		return null;
	}
	public ArrayList<MemberVO> selectAll(String id) {
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberVO> memlist = memberDAO.selectAll(id);
		return memlist;
	}
	public MemberVO selectById(String id) {
		MemberDAO mDao= new MemberDAO();
		MemberVO member =  mDao.selectById(id);
		return member;
	}
	public int selectByIdPw(String id, String pw) {
		MemberDAO mDao= new MemberDAO();
		int count =  mDao.selectByIdPw(id,pw);
		return count;
	}
	public void updateMember(MemberVO member) {
		MemberDAO mDao= new MemberDAO();
		mDao.updateMember(member);
	}
	public void deleteMember(int memberNum) {
		MemberDAO mDao= new MemberDAO();
		mDao.deleteMember(memberNum);
		
	}

}
