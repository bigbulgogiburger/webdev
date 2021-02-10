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
	public MemberVO selectByMemberNum(int memberNum) {
		MemberDAO mDao = new MemberDAO();
		MemberVO member = mDao.selectByMemberNum(memberNum);
		return member;
	}
	
	public void updateJoin(JoinVO join) {
		MemberDAO mDao = new MemberDAO();
		mDao.updateJoin(join);
	}
	public ArrayList<MemberVO> selectByNameId(String category, String memberName, String id) {
		ArrayList<MemberVO> memList = new MemberDAO().selectByNameId(category,memberName, id); 
		return memList;
	}
	public boolean idChecker(String id) {
		MemberDAO mDao = new MemberDAO();
		
		boolean check = mDao.idChecker(id);
		return check;
	}
	public int selectByPhoneNumber(String phoneNumber) {
		MemberDAO mDao = new MemberDAO();
		int membernum = mDao.selectByPhoneNumber(phoneNumber);
		return membernum;
	}
	public int findGroupNum(String id) {
		MemberDAO mDao = new MemberDAO();
		int groupNum = mDao.findGroupNum(id);
		return groupNum;
	}
	public ArrayList<MemberVO> selectAll() {
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberVO> memlist = memberDAO.selectAll();
		return memlist;
	}

}
