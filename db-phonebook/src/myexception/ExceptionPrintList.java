package myexception;

import java.util.ArrayList;

import dao.MemberDAO;
import vo.MemberVO;

public class ExceptionPrintList {
	
//	조건문에서 걸러지면 호출되는 메소드로 이루어진 클래스
	
	
	public void NumberNotInIndexBoundPrint(ArrayList<MemberVO> memberList) {
		System.out.println("올바른 숫자를 입력하세요"+"\n"+"범위 : 1~"+memberList.size());
	}
	
	public void InputMistmatchPrint() {
		System.out.println("문자가 아닌 숫자를 입력하세요");
	}
	
	
	public boolean isNotNumber(String phoneNumber) {
		// String타입의 phoneNumber를 이용하여 반복 가능한 char배열 type을 생성한다.
		char[] numberCharArray =phoneNumber.toCharArray();

		// 향상된 for문을 사용하여 char[]의 원소를 하나하나 검토한다.
		for(char x : numberCharArray) {

			// 만약 비교하는 문자가 유니코드 상의 '0'번 부터 '9'번까지의 값과 같지 않다면 true를 리턴한다.
			if(!(x>='0'&&x<='9')) {
				System.out.println("올바른 번호가 아닙니다. \n 문자를 제외한 숫자만 입력하세요.");
				return true;
			}
		}
			// 만약 이 for문을 빠져나왔다면 입력 받은 모든 문자가 유니코드 상의 '0'번 부터 '9'번까지의 값과 같기 때문에
			// false를 return 한다.
				return false;
	}

	// 이미 저장되어 있는 번호인지 알아보는 메소드
		public boolean isAlreadyStored(String phoneNumber) {
			MemberDAO memberDAO= new MemberDAO();
			 ArrayList<MemberVO> memList = memberDAO.selectByPhoneNumber(phoneNumber);
			 if(memList.size()>0) {
				 System.out.println("이미 저장되어 있는 번호입니다. 다시 입력해주세요");
				 return true;
			 }else {
				 return false;
			 }
		}

		// 번호의 길이가 11이 아닌지, 특수문자 '-'를 포함하고 있는지 0으로 시작하지 않는지 판단하는 메소드
		// 만약 어느 하나라도 true라면 출력문과 함께 true를 리턴한다.
		public boolean isNotCorrectNumber(String phoneNumber) {
			char[] phoneNumberCharArr = phoneNumber.toCharArray();
			if(phoneNumber.length()!=11||phoneNumber.contains("-")||phoneNumberCharArr[0]!='0') {
				System.out.println("올바른 번호가 아닙니다.\n 전화번호는 '-'를 제외한 숫자 11자리와 0으로 시작하게 입력해주세요");
				return true;
			}else {
				return false;
			}
		}
}
