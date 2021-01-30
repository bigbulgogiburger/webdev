package main;

import controller.MemberController;


/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 25.
 * @filename : 	PhonebookMain.java
 * @package : 	main
 * @description : 실행을 담당하는 메인영역
 */
public class PhonebookMain {
	
	public static void main(String[] args) {
		MemberController controller = new MemberController();
		controller.start();

	}

}
