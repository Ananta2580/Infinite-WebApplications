package com.java.lib.model;

import java.sql.Date;

public class TranBook {
	
	private String username;
	private int bookId;
	private Date date;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public TranBook(String username, int bookId, Date date) {
		super();
		this.username = username;
		this.bookId = bookId;
		this.date = date;
	}
	
	public TranBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
