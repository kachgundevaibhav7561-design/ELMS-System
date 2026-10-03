package com.controller;

import java.io.IOException;

import com.DAO.EmployeeDAO;
import com.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Get existing session
			HttpSession session = request.getSession(false);

			// Check login
			if (session == null || session.getAttribute("empId") == null) {

				response.sendRedirect(request.getContextPath() + "/common/employeeLogin.jsp");

				return;
			}

			// Get employee ID
			Integer empId = (Integer) session.getAttribute("empId");

			System.out.println("Edit Profile - Employee ID: " + empId);

			// DAO
			EmployeeDAO dao = new EmployeeDAO();

			// Get employee details
			Employee employee = dao.getEmployeeById(empId);

			if (employee != null) {

				// Send employee object to JSP
				request.setAttribute("employee", employee);

				// Open Edit Profile page
				request.getRequestDispatcher("/employee/editProfile.jsp").forward(request, response);

			} else {

				response.getWriter().println("Employee Record Not Found");

			}

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}