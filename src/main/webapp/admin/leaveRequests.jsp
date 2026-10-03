<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.pojo.LeaveRequest"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<meta name="viewport"
    content="width=device-width, initial-scale=1.0">

<title>ELMS - Leave Requests</title>


<!-- Bootstrap -->

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet">


<!-- Font Awesome -->

<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">


<!-- Custom CSS -->

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/leaveRequests.css">

</head>


<body>


<!-- =================================
     SIDEBAR
================================= -->

<div class="sidebar">


    <!-- Logo -->

    <div class="logo">

        <i class="fa-solid fa-calendar-check"></i>

        <span>ELMS Admin</span>

    </div>


    <!-- Admin Info -->

    <div class="admin-info">

        <div class="admin-avatar">

            <i class="fa-solid fa-user-shield"></i>

        </div>

        <h5>Administrator</h5>

        <small>System Admin</small>

    </div>


    <!-- Navigation -->

    <nav class="sidebar-menu">


        <!-- Dashboard -->

        <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">

            <i class="fa-solid fa-chart-pie"></i>

            <span>Dashboard</span>

        </a>


        <!-- Manage Employees -->

        <a href="${pageContext.request.contextPath}/ManageEmployeeServlet">

            <i class="fa-solid fa-users"></i>

            <span>Manage Employees</span>

        </a>


        <!-- Leave Requests -->

        <a href="${pageContext.request.contextPath}/LeaveRequestsServlet"
           class="active">

            <i class="fa-solid fa-file-circle-check"></i>

            <span>Leave Requests</span>

        </a>


    </nav>


    <!-- Logout -->

    <div class="logout-section">

        <a href="${pageContext.request.contextPath}/LogoutServlet">

            <i class="fa-solid fa-right-from-bracket"></i>

            <span>Logout</span>

        </a>

    </div>


</div>



<!-- =================================
     MAIN CONTENT
================================= -->

