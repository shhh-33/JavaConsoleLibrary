package kr.co.mlec.lib.ui.rent;

import kr.co.mlec.lib.dao.LibDAO;
import kr.co.mlec.lib.dao.rent.BCheckDAO;
import kr.co.mlec.lib.dao.rent.MCheckDAO;
import kr.co.mlec.lib.dao.rent.PCheckDAO;
import kr.co.mlec.lib.dao.rent.TitleDAO;
import kr.co.mlec.lib.ui.BaseUI;
import kr.co.mlec.lib.vo.RentalVO;

public class CheckInUI extends BaseUI {

	@Override
	public void execute() {
		// memberDAO memberDao = new memberDAO();
		LibDAO dao = new LibDAO();
		MCheckDAO idcheck = new MCheckDAO();
		PCheckDAO passcheck = new PCheckDAO();
		BCheckDAO bookcheck = new BCheckDAO();

		// 아이디 제대로 입력했는지

		String id = "";
		while (true) {
			id = scanStr("○ 회원 아이디를 입력하세요 : ");
			String idCheck = idcheck.mcheck(id);

			if (!id.equals(idCheck)) {
				System.out.println("○ 회원 아이디를 잘못 입력했습니다");
			} else {
				break;
			}
		}

		// 패스워드 제대로 입력했는지
		String password = "";

		while (true) {
			password = scanStr("○ 회원 패스워드를 입력하세요 : ");
			String passCheck = passcheck.pcheck(id);

			if (!password.equals(passCheck)) {
				System.out.println("○ 패스워드를 잘못 입력했습니다");
			} else {
				break;
			}
		}

		// 이미 대출된 책인지 아닌지
		String bookNo = "";
		while (true) {
			bookNo = scanStr("○ 대출할 도서 번호를 입력하세요 : ");
			String BNo = bookcheck.bookNocheck(bookNo);

			if (BNo == null) {
				System.out.println("○ 보유한 도서가 아닙니다. 책번호 오류.");
			} else if (bookNo.equals(BNo)) {
				System.out.println("○ 이미 대출된 책입니다. 책번호 오류.");
			} else {

				RentalVO bInfo = dao.checkininfo(bookNo, id);
				TitleDAO booktitleonly = new TitleDAO();
				String t = booktitleonly.titleByNo(bookNo);

				System.out.println("----------------------------------------------------");
				System.out.println("○ 대출이 완료되었습니다.");
				System.out.println("○ 책 제목 : " + t);
				System.out.println("○ 책 번호 : " + bInfo.getBookNo());
				System.out.println("○ 회원 아이디 : " + bInfo.getId());
				System.out.println("○ 대출일 : " + bInfo.getInNo());
				System.out.println("○ 반납일 : " + bInfo.getOutNo());
				System.out.println("○ 반납일을 지켜주세요.");
				System.out.println("----------------------------------------------------");
				break;
			}
		}

	}
}