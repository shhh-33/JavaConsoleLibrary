package kr.co.mlec.lib.ui;

public class ManagerBookManageUI extends BaseUI{
	
	@Override
	public void execute() {
		System.out.println("----------------------");
		System.out.println(" 도서관리 메뉴입니다.");
		System.out.println("----------------------");
		System.out.println("1.전체 도서 확인");
		System.out.println("2.도서 목록 추가");
		System.out.println("3.도서 목록 수정");
		System.out.println("4.도서 목록 삭제");

		System.out.println("----------------------");
		String type = scanStr("○ 메뉴 중 처리할 항목을 선택하세요: ");
		ILibUI ui = null;
		switch (type) {
		case "1":
			ui = new ManagerBookAll();
			break;
		case "2":
			ui = new ManagerBookInsertUI();
			break;
		case "3":
			ui = new ManagerBookUpdateUI();
			break;
		case "4":
			ui = new ManagerBookDeleteUI();
			break;
		}
		if(ui != null) {
			ui.execute();
		}else {
			System.out.println("○ 잘못입력하셨습니다. 다시 입력하세요 ");
		}
		
	}

}
