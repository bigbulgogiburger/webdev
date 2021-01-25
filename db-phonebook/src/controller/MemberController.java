package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myexception.ExceptionPrintList;
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
	
	Scanner scanner = new Scanner(System.in);
	
//	1. 추가, 3. 수정 시에 출력되는 메소드
//	첫번째 파라미터 number가 1이 들어오면 추가 2가 들어오면 수정을 하게하는 출력문이 출력된다.(view 영역에서).
//	두번째 파라미터 member는 추가시엔 new Member가 들어오고, 수정시에는 이미 값이 있는 객체를 불러온다.
	public void processInsertMember(int number, MemberVO member){
		
		if(number==1) {
			member = new MemberVO();
		}
//		number가 1일 시에는 (추가) 2일시에는 (수정)이라는 출력문이 뜬다.
		memberView.printInsertMemberIntro(number);
		String name = null;
		int rowcnt=0;
		
		// 이름을 입력하지 않았을 경우에는 다음과 같은 while문을 반복하게 한다.
		while(true) {
			name= scanner.nextLine();
//			만약 이름이 널이라면
			if(memberService.nameNullChecker(name)){
				continue;
			}else {
//				그렇지 않다면 이 반복문을 탈출한다.
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
			if(memberService.phonNumberChecker(phoneNumber, member)) {
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
		String groupName=null;
		
		memberView.printInsertMemberGroup();

		// 항상 참인 조건문(무한루프)
		while(true) {
		// 키보드를 통해 받은 String을 그룹에 대입
			groupName= scanner.nextLine();
			if(memberService.groupNameChecker(groupName)) {
				break;
			}else{
				continue;
			}
		}
		
//		예외처리가 된 개인정보를 오브젝트에 담는다.
		member.setGroup(groupName);
		member.setName(name);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);
		
		if(number==1) {
			rowcnt= memberService.insertMember(member);
		}else if(number==2) {
			rowcnt= memberService.updateMember(member);
		}
		memberView.printUpdateMember(rowcnt);
//		MemberVO object를 리턴한다
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
			if(memberService.nameNullChecker(name)){
				continue;
			}else {
//				그렇지 않다면 이 반복문을 탈출한다.
				break;
			}
			
		}
//		이름을 리턴한다.
		return name;
	}
	
		
//	2. 전체 목록 보기를 선택할 시에 출력되는 출력문
	public void selectAll() {
		
//		전체를 셀렉트한다. controller->service->dao
		memberList=memberService.selectAll();
		int memberSize= memberList.size();
//		받은 멤베리스트를 출력하게한다.
		memberView.printMemberCount(memberSize);
		for(int i =0;i<memberSize;i++) {
			memberView.printSelect(memberList.get(i),i+1);
		}
	}
//	멤버 삭제 
	public void updateOrDelete(int updateOrDelete) {
//		int로 2의 값을 주어 (삭제)에 대한 이름을 리턴하는 메소드를 불러온다(controller)
		
		String name = null;
		if(updateOrDelete==1) {
			name=nameFinder(1);
		}else if(updateOrDelete==2) {
			
			name=nameFinder(2);
		}
		
//		서비스에 이름을 대입하여 dao가 리스트를 뽑아내게 하여 받아온다.
		memberList = memberService.selectByName(name);
		int memberSize= memberList.size();
		if(memberSize==0) {
			return;
		}

		memberView.printMemberCount(memberSize);
		for(int i =0;i<memberSize;i++) {
			memberView.printSelect(memberList.get(i),i+1);
		}
		
		int index = 0;
		int rowcnt=0;
		while(true) {
			if(updateOrDelete==1) {
				memberView.printSelectNumberToUpdate(1);
			}else if(updateOrDelete==2) {
				memberView.printSelectNumberToUpdate(2);
			}
			
			try {
				index = scanner.nextInt();
			}catch(InputMismatchException e) {
				exceptionPrintList.NumberNotInIndexBoundPrint(memberSize);
				continue;
			}finally {
				scanner.nextLine();
			}
			try {
				if(updateOrDelete==1) {
					processInsertMember(2,memberList.get(index-1));
					return;
				}else if(updateOrDelete==2) {
					rowcnt = memberService.deleteMember(memberList.get(index-1).getMemberNum());
					break;
				}
			}catch(IndexOutOfBoundsException e) {
				exceptionPrintList.NumberNotInIndexBoundPrint(memberSize);
				continue;
			}
		}
		
		memberView.printUpdateMember(rowcnt); 
		
	}
	

	public void start(){
		MemberController memberController = new MemberController();
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
				memberController.processInsertMember(1, null);
				
			}else if(selector == 2){
				memberController.selectAll();
				
			}else if (selector == 3) {
				memberController.updateOrDelete(1);
				
			}else if (selector == 4) {
				// 4번일 경우 멤버를 삭제하는 메소드 출력
				memberController.updateOrDelete(2);
				
			}else if (selector == 5) {
				memberView.goodBye();
				scanner.close();
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
