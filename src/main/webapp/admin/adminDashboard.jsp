<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.DAO.EmployeeDAO"%>
<%@ page import="com.DAO.LeaveRequestDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.pojo.LeaveRequest"%>

<%
if (session.getAttribute("adminId") == null) {
	response.sendRedirect("../common/adminLogin.jsp");
	return;
}
%>
<%
EmployeeDAO employeeDAO = new EmployeeDAO();

LeaveRequestDAO leaveDAO = new LeaveRequestDAO();

int totalEmployees = employeeDAO.getTotalEmployees();

int pendingCount = leaveDAO.getLeaveCountByStatus("Pending");

int approvedCount = leaveDAO.getLeaveCountByStatus("Approved");

int rejectedCount = leaveDAO.getLeaveCountByStatus("Rejected");

List<LeaveRequest> recentLeaves = leaveDAO.getRecentLeaveRequests();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Admin Dashboard</title>


<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Font Awesome -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">


<!-- Admin CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminDashboard.css">

</head>


<body>

	<%
	String adminName = (String) session.getAttribute("adminName");
	%>



	<!-- =================================
     ADMIN SIDEBAR
================================= -->

	<div class="sidebar">


		<!-- Logo -->

		<div class="logo">

			<i class="fa-solid fa-calendar-check"></i> <span>ELMS Admin</span>

		</div>


		<!-- Admin Profile -->

		<div class="admin-info">

			<div class="admin-avatar">

				<i class="fa-solid fa-user-shield"></i>

			</div>

			<h5><%=adminName%></h5>

			<small>System Admin</small>

		</div>


		<!-- Navigation -->

		<nav class="sidebar-menu">


			<a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp"
				class="active"> <i class="fa-solid fa-chart-pie"></i> <span>Dashboard</span>

			</a> <a href="${pageContext.request.contextPath}/ManageEmployeeServlet"><i
				class="fa-solid fa-users"></i> <span>Manage Employees</span> </a> <a
				href="${pageContext.request.contextPath}/LeaveRequestsServlet">
				<i class="fa-solid fa-file-circle-check"></i> <span>Leave
					Requests</span>

			</a> <a href="addAdmin.jsp"> <i class="fa-solid fa-user-plus"></i> <span>Add
					New Admin</span>
			</a>


		</nav>


		<!-- Logout -->

		<div class="logout-section">

			<a href="../LogoutServlet"> <i
				class="fa-solid fa-right-from-bracket"></i> <span>Logout</span>
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

				<h2>Admin Dashboard</h2>

				<p>Welcome back! Here's an overview of your organization.</p>

			</div>


			<div class="admin-profile">

				<i class="fa-solid fa-circle-user"></i>

				<div>

					<strong><%=adminName%></strong> <small>System Admin</small>

				</div>

			</div>


		</div>



		<!-- =================================
         STATISTICS CARDS
    ================================= -->

		<div class="stats-grid">


			<!-- Total Employees -->

			<div class="stat-card employees">

				<div class="stat-icon">

					<i class="fa-solid fa-users"></i>

				</div>

				<div class="stat-content">

					<span>Total Employees</span>

					<h3><%=totalEmployees%></h3>
					<small> <i class="fa-solid fa-arrow-up"></i> Active
						employees
					</small>

				</div>

			</div>



			<!-- Pending -->

			<div class="stat-card pending">

				<div class="stat-icon">

					<i class="fa-solid fa-hourglass-half"></i>

				</div>

				<div class="stat-content">

					<span>Pending Requests</span>

					<h3><%=pendingCount%></h3>

					<small> Awaiting approval </small>

				</div>

			</div>



			<!-- Approved -->

			<div class="stat-card approved">

				<div class="stat-icon">

					<i class="fa-solid fa-circle-check"></i>

				</div>

				<div class="stat-content">

					<span>Approved Leaves</span>

					<h3><%=approvedCount%></h3>
					<small> Successfully approved </small>

				</div>

			</div>



			<!-- Rejected -->

			<div class="stat-card rejected">

				<div class="stat-icon">

					<i class="fa-solid fa-circle-xmark"></i>

				</div>

				<div class="stat-content">

					<span>Rejected Leaves</span>

					<h3><%=rejectedCount%></h3>
					<small> Leave requests rejected </small>

				</div>

			</div>


		</div>



		<!-- =================================
         RECENT LEAVE REQUESTS
    ================================= -->

		<div class="recent-section">


			<div class="section-header">

				<div>

					<h4>Recent Leave Requests</h4>

					<p>Review the latest employee leave applications.</p>

				</div>


				<a href="${pageContext.request.contextPath}/LeaveRequestsServlet"
					class="view-all">View All <i class="fa-solid fa-arrow-right"></i>

				</a>

			</div>



			<!-- Table -->

			<div class="table-responsive">


				<table class="table request-table">


					<thead>

						<tr>

							<th>Employee</th>

							<th>Leave Type</th>

							<th>From</th>

							<th>To</th>

							<th>Status</th>

							<th>Action</th>

						</tr>

					</thead>


					<tbody>

						<%
						if (recentLeaves != null && !recentLeaves.isEmpty()) {

							for (LeaveRequest leave : recentLeaves) {

								String status = leave.getStatus();

								String statusClass = "";

								if ("Pending".equalsIgnoreCase(status)) {
							statusClass = "pending-status";
								} else if ("Approved".equalsIgnoreCase(status)) {
							statusClass = "approved-status";
								} else if ("Rejected".equalsIgnoreCase(status)) {
							statusClass = "rejected-status";
								}

								String employeeName = leave.getEmployeeName();

								String avatar = "";

								if (employeeName != null && !employeeName.isEmpty()) {
							avatar = employeeName.substring(0, 1).toUpperCase();
								}
						%>

						<tr>

							<td>
								<div class="employee-cell">

									<div class="employee-avatar">
										<%=avatar%>
									</div>

									<strong><%=employeeName%></strong>

								</div>
							</td>

							<td><%=leave.getLeaveType()%></td>

							<td><%=leave.getFromDate()%></td>

							<td><%=leave.getToDate()%></td>

							<td><span class="status <%=statusClass%>"> <%=status%>
							</span></td>

							<td><a
								href="${pageContext.request.contextPath}/LeaveRequestsServlet"
								class="review-btn"> <%
 if ("Pending".equalsIgnoreCase(status)) {
 %>
									Review <%
 } else {
 %> View <%
 }
 %>

							</a></td>

						</tr>

						<%
						}

						} else {
						%>

						<tr>

							<td colspan="6" class="text-center">No leave requests found.
							</td>

						</tr>

						<%
						}
						%>

					</tbody>
				</table>

			</div>

		</div>


	</div>

	<%@ include file="../common/footer.jsp"%>
</body>

</html>