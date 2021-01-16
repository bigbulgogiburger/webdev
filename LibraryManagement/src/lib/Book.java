package lib;

public class Book {
	private String title;
	private String book_id;
	private String author;
	private boolean borrow=false;
	
	public Book() {
		
	}
	
	public Book(String title, String book_id, String author, boolean borrow) {
		this.title = title;
		this.book_id=book_id;
		this.author=author;
		this.borrow = borrow;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isBorrow() {
		return borrow;
	}
	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	

}
