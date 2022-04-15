package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.dao.manager;

import kr.co.mlec.util.JDBCClose;
import kr.co.mlec.util.connectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ManagerDAO {

	// 아이디
	public String checkId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new connectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id ");
			sql.append("  from t_member ");
			sql.append(" where id = 'admin' ");
			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String pw = rs.getString("id");
				return pw;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return null;
	}

//패스워드

	public String managerpassword(String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new connectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select password from t_member   ");
			sql.append(" where id = 'admin' ");

			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String pw = rs.getString("password");
				return pw;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return null;
	}
}
