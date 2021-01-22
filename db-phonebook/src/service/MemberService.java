package service;

import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberVO;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberService.java
 * @package : 	service
 * @description : 컨트롤러과 DAO를 연결해주는 서비스 클래스
 */
public class MemberService {
//	전역변수로 DAO class와 Vo class를 제네릭으로 받는 어레이리스트를 선언한다.
	MemberDAO memberDAO= new MemberDAO();
	ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
	
	
	public int insertMember(MemberVO member) {
		memberDAO= new MemberDAO();
		
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
	
	public ArrayList<MemberVO> selectByName(String name) {
		memberDAO= new MemberDAO();
		memberList	= new ArrayList<MemberVO>();
		
		while(true) {
			memberList = memberDAO.selectByName(name);
			return memberList;
			
		}
		
		
	}
	
	public ArrayList<MemberVO> selectAll() {
		memberDAO= new MemberDAO();
		memberList= new ArrayList<MemberVO>();
		
		memberList=memberDAO.selectAll();
		
		return memberList;
		
		
	}
	
	
	public void deleteMember(MemberVO member) {
		memberDAO= new MemberDAO();
		
		memberDAO.deleteMember(member);
	}
}

