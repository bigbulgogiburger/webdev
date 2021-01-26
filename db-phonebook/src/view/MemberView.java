package view;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberView.java
 * @package : 	view
 * @description :출력을 담당하는 클래스
 */
public class MemberView {
	
	
	public void viewIntro() {

			// 도입 문구 출력
			System.out.println("=====================");
			System.out.println("다음 메뉴 중 하나를 선택하세요");
			System.out.println("=====================");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 목록 보기");
			System.out.println("3. 회원정보 수정하기");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 종료");
	}
	
	
//	멤버를 추가/수정시에 호출되는 메소드
//	이름을 입력하라는 출력물까지만 출력
	public void printInsertMemberIntro(int number) {
//		1번이면 추가 2번이면 수정을 출력
		if(number==1) {
			System.out.print("추가");
		}else {
			System.out.print("수정");
		}
		// 메소드의 첫 출력문
		System.out.println("할 회원의 정보를 입력하세요.");
		System.out.println("이름 :");
	}

//	전화번호를 입력하라는 출력문
	public void printInsertMemberNumber() {
		System.out.println("전화번호(ex: 01087564576): ");
	}
//	주소를 입력하라는 출력문
	public void printInsertMemberAddress() {
		
		System.out.println("주소 :");
	}
// 멤버를 입력하라는 출력문
	public void printInsertMemberGroup() {
		System.out.println("종류(ex. 가족, 친구, 기타) :");
	}
	
//	수정/삭제시 이름을 입력받을 때 출력하는 메소드 1번이면 수정 2번이면 삭제이다.
	public void nameFinderPrint(int number) {
		
		if(number==1) {
			System.out.print("수정");
		}else {
			System.out.print("삭제");
		}
		System.out.println("할 회원의 정보를 입력하세요.");
		System.out.println("이름 : ");
	}
//	수정 삭제시에 번호를 입력하게 하는 출력물을 출력하는 메소드
	public void printSelectNumberToUpdate(int number) {
		
			if(number==1) {
				System.out.print("수정");
			}else {
				System.out.print("삭제");
			}
			System.out.println("할 회원의 번호를 입력하세요");
		}
	
	
//	sql문의 처리가 제대로 되었는지 안되었는지에 대해 rowcnt를 받고 결과를 출력하는 메소드
	public void printUpdateMember(int rowcnt) {
		if(rowcnt>0) {
			System.out.println("정상적으로 처리되었습니다.");
		}else {
			System.out.println("정상적으로 처리되지 않았습니다.");
		}
	}
//	총 몇명이 저장되어있는지 출력하는 메소드
	public void printMemberCount(int size) {
		System.out.println("총 "+size+"명의 회원이 저장되어 있습니다.");
	}
//	몇명의 회원이 저장되어 있는지, 2번 전체목록보기와 3번 수정, 4번 삭제를 할때에 출력하는 메소드이다.
//	object로 받아 객체의 정보를 최대한 주지 않는다. toString은 오버라이드 되어 있다.
	public void printSelect(Object obj,int index) {
		System.out.println(index+". "+obj.toString());
	}
	public void goodBye() {
		System.out.println("프로그램이 종료되었습니다.");
	}

	


	
	
}
	

