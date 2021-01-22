package myexception;

public class WrongGroupException extends Exception {
	private static final long serialVersionUID = 1L;

	public void print() {
		System.out.println("종류는 '가족'이나 '친구' 또는 '기타'만 가능합니다");
	}
}
