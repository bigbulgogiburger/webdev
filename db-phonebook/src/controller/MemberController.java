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
	
	
	public MemberVO processInsertMember(int number){
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

		memberView.printInsertMemberNumber();
		// 입력된 전화번호를 받아주는 String 타입의 변수 생성 및 null로 초기화
		String phoneNumber=null;

		// break를 만나기 전까지는 무한루프를 도는 while문
		while(true) {
		// 전화번호를 키보드롤 통해 받는다.
			phoneNumber= scanner.nextLine();

		// 3가지 조건 중 1개라도 만족하면 메소드 내의 문장을 출력하고, 다시 while문의 조건으로 돌아간다.
		// 1. 길이가 11이 아니거나, 0으로 시작하지 않거나, 특수문자 '-'를 포함할 것. 2.입력된 문자열의 원소 중에 하나라도 0~9 사이가 아닐것 . 3. 이미 있는 번호일 것.
			if(exceptionPrintList.isNotCorrectNumber(phoneNumber)
					||exceptionPrintList.isNotNumber(phoneNumber)
					||exceptionPrintList.isAlreadyStored(phoneNumber)) {
				continue;
			}else {
				break;
			}
		}
		memberView.printInsertMemberAddress();
		// 주소를 입력받는 String type의 변수 생성 후 키보드로 입력받는 값을 대입
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
		// 조건을 만족하지 못하면 출력문을 입력한 후 다시 while로 들어간다.
				try {
					throw new WrongGroupException();
				}catch(WrongGroupException e) {
					e.print();
					continue;
				}

			}
		}

		// 클래스 변수로 선언된 hashMap타입의 info에 휴대폰번호(key)와 멤버를 new해서 object(value)로 넣어준다.
		return new MemberVO(name,phoneNumber,address,group);
	}
	
//	다시보기
	public String nameFinder(int number) {
		
		memberView.nameFinderPrint(number);
		String name= null;
		while(true) {
			name= scanner.nextLine();
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
		return name;
	}
	// 3번(회원정보 수정하기),4번(회원 삭제)를 선택할 시에 하단부를 출력하는 메소드.
	// 숫자를 입력받아 3,4번 입력시에 해당하는 메소드에 정수값을 넘겨준다.
	public MemberVO SelectNumberToUpdate(ArrayList<MemberVO> memberList,int number) {
		int memberCount = memberList.size();
		while(true) {
			memberView.printSelectNumberToUpdate(number);
			String stringIndex=null;
			int index = 0;
			try {
			stringIndex = scanner.nextLine();
			index= Integer.parseInt(stringIndex);
			}catch(InputMismatchException|NumberFormatException e) {
				exceptionPrintList.NumberNotInIndexBoundPrint(memberCount);
				// nextInt는 따로 엔터를 처리하거나 문자열을 처리하지 않는다.
				// 만약 String과 \n이 들어왔다면 계속 IM exception을 출력하기 때문에 비워준다.
				continue;
			
			}

			try {
				return memberList.get(index-1);
			}catch(IndexOutOfBoundsException e) {
				exceptionPrintList.NumberNotInIndexBoundPrint(memberCount);
				continue;
			}
		}
	}

	

	public void insertMember(int number) {
		member = new MemberVO();
		memberService = new MemberService();
		memberView = new MemberView();
		
		member=processInsertMember(number);
		
		int rowcnt= memberService.insertMember(member);
		
		memberView.printUpdateMember(rowcnt);
	}
	
	public void selectAll() {
		memberService = new MemberService();
		memberView = new MemberView();
		
		memberList=memberService.selectAll();
		
		memberView.printSelect(memberList);
	}
//	멤버 삭제 
	public void deleteMember() {
		memberList= new ArrayList<MemberVO>();
		memberService = new MemberService();
		memberView = new MemberView();
		
		
		String name = nameFinder(2);
		memberList = memberService.selectByName(name);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			memberView.printNoSuchMember();
			return;
		}
		
		MemberVO member = SelectNumberToUpdate(memberList,2);
		
		memberService.deleteMember(member);
		
	}
	public void updateMember() {
		
		memberList= new ArrayList<MemberVO>();
		memberService = new MemberService();
		memberView = new MemberView();
		String name = nameFinder(1);
		memberList = memberService.selectByName(name);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			memberView.printNoSuchMember();
			return;
		}
		
		MemberVO member = SelectNumberToUpdate(memberList,1);
		
		memberService.deleteMember(member);
		
		insertMember(2);
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
