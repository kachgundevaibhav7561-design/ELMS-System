<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pojo.Employee"%>

<%
Employee employee = (Employee) request.getAttribute("employee");

if (employee == null) {
	response.sendRedirect(request.getContextPath() + "/ProfileServlet");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Employee Profile</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css">
</head>

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

			</a> <a href="${pageContext.request.contextPath}/ProfileServlet"
				class="active"> <i class="fa-solid fa-user"></i> <span>Profile</span>
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


		<!-- Topbar -->

		<div class="topbar">

			<div>

				<h4>My Profile</h4>

				<p>View your employee information.</p>

			</div>


			<div class="top-user">

				<i class="fa-solid fa-circle-user"></i> <span>Employee</span>

			</div>

		</div>


		<!-- Profile Card -->

		<div class="profile-card">


			<!-- Profile Header -->

			<div class="profile-header">

				<div class="profile-avatar">

					<i class="fa-solid fa-user"></i>

				</div>


				<div>

					<h2><%=employee.getName()%></h2>
					<p>Employee</p>
				</div>

			</div>


			<!-- Profile Information -->

			<div class="profile-info">


				<!-- Employee ID -->

				<div class="info-box">

					<div class="info-icon">

						<i class="fa-solid fa-id-card"></i>

					</div>

					<div>

						<span>Employee ID</span>

						<h5><%=employee.getEmpId()%></h5>
					</div>

				</div>


				<!-- Full Name -->

				<div class="info-box">

					<div class="info-icon">

						<i class="fa-solid fa-user"></i>

					</div>

					<div>

						<span>Full Name</span>

						<h5><%=employee.getName()%></h5>
					</div>

				</div>


				<!-- Email -->

				<div class="info-box">

					<div class="info-icon">

						<i class="fa-solid fa-envelope"></i>

					</div>

					<div>

						<span>Email Address</span>

						<h5><%=employee.getEmail()%></h5>
					</div>

				</div>


				<!-- Department -->

				<div class="info-box">

					<div class="info-icon">

						<i class="fa-solid fa-building"></i>

					</div>

					<div>

						<span>Department</span>

						<h5><%=employee.getDepartment()%></h5>
					</div>

				</div>


			</div>


			<!-- Edit Button -->

			<div class="profile-action">

				<a href="${pageContext.request.contextPath}/EditProfileServlet"
					class="btn btn-primary"> <i class="fa-solid fa-pen"></i> Edit
					Profile

				</a>

			</div>


		</div>


	</div>
	<%@ include file="../common/footer.jsp"%>

</body>
</html>