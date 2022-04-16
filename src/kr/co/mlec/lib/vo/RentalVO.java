package kr.co.mlec.lib.vo;

/*
	1. 디폴트생성자
	2. 매개변수 생성자
	3. getter/setter 메소드
	4. toString 메소드
*/
public class RentalVO {

	private String bookNo;
	private String id;
	private String inNo;
	private String outNo;
	public RentalVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalVO(String bookNo, String id, String inNo, String outNo) {
		super();
		this.bookNo = bookNo;
		this.id = id;
		this.inNo = inNo;
		this.outNo = outNo;
	}
	@Override
	public String toString() {
		return "RentalVO [bookNo=" + bookNo + ", id=" + id + ", inNo=" + inNo + ", outNo=" + outNo + "]";
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInNo() {
		return inNo;
	}
	public void setInNo(String inNo) {
		this.inNo = inNo;
	}
	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	
	}
