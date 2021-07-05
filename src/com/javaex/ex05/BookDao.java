package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// DB Connection()
	public void getConnection() {
		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pw);
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: Driver Loading Fail: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
	}
	
	// Connection close()
	public void close() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	// INSERT
	public int bookInsert(BookVo b) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" INSERT INTO books "
					+ " VALUES ( sqc_book_id.NEXTVAL, ?, ?, ?, ? ) "
					);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getPubs());
			pstmt.setString(3, b.getPubDate());
			pstmt.setInt(4, b.getAuthorID());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}
	
	// SELECT
	public List<BookVo> getBookList() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	bo.book_id 책코드번호, "
					+ " 	bo.title 책제목, "
					+ " 	bo.pubs 출판사, "
					+ " 	bo.pub_date 출판일, "
					+ " 	at.author_name 작가 "
					+ " FROM "
					+ " 	books bo "
					+ " 	FULL JOIN authors at ON bo.author_id = at.author_id "
					);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookVo bookVO = new BookVo(rs.getInt("책코드번호"), rs.getString("책제목"), rs.getString("출판사"), rs.getString("출판일"), rs.getString("작가"));
				bookList.add(bookVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return bookList;
	}
	
	// SELECT search
	public List<BookVo> getBookList(String keyword) {
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	bo.book_id 책코드번호, "
					+ " 	bo.title 책제목, "
					+ " 	bo.pubs 출판사, "
					+ " 	bo.pub_date 출판일, "
					+ " 	at.author_name 작가 "
					+ " FROM "
					+ " 	books bo "
					+ " 	FULL JOIN authors at ON bo.author_id = at.author_id "
					+ " WHERE "
					+ " 	bo.title LIKE '%" + keyword + "%' "
					+ " 	OR bo.pubs LIKE '%" + keyword + "%' "
					+ " 	OR at.author_name LIKE '%" + keyword + "%' "
					);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookVo bookVO = new BookVo(rs.getInt("책코드번호"), rs.getString("책제목"), rs.getString("출판사"), rs.getString("출판일"), rs.getString("작가"));
				bookList.add(bookVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return bookList;
	}
	
	
	// UPDATE
	public int bookUpdate(BookVo b) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" UPDATE books "
					+ " SET "
					+ " 	title = ?, "
					+ " 	pubs = ?, "
					+ " 	pub_date = ? "
					+ " WHERE "
					+ " 	book_id = ? "
					);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getPubs());
			pstmt.setString(3, b.getPubDate());
			pstmt.setInt(4, b.getBookID());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}
	
	
	// DELETE
	public int bookDelete(BookVo b) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 	books "
					+ " WHERE "
					+ " 	book_id = ? "
					);
			pstmt.setInt(1, 4);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}
}
