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

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // ==========================================
        // CHECK ADMIN SESSION
        // ==========================================

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("adminId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp");

            return;
        }


        // ==========================================
        // GET DATA FROM addEmployee.jsp
        // ==========================================

        int empId = Integer.parseInt(
                request.getParameter("empId"));

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String department = request.getParameter("department");


        // ==========================================
        // CREATE EMPLOYEE OBJECT
        // ==========================================

        Employee employee = new Employee();

        employee.setEmpId(empId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setDepartment(department);


        // ==========================================
        // CALL DAO
        // ==========================================

        EmployeeDAO dao = new EmployeeDAO();

        boolean result = dao.addEmployee(employee);


        // ==========================================
        // SUCCESS / FAILURE
        // ==========================================

        if (result) {

            System.out.println(
                    "EMPLOYEE ADDED SUCCESSFULLY");

            response.sendRedirect(
                    request.getContextPath()
                    + "/ManageEmployeeServlet");

        } else {

            System.out.println(
                    "EMPLOYEE ADD FAILED");

            request.setAttribute(
                    "errorMessage",
                    "Unable to add employee.");

            request.getRequestDispatcher(
                    "/admin/addEmployee.jsp")
                    .forward(request, response);
        }
    }
}