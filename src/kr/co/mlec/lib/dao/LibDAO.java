package kr.co.mlec.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.mlec.lib.vo.MemberVO;
import kr.co.mlec.lib.vo.RentalVO;
import kr.co.mlec.util.connectionFactory;
import kr.co.mlec.util.JDBCClose;


public class LibDAO {
	// 2. 회원등록
	public MemberVO insertMember(String id2, String password2, String name2, String sex2, String phone2 ) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		MemberVO memberVO = null;

		try {
			conn = new connectionFactory().getConnection();

			////////////////////////////////////////////////////
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into t_member(id, password , name, sex, phone) ");
			sql.append(" values(?, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1,id2);
			pstmt.setString(2,password2);
			pstmt.setString(3,name2);
			pstmt.setString(4,sex2);
			pstmt.setString(5,phone2);

			pstmt.executeUpdate();
			
			/*ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId("id");
				memberVO.setPassword("password");
				memberVO.setName("name");
				memberVO.setSex("sex");
				memberVO.setPhone("phone");
				*/
			///////////////////////////////////////////////

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
			return memberVO;

}
	/**
	 * 연체료 체크
	 */
	public int delayByRent(String bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delay = -20;
		
		try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" select trunc(sysdate-out_d, 0) as 연체일  ");
			sql.append("  from t_rent ");
		    sql.append(" where book_n = ? ");
		    
		    pstmt = conn.prepareStatement(sql.toString());
		    pstmt.setString(1, bookNo);
		
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	delay = rs.getInt("연체일");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return delay;
	}
	
	
	public RentalVO searchByBookNo(String id, String password) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		RentalVO rentVO = null;
		
		try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
		    sql.append(" select id, book_n, ");
		    sql.append("  to_char(in_d, 'yyyy-mm-dd') as in_d, to_char(out_d, 'yyyy-mm-dd') as out_d ");
		    sql.append("  from t_rent ");
		    sql.append(" where id = ? ");
		    
		    pstmt = conn.prepareStatement(sql.toString());
		    pstmt.setString(1, id);
		
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	rentVO = new RentalVO();
		    	rentVO.setInNo(rs.getString("in_d"));
		    	rentVO.setOutNo(rs.getString("out_d"));
		    	rentVO.setId(rs.getString("id"));
		    	rentVO.setBookNo(rs.getString("book_n"));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return rentVO;
	}
	
	public List<RentalVO> searchByBookNo2(String id, String password) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RentalVO> list = new ArrayList<>();
		
		try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select id, book_n, ");
			sql.append("  to_char(in_d, 'yyyy-mm-dd') as in_d, to_char(out_d, 'yyyy-mm-dd') as out_d  ");
			sql.append("  from t_rent ");
			sql.append(" where id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				RentalVO rentVO = new RentalVO();
				rentVO.setInNo(rs.getString("in_d"));
				rentVO.setOutNo(rs.getString("out_d"));
				rentVO.setId(rs.getString("id"));
				rentVO.setBookNo(rs.getString("book_n"));
				list.add(rentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return list;
	}
	
	
	/**
	    * 
	    * 3. 대출윤선
	    * 회원번호 틀렸을 때
	    * 비밀번호 틀렸을 때
	    * 빌린 도서는 다시 못 빌리게....
	    */
	public RentalVO checkininfo(String bookno, String id2) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	RentalVO rentVO = null;
	
	try {
	
	   conn = new connectionFactory().getConnection();
	   
	   StringBuilder sql = new StringBuilder();
	   sql.append(" insert into t_rent(id, book_n, in_d, out_d)   ");
	   sql.append(" values(?, ?, sysdate, sysdate+14)   ");
	   
	   pstmt = conn.prepareStatement(sql.toString());
	   pstmt.setString(1,id2);
	   pstmt.setString(2, bookno);
	   pstmt.executeUpdate();
	    
	    sql = new StringBuilder();
	    sql.append(" select to_char(in_d, 'yyyy-mm-dd') as in_d, to_char(out_d, 'yyyy-mm-dd') as out_d ");
	    sql.append("  from t_rent ");
	    sql.append(" where book_n = ? ");
	    
	    pstmt = conn.prepareStatement(sql.toString());
	    pstmt.setString(1, bookno);
	
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	    	rentVO = new RentalVO();
	    	rentVO.setInNo(rs.getString("in_d"));
	    	rentVO.setOutNo(rs.getString("out_d"));
	    	rentVO.setId(id2);
	    	rentVO.setBookNo(bookno);
	    }
	   
	}catch(Exception e ) {
	   e.printStackTrace();
	}finally {
	   JDBCClose.close(pstmt, conn);
	}
	   return rentVO;
	}
 
	   /**
	    * 
   /**
	    * 
	    * 4. 반납윤서
	    * 회원번호 틀렸을 때
	    * 비밀번호 틀렸을 때
	    * join 해서 책 제목 불러오는 거
	    * 
	    */
	   
	public int checkoutinfo(String bookno2, String id3){

	    Connection conn = null;
	    PreparedStatement pstmt = null;
		//rentalVO rentVO = null;
		int cnt = 0;
	    try {
	    	
//	    	System.out.println(bookno2 + id3);
	       conn = new connectionFactory().getConnection();

	       ////////////////////////////////////////////////////
		   StringBuilder sql = new StringBuilder();
		   sql.append(" delete from t_rent where book_n = ? ");
		    
		    pstmt = conn.prepareStatement(sql.toString());
		    pstmt.setString(1, bookno2);
		
		    cnt = pstmt.executeUpdate();
	       
	    }catch(Exception e) {
	       e.printStackTrace();
	    }finally {
	    JDBCClose.close(pstmt, conn);
	    }
	    return cnt;
	    }
	
	


/* 대출된 책 다시 대출 안 되게...
	public List <RentalVO> norepeat  (String bookno2){

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RentalVO> list = new ArrayList<>();
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" sel t_rent(book_n)");
			sql.append(" select ? from dual A ")	;
			sql.append(" where not exists ")		;
			sql.append("(select 0 from t_rent where book_n = ?)");
				
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				RentalVO rentVO = new RentalVO();
				rentVO.setBookNo(rs.getString("book_n"));
				list.add(rentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return list;
	}
	*/
	
	
	//책 번호로 다시 체크?
	
 /*
	public String bookNocheck(String bookno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String ggg = null;
		RentalVO rentVO = new RentalVO();
		
		try {
		
		   conn = new ConnectionFactory().getConnection();
		   
		   StringBuilder sql = new StringBuilder();
		   sql.append(" select book_n from t_rent   ");
		   sql.append(" where exists    ");
		   sql.append(" (select 0 from t_rent    ");
		   sql.append(" where book_n = ?  )  ");
		   
		   pstmt = conn.prepareStatement(sql.toString());
		   pstmt.setString(1, bookno);
		   
		   ResultSet rs = pstmt.executeQuery();
		   
			while(rs.next()) {
				ggg = rs.getString("book_n");		}
		   
		}catch(Exception e ) {
		   e.printStackTrace();
		}finally {
		   JDBCClose.close(pstmt, conn);
		}
		   return ggg;
		}*/
}
	
	