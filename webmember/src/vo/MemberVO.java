package vo;

public class MemberVO {
	private String name;   
	private String phoneNumber;
	private String address;   
	private String group;   
	private int memberNum;

	public MemberVO() {
		
	}
	public MemberVO(String name, String phoneNumber, String address, String group) {
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
//	group의 번호(sql group_info table의 group_number)를 리턴하는 메소드
	public int getGroupNumber() {
		if(group.equals("가족")) {
			return 1;
		}else if(group.equals("친구")) {
			return 2;
		}else {
			return 3;
		}
	}
	@Override
	public String toString() {
		return "[이름  : " + name + ", 전화번호  : " + phoneNumber + ", 주소  : " + address + ", 그룹 : " + group
				+ "]";
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	
	
}
