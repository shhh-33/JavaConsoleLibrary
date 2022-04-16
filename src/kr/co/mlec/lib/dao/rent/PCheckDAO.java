package kr.co.mlec.lib.dao.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class PCheckDAO {
	public String pcheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		try {
			 conn = new connectionFactory().getConnection();
			   
			   StringBuilder sql = new StringBuilder();
			   sql.append(" select password from t_member   ");
			   sql.append(" where id = ? ");
			  		
			   pstmt = conn.prepareStatement(sql.toString());
			   pstmt.setString(1, id);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
				
			   ResultSet rs = pstmt.executeQuery();
			   
			   if(rs.next())
			   {	String pw = rs.getString("password");
			   		return pw;
			   } else {
				   return null;
			   }
			}catch(Exception e ) {
						   e.printStackTrace();
					}finally {
					   JDBCClose.close(pstmt, conn);
					}
		return null;
	}
}
