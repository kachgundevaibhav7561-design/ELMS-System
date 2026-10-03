package com.controller;
import com.DAO.LeaveRequestDAO;
import java.util.List;

import com.DAO.LeaveRequestDAO;
import com.pojo.LeaveRequest;
import java.io.IOException;

import com.DAO.EmployeeDAO;
import com.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmployeeDashboardServlet")
public class EmployeeDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Cache-Control",
		        "no-cache, no-store, must-revalidate");

		response.setHeader("Pragma", "no-cache");

		response.setDateHeader("Expires", 0);

		try {

			// 1. Get existing session
			HttpSession session = request.getSession(false);

			// 2. Check login session
			if (session == null || session.getAttribute("empId") == null) {

				response.sendRedirect(request.getContextPath() + "/common/employeeLogin.jsp");

				return;
			}

			// 3. Get Employee ID from session
			Integer empId = (Integer) session.getAttribute("empId");

			System.out.println("Employee ID: " + empId);

			// 4. Create EmployeeDAO object
			EmployeeDAO dao = new EmployeeDAO();

			// 5. Get employee details from database
			Employee emp = dao.getEmployeeById(empId);
			
			LeaveRequestDAO leaveDAO = new LeaveRequestDAO();

			int totalLeaves = leaveDAO.getTotalLeaveCountByEmployee(empId);
			int approvedLeaves = leaveDAO.getApprovedLeaveCountByEmployee(empId);
			int pendingLeaves = leaveDAO.getPendingLeaveCountByEmployee(empId);
			
			List<LeaveRequest> leaveHistory =
			        leaveDAO.getLeaveHistoryByEmployeeId(empId);

			request.setAttribute("totalLeaves", totalLeaves);
			request.setAttribute("approvedLeaves", approvedLeaves);
			request.setAttribute("pendingLeaves", pendingLeaves);	
			
			request.setAttribute("leaveHistory", leaveHistory);

			// 6. Check employee record
			if (emp != null) {

				System.out.println("Employee Found: " + emp.getName());

				// 7. Send employee object to JSP
				request.setAttribute("employee", emp);

				// 8. Forward to Dashboard JSP
				request.getRequestDispatcher("/employee/employeeDashboard.jsp").forward(request, response);

			} else {

				System.out.println("Employee Record Not Found");

				response.getWriter().println("Employee Record Not Found");
			}

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}