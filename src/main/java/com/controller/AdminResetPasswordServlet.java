package com.controller;

import java.io.IOException;

import com.DAO.AdminDAO;
import com.pojo.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminResetPasswordServlet")
public class AdminResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // ==========================================
        // CHECK SESSION
        // ==========================================

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("adminOtpVerified") == null
                || !(Boolean) session.getAttribute("adminOtpVerified")) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminForgotPassword.jsp");

            return;
        }

        // ==========================================
        // GET PASSWORDS
        // ==========================================

        String newPassword =
                request.getParameter("newPassword");

        String confirmPassword =
                request.getParameter("confirmPassword");

        // ==========================================
        // CHECK EMPTY
        // ==========================================

        if (newPassword == null
                || newPassword.trim().isEmpty()
                || confirmPassword == null
                || confirmPassword.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminResetPassword.jsp?error=empty");

            return;
        }

        // ==========================================
        // PASSWORD LENGTH
        // ==========================================

        if (newPassword.length() < 6) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminResetPassword.jsp?error=length");

            return;
        }

        // ==========================================
        // PASSWORD MATCH
        // ==========================================

        if (!newPassword.equals(confirmPassword)) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminResetPassword.jsp?error=mismatch");

            return;
        }

        try {

            // ==========================================
            // GET ADMIN ID FROM SESSION
            // ==========================================

            Object adminIdObject =
                    session.getAttribute("adminResetId");

            if (adminIdObject == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminForgotPassword.jsp?error=session");

                return;
            }

            int adminId = (Integer) adminIdObject;

            // ==========================================
            // GET ADMIN
            // ==========================================

            AdminDAO dao = new AdminDAO();

            Admin admin =
                    dao.getAdminById(adminId);

            if (admin == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminForgotPassword.jsp?error=invalid");

                return;
            }

            // ==========================================
            // UPDATE PASSWORD
            // ==========================================

            boolean updated =
                    dao.updateAdminPassword(
                            adminId,
                            newPassword);

            if (updated) {

                System.out.println(
                        "ADMIN PASSWORD RESET SUCCESSFUL");

                // ==========================================
                // CLEAR RESET SESSION DATA
                // ==========================================

                session.removeAttribute("adminResetEmail");
                session.removeAttribute("adminResetId");
                session.removeAttribute("adminOtpVerified");

                // ==========================================
                // GO TO ADMIN LOGIN
                // ==========================================

                response.sendRedirect(
                        request.getContextPath()
                        + "/common/adminLogin.jsp?reset=success");

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminResetPassword.jsp?error=failed");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminResetPassword.jsp?error=failed");
        }
    }
}