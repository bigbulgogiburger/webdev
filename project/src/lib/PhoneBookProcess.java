package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 * @작성자 :   편도훈
 * @작성일 : 2020. 12. 17.
 * @filename : Memberfunction.java
 * @package : lib
 * @description : 메소드 전체를 담은 클래스
 */
public class PhoneBookProcess {

// 전화번호(key)와 회원정보 클래스(value)를 K,V로 받는 hashMap을 클래스 변수로 생성. 제네릭은 String, Member이다.
private static HashMap<String, Member> infoHashMap = new HashMap<String, Member>();

// 수정, 삭제 시에 Member object의 주소를 저장하는 ArrayList를 클래스 변수로 생성. 제네릭은 Member이다.
// ArrayList를 전역 변수로 선언하고 시작시 clear해주는 것이
// ArrayList를 특정 메소드의 지역변수로 선언하고 다른 메소드에
// 파라미터로 넣는 것 보다 나은지에 대해서 생각을 해보았다.
// 특정 메소드의 지역 변수로 생성하게 되면 메소드 실행시마다 계속 메모리에 공간을 만들기 때문에
// cpu가 처리하는 데에 있어 작게나마라도 더 효율적이지 못할 것 같다.
// 그에 반해 클래스 변수로 private static하게 선언하게 되면 캡슐화도 보장되지만, 효율면에서 더 좋은 것 같다
private static ArrayList<Member> modifierArrayList = new ArrayList<Member>();

// Scanner class는 메소드를 모아놓은 클래스 영역에 한번만 선언하고, main영역에서 import하여 사용하도록 한다.
private static Scanner scanner = new Scanner(System.in);

// 전화번호와 멤버 클래스의 정보를 추가해주는 addMember 메소드
public static void addMember() {

scanner.nextLine();
// 메소드의 첫 출력문
System.out.println("등록할 회원의 정보를 입력하세요.");
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
infoHashMap.put(phoneNumber, new Member(name,phoneNumber,address,group));
}


//멤버 수정, addMember()를 overloading 했다. 파라미터로 사용자가 입력한 St을 받아준다.
public static void addMember(Member member) {
// 수정하러 들어온 멤버를 삭제. 맨처음 작성할 때에는 삭제를 안하려고 했지만 어레이리스트에서 수정을 하는 것 보다,
// 해쉬맵에서 키,밸류를 삭제하고다시 넣는 편이 안전한 것 같다. isAlreadyStored메소드를 만족시키려면
// 수정하려고 하는 번호가 이미 존재해서는 안되지만, 수정하려고 하는 쪽의 키의 값은 이미 존재하는 키이다.
// 만약 이름이나 소속을 바꾸려고 하고, 번호를 바꾸지 않게끔 수정하게 된다면, 어레이리스트 상에 저장된 Member object내의 phoneNumber를 다른 값으로 대입해주거나
// 해쉬맵에서 해당 원소를 삭제하고 다시 넣어주는 방식이 필요한데, 나는 후자를 선택했다. 전자의 경우 key의 phoneNumber가 어떤 String으로 들어오게 될지 모르기 때문에 중복의 위험이 있고,
// 또한, 바뀐 phoneNumber는 hashMap상의 키이기 때문에, 완전성 측면에서 아예 해쉬맵에서 삭제해주는 편이 나을 것 같다.

// 해쉬맵에 저장 된 값을 먼저 삭제해준다.
infoHashMap.remove(member.getPhoneNumber());

// 시작 문구 출력
System.out.println("수정할 회원의 정보를 입력하세요.");
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
// 키보드를 통해 받은 String을 그룹에 대입
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
String address=scanner.nextLine();

// 주소를 기입하지 않았을 시에 주소없음을 대입힌다.
if(address.equals("")) {
address="(주소없음)";
}

// 그룹을 입력받는 String type의 변수 생성 후 null값으로 초기화
String group=null;
System.out.println("종류(ex. 가족, 친구, 기타) :");
while(true) {
group= scanner.nextLine();
// 3가지 조건 중 1개라도 만족하면 while문을 탈출한다.
// 1. 입력된 값이 '가족'일 것, 입력된 값이 '친구'일 것, 입력된 값이 '기타'일 것
if (group.equals("가족")||group.equals("친구")||group.equals("기타")) {
break;
}else {
// 조건에 맞지 않는 String이 등록될 시 출력되는 문장
System.out.println("종류는 '가족'이나 '친구' 또는 '기타'만 가능합니다\n 다시 입력하세요" );
}
}
// 다시 해쉬맵에 phoneNumber를 키로 Member object를 value로 가지는 해쉬맵을 넣어준다.
infoHashMap.put(phoneNumber, new Member(name,phoneNumber,address,group));
}


// 회원 목록 보기 선택시(2번) 호출 되는 메소드. 전체 회원의 목록과 정보를 보여준다.
public static void printMember() {
scanner.nextLine();
// 총 몇명의 사람들이 저장되어 있는지를 hashMap이 소유한 size메소드를 통해 보여준다.
System.out.println("총 "+infoHashMap.size()+"의 회원이 저장되어 있습니다.");

// Set를 사용하여 hashMap의 메소드인 keySet()를 사용해 phoneNumber들을 한곳에 모아준다.
// 이 keySet은 phoneNumber를 공유받게 된다.
Set<String> phoneNumberKey = infoHashMap.keySet();

// keySet으로 모여도 phoneNumberKey는 순서가 없어서 반복문을 사용하지 못한다.
// Iterator를 import하여 이들을 반복 가능하게 한다.
Iterator<String> itPhoneNumberKey = phoneNumberKey.iterator();

// iterator의 순서에 따라, 다음 set가 있는지 보고(반복문의 조건), 있다면 key(String phoneNumber)를 String에 대입하여
// 그 key에 따른 Member object의 정보들을 hashMap의 get()을 통해 꺼내오는 반복문
while(itPhoneNumberKey.hasNext()) {

// String key를 선언하여 다음 키가 있다면 그 키를 대입받아 온다.
String key = itPhoneNumberKey.next();

// 그 key를 통하여 hashMap의 value 영역에 저장된 Member object의 정보를 가져온다.
System.out.println("회원정보 : 이름 = "+ infoHashMap.get(key).getName()+", 전화번호 : "
+infoHashMap.get(key).getPhoneNumber()
+", 주소 : "+infoHashMap.get(key).getAddress()+", 종류 : "
+ infoHashMap.get(key).getGroup());
}
}
// 3번(회원정보 수정하기),4번(회원 삭제)를 선택할 시에 modifyMember(), removeMember() 내에서 호출하는 메소드
// printMember()를 overloading 하였다.
// 수정, 삭제할 회원의 String type의 name을 파라미터로 받아, Set와 Iterator를 통해 hashMap 원소의 name을
// 전체적으로 찾는다.
// 그 다음 파라미터로 받은 name을 가진 hashMap의 value를 ArrayList의 변수의 값(주소)으로 넘겨주고
// 총 몇명인지 출력하는 메소드
public static void printMember(String name) {
// 이 메소드를 사용하기 전에 ArrayList에 저장된 정보들을 전부 지워준다.
modifierArrayList.clear();//ArrayList clear

// Set를 사용하여 hashMap의 메소드인 keySet()를 사용해 phoneNumber들을 한곳에 모아준다.
// 이 keySet은 phoneNumber를 공유받게 된다.
Set<String> phoneNumberKey = infoHashMap.keySet();

// keySet으로 모여도 phoneNumberKey는 순서가 없어서 반복문을 사용하지 못한다.
// Iterator를 import하여 이들을 반복 가능하게 한다.
Iterator<String> itPhoneNumberKey = phoneNumberKey.iterator();

// iterator의 순서에 따라, 다음 set가 있는지 보고(반복문의 조건), 있다면 key(String phoneNumber)를 String에 대입하여
// 그 key에 따른 Member object의 정보들을 hashMap의 get()을 통해 꺼내오는 반복문
while(itPhoneNumberKey.hasNext()) {

// String key를 선언하여 다음 키가 있다면 그 키를 대입받아 온다.
String number=itPhoneNumberKey.next();

// 만약(조건문) hashMap상의 value(Member)가 소유하고 있는 getName()메소드를 사용하여
// Member의 이름을 꺼내왔을 때, 그 이름이 파라미터로 들어온 name과 같다면, HashMap의 현재 key에 대응하는 value를
// ArrayList로 하나씩 가져온다.
if(infoHashMap.get(number).getName().equals(name)) {
modifierArrayList.add(infoHashMap.get(number));
}
}

// 만약 ArrayList의 크기가 0이라면 해당하는 정보가 없다고 출력하고 이 메소드를 탈출(return)한다.
if(modifierArrayList.isEmpty()) {
System.out.println("해당하는 회원의 정보가 없습니다.");
return;
}else {
// ArrayList의 크기가0이 아니라면 입력받은 이름과 같은 회원의 수를 출력한다.
System.out.println("총 "+modifierArrayList.size()+"명의 회원이 저장되어 있습니다.");
}
}



// 3번(회원정보 수정하기),4번(회원 삭제)를 선택할 시에 하단부를 출력하는 메소드.
// 숫자를 입력받아 3,4번 입력시에 해당하는 메소드에 정수값을 넘겨준다.
public static int showList() {



int k = 0;
// 무한루프 반복문
while(true) {
// ArrayList에 저장되어 있는 값(동일한 이름을 가지고 있는 Member object)을 하나씩 번호와 함께 출력
// ArrayList는 순서가 있기 때문에 해당하는 인덱스의 값을 받아 접근할 수 있다.
for(int i = 0 ; i<modifierArrayList.size();i++) {
System.out.println((i+1)+". "+"회원정보 : 이름 = "+ modifierArrayList.get(i).getName()+", 전화번호 : "
  +modifierArrayList.get(i).getPhoneNumber()
  +", 주소 : "+modifierArrayList.get(i).getAddress()+", 종류 : "
  + modifierArrayList.get(i).getGroup());
}
// 입력된 값이 정수의 값이 아니라면 예외처리와 함께 while문의 처음으로 돌아간다.
// 만약 예외가 들어온다면 출력문과 함께 while문의 조건으로 다시 돌아간다. 만약 예외가 발생하지 않는다면
// 값을 리턴하고 메소드를 종료한다.
try {
k = scanner.nextInt();
scanner.nextLine();
return k;
}catch(InputMismatchException e) {
System.out.println("올바른 숫자를 입력하세요");
// nextInt는 따로 엔터를 처리하거나 문자열을 처리하지 않는다.
// 만약 String과 \n이 들어왔다면 계속 IM exception을 출력하기 때문에 비워준다.
scanner.nextLine();

continue;
}
}
}

// 3. 회원정보 수정하기를 선택할 시에 실행되는 메소드이다.
public static void modifyMember() {

scanner.nextLine();
System.out.println("수정할 회원의 이름을 입력하세요 \n 이름 :");
// 회원의 이름을 입력받고 Overloading 된 printMember를 실행하여 출력문의 상단을 출력한다.
printMember(scanner.nextLine());

// 만약 ArrayList의 size가 0이라면(해당 이름을 가진 사람이 없다면), 이 메소드를 탈출한다.
if(modifierArrayList.isEmpty()) {
return;
}
System.out.println("아래의 목록 중 수정할 회원의 번호를 입력하세요");

// 무한루프 반복문
// showList()메소드 내에서는 하단의 출력물을 출력하고, 리턴 시에 int 값을 준다.
while(true) {
int i = showList();

try{
// addMember()를 overLoading한 addMember(Member member)를 실행한다.
// 입력받은 번호에 1을 빼주어 올바른 인덱스로 접근하게 한다.
// ArrayList의 범위를 넘어가는 숫자를 넣게 되면 예외처리를 하여 다시 문구와 함께 while문의 조건으로 돌아가게 한다.
// 만약 예외가 발생하지 않는다면 반복문을 빠져나간다.
addMember(modifierArrayList.get(i-1));
System.out.println("정상적으로 수정되었습니다.");
return;
}catch(IndexOutOfBoundsException e){
// 만약 showList에서 받은 값이 ArrayList의 범위를 넘어가게 되면,출력문과 함께 showList()를 다시 실행하게 한다.
System.out.println("올바른 범위("+ 1 + "~"+modifierArrayList.size()+")의 값을 넣어주세요\n");
continue;
}
}
}

// 4. 회원 삭제를 입력할시에 실행하는 메소드이다.
public static void removeMember() {

scanner.nextLine();
System.out.println("삭제할 회원의 이름을 입력하세요 \n 이름 :");

// 회원의 이름을 입력받고 Overloading 된 printMember를 실행하여 출력문의 상단을 출력한다.
printMember(scanner.nextLine());

// 만약 ArrayList의 size가 0이라면(해당 이름을 가진 사람이 없다면), 이 메소드를 탈출한다.
if(modifierArrayList.isEmpty()) {
return;
}

System.out.println("아래의 목록 중 삭제할 회원의 번호를 입력하세요.");

// 무한루프 반복문
// showList()메소드 내에서는 하단의 출력물을 출력하고, 리턴 시에 int 값을 준다.
while(true) {
int i = showList();

try {
// ArrayList의 Member가 가지고 있는 phoneNumber를 String으로 받아
// 해당 key를 가지고 있는 해쉬맵의 원소를 삭제한다.
// 입력받은 번호에 1을 빼주어 올바른 인덱스로 접근하게 한다.
// ArrayList의 범위를 넘어가는 숫자를 넣게 되면 예외처리를 하여 다시 문구와 함께 while문의 조건으로 돌아가게 한다.
// 만약 예외가 발생하지 않는다면 메소드를 빠져나간다.
String key = modifierArrayList.get(i-1).getPhoneNumber();
infoHashMap.remove(key);
System.out.println("정상적으로 삭제되었습니다.");
return;
}catch(IndexOutOfBoundsException e) {

// 만약 showList에서 입력받은 값이 ArrayList의 범위를 넘어가게 되면,출력문과 함께 showList()를 다시 실행하게 한다.
System.out.println("올바른 범위("+ 1 + "~"+modifierArrayList.size()+")의 값을 넣어주세요\n");
continue;
}
}
}

// 입력되는 번호중 하나라도 숫자가 아닌지 판단하는 메소드
// 조건문에 쓰이기 때문에 boolean형으로 출력하고, 파라미터로 phoneNumber를 입력받는다.
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

// 입력되는 번호가 이미 저장되어 있는 번호인지 판단하는 메소드.
// 조건문에 쓰이기 때문에 boolean형으로 출력하고, 파라미터로 phoneNumber를 입력받는다.
public static boolean isAlreadyStored(String phoneNumber) {

// 현재 저장되어 있는 해쉬맵 상의 원소들의 keySet을 만든다. 제너릭은 String type
Set<String> phoneNumberKey = infoHashMap.keySet();
// Set은 반복이 되지 않기 때문에, Iterator를 사용해 반복문에 사용할 수 있게 한다. 제너릭은 String type
Iterator<String> itPhoneNumberKey = phoneNumberKey.iterator();

// 다음 key셋이 존재하면 반복하는 while문
while(itPhoneNumberKey.hasNext()) {

// keySet의 원소(String type)을 하나씩 받는 변수를 생성한다.
String number=itPhoneNumberKey.next();

// key셋으로 저장되어 있는 핸드폰 번호와, 파라미터로 받은 phoneNumber가 같은지 확인한다.
if(infoHashMap.get(number).getPhoneNumber().equals(phoneNumber)) {

// 만약 두 번호가 같다면,출력문을 출력하고 true를 리턴한다.
System.out.println("이미 존재하는 번호입니다. \n 다른 번호를 입력하세요");
return true;
}
}
// hashMap의 키와 입력받은 파라미터가 같은 것이 하나도 없다면 false를 리턴한다.
return false;
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

// scanner를 리턴하는 메소드(메인 메소드에서도 공용으로 import해서 사용한다.)
public static Scanner getScanner() {
return scanner;
}
}