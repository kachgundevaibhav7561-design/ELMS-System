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

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Get existing session
			HttpSession session = request.getSession(false);

			// Check login
			if (session == null || session.getAttribute("empId") == null) {

				response.sendRedirect(request.getContextPath() + "/common/employeeLogin.jsp");

				return;
			}

			// Get Employee ID from session
			Integer empId = (Integer) session.getAttribute("empId");

			// Get updated values from form
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String department = request.getParameter("department");

			// Create Employee object
			Employee employee = new Employee();

			employee.setEmpId(empId);
			employee.setName(name);
			employee.setEmail(email);
			employee.setPassword(password);
			employee.setDepartment(department);

			// DAO
			EmployeeDAO dao = new EmployeeDAO();

			// Update employee
			boolean status = dao.updateEmployee(employee);

			if (status) {

				System.out.println("PROFILE UPDATED SUCCESSFULLY");

				// Redirect to Profile Servlet
				response.sendRedirect(request.getContextPath() + "/ProfileServlet");

			} else {

				System.out.println("PROFILE UPDATE FAILED");

				response.sendRedirect(request.getContextPath() + "/EditProfileServlet?error=update");

			}

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}