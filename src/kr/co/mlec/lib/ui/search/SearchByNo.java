package kr.co.mlec.lib.ui.search;

import java.util.List;

import kr.co.mlec.lib.dao.search.SearchBookDAO;
import kr.co.mlec.lib.vo.BookVO;

public class SearchByNo extends SearchBookUI {

	@Override
	public void execute() {
		String no = scanStr("○ 조회할 책 번호를 입력하세요: ");

		SearchBookDAO dao = new SearchBookDAO();
		List<BookVO> list = dao.selectByNo(no);

		////////////////

		System.out.println(
				"============================ <   책          출         력     >==================================");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println(" 책 번호\t\t  " + "제목 \t\t" + "저자\t\t" + "출판사\t\t" + "가격");
		System.out.println("------------------------------------------------------------------------------------");
		if (list.isEmpty()) {
			System.out.println("○ 조회된 책이 없습니다");
		} else {
			for (BookVO book : list) {
				System.out.printf("%3s\t%-18s\t%-12s\t%-10s\t%-10s\n", book.getBookNo(), book.getTitle(),
						book.getAuthor(), book.getPublisher(), book.getPrice());

			}

		}
		System.out.println("=====================================================================================");
}}