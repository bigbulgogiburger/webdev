package service;

import java.util.ArrayList;

import dao.MemberDAO;
import view.MemberView;
import vo.MemberVO;

public class MemberService {

	public static void insertMember(MemberVO member) {
		int rowcnt= MemberDAO.insertMember(member);
		MemberView.printUpdateMember(rowcnt);
	}
	
	public static ArrayList<MemberVO> selectByName(int number) {
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		String name = null;
		while(true) {
			name=MemberView.nameFinder(number);
			memberList = MemberDAO.selectByName(name);
			if(memberList.size()==0) {
				MemberView.printNoSuchMember();
				return memberList;
			}else {
				return memberList;
			}
		}
		
		
	}
	public static void selectAll() {
		ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
		memberList=MemberDAO.selectAll();
		MemberView.printSelect(memberList);
	}
	
	
	public static void deleteMember(MemberVO member) {
		MemberDAO.deleteMember(member);
	}
}

