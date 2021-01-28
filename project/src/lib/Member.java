package lib;


/**
 * @작성자 :   편도훈
 * @작성일 : 2020. 12. 17.
 * @filename : Member.java
 * @package : lib
 * @description : 멤버의 정보를 담은 클래스
 */
public class Member {

private String name;   //이름을 저장하는 String타입의 name변수이며, 내부에서만 사용 가능하며 get/set method를 이용하여 값을 받는다.
private String phoneNumber;//핸드폰 번호를 저장하는 String타입의 phoneNumber 변수이며, 내부에서만 사용 가능하며 get/set method를 이용하여 값을 받는다.
  //phoneNumber는 메소드 클래스 내의 hashMap의 키가 된다.
private String address;   //주소를 저장하는 String타입의 address 변수이며, 내부에서만 사용 가능하며 get/set method를 이용하여 값을 받는다.
private String group;   //종류를 저장하는 String타입의 group 변수이며, 내부에서만 사용 가능하며 get/set method를 이용하여 값을 받는다.

// 생성자 선언. hashMap에 넣을때만 쓰이기 때문에 디폴트 생성자는 만들지 않아도 된다.
public Member(String name, String phoneNumber, String address, String group) {
this.name=name;
this.phoneNumber=phoneNumber;
this.address=address;
this.group=group;
}

// 위의 선언된 변수들의 getter/setter method들

// name을 리턴하는 메소드
public String getName() {
return name;
}
//  name에 값을 대입하는 메소드
public void setName(String name) {
this.name = name;
}
//  phoneNumber를 리턴하는 메소드
public String getPhoneNumber() {
return phoneNumber;
}
// phoneNumber에 값을 대입하는 메소드
public void setPhoneNumber(String phoneNumber) {
this.phoneNumber = phoneNumber;
}
//  address를 리턴하는 메소드
public String getAddress() {
return address;
}
//  address에 값을 대입하는 메소드
public void setAddress(String address) {
this.address = address;
}
// group을 리턴하는 메소드
public String getGroup() {
return group;
}
// group에 값을 대입하는 메소드
public void setGroup(String group) {
this.group = group;
}

}
