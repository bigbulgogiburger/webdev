package myexception;

public class NotNumberException extends SimpleException {
	private static final long serialVersionUID = 1L;

	public void print() {
		System.out.println("올바른 번호가 아닙니다. \n 문자를 제외한 숫자만 입력하세요.");
	}
}
