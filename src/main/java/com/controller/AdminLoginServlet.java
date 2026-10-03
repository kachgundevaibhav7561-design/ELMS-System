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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int adminId = Integer.parseInt(
                request.getParameter("adminId"));

        String password =
                request.getParameter("password");

        AdminDAO dao = new AdminDAO();

        Admin admin =
                dao.loginAdmin(adminId, password);

        if (admin != null) {

            System.out.println(
                    "ADMIN LOGIN SUCCESSFUL");

            // Get old session if available
            HttpSession oldSession =
                    request.getSession(false);

            // Destroy old session
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Create fresh admin session
            HttpSession session =
                    request.getSession(true);

            session.setAttribute(
                    "admin", admin);

            session.setAttribute(
                    "adminId",
                    admin.getAdminId());

            session.setAttribute(
                    "adminName",
                    admin.getName());

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/adminDashboard.jsp");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp"
                    + "?error=Invalid%20Admin%20ID%20or%20Password");
        }
    }
}