package myexception;

public class NoSuchMemberException extends Exception{
	private static final long serialVersionUID = 1L;

	public void print() {
		System.out.println("해당하는 이름의 회원이 저장되어 있지 않습니다.");
	}
}
