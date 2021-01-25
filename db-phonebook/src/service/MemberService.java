package service;

import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberVO;
import myexception.ExceptionPrintList;
import myexception.NameInputException;
import myexception.NoSuchMemberException;
import myexception.WrongGroupException;
/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberService.java
 * @package : 	service
 * @description : 컨트롤러과 DAO를 연결해주는 서비스 클래스
 */
public class MemberService {
//	전역변수로 DAO class와 Vo class를 제네릭으로 받는 어레이리스트를 선언한다.
	ExceptionPrintList exceptionPrintList = new ExceptionPrintList();
	MemberDAO memberDAO= new MemberDAO();
	ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
	
	
	public int insertMember(MemberVO member) {
		
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
	
	public ArrayList<MemberVO> selectByName(String name) {
		
		memberList = memberDAO.selectByName(name);
		
//		받은 멤버의 사이즈가 0이라면 예외를 던지고 메소드를 종료한다.
		if(memberList.size()==0) {
			try {
//				만약 사이즈가 0이라면 예외를 강제로 던진다.
				throw new NoSuchMemberException();
				
//				던져진 예외를 catch한다.
			}catch(NoSuchMemberException e) {
//				그런 멤버는 없다고 출력하고
				e.print();
//				메소드를 종료한다.
			}
		}
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
	
	public int updateMember(MemberVO member) {
		int member_num = member.getMemberNum();
		int rowcnt = memberDAO.updateMember(member,member_num);
		
		return rowcnt;
	}
	
	
	public int deleteMember(int member_num) {
		
		int rowcnt = memberDAO.deleteMember(member_num);
		return rowcnt;
	}
	
	public boolean phonNumberChecker(String phoneNumber, MemberVO member) {
		
	// 3가지 조건 중 1개라도 만족하면 메소드 내의 문장을 출력하고, 다시 while문의 조건으로 돌아간다.
	// 1. 길이가 11이 아니거나, 0으로 시작하지 않거나, 특수문자 '-'를 포함할 것. 2.입력된 문자열의 원소 중에 하나라도 0~9 사이가 아닐것 . 3. 이미 있는 번호일 것.
		if(exceptionPrintList.isNotCorrectNumber(phoneNumber)
					||exceptionPrintList.isNotNumber(phoneNumber)
	//		이미 저장되어 있는 번호의 경우. 파라미터로 들어온 member의 member_num과 phonenumber의 member_num을 비교하게된다.
	//		멤버 번호가 다르다면 이미 저장되어 있는 번호이고, 멤버 번호가 같다면(같은사람인데 전화번호를 수정하지 않는 경우에는) 넘어간다.
					||exceptionPrintList.isAlreadyStored(phoneNumber,member)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean groupNameChecker(String groupName) {
	// 3가지 조건 중 1개라도 만족하면 while문을 탈출한다.
	// 1. 입력된 값이 '가족'일 것, 입력된 값이 '친구'일 것, 입력된 값이 '기타'일 것
		if (groupName.equals("가족")||groupName.equals("친구")||groupName.equals("기타")) {
			return true;

		}else {
			// 조건을 만족하지 못하면 예외를 던진 후 다시 while로 들어간다.
			try {
//				그룹이 가족,친구,기타가 아닐때 예외를 던진다.
				throw new WrongGroupException();
				
			}catch(WrongGroupException e) {
//				예외시 출력되는 출력문
				e.print();
				return false;
			}

		}
	}
	public boolean nameNullChecker(String name) {
		if(name.equals("")){
			try{
//				예외를 던진다.
				throw new NameInputException();
			}catch(NameInputException e) {
//				예외의 출력문
				e.print();
				return true;
			}
		}else {
//			그렇지 않다면 이 반복문을 탈출한다.
			return false;
		}
	}
}

