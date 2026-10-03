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


@WebServlet("/LeaveHistoryServlet")
public class LeaveHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        // ==========================================
        // CHECK EMPLOYEE SESSION
        // ==========================================

        HttpSession session = request.getSession(false);


        if (session == null ||
            session.getAttribute("empId") == null) {


            response.sendRedirect(
                    request.getContextPath()
                    + "/common/employeeLogin.jsp");

            return;
        }


        // ==========================================
        // GET EMPLOYEE ID
        // ==========================================

        int empId = (int) session.getAttribute("empId");


        // ==========================================
        // FETCH LEAVE HISTORY
        // ==========================================

        LeaveRequestDAO dao = new LeaveRequestDAO();

        List<LeaveRequest> leaveHistory =
                dao.getLeaveHistoryByEmployeeId(empId);


        // ==========================================
        // SEND DATA TO JSP
        // ==========================================

        request.setAttribute(
                "leaveHistory",
                leaveHistory);



        // ==========================================
        // OPEN JSP
        // ==========================================

        request.getRequestDispatcher(
                "/employee/leaveHistory.jsp")
                .forward(request, response);

    }

}