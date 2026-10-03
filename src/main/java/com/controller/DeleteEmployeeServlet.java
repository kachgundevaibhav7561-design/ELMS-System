package com.controller;

import java.io.IOException;

import com.DAO.EmployeeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    // ==========================================
    // DELETE EMPLOYEE
    // ==========================================

    protected void doGet(HttpServletRequest request,
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
        // GET EMPLOYEE ID
        // ==========================================

        int empId = Integer.parseInt(
                request.getParameter("empId"));


        // ==========================================
        // DELETE EMPLOYEE
        // ==========================================

        EmployeeDAO dao = new EmployeeDAO();

        boolean result = dao.deleteEmployee(empId);


        // ==========================================
        // CHECK RESULT
        // ==========================================

        if (result) {

            System.out.println(
                    "EMPLOYEE DELETE SUCCESSFUL");

        } else {

            System.out.println(
                    "EMPLOYEE DELETE FAILED");
        }


        // ==========================================
        // BACK TO MANAGE EMPLOYEES
        // ==========================================

        response.sendRedirect(
                request.getContextPath()
                + "/ManageEmployeeServlet");
    }
}