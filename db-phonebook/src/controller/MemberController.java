package controller;

import java.util.ArrayList;
import java.util.Scanner;

import service.MemberService;
import view.MemberView;
import vo.MemberVO;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberController.java
 * @package : 	controller
 * @description : 프로그램을 제어하는 클래스
 */


public class MemberController {
	Scanner scanner = new Scanner(System.in);
	
	//controller
	MemberVO member = new MemberVO();
	MemberService memberService = new MemberService();
	MemberView memberView = new MemberView();
	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	
	public void insertMember(int number) {
		member = new MemberVO();
		memberService = new MemberService();
		memberView = new MemberView();
		
		member=memberView.printInsertMember(number);
		
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
		
		memberList = memberService.selectByName(2);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			return;
		}
		
		MemberVO member = memberView.SelectNumberToUpdate(memberList,2);
		
		memberService.deleteMember(member);
		
	}
	public void updateMember() {
		
		memberList= new ArrayList<MemberVO>();
		memberService = new MemberService();
		memberView = new MemberView();
		
		memberList = memberService.selectByName(1);
		
		memberView.printSelect(memberList);
		
		if(memberList.size()==0) {
			return;
		}
		
		MemberVO member = memberView.SelectNumberToUpdate(memberList,1);
		
		memberService.deleteMember(member);
		
		insertMember(2);
	}

	public static void main(String[] args) {
		
		MemberController memberController = new MemberController();
		MemberView memberView = new MemberView();
		while(true) {
			
			int selector = memberView.viewIntro();
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
			}
		}
	}

}
