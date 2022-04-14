package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.ui;

import kr.co.mlec.lib.ui.manager.ManagerrUI;
import kr.co.mlec.lib.ui.member.AddMemberUI;
import kr.co.mlec.lib.ui.rent.CheckInUI;
import kr.co.mlec.lib.ui.rent.CheckOutUI;
import kr.co.mlec.lib.ui.search.SearchBookUI;

public class BoardUI extends BaseUI {

	private String menu() {
		
		System.out.println("------------------------------");
		System.out.println("\t1. 도서검색");
		System.out.println("\t2. 회원등록");
		System.out.println("\t3. 대출");
		System.out.println("\t4. 반납");
		System.out.println("\t5. 관리자 모드");		
		System.out.println("\t0. 종료");
		System.out.println("------------------------------");
		
		String type = scanStr("○ 메뉴 중 처리할 항목을 선택하세요 : ");
		
		return type;
	}
	@Override
	public void execute() {
		
	while (true) {
			
			ILibUI ui = null;
			String type = menu();

			switch (type) {
			case "1":
				ui = new SearchBookUI();
				break;
			case "2":
				ui = new AddMemberUI();
				break;
			case "3":
				ui = new CheckInUI();
				break;
			case "4":
				ui = new CheckOutUI();
				break;			
			case "5":
				ui = new ManagerrUI();
				break;
			case "0":
				ui = new ExitUI();
				break;
			}
			
			if(ui != null) {
				ui.execute();
			} else {
				System.out.println("○ 잘못입력하셨습니다. 다시 입력해주세요");
			}
		}
	}
}

	//1. 도서검색
	//2. 회원등록
	//3. 대출
	//4. 반납
	//0. 종료
