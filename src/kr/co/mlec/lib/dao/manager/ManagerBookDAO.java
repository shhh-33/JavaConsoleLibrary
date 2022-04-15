package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.dao.manager;

import kr.co.mlec.lib.vo.BookVO;
import kr.co.mlec.util.JDBCClose;
import kr.co.mlec.util.connectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerBookDAO {

	// 2. 관리자 - 도서 목록 추가
	public BookVO insertBook(String book_n, String title, String author, String publisher, String price) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		BookVO bookVO = null;

		try {
			conn = new connectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_book(book_n, title , author, publisher, price) ");
			sql.append(" values(?, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, book_n);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setString(4, publisher);
			pstmt.setString(5, price);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return bookVO;

	}

	// 3. 관리자 - 도서 목록 수정

	public int updateBook(BookVO ub) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// bookVO bookVO = null;
		int a = 0;
		try {
			conn = new connectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update t_book ");
			sql.append(" set title=?,author=?,publisher=?,price=? ");
			sql.append(" where book_n = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(5, ub.getBookNo());
			pstmt.setString(1, ub.getTitle());
			pstmt.setString(2, ub.getAuthor());
			pstmt.setString(3, ub.getPublisher());
			pstmt.setString(4, ub.getPrice());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return a;
	}

	// 4. 관리자 - 도서 목록 삭제
	public List<BookVO> deleteBook(String book_n) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BookVO> bookdelete = new ArrayList<>();
			try {
			conn = new connectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from t_book where book_n = ? ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, book_n);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String book_n1 = rs.getString("book_n");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				String price = rs.getString("price");

				BookVO book = new BookVO(book_n1, title, author, publisher, price);
				bookdelete.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return bookdelete;
	}

	
}
