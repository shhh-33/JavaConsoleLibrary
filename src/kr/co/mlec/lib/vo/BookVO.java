package kr.co.mlec.lib.vo;
/*
	1. 디폴트생성자
	2. 매개변수 생성자
	3. getter/setter 메소드
	4. toString 메소드
*/
public class BookVO {

	private String bookNo;
	private String title;
	private String author;
	private String publisher;
	private String price; //네?
	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
 }
	public BookVO(String bookNo, String title, String author, String publisher, String price) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookVO [bookNo=" + bookNo + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", price=" + price + "]";
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
