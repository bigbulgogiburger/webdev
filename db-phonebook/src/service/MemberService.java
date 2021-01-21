package service;

import java.util.ArrayList;

import dao.MemberDAO;
import view.MemberView;
import vo.MemberVO;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberService.java
 * @package : 	service
 * @description : 컨트롤러과 DAO를 연결해주는 클래스
 */
public class MemberService {
	MemberDAO memberDAO= new MemberDAO();
	MemberView memberView = new MemberView();
	ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
	
	public int insertMember(MemberVO member) {
		memberDAO= new MemberDAO();
		
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
	
	public ArrayList<MemberVO> selectByName(int number) {
		memberDAO= new MemberDAO();
		memberView = new MemberView();
		memberList	= new ArrayList<MemberVO>();
		String name = null;
		
		while(true) {
			name=memberView.nameFinder(number);
			memberList = memberDAO.selectByName(name);
			
			if(memberList.size()==0) {
				
				memberView.printNoSuchMember();
				
				return memberList;
			}else {
				
				return memberList;
			}
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

