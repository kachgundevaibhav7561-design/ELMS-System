package com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DAO.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int empId = Integer.parseInt(
                request.getParameter("empId"));

        String password =
                request.getParameter("password");

        LoginDao dao = new LoginDao();

        boolean status =
                dao.checkLogin(empId, password);

        if (status) {

            System.out.println(
                    "EMPLOYEE LOGIN SUCCESSFUL");

            // Get old session if available
            HttpSession oldSession =
                    request.getSession(false);

            // Destroy old session
            if (oldSession != null) {
                oldSession.invalidate();
            }

            // Create fresh employee session
            HttpSession session =
                    request.getSession(true);

            session.setAttribute("empId", empId);

            response.sendRedirect(
                    request.getContextPath()
                    + "/EmployeeDashboardServlet");

        } else {

            System.out.println(
                    "EMPLOYEE LOGIN FAILED");

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/employeeLogin.jsp?error=invalid");
        }
    }
}