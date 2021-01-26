package myexception;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 26.
 * @filename : 	SimpleException.java
 * @package : 	myexception
 * @description : 모든 익셉션의 추상클래스
 */
public abstract class SimpleException extends Exception {
	abstract void print();
	

}
