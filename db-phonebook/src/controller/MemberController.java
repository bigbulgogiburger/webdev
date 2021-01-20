package controller;

import java.util.ArrayList;

import dao.MemberDAO;
import service.MemberService;
import view.MemberView;
import vo.MemberVO;

public class MemberController {
	
	//controller

	
	public static void insertMember() {
		MemberVO member = new MemberVO();
		
		member=MemberView.printInsertMember();
		
		MemberService.insertMember(member);
	}
	public static void selectAll() {
		MemberService.selectAll();
	}
//	멤버 삭제 
	public static void deleteMember() {
		ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
		
		memberList = MemberService.selectByName(2);
		MemberView.printSelect(memberList);
		MemberVO member = MemberView.SelectNumberToUpdate(memberList,2);
		MemberService.deleteMember(member);
		
	}
	public static void updateMember() {
		ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();
		
		memberList = MemberService.selectByName(1);
		MemberView.printSelect(memberList);
		MemberVO member = MemberView.SelectNumberToUpdate(memberList,1);
		MemberService.deleteMember(member);
		insertMember();
	}

	public static void main(String[] args) {
		
		while(true) {
			
			int selector = MemberView.viewIntro();
			if(selector == 1) {
				insertMember();
			}else if(selector == 2){
				selectAll();
			}else if (selector == 3) {
				updateMember();
			}else if (selector == 4) {
				// 4번일 경우 멤버를 삭제하는 메소드 출력
				deleteMember();
			}else if (selector == 5) {
				// 5번일 경우 해당 while문을 빠져나간다.
				// 반복문을 빠져 나가고 메인 블록을 닫는 중괄호로 가게 되면 출력되는 출력문
				System.out.println("프로그램이 정상적으로 종료됩니다.");
				return ;
			}
		}
	}

}
