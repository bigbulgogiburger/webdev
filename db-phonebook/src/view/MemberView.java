package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.MemberDAO;
import vo.MemberVO;



public class MemberView {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static int viewIntro() {
		while(true) {

			// 도입 문구 출력
			int i =0;
			System.out.println("=====================");
			System.out.println("다음 메뉴 중 하나를 선택하세요");
			System.out.println("=====================");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 목록 보기");
			System.out.println("3. 회원정보 수정하기");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 종료");

			// 입력된 타입이 integer type이 아닐 때에 발생하는 예외를 처리하는 try-catch문
			try {
			i = scanner.nextInt();//scan에서 받아들인 String을 int로 변환하는때에 예외발생.
			}catch(InputMismatchException e) {//예외가 발생한다면 문자열을 출력하고 while문의 조건으로 돌아간다.
			System.out.println("숫자를 입력하세요");
			// 이때 scan의 값을 비워내준다. nextInt는 따로 엔터를 처리하거나 문자열을 처리하지 않는다.
			// 만약 String과 \n이 들어왔다면 계속 IM exception을 출력하기 때문에 비워준다.
			scanner.nextLine();
			continue;
			}
			scanner.nextLine();
			// 입력된 타입이 integer type이라면 입력받은 번호의 조건을 따져보게 된다.
			// 1~5의 범위 밖이라면 다시 while문으로 돌아간다.
			if(i == 1) {
			// 1번일 경우 멤버를 추가하는 메소드를 실행
				return 1;
			}else if(i == 2){
			// 2번일 경우 멤버의 목록을 출력하는 메소드 출력
				return 2;
			}else if (i == 3) {
			// 3번일 경우 멤버를 수정하는 메소드 출력
				return 3;
			}else if (i == 4) {
			// 4번일 경우 멤버를 삭제하는 메소드 출력
				return 4;
			}else if (i == 5) {
			// 5번일 경우 해당 while문을 빠져나간다.
				// 반복문을 빠져 나가고 메인 블록을 닫는 중괄호로 가게 되면 출력되는 출력문
				System.out.println("프로그램이 정상적으로 종료됩니다.");
				
				return 5;
			}else {
				// 1~5사이의 값을 입력하지 않았을 시에 출력되는 문구
				System.out.println("올바른 번호가 아닙니다. 1~5 사이의 숫자를 입력하세요");
			}
		}
	}

	

	public static MemberVO printInsertMember(int number) {
		if(number==1) {
			System.out.print("추가");
		}else {
			System.out.print("수정");
		}
		// 메소드의 첫 출력문
		System.out.println("할 회원의 정보를 입력하세요.");
		System.out.println("이름 :");

		// 입력된 이름을 받아주는 String 타입의 변수 생성
		String name = null;

		// 이름을 입력하지 않았을 경우에는 다음과 같은 while문을 반복하게 한다.
		while(true) {

			name = scanner.nextLine();

			if(name.equals("")){
				System.out.println("이름을 입력하지 않으셨습니다. 다시입력하세요");
				continue;
			}else {
				break;
			}
		}

		System.out.println("전화번호(ex: 01087564576): ");
		// 입력된 전화번호를 받아주는 String 타입의 변수 생성 및 null로 초기화
		String phoneNumber=null;

		// break를 만나기 전까지는 무한루프를 도는 while문
		while(true) {
		// 전화번호를 키보드롤 통해 받는다.
			phoneNumber= scanner.nextLine();

		// 3가지 조건 중 1개라도 만족하면 메소드 내의 문장을 출력하고, 다시 while문의 조건으로 돌아간다.
		// 1. 길이가 11이 아니거나, 0으로 시작하지 않거나, 특수문자 '-'를 포함할 것. 2.입력된 문자열의 원소 중에 하나라도 0~9 사이가 아닐것 . 3. 이미 있는 번호일 것.
			if(isNotCorrectNumber(phoneNumber)||isNotNumber(phoneNumber)||isAlreadyStored(phoneNumber)) {
				continue;
			}else {
				break;
			}
		}
		System.out.println("주소 :");
		// 주소를 입력받는 String type의 변수 생성 후 키보드로 입력받는 값을 대입
		String address= scanner.nextLine();

		// 주소를 기입하지 않았을 시에 주소없음을 대입힌다.
		if(address.equals("")) {
			address="(주소없음)";
		}

		// 그룹을 입력받는 String type의 변수 생성 후 null값으로 초기화
		String group=null;
		System.out.println("종류(ex. 가족, 친구, 기타) :");

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
				System.out.println("종류는 '가족'이나 '친구' 또는 '기타'만 가능합니다");
			}
		}

		// 클래스 변수로 선언된 hashMap타입의 info에 휴대폰번호(key)와 멤버를 new해서 object(value)로 넣어준다.
		return new MemberVO(name,phoneNumber,address,group);
	}
		
	



