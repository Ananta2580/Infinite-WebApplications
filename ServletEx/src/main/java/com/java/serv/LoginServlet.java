package com.java.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		
		response.setContentType("text/html");
		String user = "Ananta";
		String pass = "Ananta@2003";
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		
		if(user.equals(userName) && pass.equals(passWord)) {
			RequestDispatcher dis = request.getRequestDispatcher("Menu.html");
			dis.forward(request, response);
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("login.html");
			dis.include(request, response);
			out.println("<p style='color:red'>User not found</p>");
		}
		
	}

}
