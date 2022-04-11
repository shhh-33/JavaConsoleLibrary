package kr.co.mlec.lib.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.mlec.lib.dao.LibDAO;
import kr.co.mlec.lib.dao.MemberDAO;


public class AddMemberUI extends BaseUI {

	@Override
	public void execute() {

		// 아이디 입력
		MemberDAO memberDao = new MemberDAO(); // DAO에서 다시 재정의
		LibDAO dao = new LibDAO();
		String id = "";
		boolean bool = false; // bool이제 알려준다. false면 등록된 값이 없어서 등록 진행
		
		do {
			id = scanStr("○ 등록할 id를 입력하세요 : "); //
			bool = memberDao.checkId(id);
			if (bool) {
				System.out.println("○ 이미 사용중인 아이디입니다 다시입력하세요 - ");
			}
		} while (bool);

		// 패스워드 입력
		String password = "";
		while (true) {
			password = scanStr("○ 패스워드를 입력하세요 : ");
			if (password == "") {
				System.out.println("○ 패스워드 값이 비어있습니다 다시 입력하세요.");
			} else {
				break;
			}
		}
		// 이름 입력
		String name = "";
		while (true) {
			name = scanStr("○ 이름을 입력하세요 : ");
			if (name == "") {
				System.out.println("○ 이름값이 없습니다. 다시 입력하세요.");
			} else {
				break;
			}
		}

		// 성별 입력
		String sex = "";
		while (true) {
			sex = scanStr("○ 성별을 입력하세요 (여/남) : ");
			String m = "남";
			String f = "여";

			if (!sex.equals(m) && !sex.equals(f)) {
				System.out.println("○ 다시입력하세요");
			} else {
				break;
			}
		}

		// 핸드폰 번호 입력
		String phone = "";
		while (true) {
			phone = scanStr("○ 핸드폰 번호를 입력하세요. ex) 01x-xxx(or xxxx)-xxxx: ");
			Pattern p = Pattern.compile("^01(?:0|1|[6-9])[-]?(\\d{3}|\\d{4})[-]?(\\d{4})$");
			Matcher m = p.matcher(phone);
			if (!m.find()) {
				System.out.println("○ 다시 입력하세요 (형식: 01x-xxxx-xxxx)");
			} else {
				break;
			}
		}

		dao.insertMember(id, password, name, sex, phone);
		System.out.println("○ 회원등록을 완료하였습니다");
	}
}
