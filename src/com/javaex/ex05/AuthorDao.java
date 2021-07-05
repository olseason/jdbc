package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// field
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// constructors

	// method getter/setter

	// method
	// DB연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
	}
	
	// 자원정리
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	
	
	// 작가 리스트 메서드
	public List<AuthorVo> getAuthorList() {
		// DB값을 가져와서 ArrayList로 전달
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		getConnection();
		
		try {
		// 3. SQL문 준비 / 바인딩 / 실행
		pstmt = conn.prepareStatement(
				" SELECT "
				+ "		author_id, "
				+ "		author_name, "
				+ "		author_desc "
				+ " FROM "
				+ "		authors "
				+ " ORDER BY "
				+ " 	author_id ASC ");

		rs = pstmt.executeQuery();
		
		// 4.결과처리
		while(rs.next()) {
			int authorID = rs.getInt("author_id");
			String authorName = rs.getString("author_name");
			String authorDesc = rs.getString("author_desc");

			AuthorVo authorVO = new AuthorVo(authorID, authorName, authorDesc);
			
			authorList.add(authorVO);
		}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();
		
		return authorList;
	}

	// 작가 1명 불러오기 메서드
	public void getAuthorOne() {

	}

	// 작가 등록 메서드
	public int authorInsert(AuthorVo a) {
		
		int count = -1;
		
		getConnection();
		
		try {
	    // 3. SQL문 준비 / 바인딩 / 실행
		pstmt = conn.prepareStatement(
				" INSERT INTO authors "
				+ " VALUES ( sqc_author_id.NEXTVAL, ?, ? ) "
				);
		
		pstmt.setString(1, a.getAuthorName());
		pstmt.setString(2, a.getAuthorDesc());
		
		count = pstmt.executeUpdate();
	    
	    // 4.결과처리
		System.out.println(count + "건 등록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count; // 성공 개수 리턴
	}

	// 작가 수정 메서드
	public int authorUpdate(AuthorVo a) {

		int count = -1;
		
		getConnection();
		
		try {
	    // 3. SQL문 준비 / 바인딩 / 실행
	    pstmt = conn.prepareStatement(
	    		" UPDATE "
	    		+ "		authors "
	    		+ " SET "
	    		+ " 	author_name = ?, "
	    		+ " 	author_desc = ? "
	    		+ " WHERE "
	    		+ " 	author_id = ? "
	    		);
	    
	    pstmt.setString(1, a.getAuthorName());
	    pstmt.setString(2, a.getAuthorDesc());
	    pstmt.setInt(3, a.getAuthorID());
	    
	    count = pstmt.executeUpdate();
	    // 4.결과처리
	    System.out.println(count + "건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;

	}
	
	// 작가 삭제 메서드
	public int authorDelete(AuthorVo a) {

		int count = -1;
		
		getConnection();
		
	    // 3. SQL문 준비 / 바인딩 / 실행
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM authors "
					+ " WHERE author_id = ? "
					);
			pstmt.setInt(1, a.getAuthorID());
		    
		    count = pstmt.executeUpdate();
		    
		    // 4.결과처리
		    System.out.println(count + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
	    close();
	    
		return count;

	}

}
