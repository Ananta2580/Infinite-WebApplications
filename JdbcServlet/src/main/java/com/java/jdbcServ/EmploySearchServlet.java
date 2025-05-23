package com.java.jdbcServ;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.jdbc.dao.EmployDao;
import com.java.jdbc.dao.EmployDaoImpl;
import com.java.jdbc.model.Employ;

/**
 * Servlet implementation class EmploySearchServlet
 */

public class EmploySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmploySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int empId = Integer.parseInt(request.getParameter("empNo"));
		EmployDao dao = new EmployDaoImpl();
		try {
			Employ employ = dao.searchEmployDao(empId);
			if(employ != null) {
				out.println("Employ No: "+employ.getEmpId()+" <br>");
				out.println("Employ Name: "+employ.getName()+" <br>");
				out.println("Gender: "+employ.getGender()+" <br>");
				out.println("Department: "+employ.getDept()+" <br>");
				out.println("Designation: "+employ.getDesig()+" <br>");
				out.println("Basic Salary: "+employ.getBasic()+" <br> <hr>");
			}
			else {
				out.println("No records found");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
