package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class MCheckDAO {

	public int mcheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			 conn = new connectionFactory().getConnection();
			   
			   StringBuilder sql = new StringBuilder();
			   sql.append(" select id from t_member   ");
			   sql.append(" where id = ? ");
			  		
			   pstmt = conn.prepareStatement(sql.toString());
			   pstmt.setString(1, id);
				
			   ResultSet rs = pstmt.executeQuery();
			   
			   if(rs.next())
			   {	
				   return 1;
			   } else {
				   return 0;
			   }
			}catch(Exception e ) {
						   e.printStackTrace();
					}finally {
					   JDBCClose.close(pstmt, conn);
					}
		return 0;
	}
}
					
