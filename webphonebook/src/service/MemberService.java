package service;

import java.util.ArrayList;
import java.util.InputMismatchException;

import controller.MemberController;
import dao.MemberDAO;
import vo.MemberVO;
import myexception.ExceptionPrintList;
import myexception.NameInputException;
import myexception.NoSuchMemberException;
import myexception.WrongGroupException;
import myexception.WrongNumberException;
/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberService.java
 * @package : 	service
 * @description : 컨트롤러과 DAO를 연결해주는 서비스 클래스
 */
public class MemberService {
//	컨트롤러를 가져온다.
	MemberController controller = new MemberController();
// 전화번호 판별, 기타 예외 출력문들을 담당하는 ExceptionPrintList를 가져온다..
	ExceptionPrintList exceptionPrintList = new ExceptionPrintList();
	MemberDAO memberDAO= new MemberDAO();
//	전역변수로 DAO class와 Vo class를 제네릭으로 받는 어레이리스트를 선언한다.
	ArrayList<MemberVO> memberList= new ArrayList<MemberVO>();

//	1. 추가, 3. 수정 시에 출력되는 메소드
//	첫번째 파라미터 number가 1이 들어오면 추가 2가 들어오면 수정을 하게하는 출력문이 출력된다.(view 영역에서).
//	두번째 파라미터 member는 추가시엔 null이 들어오고, 수정시에는 이미 값이 있는 객체를 불러온다.
	public void processInsertMember(int number, MemberVO member){
//		만약 회원추가라면 새로운 object를 생성.
		if(number==1) {
			member = new MemberVO();
		}
//		number가 1일 시에는 (추가) 2일시에는 (수정)이라는 출력문이 뜬다.
		controller.memberView.printInsertMemberIntro(number);
		String name = null;
		
//		sql row의update의 값을 저장하는 rowcnt
		int rowcnt=0;
		
		// 이름을 입력하지 않았을 경우에는 다음과 같은 while문을 반복하게 한다.
		while(true) {
			name= controller.scanner.nextLine();
//			만약 이름이 널이라면
			if(nameNullChecker(name)){
				continue;
			}else {
//				그렇지 않다면 이 반복문을 탈출한다.
				break;
			}
			
		}
		// 입력된 전화번호를 받아주는 String 타입의 변수 생성 및 null로 초기화
		controller.memberView.printInsertMemberNumber();
		
		String phoneNumber=null;

		// break를 만나기 전까지는 무한루프를 도는 while문
		while(true) {
			// 전화번호를 키보드롤 통해 받는다.
			phoneNumber= controller.scanner.nextLine();
//			받은 번호는 예외처리 과정을 거친다.
			if(phonNumberChecker(phoneNumber, member)) {
				continue;
			}else {
//				만약 중복,입력값이 전부 예외에 걸리지 않았다면 반복문을 빠져나간다.
				break;
			}
		
		}
		// 주소를 입력받는 String type의 변수 생성 후 키보드로 입력받는 값을 대입
		controller.memberView.printInsertMemberAddress();
		
		String address= controller.scanner.nextLine();

		// 주소를 기입하지 않았을 시에 주소없음을 대입힌다.
		if(address.equals("")) {
			address="(주소없음)";
		}

		// 그룹을 입력받는 String type의 변수 생성 후 null값으로 초기화
		String groupName=null;
		
		controller.memberView.printInsertMemberGroup();

		// 항상 참인 조건문(무한루프)
		while(true) {
		// 키보드를 통해 받은 String을 그룹에 대입
			groupName= controller.scanner.nextLine();
			if(groupNameChecker(groupName)) {
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
//		저장된 정보를 1번(추가) insert로 2번(수정)메소드로 보낸다.
		if(number==1) {
			rowcnt= insertMember(member);
		}else if(number==2) {
			rowcnt= updateMember(member);
		}
		controller.memberView.printUpdateMember(rowcnt);
//		
	}
	
//	2. 전체 목록 보기를 선택할 시에 출력되는 출력문
	public void selectAllProcess() {
		
//		전체를 셀렉트한다. controller->service->dao
		memberList=selectAll();
		int memberSize= memberList.size();
//		받은 멤베리스트를 출력하게한다.
		controller.memberView.printMemberCount(memberSize);
		for(int i =0;i<memberSize;i++) {
			controller.memberView.printSelect(memberList.get(i),i+1);
		}
	}
	
//	단순히 이름을 리턴하는 메소드
//	파라미터가 1이면 (수정) 2이면 (삭제)를 출력하게 한다.
//	이름이 null이라면 예외를 던진다.
	public String nameFinder(int number) {
		
		controller.memberView.nameFinderPrint(number);
		String name= null;
		while(true) {
			name= controller.scanner.nextLine();
//			만약 이름이 널이라면
			if(nameNullChecker(name)){
				continue;
			}else {
//				그렇지 않다면 이 반복문을 탈출한다.
				break;
			}
			
		}
//		이름을 리턴한다.
		return name;
	}
	
//	멤버 삭제 및 수정시에 맨처음에 호출되는 메소드이다. 
	public void updateOrDelete(int updateOrDelete) {
		String name = null;
//		updateOrDelete가 1이면 수정, updateOrDelete가 2이면 삭제라고 출력하게 한다.
		if(updateOrDelete==1) {
			name=nameFinder(1);
		}else if(updateOrDelete==2) {
			
			name=nameFinder(2);
		}
		
//		서비스에 이름을 대입하여 dao가 리스트를 뽑아내게 하여 받아온다.
		memberList = selectByName(name);
//		멤버리스트의 사이즈를 구한다.
		int memberSize= memberList.size();
//		만약 사이즈가 0이면 찾는 이름이 없다는 출력문과 함께 메소드를 끝낸다.
		if(memberSize==0) {
			return;
		}
// 		총 몇명이 저장되어 있는지 나타내는출력문 
		controller.memberView.printMemberCount(memberSize);
//		멤버들을 하나하나 출력한다.(view는 Object로 받는다.)
		for(int i =0;i<memberSize;i++) {
			controller.memberView.printSelect(memberList.get(i),i+1);
		}
		
		int index = 0;
		int rowcnt=0;
		while(true) {
//			1번이면 수정 2번이면 삭제를 출력하는 view의 메소드 실행
			if(updateOrDelete==1) {
				controller.memberView.printSelectNumberToUpdate(1);
			}else if(updateOrDelete==2) {
				controller.memberView.printSelectNumberToUpdate(2);
			}
//			스캐너로 번호를 입력을 받아 예외처리를한다. 번호인지 아닌지 구별하는 트라이-캐치
			try {
				index = controller.scanner.nextInt();
			}catch(InputMismatchException e) { 
				exceptionPrintList.NumberNotInIndexBoundPrint(memberSize);
				continue;
			}finally {
				controller.scanner.nextLine();
			}
			
//			그리고 받은 번호가 올바른 범위의 번호인지 확인하는 메소드이다.
			try {
//				1번이면 수정을 하는 controller의 메소드 영역으로 간다.
				if(updateOrDelete==1) {
//					수정시에는 수정을 진행한 후 이 메소드를 탈출한다.
					processInsertMember(2,memberList.get(index-1));
					return;
				}else if(updateOrDelete==2) {
//					2번이면 삭제를 하는 service메소드로 간다.
					rowcnt = deleteMember(memberList.get(index-1).getMemberNum());
					break;
				}
//				만약 올바른 범위가 아니라면 while문의 최상단으로 올라간다.
			}catch(IndexOutOfBoundsException e) {
				exceptionPrintList.NumberNotInIndexBoundPrint(memberSize);
				continue;
			}
		}
//		삭제시에 로우카운트를 뷰에게 전달해 올바르게 처리가 되었는지 확인한다.
		controller.memberView.printUpdateMember(rowcnt); 
		
	}

	
//	멤버를 추가하는 메소드이다. VO를 전달받아 그 값을 DAO에게 넘겨준다.
	public int insertMember(MemberVO member) {
		
//		Service는 rowcnt를 전달받아 controller에게 전달
		int rowcnt= memberDAO.insertMember(member);
		
		return rowcnt;
	}
//	 이름을 전달받아 DAO에게 전달하는 메소드이다.
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
	
//	핸드폰 번호를 전달받아 dao에게 전달하는 메소드. 
//	이 메소드는 dao에게 해당 핸드폰 번호를 가진 멤버의 회원번호를 전달받는다.
//	전달받은 회원번호를 예외처리를 진행할때에 사용하는 메소드이다.
	public int selectByPhoneNumber(String phoneNumber) {
		int member_num = memberDAO.selectByPhoneNumber(phoneNumber); 
		return member_num;
	}
	
//	dao에게 어레이리스트를 받아 그 값을 전달하는 메소드
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
	
//	dao에게 멤버 삭제를 요청하고 그 결과값으로 rowcnt를 받는 메소드. 해당 rowcnt는 리턴된다.
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
//	메인 클래스가 호출하는 메소드
	public void start(){
		int selector =0;
		
		while(true) {
			controller.memberView.viewIntro();
			
			try {
				selector =controller.scanner.nextInt();
			
			}catch(InputMismatchException e) {
				exceptionPrintList.InputMistmatchPrint();
				continue;
			}finally {
				controller.scanner.nextLine();
			}
			
			
			if(selector == 1) {
//				1번일 경우 멤버를 추가하는 메소드를 호출한다. MemberVO의 값으로 null을 준다.
				controller.processInsertMember(1, null);
				
			}else if(selector == 2){
//				2번일 경우 전체 멤버를 데리고 온다.
				controller.selectAll();
				
			}else if (selector == 3) {
//				3번일 경우 수정하는 메소드를 호출한다.
				controller.updateOrDelete(1);
				
			}else if (selector == 4) {
				// 4번일 경우 멤버를 삭제하는 메소드 호출
				controller.updateOrDelete(2);
				
			}else if (selector == 5) {
//				5번일 경우 종료한다.
				controller.memberView.goodBye();
				controller.scanner.close();
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

