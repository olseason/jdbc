package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertApp {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 2.1 commit설정 변경
			conn.setAutoCommit(false);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO book ";
			query += " VALUES (seq_book_id.nextval, ?, ?, ?, ? ) ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, "삼국지");// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, "민음사");// ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, "2002-03-01");// ?(물음표) 중 3번째, 순서중요
			pstmt.setInt(4, 1);// ?(물음표) 중 4번째, 순서중요

			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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

	}

}
