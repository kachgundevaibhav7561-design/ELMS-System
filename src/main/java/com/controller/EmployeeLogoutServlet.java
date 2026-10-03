package com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmployeeLogoutServlet")
public class EmployeeLogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get existing session
		HttpSession session = request.getSession(false);

		// Invalidate employee session
		if (session != null) {
			session.invalidate();
		}

		System.out.println("EMPLOYEE LOGOUT SUCCESSFUL");

		// Redirect to Employee Login
		response.sendRedirect(request.getContextPath() + "/common/employeeLogin.jsp");
	}
}