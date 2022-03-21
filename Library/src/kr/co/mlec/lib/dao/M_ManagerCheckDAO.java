package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class M_ManagerCheckDAO {

	public String mcheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String id1 = null;
		
		try {
			 conn = new connectionFactory().getConnection();
			   
			   StringBuilder sql = new StringBuilder();
			   sql.append(" select id from t_member   ");
			   sql.append(" where id = ? ");
			  		
			   pstmt = conn.prepareStatement(sql.toString());
			   pstmt.setString(1, id);
				
			   ResultSet rs = pstmt.executeQuery();
			   
			   if(rs.next())
			   {   id1 = rs.getString("id");
				   return id1;	   
			   } else {
				   return null;
			   }
			}catch(Exception e ) {
						   e.printStackTrace();
					}finally {
					   JDBCClose.close(pstmt, conn);
					}
		return id1;
		
	}
}
					
