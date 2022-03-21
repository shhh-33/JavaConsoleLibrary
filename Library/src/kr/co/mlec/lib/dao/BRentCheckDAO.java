package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import kr.co.mlec.lib.vo.rentalVO;
import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class BRentCheckDAO {
	public String bookNocheck(String bookno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String ggg = null;
		
		
		try {
		
		   conn = new connectionFactory().getConnection();
		   
		   StringBuilder sql = new StringBuilder();
		   sql.append(" select book_n from t_rent   ");
		   sql.append(" where book_n = ?    ");
		   
		   pstmt = conn.prepareStatement(sql.toString());
		   pstmt.setString(1, bookno);
		   
		   ResultSet rs = pstmt.executeQuery();
		   
			if(rs.next()) {
				ggg = rs.getString("book_n");	
				return ggg;
			}else {
				return null;
			}	   
		}catch(Exception e ) {
		   e.printStackTrace();
		}finally {
		   JDBCClose.close(pstmt, conn);
		}
		   return ggg;
		}
}
