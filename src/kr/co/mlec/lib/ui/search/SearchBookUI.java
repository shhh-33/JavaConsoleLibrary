package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.ui.search;

import kr.co.mlec.lib.ui.BaseUI;
import kr.co.mlec.lib.ui.ILibUI;

public class SearchBookUI extends BaseUI {

	@Override
	public void execute() {

		System.out.println("------------------------------");
		System.out.println("어떤 방법으로 책을 찾겠습니까?");
		System.out.println(" 0. 모든 도서 보기  ");
		System.out.println(" 1. 책 번호로 책 찾기  ");
		System.out.println(" 2. 책 제목으로 책 찾기  ");
		System.out.println(" 3. 저자로 책 찾기 ");
		System.out.println(" 4. 출판사로 책 찾기");
		System.out.println("------------------------------");

		String type = scanStr("○ 메뉴 중 처리할 항목을 선택하세요: ");
		ILibUI ui = null;
		switch (type) {
		case "0":
			ui = new SearchByAllUI();
			break;
		case "1":
			ui = new SearchByNo();
			break;
		case "2":
			ui = new SearchByTitle();
			break;
		case "3":
			ui = new SearchByAuthor();
			break;
		case "4":
			ui = new SearchByPublisher();
			break;
		}
		if (ui != null) {
			ui.execute();
		} else {
			System.out.println("○ 잘못입력하셨습니다. 다시 입력하세요 ");
		}

		}

}
