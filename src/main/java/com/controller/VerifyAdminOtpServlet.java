package com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyAdminOtpServlet")
public class VerifyAdminOtpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String otpText = request.getParameter("otp");

        HttpSession session = request.getSession(false);

        // ==========================================
        // CHECK SESSION
        // ==========================================

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminForgotPassword.jsp?error=session");

            return;
        }

        // ==========================================
        // CHECK EMPTY OTP
        // ==========================================

        if (otpText == null || otpText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminVerifyOtp.jsp?error=empty");

            return;
        }

        try {

            int enteredOtp = Integer.parseInt(otpText);

            Object savedOtpObject =
                    session.getAttribute("adminResetOtp");

            Object expiryObject =
                    session.getAttribute("adminResetOtpExpiry");

            // ==========================================
            // CHECK OTP DATA
            // ==========================================

            if (savedOtpObject == null
                    || expiryObject == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminForgotPassword.jsp?error=session");

                return;
            }

            int savedOtp = (Integer) savedOtpObject;

            long expiryTime = (Long) expiryObject;

            // ==========================================
            // CHECK OTP EXPIRY
            // ==========================================

            if (System.currentTimeMillis() > expiryTime) {

                session.removeAttribute("adminResetOtp");
                session.removeAttribute("adminResetOtpExpiry");

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminForgotPassword.jsp?error=expired");

                return;
            }

            // ==========================================
            // CHECK OTP
            // ==========================================

            if (enteredOtp != savedOtp) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/admin/adminVerifyOtp.jsp?error=invalid");

                return;
            }

            // ==========================================
            // OTP VERIFIED
            // ==========================================

            System.out.println(
                    "ADMIN OTP VERIFIED SUCCESSFULLY");

            session.setAttribute(
                    "adminOtpVerified",
                    true);

            // OTP cannot be reused
            session.removeAttribute("adminResetOtp");
            session.removeAttribute("adminResetOtpExpiry");

            // ==========================================
            // GO TO ADMIN RESET PASSWORD PAGE
            // ==========================================

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminResetPassword.jsp");

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminVerifyOtp.jsp?error=invalid");
        }
    }
}