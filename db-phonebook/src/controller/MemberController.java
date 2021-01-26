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
 * @description : 프로그램을 뷰와 서비스 사이에서 제어하는 클래스, 직접처리를 담당하지 않는다.
 */


public class MemberController {

	MemberVO member = new MemberVO();
	MemberService memberService = null;
	public MemberView memberView = new MemberView();
	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	ExceptionPrintList exceptionPrintList = new ExceptionPrintList();
	
	public Scanner scanner = new Scanner(System.in);
	
//	1. 추가, 3. 수정 시에 출력되는 메소드
//	첫번째 파라미터 number가 1이 들어오면 추가 2가 들어오면 수정을 하게하는 출력문이 출력된다.(view 영역에서).
//	두번째 파라미터 member는 추가시엔 null이 들어오고, 수정시에는 이미 값이 있는 객체를 불러온다.
	public void processInsertMember(int number, MemberVO member){
		memberService = new  MemberService();
		memberService.processInsertMember(number, member);
//		
	}
		
//	2. 전체 목록 보기를 선택할 시에 출력되는 출력문
	public void selectAll() {
		memberService = new  MemberService();
		memberService.selectAllProcess();
		
	}
//	멤버 삭제 및 수정시에 맨처음에 호출되는 메소드이다. 
	public void updateOrDelete(int updateOrDelete) {
		memberService = new  MemberService();
		memberService.updateOrDelete(updateOrDelete);
//		
	}

//	메인 클래스가 호출하는 메소드
	public void start(){
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
//				1번일 경우 멤버를 추가하는 메소드를 호출한다. MemberVO의 값으로 null을 준다.
				processInsertMember(1, null);
				
			}else if(selector == 2){
//				2번일 경우 전체 멤버를 데리고 온다.
				selectAll();
				
			}else if (selector == 3) {
//				3번일 경우 수정하는 메소드를 호출한다.
				updateOrDelete(1);
				
			}else if (selector == 4) {
				// 4번일 경우 멤버를 삭제하는 메소드 호출
				updateOrDelete(2);
				
			}else if (selector == 5) {
//				5번일 경우 종료한다.
				memberView.goodBye();
				scanner.close();
				return ;
			}else {
//				만약 올바른 범위가 아니면 예외를 던지고 출력문을 출력한다.
				try {
					throw new WrongNumberException();
					
				}catch(WrongNumberException e) {
					e.print();
				}
 			}
		}
	}

}
