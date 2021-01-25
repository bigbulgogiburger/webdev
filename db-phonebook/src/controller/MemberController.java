package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myexception.ExceptionPrintList;
import myexception.NameInputException;
import myexception.WrongGroupException;
import myexception.WrongNumberException;
import service.MemberService;
import view.MemberView;
import vo.MemberVO;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberController.java
 * @package : 	controller
 * @description : 프로그램을 뷰와 서비스 사이에서 제어하는 클래스
 */


public class MemberController {

	MemberVO member = new MemberVO();
	MemberService memberService = new MemberService();
	MemberView memberView = new MemberView();
	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	ExceptionPrintList exceptionPrintList = new ExceptionPrintList();
	private static Scanner scanner = new Scanner(System.in);
	
//	1. 추가, 3. 수정 시에 출력되는 메소드
//	첫번째 파라미터 number가 1이 들어오면 추가 2가 들어오면 수정을 하게하는 출력문이 출력된다.(view 영역에서).
//	두번째 파라미터 member는 추가시엔 new Member가 들어오고, 수정시에는 이미 작성되어 있는 객체를 불러온다.
	public MemberVO processInsertMember(int number, MemberVO member){
//		number가 1일 시에는 (추가) 2일시에는 (수정)이라는 출력문이 뜬다.
		memberView.printInsertMemberIntro(number);
		String name = null;
		// 이름을 입력하지 않았을 경우에는 다음과 같은 while문을 반복하게 한다.
		while(true) {

			name = scanner.nextLine();

			if(name.equals("")){
				try{
					throw new NameInputException();
				}catch(NameInputException e) {
					e.print();
					continue;	
				}
			}else {
				break;
			}
		}
		// 입력된 전화번호를 받아주는 String 타입의 변수 생성 및 null로 초기화
		memberView.printInsertMemberNumber();
		
		String phoneNumber=null;

		// break를 만나기 전까지는 무한루프를 도는 while문
		while(true) {
		// 전화번호를 키보드롤 통해 받는다.
			phoneNumber= scanner.nextLine();

		// 3가지 조건 중 1개라도 만족하면 메소드 내의 문장을 출력하고, 다시 while문의 조건으로 돌아간다.
		// 1. 길이가 11이 아니거나, 0으로 시작하지 않거나, 특수문자 '-'를 포함할 것. 2.입력된 문자열의 원소 중에 하나라도 0~9 사이가 아닐것 . 3. 이미 있는 번호일 것.
			if(exceptionPrintList.isNotCorrectNumber(phoneNumber)
					||exceptionPrintList.isNotNumber(phoneNumber)
//					이미 저장되어 있는 번호의 경우. 파라미터로 들어온 member의 member_num과 phonenumber의 member_num을 비교하게된다.
//					멤버 번호가 다르다면 이미 저장되어 있는 번호이고, 멤버 번호가 같다면(같은사람인데 전화번호를 수정하지 않는 경우에는) 넘어간다.
					||exceptionPrintList.isAlreadyStored(phoneNumber,member)) {
				continue;
			}else {
				break;
			}
		}
		// 주소를 입력받는 String type의 변수 생성 후 키보드로 입력받는 값을 대입
		memberView.printInsertMemberAddress();
		
		String address= scanner.nextLine();

		// 주소를 기입하지 않았을 시에 주소없음을 대입힌다.
		if(address.equals("")) {
			address="(주소없음)";
		}

		// 그룹을 입력받는 String type의 변수 생성 후 null값으로 초기화
		String group=null;
		
		memberView.printInsertMemberGroup();

		// 항상 참인 조건문(무한루프)
		while(true) {
		// 키보드를 통해 받은 String을 그룹에 대입
			group= scanner.nextLine();

		// 3가지 조건 중 1개라도 만족하면 while문을 탈출한다.
		// 1. 입력된 값이 '가족'일 것, 입력된 값이 '친구'일 것, 입력된 값이 '기타'일 것
			if (group.equals("가족")||group.equals("친구")||group.equals("기타")) {
					break;
				
				
			}else {
		// 조건을 만족하지 못하면 예외를 던진 후 다시 while로 들어간다.
				try {
//					그룹이 가족,친구,기타가 아닐때 예외를 던진다.
					throw new WrongGroupException();
				}catch(WrongGroupException e) {
//					예외시 출력되는 출력문
					e.print();
					continue;
				}

			}
		}
		
//		예외처리가 된 개인정보를 오브젝트에 담는다.
		member.setGroup(group);
		member.setName(name);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);

//		MemberVO object를 리턴한다
		return member;
	}
	
//	단순히 이름을 리턴하는 메소드
//	파라미터가 1이면 (수정) 2이면 (삭제)를 출력하게 한다.
//	이름이 null이라면 예외를 던진다.
	public String nameFinder(int number) {
		
		memberView.nameFinderPrint(number);
		String name= null;
		while(true) {
			name= scanner.nextLine();
//			만약 이름이 널이라면
			if(name.equals("")){
				try{
//					예외를 던진다.
					throw new NameInputException();
				}catch(NameInputException e) {
//					예외의 출력문
					e.print();
					continue;	
				}
			}else {
//				그렇지 않다면 이 반복문을 탈출한다.
				break;
			}
			
		}
//		이름을 리턴한다.
		return name;
	}
	
