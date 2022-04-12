package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class TitleFromRentDAO {
	//도서 번호로 책 제목 끌어오기 대출된 정보 나올 때 rent 테이블 정보랑 같이 붙이기 위해서....
	public String titleByNo (String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String titleBybookNo = null;
	
		try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" create or replace view t_rent_view  ");
			sql.append(" as  ");
			sql.append("  select b.title, a.id ");
			sql.append("  from t_rent a, t_book b  ");
			sql.append("  where a.book_n = b.book_n  ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.executeUpdate();

			   sql = new StringBuilder();
			   sql.append( "select title from t_rent_view  ");
			   sql.append( " where id = ?  ");
			  
			   pstmt = conn.prepareStatement(sql.toString());
			   pstmt.setString(1, id);
			   ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				titleBybookNo = rs.getString("title");
				return titleBybookNo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return titleBybookNo;
	}	
}
