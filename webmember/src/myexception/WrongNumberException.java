package myexception;

public class WrongNumberException extends SimpleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void print() {;
	System.out.println("올바른 번호가 아닙니다. 1~5 사이의 숫자를 입력하세요");
	
	}
}
