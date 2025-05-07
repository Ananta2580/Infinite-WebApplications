package com.java.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.util.ConnectionHelper;
import com.java.lib.util.EncryptPassword;

public class LibraryDaoImpl implements LibraryDao{
	
	Connection conn;
	PreparedStatement ps;
	
	

	@Override
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassword());
		conn = ConnectionHelper.getConnection();
		String query = "insert into libusers(username,password) values(?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, libUsers.getUsername());
		ps.setString(2, encr);
		ps.executeUpdate();
		return "User Account Created Successfully";
	}

	@Override
	public int login(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassword());
		conn = ConnectionHelper.getConnection();
		String query = "select count(*)  cnt from LibUsers where username = ? and password = ? ";
		ps= conn.prepareStatement(query);
		ps.setString(1, libUsers.getUsername());
		ps.setString(2, encr);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	@Override
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String query;
		boolean isValid = true;
		if(searchType.equals("id")) {
			query = "select * from Books where id = ?";
		}else if(searchType.equals("bookname")) {
			query = "select * from Books where name = ?";
		}else if(searchType.equals("authorname")) {
			query = "select * from Books where author = ?";
		}else if(searchType.equals("dept")) {
			query = "select * from Books where dept = ?";
		}else {
			isValid = false;
			query = "select * from Books";
		}
		
		conn = ConnectionHelper.getConnection();
		ps = conn.prepareStatement(query);
		if (isValid == true) {
			ps.setString(1, searchValue);
		}
		ResultSet rs = ps.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}

	@Override
	public int issueOrNot(String username, int bookId) throws ClassNotFoundException, SQLException {
		conn = ConnectionHelper.getConnection();
		String sql = "select count(*) cnt from tranbook where username = ? and bookid = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, bookId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	@Override
	public String issueBook(String username, int bookId) throws ClassNotFoundException, SQLException {
		int count = issueOrNot(username, bookId);
		if(count == 0) {
			conn = ConnectionHelper.getConnection();
			String query = "insert into tranbook(username,bookid) values(?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, bookId);
			ps.executeUpdate();
			query = "update books set totalbooks = totalbooks-1 where id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			return "Book Id "+bookId+" Issued Successfully";
		}else {
			return "Book Id "+bookId+" for user "+username+" already issued";
		}
		
	}

	@Override
	public List<TranBook> accountDetails(String username) throws ClassNotFoundException, SQLException {
		conn = ConnectionHelper.getConnection();
		String query = "select * from tranbook where username = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		List<TranBook> bookIssued = new ArrayList<TranBook>();
		TranBook tranBook = null;
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("bookId"));
			tranBook.setUsername(rs.getString("username"));
			tranBook.setDate(rs.getDate("Fromdate"));
			bookIssued.add(tranBook);
		}
		return bookIssued;
	}

}
