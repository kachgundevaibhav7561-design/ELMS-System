package com.controller;

import java.io.IOException;
import java.util.List;

import com.DAO.EmployeeDAO;
import com.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ManageEmployeeServlet")
public class ManageEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check Admin Session

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("adminId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp");

            return;
        }

        // Create Employee DAO

        EmployeeDAO dao = new EmployeeDAO();

        // ==========================================
        // PAGINATION SETTINGS
        // ==========================================

        int currentPage = 1;

        int recordsPerPage = 5;

        // Get page number from URL

        String pageParameter = request.getParameter("page");

        if (pageParameter != null) {

            try {

                currentPage = Integer.parseInt(pageParameter);

            } catch (NumberFormatException e) {

                currentPage = 1;
            }
        }

        // Prevent invalid page number

        if (currentPage < 1) {
            currentPage = 1;
        }

        // ==========================================
        // GET EMPLOYEES FOR CURRENT PAGE
        // ==========================================

        List<Employee> employees =
                dao.getEmployeesByPage(
                        currentPage,
                        recordsPerPage);

        // ==========================================
        // GET TOTAL PAGES
        // ==========================================

        int totalPages =
                dao.getTotalEmployeePages(recordsPerPage);

        // If page is greater than total pages

        if (totalPages > 0 && currentPage > totalPages) {

            currentPage = totalPages;

            employees =
                    dao.getEmployeesByPage(
                            currentPage,
                            recordsPerPage);
        }

        // ==========================================
        // SEND DATA TO JSP
        // ==========================================

        request.setAttribute("employees", employees);

        request.setAttribute("currentPage", currentPage);

        request.setAttribute("totalPages", totalPages);

        request.setAttribute("recordsPerPage", recordsPerPage);

        // ==========================================
        // OPEN MANAGE EMPLOYEE PAGE
        // ==========================================

        request.getRequestDispatcher(
                "/admin/manageEmployees.jsp")
                .forward(request, response);
    }
}