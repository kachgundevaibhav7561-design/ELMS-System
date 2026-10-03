package com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyEmployeeOtpServlet")
public class VerifyEmployeeOtpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String otpText = request.getParameter("otp");

        HttpSession session = request.getSession(false);

        // Session not found
        if (session == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/forgotPassword.jsp?error=session");

            return;
        }

        // OTP empty
        if (otpText == null || otpText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/verifyOtp.jsp?error=empty");

            return;
        }

        try {

            int enteredOtp = Integer.parseInt(otpText);

            Object savedOtpObject =
                    session.getAttribute("resetOtp");

            Object expiryObject =
                    session.getAttribute("resetOtpExpiry");

            // OTP data not available
            if (savedOtpObject == null
                    || expiryObject == null) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/forgotPassword.jsp?error=session");

                return;
            }

            int savedOtp = (Integer) savedOtpObject;

            long expiryTime = (Long) expiryObject;

            // ==========================================
            // CHECK OTP EXPIRY
            // ==========================================

            if (System.currentTimeMillis() > expiryTime) {

                session.removeAttribute("resetOtp");
                session.removeAttribute("resetOtpExpiry");

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/forgotPassword.jsp?error=expired");

                return;
            }

            // ==========================================
            // CHECK OTP
            // ==========================================

            if (enteredOtp != savedOtp) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/verifyOtp.jsp?error=invalid");

                return;
            }

            // ==========================================
            // OTP SUCCESS
            // ==========================================

            System.out.println(
                    "EMPLOYEE OTP VERIFIED SUCCESSFULLY");

            session.setAttribute(
                    "otpVerified",
                    true);

            // OTP should not be usable again
            session.removeAttribute("resetOtp");
            session.removeAttribute("resetOtpExpiry");

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/resetPassword.jsp");

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/verifyOtp.jsp?error=invalid");
        }
    }
}