package main;

import java.util.InputMismatchException;


import lib.PhoneBookProcess;


/**
 * @작성자 :   편도훈
 * @작성일 : 2020. 12. 17.
 * @filename : PhoneProgram.java
 * @package : main
 * @description : 메인 메소드. 실행 담당
 */
public class PhoneProgram {

public static void main(String[] args) {

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
i = PhoneBookProcess.getScanner().nextInt();//scan에서 받아들인 String을 int로 변환하는때에 예외발생.
}catch(InputMismatchException e) {//예외가 발생한다면 문자열을 출력하고 while문의 조건으로 돌아간다.
System.out.println("숫자를 입력하세요");
// 이때 scan의 값을 비워내준다. nextInt는 따로 엔터를 처리하거나 문자열을 처리하지 않는다.
// 만약 String과 \n이 들어왔다면 계속 IM exception을 출력하기 때문에 비워준다.
PhoneBookProcess.getScanner().nextLine();
continue;
}
// 입력된 타입이 integer type이라면 입력받은 번호의 조건을 따져보게 된다.
// 1~5의 범위 밖이라면 다시 while문으로 돌아간다.
if(i == 1) {
// 1번일 경우 멤버를 추가하는 메소드를 실행
PhoneBookProcess.addMember();
}else if(i == 2){
// 2번일 경우 멤버의 목록을 출력하는 메소드 출력
PhoneBookProcess.printMember();
}else if (i == 3) {
// 3번일 경우 멤버를 수정하는 메소드 출력
PhoneBookProcess.modifyMember();
}else if (i == 4) {
// 4번일 경우 멤버를 삭제하는 메소드 출력
PhoneBookProcess.removeMember();
}else if (i == 5) {
// 5번일 경우 해당 while문을 빠져나간다.
break;
}else {
// 1~5사이의 값을 입력하지 않았을 시에 출력되는 문구
System.out.println("올바른 번호가 아닙니다. 1~5 사이의 숫자를 입력하세요");
}
}
// 반복문을 빠져 나가고 메인 블록을 닫는 중괄호로 가게 되면 출력되는 출력문
System.out.println("프로그램이 정상적으로 종료됩니다.");
}

}