<div class="main-content">


    <!-- Top Header -->

    <div class="top-header">


        <div>

            <h2>Leave Requests</h2>

            <p>Review and manage employee leave applications.</p>

        </div>


        <div class="admin-profile">

            <i class="fa-solid fa-circle-user"></i>

            <div>

                <strong>Administrator</strong>

                <small>System Admin</small>

            </div>

        </div>


    </div>



    <!-- =================================
         REQUEST SUMMARY
    ================================= -->

    <div class="request-summary">


        <!-- Pending -->

        <div class="summary-item">

            <div class="summary-icon pending-icon">

                <i class="fa-solid fa-hourglass-half"></i>

            </div>

            <div>

                <span>Pending</span>

                <h3>
                    <%= request.getAttribute("pendingCount") %>
                </h3>

            </div>

        </div>


        <!-- Approved -->

        <div class="summary-item">

            <div class="summary-icon approved-icon">

                <i class="fa-solid fa-circle-check"></i>

            </div>

            <div>

                <span>Approved</span>

                <h3>
                    <%= request.getAttribute("approvedCount") %>
                </h3>

            </div>

        </div>


        <!-- Rejected -->

        <div class="summary-item">

            <div class="summary-icon rejected-icon">

                <i class="fa-solid fa-circle-xmark"></i>

            </div>

            <div>

                <span>Rejected</span>

                <h3>
                    <%= request.getAttribute("rejectedCount") %>
                </h3>

            </div>

        </div>


    </div>



    <!-- =================================
         REQUEST TABLE
    ================================= -->

    <div class="request-card">


        <div class="card-header-custom">


            <div>

                <h4>All Leave Requests</h4>

                <p>
                    Review employee leave applications and take action.
                </p>

            </div>


            <!-- STATUS FILTER -->

            <div class="filter-box">

                <form
                    action="${pageContext.request.contextPath}/LeaveRequestsServlet"
                    method="get">


                    <select name="status"
                            onchange="this.form.submit()">


                        <!-- ALL -->

                        <option value="All"
                            <%=
                            "All".equals(request.getAttribute("selectedStatus"))
                            || request.getAttribute("selectedStatus") == null
                            ? "selected"
                            : ""
                            %>>

                            All Status

                        </option>


                        <!-- PENDING -->

                        <option value="Pending"
                            <%=
                            "Pending".equals(request.getAttribute("selectedStatus"))
                            ? "selected"
                            : ""
                            %>>

                            Pending

                        </option>


                        <!-- APPROVED -->

                        <option value="Approved"
                            <%=
                            "Approved".equals(request.getAttribute("selectedStatus"))
                            ? "selected"
                            : ""
                            %>>

                            Approved

                        </option>


                        <!-- REJECTED -->

                        <option value="Rejected"
                            <%=
                            "Rejected".equals(request.getAttribute("selectedStatus"))
                            ? "selected"
                            : ""
                            %>>

                            Rejected

                        </option>


                    </select>


                </form>

            </div>


        </div>



        <!-- =================================
             TABLE
        ================================= -->

        <div class="table-responsive">


            <table class="table request-table">


                <!-- TABLE HEADER -->

                <thead>

                    <tr>

                        <th>Employee</th>

                        <th>Leave Type</th>

                        <th>From Date</th>

                        <th>To Date</th>

                        <th>Reason</th>

                        <th>Status</th>

                        <th>Action</th>

                    </tr>

                </thead>



                <!-- TABLE BODY -->

                <tbody>


                    <%

                    List<LeaveRequest> leaveRequests =
                            (List<LeaveRequest>)
                            request.getAttribute("leaveRequests");


                    if (leaveRequests != null
                            && !leaveRequests.isEmpty()) {


                        for (LeaveRequest leave : leaveRequests) {

                    %>


                    <tr>


                        <!-- EMPLOYEE -->

                        <td>

                            <div class="employee-cell">


                                <div class="employee-avatar">

                                    <%= leave.getEmployeeName().charAt(0) %>

                                </div>


                                <strong>

                                    <%= leave.getEmployeeName() %>

                                </strong>


                            </div>

                        </td>



                        <!-- LEAVE TYPE -->

                        <td>

                            <%= leave.getLeaveType() %>

                        </td>



                        <!-- FROM DATE -->

                        <td>

                            <%= leave.getFromDate() %>

                        </td>



                        <!-- TO DATE -->

                        <td>

                            <%= leave.getToDate() %>

                        </td>



                        <!-- REASON -->

                        <td>

                            <%= leave.getReason() %>

                        </td>



                        <!-- STATUS -->

                        <td>

                            <span class="status

                                <%= leave.getStatus().equals("Pending")
                                    ? "pending-status"
                                    : leave.getStatus().equals("Approved")
                                    ? "approved-status"
                                    : "rejected-status"
                                %>">


                                <%= leave.getStatus() %>


                            </span>

                        </td>



                        <!-- ACTION -->

                        <td>

                            <div class="action-buttons">


                                <!-- APPROVE -->

                                <a
                                    href="${pageContext.request.contextPath}/UpdateLeaveStatusServlet?leaveId=<%= leave.getLeaveId() %>&status=Approved"
                                    class="approve-btn"
                                    title="Approve">


                                    <i class="fa-solid fa-check"></i>


                                </a>


                                <!-- REJECT -->

                                <a
                                    href="${pageContext.request.contextPath}/UpdateLeaveStatusServlet?leaveId=<%= leave.getLeaveId() %>&status=Rejected"
                                    class="reject-btn"
                                    title="Reject">


                                    <i class="fa-solid fa-xmark"></i>


                                </a>


                            </div>

                        </td>


                    </tr>


                    <%

                        }

                    } else {

                    %>


                    <!-- NO RECORDS -->

                    <tr>

                        <td colspan="7"
                            class="text-center">

                            No Leave Requests Found

                        </td>

                    </tr>


                    <%

                    }

                    %>


                </tbody>


            </table>


        </div>



        <!-- =================================
             PAGINATION
        ================================= -->

        <%

        Integer currentPage =
                (Integer) request.getAttribute("currentPage");


        Integer totalPages =
                (Integer) request.getAttribute("totalPages");


        if (currentPage == null) {

            currentPage = 1;

        }


        if (totalPages == null) {

            totalPages = 1;

        }


        // Show pagination only if more than one page

        if (totalPages > 1) {

        %>


        <div class="d-flex justify-content-center mt-4 mb-3">


            <nav aria-label="Leave request pagination">


                <ul class="pagination">


                    <!-- =================================
                         PREVIOUS
                    ================================= -->

                    <li class="page-item
                        <%= (currentPage == 1)
                            ? "disabled"
                            : "" %>">


                        <a class="page-link"
                           href="${pageContext.request.contextPath}/LeaveRequestsServlet?page=<%= currentPage - 1 %><%= (request.getAttribute("selectedStatus") != null && !request.getAttribute("selectedStatus").toString().equalsIgnoreCase("All")) ? "&status=" + request.getAttribute("selectedStatus") : "" %>">


                            <i class="fa-solid fa-chevron-left"></i>

                            Previous


                        </a>


                    </li>



                    <!-- =================================
                         PAGE NUMBERS
                    ================================= -->

                    <%

                    for (int i = 1; i <= totalPages; i++) {

                    %>


                    <li class="page-item
                        <%= (i == currentPage)
                            ? "active"
                            : "" %>">


                        <a class="page-link"
                           href="${pageContext.request.contextPath}/LeaveRequestsServlet?page=<%= i %><%= (request.getAttribute("selectedStatus") != null && !request.getAttribute("selectedStatus").toString().equalsIgnoreCase("All")) ? "&status=" + request.getAttribute("selectedStatus") : "" %>">


                            <%= i %>


                        </a>


                    </li>


                    <%

                    }

                    %>



                    <!-- =================================
                         NEXT
                    ================================= -->

                    <li class="page-item
                        <%= (currentPage == totalPages)
                            ? "disabled"
                            : "" %>">


                        <a class="page-link"
                           href="${pageContext.request.contextPath}/LeaveRequestsServlet?page=<%= currentPage + 1 %><%= (request.getAttribute("selectedStatus") != null && !request.getAttribute("selectedStatus").toString().equalsIgnoreCase("All")) ? "&status=" + request.getAttribute("selectedStatus") : "" %>">


                            Next

                            <i class="fa-solid fa-chevron-right"></i>


                        </a>


                    </li>


                </ul>


            </nav>


        </div>


        <%

        }

        %>


    </div>


</div>



<!-- Footer -->

<%@ include file="../common/footer.jsp"%>


</body>

</html>