
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.pojo.LeaveRequest"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="Cache-Control"
      content="no-cache, no-store, must-revalidate">

<meta http-equiv="Pragma"
      content="no-cache">

<meta http-equiv="Expires"
      content="0">
      
<title>ELMS - Employee Dashboard</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<!-- Dashboard CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/employeeDashboard.css">

</head>

<%
Integer empId = (Integer) session.getAttribute("empId");

if (empId == null) {
	response.sendRedirect("../common/employeeLogin.jsp");
	return;
}
%>

<body>

	<!-- =========================
     Sidebar
========================= -->

	<div class="sidebar">

		<div class="logo">

			<i class="fa-solid fa-calendar-check"></i> <span>ELMS</span>

		</div>


		<div class="user-info">

			<div class="user-icon">

				<i class="fa-solid fa-user"></i>

			</div>

			<h5>Employee</h5>

			<small>Employee Portal</small>

		</div>


		<nav class="sidebar-menu">
			<a href="${pageContext.request.contextPath}/EmployeeDashboardServlet">
				<i class="fa-solid fa-house"></i> <span>Dashboard</span>

			</a> <a href="${pageContext.request.contextPath}/employee/applyLeave.jsp">
				<i class="fa-solid fa-file-pen"></i> <span>Apply Leave</span>

			</a> <a href="${pageContext.request.contextPath}/LeaveHistoryServlet">
				<i class="fa-solid fa-clock-rotate-left"></i> <span>Leave
					History</span>
			</a> <a href="${pageContext.request.contextPath}/employee/profile.jsp">
				<i class="fa-solid fa-user"></i> <span>Profile</span>

			</a>

		</nav>


		<div class="logout-section">

			<a href="${pageContext.request.contextPath}/EmployeeLogoutServlet">
				<i class="fa-solid fa-right-from-bracket"></i> <span>Logout</span>
			</a>
		</div>

	</div>


	<!-- =========================
     Main Content
========================= -->

	<div class="main-content">


		<!-- Top Navbar -->

		<div class="topbar">

			<div>

				<h4>Employee Dashboard</h4>

				<p>Manage your leave requests easily.</p>

			</div>


			<div class="top-user">

				<i class="fa-solid fa-circle-user"></i> <span>Employee</span>

			</div>

		</div>


		<!-- Welcome Section -->

		<div class="welcome-box">

			<div>

				<h2>Welcome Back! 👋</h2>

				<p>Manage your leave requests and track your leave status from
					here.</p>

			</div>

			<i class="fa-solid fa-calendar-days"></i>

		</div>


		<!-- Summary Cards -->

		<div class="row g-4 mt-2">


			<div class="col-lg-4 col-md-6">

				<div class="dashboard-card total-card">

					<div class="card-icon">

						<i class="fa-solid fa-calendar-days"></i>

					</div>

					<div>

						<h6>Total Leave Requests</h6>

						<h2>${totalLeaves}</h2>

					</div>

				</div>

			</div>


			<div class="col-lg-4 col-md-6">

				<div class="dashboard-card approved-card">

					<div class="card-icon">

						<i class="fa-solid fa-circle-check"></i>

					</div>

					<div>

						<h6>Approved Leaves</h6>

						<h2>${approvedLeaves}</h2>

					</div>

				</div>

			</div>


			<div class="col-lg-4 col-md-6">

				<div class="dashboard-card pending-card">

					<div class="card-icon">

						<i class="fa-solid fa-hourglass-half"></i>

					</div>

					<div>

						<h6>Pending Leaves</h6>

						<h2>${pendingLeaves}</h2>

					</div>

				</div>

			</div>


		</div>


		<!-- Recent Leave Requests -->

		<div class="recent-section">


			<div class="section-header">

				<div>

					<h4>Recent Leave Requests</h4>

					<p>Overview of your latest leave applications.</p>

				</div>

				<a href="${pageContext.request.contextPath}/LeaveHistoryServlet">
					View All <i class="fa-solid fa-arrow-right"></i>
				</a>

			</div>


			<div class="table-responsive">

				<table class="table">

					<thead>

						<tr>

							<th>Leave Type</th>

							<th>From Date</th>

							<th>To Date</th>

							<th>Reason</th>

							<th>Status</th>

						</tr>

					</thead>


					<tbody>

						<%
						List<LeaveRequest> leaveHistory = (List<LeaveRequest>) request.getAttribute("leaveHistory");

						if (leaveHistory != null && !leaveHistory.isEmpty()) {

							int count = 0;

							for (LeaveRequest leave : leaveHistory) {

								if (count == 3) {
							break;
								}

								count++;

								String status = leave.getStatus();
						%>

						<tr>

							<td><%=leave.getLeaveType()%></td>

							<td><%=leave.getFromDate()%></td>

							<td><%=leave.getToDate()%></td>

							<td><%=leave.getReason()%></td>

							<td>
								<%
								if ("Approved".equals(status)) {
								%> <span class="status approved"> Approved </span> <%
 } else if ("Pending".equals(status)) {
 %> <span class="status pending"> Pending </span> <%
 } else {
 %> <span class="status rejected"> Rejected </span> <%
 }
 %>

							</td>

						</tr>

						<%
						}

						} else {
						%>

						<tr>

							<td colspan="5" class="text-center">No Leave History Found</td>

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