//	수정 또는 삭제시에 번호를 고르게 하는 메소드. VO의 어레이리스트와 숫자를 입력받아 수정/삭제될 1명을 리턴한다.
	public MemberVO SelectNumberToUpdate(ArrayList<MemberVO> memberList, int number) {
		
//		총 몇명인지 세는 변수를 선언.
		int memberCount = memberList.size();
		
		while(true) {
//			수정/삭제 할 회원의 번호를 입력하게 한다.
			memberView.printSelectNumberToUpdate(number);
			
			String stringIndex=null;
			int index = 0;
			
			try {
//			몇번을 선택할 것인지 String으로 받는다.
			stringIndex = scanner.nextLine();
	
//			String을 integer로 받은 후에 자동형변환을 진행한다.
			
			index= Integer.parseInt(stringIndex);
//			그때의 에러는 크게 2가지로 나올 수 있다. 
//			1. String으로 들어간 변수가 잘못된 경우와,
//			2. 입력 받은 것을 parsing 할때에 숫자가 아닌 경우.
//			그 두 가지 경우를 한번에 묶어버려서 예외처리 한다.
			}catch(InputMismatchException|NumberFormatException e) {
//				올바른 숫자를 입력하라는 출력문
				exceptionPrintList.NumberNotInIndexBoundPrint(memberCount);
				continue;
			}

			try {
//				숫자가 들어오고 VO를 반납할때에 올바른 범위가 아니면 발생하는 예외
				return memberList.get(index-1);
			}catch(IndexOutOfBoundsException e) {
//				올바른 숫자를 입력하라는 출력문
				exceptionPrintList.NumberNotInIndexBoundPrint(memberCount);
				continue;
			}
		}
	}

	
// 추가할때에 제일 먼저 호출되는 메소드
	public void insertMember(int number) {
		member = new MemberVO();
		
//		VO는 상단의 processInsertMember 메소드를 가져온다.
//		1번과 새로운 VO object를 전달하고, insert할 VO를 리턴받는다.
		member=processInsertMember(number, new MemberVO());
		
//		받은 값을 service 영역에다 전달한다. service는 dao의 sql처리 결과를 rowcnt로 리턴하고
//		그 결과를 controller의 이 메소드가 받는다.
		int rowcnt= memberService.insertMember(member);
//		rowcnt가 1이상이면 성공 0이면 실패했다고 출력한다.
		memberView.printUpdateMember(rowcnt);
	}
	
//	수정할때에 호출되는 메소드(오버로딩됨)
	public void insertMember(int number,MemberVO member) {
//		수정할때에는 number=2, member는 search를 통해 찾은 이름 중 선택 된 member object이다.
		member=processInsertMember(number,member);
		
//		멤버의 멤버번호를 get
		int member_num = member.getMemberNum();
		
		int rowcnt= memberService.updateMember(member,member_num);
		
		memberView.printUpdateMember(rowcnt);
	}
//	2. 전체 목록 보기를 선택할 시에 출력되는 출력문
	public void selectAll() {
		
		memberList=memberService.selectAll();
		
		memberView.printSelect(memberList);
	}
//	멤버 삭제 
	public void deleteMember() {
		
		String name = nameFinder(2);
		memberList = memberService.selectByName(name);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			memberView.printNoSuchMember();
			return;
		}
		
		MemberVO member = SelectNumberToUpdate(memberList,2);
		
		int rowcnt = memberService.deleteMember(member.getMemberNum());
		
		memberView.printUpdateMember(rowcnt); 
		
	}
	
	
	public void updateMember() {
		
		String name = nameFinder(1);
		
		memberList = memberService.selectByName(name);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			memberView.printNoSuchMember();
			return;
		}
		
		MemberVO member = SelectNumberToUpdate(memberList,1);
		insertMember(2,member);
	}

	public void start(){
		ExceptionPrintList exceptionPrintList = new ExceptionPrintList();
		MemberController memberController = new MemberController();
		MemberView memberView = new MemberView();
		int selector =0;
		
		while(true) {
			memberView.viewIntro();
			
			try {
				selector =scanner.nextInt();
			
			}catch(InputMismatchException e) {
				exceptionPrintList.InputMistmatchPrint();
				continue;
			}finally {
				scanner.nextLine();
			}
			
			
			if(selector == 1) {
				memberController.insertMember(1);
				
			}else if(selector == 2){
				memberController.selectAll();
				
			}else if (selector == 3) {
				memberController.updateMember();
				
			}else if (selector == 4) {
				// 4번일 경우 멤버를 삭제하는 메소드 출력
				memberController.deleteMember();
				
			}else if (selector == 5) {
				// 5번일 경우 해당 while문을 빠져나간다.
				// 반복문을 빠져 나가고 메인 블록을 닫는 중괄호로 가게 되면 출력되는 출력문
				return ;
			}else {
				try {
					throw new WrongNumberException();
				}catch(WrongNumberException e) {
					e.print();
				}
 			}
		}
	}

}
