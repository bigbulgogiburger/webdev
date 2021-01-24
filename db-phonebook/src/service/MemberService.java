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
		
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
	
	public ArrayList<MemberVO> selectByName(String name) {
		
		memberList = memberDAO.selectByName(name);
		return memberList;
	
		
	}
	
	public int selectByPhoneNumber(String phoneNumber) {
		int member_num = memberDAO.selectByPhoneNumber(phoneNumber); 
		return member_num;
	}
	
	
	public ArrayList<MemberVO> selectAll() {
		
		memberList=memberDAO.selectAll();
		
		return memberList;
		
		
	}
	
	public int updateMember(MemberVO member,int member_num) {
		int rowcnt = memberDAO.updateMember(member,member_num);
		return rowcnt;
	}
	
	
	public int deleteMember(int member_num) {
		
		int rowcnt = memberDAO.deleteMember(member_num);
		return rowcnt;
	}
}

