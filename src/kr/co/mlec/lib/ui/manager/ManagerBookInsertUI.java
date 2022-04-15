package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.ui.manager;

import kr.co.mlec.lib.dao.manager.ManagerBookDAO;
import kr.co.mlec.lib.dao.rent.BCheckDAO;
import kr.co.mlec.lib.ui.BaseUI;
import kr.co.mlec.lib.vo.BookVO;

public class ManagerBookInsertUI extends BaseUI {

	@Override
	public void execute() {

		String book_n = "";

		boolean bool = false;
		do {
			book_n = scanStr("○ 등록할 책번호를 입력하세요 : ");
			bool = BCheckDAO.checkBNum(book_n);
			if (bool) {
				System.out.println("○ 이미 사용중인 책번호입니다.다시입력하세요");
			}
		} while (bool);

		String title = scanStr("○ 책 제목을 입력하세요.");
		String author = scanStr("○ 저자를 입력하세요.");
		String publisher = scanStr("○ 출판사를 입력하세요.");
		String price = scanStr("○ 가격을 입력하세요.");

		ManagerBookDAO aBookDao = new ManagerBookDAO();
		BookVO book = new BookVO();

		book.setBookNo(book_n);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);

		aBookDao.insertBook(book_n, title, author, publisher, price);

		System.out.println("○ 책 등록을 완료하였습니다");

	}

}