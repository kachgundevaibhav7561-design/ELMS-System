package com.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.DAO.LeaveRequestDAO;
import com.pojo.LeaveRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ApplyLeaveServlet")
public class ApplyLeaveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        try {

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

            Integer empId =
                    (Integer) session.getAttribute("empId");

            // ==========================================
            // GET FORM DATA
            // ==========================================

            String leaveType =
                    request.getParameter("leaveType");

            String fromDateString =
                    request.getParameter("fromDate");

            String toDateString =
                    request.getParameter("toDate");

            String reason =
                    request.getParameter("reason");

            // ==========================================
            // VALIDATION
            // ==========================================

            if (leaveType == null ||
                leaveType.trim().isEmpty() ||
                fromDateString == null ||
                fromDateString.trim().isEmpty() ||
                toDateString == null ||
                toDateString.trim().isEmpty() ||
                reason == null ||
                reason.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/applyLeave.jsp?error=empty");

                return;
            }

            // ==========================================
            // CONVERT DATE
            // ==========================================

            Date fromDate =
                    Date.valueOf(fromDateString);

            Date toDate =
                    Date.valueOf(toDateString);

         // ==========================================
         // CHECK DATE RANGE
         // ==========================================

         LocalDate today = LocalDate.now();

         LocalDate fromLocalDate = fromDate.toLocalDate();
         LocalDate toLocalDate = toDate.toLocalDate();

         // From Date cannot be before today
         if (fromLocalDate.isBefore(today)) {

             System.out.println("LEAVE APPLICATION FAILED - PAST DATE");

             response.sendRedirect(
                     request.getContextPath()
                     + "/employee/applyLeave.jsp?error=pastdate");

             return;
         }

         // To Date cannot be before From Date
         if (toLocalDate.isBefore(fromLocalDate)) {

             System.out.println("LEAVE APPLICATION FAILED - INVALID DATE RANGE");

             response.sendRedirect(
                     request.getContextPath()
                     + "/employee/applyLeave.jsp?error=date");

             return;
         }

            // ==========================================
            // CREATE LEAVE OBJECT
            // ==========================================

            LeaveRequest leave =
                    new LeaveRequest();

            leave.setEmpId(empId);
            leave.setLeaveType(leaveType);
            leave.setFromDate(fromDate);
            leave.setToDate(toDate);
            leave.setReason(reason.trim());
            leave.setStatus("Pending");

            // ==========================================
            // SAVE LEAVE REQUEST
            // ==========================================

            LeaveRequestDAO dao =
                    new LeaveRequestDAO();

            boolean result =
                    dao.applyLeave(leave);

            // ==========================================
            // RESULT
            // ==========================================

            if (result) {

                System.out.println(
                        "LEAVE APPLICATION SUCCESSFUL");

                response.sendRedirect(
                        request.getContextPath()
                        + "/LeaveHistoryServlet?success=true");

            } else {

                System.out.println(
                        "LEAVE APPLICATION FAILED");

                response.sendRedirect(
                        request.getContextPath()
                        + "/employee/applyLeave.jsp?error=failed");
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "INVALID DATE FORMAT");

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/applyLeave.jsp?error=date");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/employee/applyLeave.jsp?error=failed");
        }
    }
}

