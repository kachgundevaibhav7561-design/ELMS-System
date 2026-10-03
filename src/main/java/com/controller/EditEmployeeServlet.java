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

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    // ==========================================
    // GET - OPEN EDIT EMPLOYEE FORM
    // ==========================================

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check Admin Session

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("adminId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp");

            return;
        }


        // Get Employee ID

        int empId = Integer.parseInt(
                request.getParameter("empId"));


        // Get Employee From Database

        EmployeeDAO dao = new EmployeeDAO();

        Employee employee = dao.getEmployeeById(empId);


        // Check Employee

        if (employee != null) {

            // Send employee object to JSP

            request.setAttribute(
                    "employee",
                    employee);


            // Open Edit Employee Page

            request.getRequestDispatcher(
                    "/admin/editEmployee.jsp")
                    .forward(request, response);

        } else {

            // Employee Not Found

            response.sendRedirect(
                    request.getContextPath()
                    + "/ManageEmployeeServlet");
        }
    }


    // ==========================================
    // POST - UPDATE EMPLOYEE
    // ==========================================

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // Check Admin Session

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("adminId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp");

            return;
        }


        // Get Updated Employee Data

        int empId = Integer.parseInt(
                request.getParameter("empId"));

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String department = request.getParameter("department");


        // Create Employee Object

        Employee employee = new Employee();

        employee.setEmpId(empId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setDepartment(department);


        // Update Employee

        EmployeeDAO dao = new EmployeeDAO();

        boolean result = dao.updateEmployee(employee);


        // Check Result

        if (result) {

            System.out.println(
                    "EMPLOYEE UPDATE SUCCESSFUL");


            // Go back to Manage Employees

            response.sendRedirect(
                    request.getContextPath()
                    + "/ManageEmployeeServlet");

        } else {

            System.out.println(
                    "EMPLOYEE UPDATE FAILED");


            // Send Error Message

            request.setAttribute(
                    "errorMessage",
                    "Unable to update employee.");


            // Keep Employee Data

            request.setAttribute(
                    "employee",
                    employee);


            // Back to Edit Page

            request.getRequestDispatcher(
                    "/admin/editEmployee.jsp")
                    .forward(request, response);
        }
    }
}