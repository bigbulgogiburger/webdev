package dao;

import java.util.ArrayList;

import vo.MemberVO;

public class tempMain {
	public static void main(String[] args) {
		
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		memberList=MemberDAO.selectByName("편도훈");
		for(MemberVO x : memberList) {
			System.out.println(x.toString());
		}
	}
}
