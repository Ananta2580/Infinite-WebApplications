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
import com.java.jdbc.model.Gender;

/**
 * Servlet implementation class EmployUpdateServlet
 */
public class EmployUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		EmployDao dao = new EmployDaoImpl();
		Employ employ = new Employ();
		
		int empId = Integer.parseInt(request.getParameter("empId"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dept = request.getParameter("dept");
        String desig = request.getParameter("desig");
        double basic = Double.parseDouble(request.getParameter("basic"));
        
        employ.setEmpId(empId);
        employ.setName(name);
        employ.setGender(Gender.valueOf(gender));
        employ.setDept(dept);
        employ.setDesig(desig);
        employ.setBasic(basic);
        
        try {
			String update = dao.updateEmployDao(employ);
			out.println(update);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