public static boolean isNotNumber(String phoneNumber) {
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
	public static boolean isAlreadyStored(String phoneNumber) {
		 ArrayList<MemberVO> memList = MemberDAO.selectByPhoneNumber(phoneNumber);
		 if(memList.size()>0) {
			 System.out.println("이미 저장되어 있는 번호입니다. 다시 입력해주세요");
			 return true;
		 }else {
			 return false;
		 }

	
	}

	// 번호의 길이가 11이 아닌지, 특수문자 '-'를 포함하고 있는지 0으로 시작하지 않는지 판단하는 메소드
	// 만약 어느 하나라도 true라면 출력문과 함께 true를 리턴한다.
	public static boolean isNotCorrectNumber(String phoneNumber) {
		char[] phoneNumberCharArr = phoneNumber.toCharArray();
		if(phoneNumber.length()!=11||phoneNumber.contains("-")||phoneNumberCharArr[0]!='0') {
			System.out.println("올바른 번호가 아닙니다.\n 전화번호는 '-'를 제외한 숫자 11자리와 0으로 시작하게 입력해주세요");
			return true;
		}else {
			return false;
		}
	}

	
	public static String nameFinder(int number) {
		
		if(number==1) {
			System.out.print("수정");
		}else {
			System.out.print("삭제");
		}
		System.out.println("할 회원의 정보를 입력하세요.");
		System.out.println("이름 : ");
		String name= scanner.nextLine();
		return name;
	}
	// 3번(회원정보 수정하기),4번(회원 삭제)를 선택할 시에 하단부를 출력하는 메소드.
	// 숫자를 입력받아 3,4번 입력시에 해당하는 메소드에 정수값을 넘겨준다.
	public static MemberVO SelectNumberToUpdate(ArrayList<MemberVO> memberList,int number) {
		while(true) {
			if(number==1) {
				System.out.print("수정");
			}else {
				System.out.print("삭제");
			}
			System.out.println("할 회원의 번호를 입력하세요");
			int index =0;
			try {
			index = scanner.nextInt();
			scanner.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("올바른 숫자를 입력하세요");
				// nextInt는 따로 엔터를 처리하거나 문자열을 처리하지 않는다.
				// 만약 String과 \n이 들어왔다면 계속 IM exception을 출력하기 때문에 비워준다.
				scanner.nextLine();
				continue;
			
			}

			try {
				return memberList.get(index-1);
			}catch(IndexOutOfBoundsException e) {
				System.out.println("범위 안의 숫자를 입력하세요"+"1~"+memberList.size());
				continue;
			}
			
			
			
			
		}
	}
	
	
	public static void printUpdateMember(int rowcnt) {
		if(rowcnt>0) {
			System.out.println("정상적으로 처리되었습니다.");
		}else {
			System.out.println("정상적으로 처리되지 않았습니다.");
		}
	}


	public static void printSelect(ArrayList<MemberVO> memberList) {
		System.out.println("총 "+memberList.size()+"의 회원이 저장되어 있습니다.");
		
		int i=1;
		for(MemberVO member : memberList) {
			System.out.println(i+". "+member.toString());
			i++;
		}
		
	}
}
	

