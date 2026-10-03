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

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// ==========================================
			// 1. GET EXISTING SESSION
			// ==========================================

			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("empId") == null) {

				response.sendRedirect(request.getContextPath() + "/common/employeeLogin.jsp");

				return;
			}

			// ==========================================
			// 2. GET EMPLOYEE ID FROM SESSION
			// ==========================================

			Integer empId = (Integer) session.getAttribute("empId");

			System.out.println("Profile Employee ID: " + empId);

			// ==========================================
			// 3. GET EMPLOYEE FROM DATABASE
			// ==========================================

			EmployeeDAO dao = new EmployeeDAO();

			Employee employee = dao.getEmployeeById(empId);

			// ==========================================
			// 4. CHECK EMPLOYEE
			// ==========================================

			if (employee != null) {

				System.out.println("Profile Employee Found: " + employee.getName());

				// Send employee object to JSP
				request.setAttribute("employee", employee);

				// Forward to Profile JSP
				request.getRequestDispatcher("/employee/profile.jsp").forward(request, response);

			} else {

				System.out.println("Profile Employee Not Found");

				response.getWriter().println("Employee Record Not Found");
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}