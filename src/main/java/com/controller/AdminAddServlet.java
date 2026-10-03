package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.connection.DBConnection;

/**
 * Servlet implementation class AdminAddServlet
 */
@WebServlet("/AdminAddServlet")
public class AdminAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    try {

	        // Get form data
	        int adminId = Integer.parseInt(request.getParameter("adminId"));
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        // Database Connection
	        Connection con = DBConnection.getConnection();

	        // SQL Query
	        String sql = "INSERT INTO admin (admin_id, name, email, password) VALUES (?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        // Set values
	        ps.setInt(1, adminId);
	        ps.setString(2, name);
	        ps.setString(3, email);
	        ps.setString(4, password);

	        // Execute Query
	        int result = ps.executeUpdate();

	        if (result > 0) {

	            System.out.println("Admin Added Successfully");

	            response.sendRedirect("admin/adminDashboard.jsp");

	        } else {

	            System.out.println("Admin Not Added");

	        }

	    } catch (Exception e) {

	        e.printStackTrace();


	}

	}
}

