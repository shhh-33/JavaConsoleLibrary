package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.ui.manager;

import kr.co.mlec.lib.dao.member.MemberDAO;
import kr.co.mlec.lib.vo.MemberVO;

import java.util.List;

//상속??
public class ManagerMemberAllUI extends ManagerMemberManageUI {
	@Override
	public void execute() {

		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAllMember();

		System.out.println(
				"============================ <   회      원     출       력     >==================================");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println(" 아이디\t\t  " + "패스워드 \t\t" + "이름\t\t" + "성별\t\t" + "핸드폰번호");
		System.out.println("---------------------------------------------------------------------------------");
		for (MemberVO book : list) {
			System.out.printf("%10s\t%-13s\t%-17s\t%-15s\t%-20s\n", book.getId(), book.getPassword(), book.getName(),
					book.getSex(), book.getPhone());
		}

		System.out.println("=====================================================================================");
	}

}
