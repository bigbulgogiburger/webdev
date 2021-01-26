package myexception;

public class AlreadyStoredException extends SimpleException{

	private static final long serialVersionUID = 1L;

	public void print() {
		System.out.println("이미 저장되어 있는 번호입니다. 다시 입력해주세요");
	}
}
