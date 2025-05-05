package com.java.jdbcServ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCookieServlet
 */

public class AddCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ckAnanta = new Cookie("Ananta", "69");
		ckAnanta.setMaxAge(1000 * 60 * 60 * 24);
		response.addCookie(ckAnanta);
		
		Cookie ckAnshu = new Cookie("Anshu", "96");
		ckAnshu.setMaxAge(1000 * 60 * 60 * 24);
		response.addCookie(ckAnshu);
		
		Cookie ckAli = new Cookie("Ali", "56");
		ckAli.setMaxAge(1000 * 60 * 60 * 24);
		response.addCookie(ckAli);
		
		PrintWriter out = response.getWriter();
		out.println("<b> Cookies are created successfully</b>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
