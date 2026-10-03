package com.controller;

import java.io.IOException;
import java.util.Random;

import com.DAO.EmployeeDAO;
import com.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmployeeForgotPasswordServlet")
public class EmployeeForgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/forgotPassword.jsp?error=empty");

            return;
        }

        email = email.trim();

        try {

            EmployeeDAO dao = new EmployeeDAO();

            Employee employee = dao.getEmployeeByEmail(email);

            // Employee not found
            if (employee == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/forgotPassword.jsp?error=invalid");

                return;
            }

            // ==========================================
            // GENERATE 6 DIGIT OTP
            // ==========================================

            Random random = new Random();

            int otp = 100000 + random.nextInt(900000);

            System.out.println(
                    "EMPLOYEE PASSWORD RESET OTP: " + otp);

            // ==========================================
            // STORE OTP IN SESSION
            // ==========================================

            HttpSession session = request.getSession();

            session.setAttribute("resetEmail", email);
            session.setAttribute("resetEmpId", employee.getEmpId());
            session.setAttribute("resetOtp", otp);

            // OTP expiry - 5 minutes
            long expiryTime =
                    System.currentTimeMillis()
                    + (5 * 60 * 1000);

            session.setAttribute(
                    "resetOtpExpiry",
                    expiryTime);

            // ==========================================
            // GO TO OTP PAGE
            // ==========================================

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/verifyOtp.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/forgotPassword.jsp?error=failed");
        }
    }
}