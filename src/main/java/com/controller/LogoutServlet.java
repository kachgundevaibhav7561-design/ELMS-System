package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Prevent browser from showing cached pages
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // Get existing session
        HttpSession session = request.getSession(false);

        boolean isAdmin = false;

        if (session != null) {

            // Check whether logged-in user is Admin
            if (session.getAttribute("adminId") != null) {
                isAdmin = true;
            }

            // Destroy session
            session.invalidate();
        }

        // Redirect to correct login page
        if (isAdmin) {

        	response.sendRedirect(
        	        request.getContextPath()
        	        + "/common/adminLogin.jsp?logout=true");

        } else {

        	response.sendRedirect(
        	        request.getContextPath()
        	        + "/common/employeeLogin.jsp?logout=true");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}