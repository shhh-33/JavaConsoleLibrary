package kr.co.mlec.lib.dao.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;

public class BCheckDAO {
   // 책 검색 시 제목으로 찾기
   public int bCheck(String title) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = new connectionFactory().getConnection();

         StringBuilder sql = new StringBuilder();
         sql.append(" select title from t_book   ");
         sql.append(" where title = ? ");

         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, title);

         ResultSet rs = pstmt.executeQuery();

         if (rs.next()) {
            return 1;
         } else {
            return 0;
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCClose.close(pstmt, conn);
      }
      return 0;
   }

   // 도서 넘버 체크
   public static boolean checkBNum(String book_n) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = new connectionFactory().getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("select book_n ");
         sql.append("  from t_book ");
         sql.append(" where book_n = ? ");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, book_n);
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
   
   
   public static int checkBNum2(String book_n) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = new connectionFactory().getConnection();
         StringBuilder sql = new StringBuilder();
         sql.append("select book_n ");
         sql.append("  from t_book ");
         sql.append(" where book_n = ? ");
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, book_n);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            return 1;
         } else {
            return 0;
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCClose.close(pstmt, conn);
      }
      return 0;
   }
   
   //대출된 책인지 아닌지 확인할 때
	public String bookNocheck(String bookno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String bookNo = null;
		
		
		try {
		
		   conn = new connectionFactory().getConnection();
		   
		   StringBuilder sql = new StringBuilder();
		   sql.append(" select book_n from t_rent   ");
		   sql.append(" where book_n = ?    ");
		   
		   pstmt = conn.prepareStatement(sql.toString());
		   pstmt.setString(1, bookno);
		   
		   ResultSet rs = pstmt.executeQuery();
		   
			if(rs.next()) {
				bookNo = rs.getString("book_n");	
				return bookNo;
			}else {
				return null;
			}	   
		}catch(Exception e ) {
		   e.printStackTrace();
		}finally {
		   JDBCClose.close(pstmt, conn);
		}
		   return bookNo;
		}

}
