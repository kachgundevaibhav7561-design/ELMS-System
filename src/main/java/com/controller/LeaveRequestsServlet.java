package com.controller;

import java.io.IOException;
import java.util.List;

import com.DAO.LeaveRequestDAO;
import com.pojo.LeaveRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LeaveRequestsServlet")
public class LeaveRequestsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // ==========================================
        // CHECK ADMIN SESSION
        // ==========================================

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adminId") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/common/adminLogin.jsp");

            return;
        }


        // ==========================================
        // DAO
        // ==========================================

        LeaveRequestDAO dao = new LeaveRequestDAO();


        // ==========================================
        // FILTER STATUS
        // ==========================================

        String status = request.getParameter("status");


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
        // GET LEAVE REQUESTS
        // ==========================================

        List<LeaveRequest> leaveRequests;

        int totalPages;


        // ==========================================
        // STATUS FILTER + PAGINATION
        // ==========================================

        if (status != null
                && !status.trim().isEmpty()
                && !status.equalsIgnoreCase("All")) {


            // Get filtered records

            leaveRequests =
                    dao.getLeaveRequestsByStatusAndPage(
                            status,
                            currentPage,
                            recordsPerPage);


            // Get total pages for selected status

            totalPages =
                    dao.getTotalLeaveRequestPagesByStatus(
                            status,
                            recordsPerPage);


        } else {


            // Get all records

            leaveRequests =
                    dao.getLeaveRequestsByPage(
                            currentPage,
                            recordsPerPage);


            // Get total pages for all records

            totalPages =
                    dao.getTotalLeaveRequestPages(
                            recordsPerPage);
        }


        // ==========================================
        // CHECK PAGE LIMIT
        // ==========================================

        if (totalPages > 0 && currentPage > totalPages) {

            currentPage = totalPages;


            // Reload correct page

            if (status != null
                    && !status.trim().isEmpty()
                    && !status.equalsIgnoreCase("All")) {


                leaveRequests =
                        dao.getLeaveRequestsByStatusAndPage(
                                status,
                                currentPage,
                                recordsPerPage);

            } else {


                leaveRequests =
                        dao.getLeaveRequestsByPage(
                                currentPage,
                                recordsPerPage);
            }
        }


        // ==========================================
        // CARDS COUNT
        // ==========================================

        int pendingCount =
                dao.getLeaveCountByStatus("Pending");

        int approvedCount =
                dao.getLeaveCountByStatus("Approved");

        int rejectedCount =
                dao.getLeaveCountByStatus("Rejected");


        // ==========================================
        // SEND DATA TO JSP
        // ==========================================

        request.setAttribute(
                "leaveRequests",
                leaveRequests);

        request.setAttribute(
                "pendingCount",
                pendingCount);

        request.setAttribute(
                "approvedCount",
                approvedCount);

        request.setAttribute(
                "rejectedCount",
                rejectedCount);

        request.setAttribute(
                "selectedStatus",
                status);


        // Pagination data

        request.setAttribute(
                "currentPage",
                currentPage);

        request.setAttribute(
                "totalPages",
                totalPages);

        request.setAttribute(
                "recordsPerPage",
                recordsPerPage);


        // ==========================================
        // FORWARD TO JSP
        // ==========================================

        request.getRequestDispatcher(
                "/admin/leaveRequests.jsp")
                .forward(request, response);
    }
}