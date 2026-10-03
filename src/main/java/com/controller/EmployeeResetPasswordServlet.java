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

@WebServlet("/EmployeeResetPasswordServlet")
public class EmployeeResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ==========================================
        // CHECK OTP VERIFICATION
        // ==========================================

        if (session == null
                || session.getAttribute("otpVerified") == null
                || !(Boolean) session.getAttribute("otpVerified")) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/forgotPassword.jsp");

            return;
        }

        String newPassword =
                request.getParameter("newPassword");

        String confirmPassword =
                request.getParameter("confirmPassword");

        // ==========================================
        // CHECK EMPTY PASSWORD
        // ==========================================

        if (newPassword == null
                || newPassword.trim().isEmpty()
                || confirmPassword == null
                || confirmPassword.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/resetPassword.jsp?error=empty");

            return;
        }

        // ==========================================
        // PASSWORD LENGTH
        // ==========================================

        if (newPassword.length() < 6) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/resetPassword.jsp?error=length");

            return;
        }

        // ==========================================
        // PASSWORD MATCH
        // ==========================================

        if (!newPassword.equals(confirmPassword)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/resetPassword.jsp?error=mismatch");

            return;
        }

        try {

            Object empIdObject =
                    session.getAttribute("resetEmpId");

            if (empIdObject == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/forgotPassword.jsp?error=session");

                return;
            }

            int empId = (Integer) empIdObject;

            EmployeeDAO dao = new EmployeeDAO();

            Employee employee =
                    dao.getEmployeeById(empId);

            if (employee == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/forgotPassword.jsp?error=invalid");

                return;
            }

            // ==========================================
            // UPDATE PASSWORD
            // ==========================================

            employee.setPassword(newPassword);

            boolean updated =
                    dao.updateEmployee(employee);

            if (updated) {

                System.out.println(
                        "EMPLOYEE PASSWORD RESET SUCCESSFUL");

                // Clear reset information
                session.removeAttribute("resetEmail");
                session.removeAttribute("resetEmpId");
                session.removeAttribute("otpVerified");

                response.sendRedirect(
                        request.getContextPath()
                        + "/common/employeeLogin.jsp?reset=success");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/resetPassword.jsp?error=failed");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/resetPassword.jsp?error=failed");
        }
    }
}