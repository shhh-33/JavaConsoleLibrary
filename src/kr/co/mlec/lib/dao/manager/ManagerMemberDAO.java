package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.dao.manager;

import kr.co.mlec.lib.vo.MemberVO;
import kr.co.mlec.util.JDBCClose;
import kr.co.mlec.util.connectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManagerMemberDAO {

	// 2. 관리자 - 회원 목록 추가
	public MemberVO insertMember(String id, String password, String name, String sex, String phone) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;
		int a = 0;
		try {
			conn = new connectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_member(id, password , name, sex, phone) ");
			sql.append(" values(?, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, sex);
			pstmt.setString(5, phone);

			a = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return memberVO;

	}

	// 3. 관리자 - 회원 목록 수정
	public int UpdateMember(MemberVO bm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int a = 0;

		try {
			conn = new connectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update t_member ");
			sql.append(" set password = ?, name = ?,sex = ?,phone =? ");
			sql.append(" where id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(5, bm.getId());
			pstmt.setString(1, bm.getPassword());
			pstmt.setString(2, bm.getName());
			pstmt.setString(3, bm.getSex());
			pstmt.setString(4, bm.getPhone());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return a;
	}

	// 4. 관리자 - 회원 목록 삭제
	public int deleteMember(String id, String password) {

	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      int cnt = 0;

	      try {
	         conn = new connectionFactory().getConnection();
	         StringBuilder sql = new StringBuilder();

	         sql.append("delete from t_member where id = ? ");

	         pstmt = conn.prepareStatement(sql.toString());
	         pstmt.setString(1, id);

	         cnt = pstmt.executeUpdate();

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         JDBCClose.close(pstmt, conn);
	      }
	      return cnt;
	   }
}