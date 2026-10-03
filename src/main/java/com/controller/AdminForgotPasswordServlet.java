package com.controller;

import java.io.IOException;
import java.util.Random;

import com.DAO.AdminDAO;
import com.pojo.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminForgotPasswordServlet")
public class AdminForgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        if (email == null || email.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminForgotPassword.jsp?error=empty");

            return;
        }

        email = email.trim();

        try {

            AdminDAO dao = new AdminDAO();

            // We need to find admin by email here.
            Admin admin = dao.getAdminByEmail(email);

            if (admin == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminForgotPassword.jsp?error=invalid");

                return;
            }

            // ==========================================
            // GENERATE 6 DIGIT OTP
            // ==========================================

            Random random = new Random();

            int otp = 100000 + random.nextInt(900000);

            System.out.println(
                    "ADMIN PASSWORD RESET OTP: " + otp);

            // ==========================================
            // STORE OTP IN SESSION
            // ==========================================

            HttpSession session = request.getSession();

            session.setAttribute("adminResetEmail", email);
            session.setAttribute(
                    "adminResetId",
                    admin.getAdminId());

            session.setAttribute("adminResetOtp", otp);

            // OTP expiry - 5 minutes
            long expiryTime =
                    System.currentTimeMillis()
                    + (5 * 60 * 1000);

            session.setAttribute(
                    "adminResetOtpExpiry",
                    expiryTime);

            // ==========================================
            // GO TO OTP PAGE
            // ==========================================

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminVerifyOtp.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminForgotPassword.jsp?error=failed");
        }
    }
}