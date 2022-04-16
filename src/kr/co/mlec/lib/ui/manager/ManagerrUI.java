package kr.co.mlec.lib.ui.manager;

import kr.co.mlec.lib.dao.manager.ManagerDAO;
import kr.co.mlec.lib.ui.BaseUI;
import kr.co.mlec.lib.ui.ILibUI;


public class ManagerrUI extends BaseUI {

	@Override
	public void execute() {

		System.out.println("----------------------");
		// 로그인

		while (true) {
			String id = scanStr("○ 관리자 아이디를 입력하세요 : ");
			String password = scanStr("○ 관리자 패스워드를 입력하세요 : ");

			ManagerDAO daoId = new ManagerDAO(); // 아이디
			String result = daoId.checkId(id);

			ManagerDAO daoPass = new ManagerDAO(); // 패스워드
			String b = daoPass.managerpassword(password);

			{
				if (result == null || b == null) {
					System.out.println("○ 회원 아이디를 잘못 입력했습니다");
				} else if (!password.equals(b)) {
					System.out.println("○ 아이디,패스워드를 잘못 입력했습니다");
				} else {
					System.out.println("○ 관리자 모드 로그인 완료");
					break;
				}
			}

		}

		System.out.println("----------------------");
		String type = scanStr("○ 메뉴 중 처리할 항목을 선택하세요.\n1.도서 관리  \n2.회원 관리 ");

		ILibUI ui = null;
		switch (type) {
		case "1":
			ui = new ManagerBookManageUI();
			break;
		case "2":
			ui = new ManagerMemberManageUI();
			break;
		}
		if (ui != null) {
			ui.execute();
		} else {
			System.out.println("○ 잘못입력하셨습니다. 다시 입력하세요 ");
		}

	}

}
