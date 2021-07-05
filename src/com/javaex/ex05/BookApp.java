package com.javaex.ex05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) throws IOException {
		
//		BufferedReader bfR = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(System.in);
		
		AuthorDao authorDAO = new AuthorDao();
		BookDao bookDAO = new BookDao();
		
		//(1)/////////////////////////////////////////////(1)
		// 작가테이블 책테이블 + 시퀀스 완성 돼있어야함
		
		
		// authorDao.authorInsert(); 작가 테이터 추가(6)
		authorDAO.authorInsert(new AuthorVo("이문열", "정보1"));
		authorDAO.authorInsert(new AuthorVo("박경리", "정보2"));
		authorDAO.authorInsert(new AuthorVo("이고잉", "정보3"));
		authorDAO.authorInsert(new AuthorVo("기안84", "정보4"));
		authorDAO.authorInsert(new AuthorVo("강풀", "정보5"));
		authorDAO.authorInsert(new AuthorVo("김영하", "정보6"));
		
		// bookDao.bookInsert(); 책 데이터 추가(8)
		bookDAO.bookInsert(new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1));
		bookDAO.bookInsert(new BookVo("삼국지", "민음사", "2002-03-01", 1));
		bookDAO.bookInsert(new BookVo("토지", "마로니에북스", "2012-08-15", 2));
		bookDAO.bookInsert(new BookVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 3));
		bookDAO.bookInsert(new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4));
		bookDAO.bookInsert(new BookVo("순정만화", "재미주의", "2011-08-03", 5));
		bookDAO.bookInsert(new BookVo("오직두사람", "문학동네", "2017-05-04", 6));
		bookDAO.bookInsert(new BookVo("26년", "재미주의", "2012-02-04", 5));
		
		// 책 리스트 출력
		showList(bookDAO.getBookList());
		
		// 책수정 - 출력
		bookDAO.bookUpdate(new BookVo(4, "제목은 이호진짱", "이호진짱짱", "1993-10-07"));
		showList(bookDAO.getBookList());

		// 책삭제 - 출력
		bookDAO.bookDelete(new BookVo(4));
		showList(bookDAO.getBookList());
		
		//(2)/////////////////////////////////////////////(2)
		
		// 스캐너로 키워드 입력받음: 검색시스템
		System.out.println("찾고싶은 책의 검색어를 입력해 주세요.");
		System.out.print("검색어: ");
		String keyword = scan.nextLine();
//		String keyword = bfR.readLine();

		// bookDao.getBookList(keyword); 책 정보 출력
		showList(bookDAO.getBookList(keyword));
		
//		bfR.close();
		scan.close();
		
	}
	
	// 책 리스트 풀력 메서드
	public static void showList(List<BookVo> e) {
		for (BookVo b : e ) {
			System.out.println(
					"책코드번호: " + b.getBookID() + ",\t"
					+ "책제목: " + b.getTitle() + ",\t"
					+ "출판사: " + b.getPubs() + ",\t"
					+ "출판일: " + b.getPubDate() + ",\t"
					+ "작가: " + b.getAuthorName() + ",\t"
					);
		}
		System.out.println();
		System.out.println("======= NEXT =======");
		System.out.println();
	}


}
