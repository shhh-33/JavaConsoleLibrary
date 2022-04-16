package kr.co.mlec.lib.ui.rent;

import java.util.List;

import kr.co.mlec.lib.dao.LibDAO;
import kr.co.mlec.lib.dao.rent.MCheckDAO;
import kr.co.mlec.lib.dao.rent.PCheckDAO;
import kr.co.mlec.lib.dao.rent.TitleDAO;
import kr.co.mlec.lib.ui.BaseUI;
import kr.co.mlec.lib.vo.RentalVO;;

public class CheckOutUI extends BaseUI {

	@Override
	public void execute() {
		LibDAO dao = new LibDAO();
		MCheckDAO idcheck = new MCheckDAO();
		PCheckDAO passcheck = new PCheckDAO();

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
			List<RentalVO> list2 = dao.searchByBookNo2(id, password);

			password = scanStr("○ 회원 패스워드를 입력하세요 : ");
			String passCheck = passcheck.pcheck(id);

			if (!password.equals(passCheck)) {
				System.out.println("○ 패스워드를 잘못 입력했습니다");
			} else {
				TitleDAO booktitle = new TitleDAO();
				String bTitle = booktitle.titleByNo(id);

				for (RentalVO result : list2) {
					System.out.println("---------------------------------");
					System.out.println(result.getId() + "님의 대출 정보입니다.");
					System.out.println("○ 도서제목 : " + bTitle);
					System.out.println("○ 도서번호 : " + result.getBookNo());
					System.out.println("○ 대여날짜 : " + result.getInNo());
					System.out.println("○ 반납날짜 : " + result.getOutNo());
					System.out.println("------------------------------");
				}

				while (true) {
					String bookNo = scanStr("○ 반납할 도서 번호를 입력하세요 : ");

					for (RentalVO result : list2) {

						if (bookNo.equals(result.getBookNo())) {
							int delay = dao.delayByRent(bookNo);
							if (delay > 0) {
								System.out.println(delay + "일 연체가 발생하였습니다");
								System.out.println("○ 연체료는 " + (1000 * delay) + "원입니다");
								System.out.println("○ 반납은 관리자에게 문의하세요.");
							} else {
								System.out.println("○ 반납이 완료되었습니다."); // 반납이 완료되었습니다 할 때 나오는 bookNo, id 왜?

							}

							return;

						}
						continue;
					}

					System.out.println("○ 도서 번호 오류. 다시 입력하세요.");
				}
			}
			break;
		}
	}
}
