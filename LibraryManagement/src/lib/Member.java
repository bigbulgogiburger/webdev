package lib;

public class Member {
	private String name;
	private String phoneNumber;
	private String memberId;
	private Book[] book;
	
	public Member(String name, String phoneNumber, String memberId) {
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.memberId=memberId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Book[] getBook() {
		return book;
	}
	public void setBook(Book[] book) {
		this.book = book;
	}

}
