package com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.jdbc.model.Employ;
import com.java.jdbc.model.Gender;
import com.java.jdbc.util.ConnectionHelper;
import com.java.jdbc.util.EncryptPassword;

public class EmployDaoImpl implements EmployDao{
	
	Connection connection;
	PreparedStatement ps;
	
	@Override
	public List<Employ> showEmployDao() throws ClassNotFoundException, SQLException {
		List<Employ> employList = new ArrayList<Employ>();
		Employ employ = null;
		
		connection = ConnectionHelper.getConnection();
		String query = "select * from Employ";
		ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			employ = new Employ();
			employ.setEmpId(rs.getInt("Empno"));
			employ.setName(rs.getString("Name"));
			employ.setGender(Gender.valueOf(rs.getString("Gender")));
			employ.setDept(rs.getString("Dept"));
			employ.setDesig(rs.getString("Desig"));
			employ.setBasic(rs.getDouble("Basic"));
			
			employList.add(employ);
		}
		return employList;
	}

	@Override
	public Employ searchEmployDao(int empno) throws ClassNotFoundException, SQLException {
		
		Employ employ = null;
		connection = ConnectionHelper.getConnection();
		String query = "select * from Employ where Empno = ?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, empno);
		ResultSet rs =ps.executeQuery();
		
		if(rs.next()) {
			employ = new Employ();
			employ.setEmpId(rs.getInt("Empno"));
			employ.setName(rs.getString("Name"));
			employ.setGender(Gender.valueOf(rs.getString("Gender")));
			employ.setDept(rs.getString("Dept"));
			employ.setDesig(rs.getString("Desig"));
			employ.setBasic(rs.getDouble("Basic"));
		}
		
		return employ;
	}

	@Override
	public String addEmploydao(Employ employ) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String query = "INSERT INTO Employ (Empno, Name, Gender, Dept, Desig, Basic) VALUES (?, ?, ?, ?, ?, ?)";
		ps = connection.prepareStatement(query);
		
		ps.setInt(1, employ.getEmpId());
		ps.setString(2, employ.getName());
		ps.setString(3, employ.getGender().toString());
		ps.setString(4, employ.getDept());
		ps.setString(5, employ.getDesig());
		ps.setDouble(6, employ.getBasic());
		
		ps.executeUpdate();
		
		return "Employ Record Inserted";
	}

	@Override
	public String deleteEmployDao(int empno) throws ClassNotFoundException, SQLException {
		Employ found = searchEmployDao(empno);
		if(found != null) {
			connection = ConnectionHelper.getConnection();
			String query = "delete from Employ where empno = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, empno);
			ps.executeUpdate();
			
			return "Employ record deleted";
		}
		return "Employ record not found";
	}

	@Override
	public String updateEmployDao(Employ employ) throws ClassNotFoundException, SQLException {
		Employ found = searchEmployDao(employ.getEmpId());
		if(found == null) {
			return "Employ record not found";
		}
		
		String query = "update Employ set Name = ?, Gender = ?, Dept = ?, Desig = ?, Basic = ? where Empno = ?";
		connection = ConnectionHelper.getConnection();
		ps = connection.prepareStatement(query);
		
		ps.setString(1, employ.getName());
		ps.setString(2, employ.getGender().toString());
		ps.setString(3, employ.getDept());
		ps.setString(4, employ.getDesig());
		ps.setDouble(5, employ.getBasic());
		ps.setInt(6, employ.getEmpId());
		
		ps.executeUpdate();
		
		return "Record updated successfully";
	}

	@Override
	public String addUser(String user, String pwd) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(pwd);
		String query = "insert into Login values(?,?)";
		
		connection = ConnectionHelper.getConnection();
		ps = connection.prepareStatement(query);
		
		ps.setString(1, user);
		ps.setString(2, encr);
		
		ps.executeUpdate();
		
		return "User Inserted Successfully";
	}

	@Override
	public int authenticate(String user, String pwd) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(pwd);
		String cmd = "select count(*) cnt from Login where username=? and password=?";
		connection = ConnectionHelper.getConnection();
		ps = connection.prepareStatement(cmd);
		ps.setString(1, user); 
		ps.setString(2, encr);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt("cnt");
	}

}
