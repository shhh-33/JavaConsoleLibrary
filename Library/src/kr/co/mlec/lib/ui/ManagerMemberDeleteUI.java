package kr.co.mlec.lib.ui;

import kr.co.mlec.lib.dao.ManagerMemberDAO;
import kr.co.mlec.lib.dao.MCheckDAO;
import kr.co.mlec.lib.dao.PManagerCheckDAO;

public class ManagerMemberDeleteUI extends BaseUI {

	@Override
	public void execute() {

		String id = scanStr("삭제할 회원 아이디를 입력하세요 : ");
		String password = scanStr("삭제할 회원 비밀번호를 입력하세요 : ");

		ManagerMemberDAO mDAO = new ManagerMemberDAO(); // 삭제할때 쓸 dao

		MCheckDAO idDAO = new MCheckDAO(); // 회원 아이디 확인dao
		int resultID = idDAO.mcheck(id);

		PManagerCheckDAO passDAO = new PManagerCheckDAO(); // 회원 비밀번호 확인 dao
		String memberPassword = passDAO.pcheck(id);

		if (resultID == 0 || memberPassword == null) {
			System.out.println("회원 아이디를 잘못 입력했습니다");
		} else if (!password.equals(memberPassword)) {
			System.out.println("패스워드를 잘못 입력했습니다");

		} else {
			System.out.println("회원정보가 삭제되었습니다.");

			mDAO.deleteMember(id, password);

		}


	}

}