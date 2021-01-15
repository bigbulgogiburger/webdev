package lib;

import java.util.ArrayList;
import java.util.Scanner;
	
public class BookManager {
	private static int book_id= 10000000;
	private static Scanner scanner = new Scanner(System.in);
//	도서목록을 저장하고 있는 어레이리스트
	private static ArrayList<Book> bookAl = new ArrayList<Book>();
//	도서추가
	public static void addBook(Book book) {
		System.out.println("책 제목을 입력하세요");
		String title = scanner.nextLine();
		System.out.println("작가를 입력하세요");
		String author = scanner.nextLine();
		book_id = book_id+1;
		
		bookAl.add(new Book(title,Integer.toString(book_id), author, false));
	}
//	도서검색
	public static void searchBook() {
		System.out.println("전체 도서 목록 입니다.");
		for(Book x : bookAl) {
			System.out.println("책 제목  : " +x.getTitle());
			System.out.println("저자  : " +x.getAuthor());
			System.out.println("대여 여부 : " +x.isBorrow());
		}
	}
//	저자나 제목으로 검색
	public static void searchBook(int number) {
		
		ArrayList<Book> tempArray =new ArrayList<Book>();
		if(number==2) {
			
			System.out.println("저자를 입력해 주세요.");
			String findByAuthor = scanner.nextLine();
			
			for(Book x : bookAl) {
				if(x.getAuthor().equalsIgnoreCase(findByAuthor)) {
					tempArray.add(x);
				}
			}
			if(tempArray.size()==0) {
				
				System.out.println("찾으시는 저자의 책이 없습니다.");
				return;
			}else {
				
				for(Book x : bookAl) {
					System.out.println("책 제목  : " +x.getTitle());
					System.out.println("저자  : " +x.getAuthor());
					System.out.println("대여 여부 : " +x.isBorrow());
				}
			}
		}
		else if(number==3) {
			System.out.println("책 제목을 입력해 주세요.");
			String findByTitle = scanner.nextLine();
			
			for(Book x : bookAl) {
				if(x.getTitle().equalsIgnoreCase(findByTitle)) {
					tempArray.add(x);
				}
			}
			if(tempArray.size()==0) {
				
				System.out.println("찾으시는 제목의 책이 없습니다.");
				return;
			}else {
				
				for(Book x : bookAl) {
					System.out.println("책 제목  : " +x.getTitle());
					System.out.println("저자  : " +x.getAuthor());
					System.out.println("대여 여부 : " +x.isBorrow());
				}
			}
		}
	}
		
	
	
	
	public static void borrowBook(Member member) {
		ArrayList<Book> tempArray =new ArrayList<Book>();
		System.out.println("책 제목을 입력해 주세요.");
		int canBorrow = 0;
		String findByTitle = scanner.nextLine();
		
		for(Book x : bookAl) {
			if(x.getTitle().equalsIgnoreCase(findByTitle)) {
				tempArray.add(x);
				if(x.isBorrow()==false) {
					canBorrow++;
				}
			}
		}
		if(tempArray.size()==0) {
			
			System.out.println("찾으시는 제목의 책이 없습니다.");
			return;
		}else {
			if(canBorrow==0) {
				System.out.println("찾으시는 책이 현재 모두 대여중입니다.");
				return;
			}else {
				System.out.println("총 "+tempArray.size()+"권의 책이 있습니다.");
				for(Book x : bookAl) {
					System.out.println("책 제목  : " +x.getTitle());
					System.out.println("저자  : " +x.getAuthor());
					System.out.println("도서 ID :"+x.getBook_id());
				}
				System.out.println("책을 빌리시려면  1번, 빌리지 않으시려면 2번을 눌러주세요");
				String borrownumber= scanner.nextLine();
				if(borrownumber=="1") {
					tempArray.get(0).setBorrow(true);
				}else {
					return;
				}
			}
			
		}
	}
	public static void returnBook() {
		
	}
	
	public static void deleteBook() {
		
	}

}
