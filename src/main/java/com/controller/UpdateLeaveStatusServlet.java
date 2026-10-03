package com.controller;

import java.io.IOException;

import com.DAO.LeaveRequestDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UpdateLeaveStatusServlet")
public class UpdateLeaveStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        // Get values from JSP

        int leaveId = Integer.parseInt(
                request.getParameter("leaveId")
        );

        String status =
                request.getParameter("status");


        // DAO call

        LeaveRequestDAO dao = new LeaveRequestDAO();

        boolean result =
                dao.updateLeaveStatus(leaveId, status);


        if(result) {

            System.out.println(
                    "Leave Status Updated Successfully"
            );

        } else {

            System.out.println(
                    "Leave Status Update Failed"
            );
        }


        // Back to leave requests page

        response.sendRedirect(
                request.getContextPath()
                + "/LeaveRequestsServlet"
        );
    }
}