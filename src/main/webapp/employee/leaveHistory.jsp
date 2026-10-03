<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.pojo.LeaveRequest"%>


<%
Integer empId = (Integer) session.getAttribute("empId");

if (empId == null) {

	response.sendRedirect("../common/employeeLogin.jsp");

	return;
}

List<LeaveRequest> leaveHistory = (List<LeaveRequest>) request.getAttribute("leaveHistory");
%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>ELMS - Leave History</title>


<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Font Awesome -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">


<!-- Employee Dashboard CSS -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/employeeDashboard.css">


</head>


<body>


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

			</a> <a href="${pageContext.request.contextPath}/employee/applyLeave.jsp"><i
				class="fa-solid fa-file-pen"></i> <span>Apply Leave</span> </a> <a
				href="${pageContext.request.contextPath}/LeaveHistoryServlet"
				class="active"> <i class="fa-solid fa-clock-rotate-left"></i> <span>Leave
					History</span>

			</a> <a href="${pageContext.request.contextPath}/employee/profile.jsp"><i
				class="fa-solid fa-user"></i> <span>Profile</span> </a>


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


		<!-- Topbar -->

		<div class="topbar">


			<div>

				<h4>Leave History</h4>

				<p>Track your leave applications and status.</p>

			</div>


			<div class="top-user">

				<i class="fa-solid fa-circle-user"></i> <span>Employee</span>

			</div>


		</div>



		<!-- Leave History Section -->


		<div class="recent-section">


			<div class="section-header">

				<div>

					<h4>My Leave Applications</h4>

					<p>Overview of your submitted leave requests.</p>

				</div>

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
						if (leaveHistory != null && !leaveHistory.isEmpty()) {

							for (LeaveRequest leave : leaveHistory) {
						%>


						<tr>


							<td><%=leave.getLeaveType()%></td>


							<td><%=leave.getFromDate()%></td>


							<td><%=leave.getToDate()%></td>


							<td><%=leave.getReason()%></td>



							<td>
								<%
								String status = leave.getStatus();

								if (status.equals("Pending")) {
								%> <span class="status pending"> Pending </span> <%
 } else if (status.equals("Approved")) {
 %> <span class="status approved"> Approved </span> <%
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