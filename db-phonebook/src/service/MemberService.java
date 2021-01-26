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
	
	
//	멤버를 추가하는 메소드이다. controller에게 VO를 전달받아 그 값을 DAO에게 넘겨준다.
	public int insertMember(MemberVO member) {
		
//		Service는 rowcnt를 전달받아 controller에게 전달
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
//	controller에게 이름을 전달받아 DAO에게 전달하는 메소드이다.
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
//		만약 해당하는 멤버가 없다면 전달되는 어레이리스트의 사이즈는 0이다. 
		return memberList;
	}
	
//	핸드폰 번호를 controller에게 전달받아 dao에게 전달하는 메소드. 
//	이 메소드는 dao에게 해당 핸드폰 번호를 가진 멤버의 회원번호를 전달받는다.
//	전달받은 회원번호를 예외처리를 진행할때에 사용하는 메소드이다.
	public int selectByPhoneNumber(String phoneNumber) {
		int member_num = memberDAO.selectByPhoneNumber(phoneNumber); 
		return member_num;
	}
	
//	dao에게 어레이리스트를 받아 그 값을 controller에게 전달하는 메소드
	public ArrayList<MemberVO> selectAll() {
		
		memberList=memberDAO.selectAll();
		
		return memberList;
		
		
	}
//	멤버를 수정할때에 사용하는 메소드이다. dao는 수정시에 member_num을 이 메소드로부터 따로 전달받는다.
	public int updateMember(MemberVO member) {
		int member_num = member.getMemberNum();
		int rowcnt = memberDAO.updateMember(member,member_num);
		
		return rowcnt;
	}
	
//	dao에게 멤버 삭제를 요청하고 그 결과값으로 rowcnt를 받는 메소드. 해당 rowcnt는 controller에게 전달된다.
	public int deleteMember(int member_num) {
		
		int rowcnt = memberDAO.deleteMember(member_num);
		return rowcnt;
	}
//	회원 추가, 수정시에 핸드폰번호에 대한 예외처리가 담겨있는 메소드.
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
//	그룹이름이 올바른지 확인하는 메소드
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
	
//	이름이 null인지 판별하여 boolean을 전달하는 메소드
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

