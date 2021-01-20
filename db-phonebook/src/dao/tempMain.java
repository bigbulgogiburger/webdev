package dao;

import java.util.ArrayList;

import vo.MemberVO;

public class tempMain {
	public static void main(String[] args) {
		
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		memberList= MemberDAO.selectAll();
		for(MemberVO member : memberList) {
			System.out.println(member);
			
		}
		MemberVO member= new MemberVO();
		member.setName("박주현");
		member.setPhoneNumber("01087533333");
		member.setAddress("강남");
		member.setGroup("가족");

		memberList= MemberDAO.selectAll();
		for(MemberVO xx : memberList) {
			System.out.println(xx);
		
		
	}
		MemberDAO.deleteMember(member);
		System.out.println("===================");
		memberList= MemberDAO.selectAll();
	}
}
