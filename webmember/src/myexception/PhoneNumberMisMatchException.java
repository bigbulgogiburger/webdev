package myexception;

public class PhoneNumberMisMatchException extends SimpleException{
	public void print() {
		System.out.println("올바른 번호가 아닙니다.\n 전화번호는 '-'를 제외한 숫자 11자리와 0으로 시작하게 입력해주세요");
	}
}
