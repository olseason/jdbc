package com.javaex.ex05;

public class AuthorVo {

	// field
	protected int authorID;
	protected String authorName;
	protected String authorDesc;

	// constructors
	public AuthorVo() {

	}
	
	public AuthorVo(int authorID) {
		this.authorID = authorID;
	}
	
	public AuthorVo(String authorName, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	public AuthorVo(int authorID, String authorName, String authorDesc) {
		this.authorID = authorID;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	// method getter/setter
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

	// method
	public String toString() {
		return "AuthorVo [authorID= " + authorID + ", authorName= " + authorName + ", authorDesc= " + authorDesc + "]";
	}

}
