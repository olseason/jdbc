package com.javaex.ex05;

public class BookVo {

	// field
	private int bookID;
	private String title;
	private String pubs;
	private String pubDate;
	private int authorID;
	private String authorName;
	private String authorDesc;

	public BookVo() {}
	
	public BookVo(int bookID) {
		this.bookID = bookID;
	}
	
	public BookVo(int bookID, String title, String pubs, String pubDate) {
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.bookID = bookID;
	}
	
	public BookVo(String title, String pubs, String pubDate, int authorID) {
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorID = authorID;
	}
	
	public BookVo(int bookID, String title, String pubs, String pubDate, int authorID) {
		this.bookID = bookID;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorID = authorID;
	}
	
	public BookVo(int bookID, String title, String pubs, String pubDate, String authorName) {
		this.bookID = bookID;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorName = authorName;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public int getBookID() {
		return bookID;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPubs() {
		return pubs;
	}
	
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getPubDate() {
		return pubDate;
	}
	
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getAuthorID() {
		return authorID;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}

	@Override
	public String toString() {
		return "BookVo [bookID=" + bookID + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorID=" + authorID + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	
	

}
