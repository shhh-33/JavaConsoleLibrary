package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.mlec.lib.vo.MemberVO;
import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class MemberDAO {

	//전체회원확인
	
 public List<MemberVO> selectAllMember() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<MemberVO> MemberListAll = new ArrayList<>();
		
		try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, name, sex, phone ");
			sql.append(" from t_member ");
			sql.append("order by name");
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String phone = rs.getString("phone");
					
				MemberVO book = new MemberVO(id, password, name, sex, phone);
			
				MemberListAll.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return MemberListAll;
	}
		
	
	/**
	 * 중복아이디 체크
	 * 
	 * @param id 중복체크할id
	 * @return flag 중복여부 boolean
	 */

	public boolean checkId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new connectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id ");
			sql.append("  from t_member ");
			sql.append(" where id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return false;
	}
}


