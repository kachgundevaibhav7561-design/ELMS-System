<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Add Employee</title>

<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<!-- Existing Admin CSS -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/manageEmployees.css">

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

			<a href="${pageContext.request.contextPath}/ManageEmployeeServlet"
				class="active">

				<i class="fa-solid fa-users"></i>

				<span>Manage Employees</span>

			</a>


			<!-- Leave Requests -->

			<a href="leaveRequests.jsp">

				<i class="fa-solid fa-file-circle-check"></i>

				<span>Leave Requests</span>

			</a>

		</nav>


		<!-- Logout -->

		<div class="logout-section">

			<a href="${pageContext.request.contextPath}/common/adminLogin.jsp">

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

				<h2>Add Employee</h2>

				<p>Add a new employee to the system.</p>

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
		     ADD EMPLOYEE FORM
		================================= -->

		<div class="employee-card p-4">

			<div class="mb-4">

				<h4>

					<i class="fa-solid fa-user-plus"></i>

					Employee Information

				</h4>

				<p>Add employee details below.</p>

			</div>


			<form action="${pageContext.request.contextPath}/AddEmployeeServlet"
				method="post">


				<div class="row">


					<!-- Employee ID -->

					<div class="col-md-6 mb-3">

						<label class="form-label">

							Employee ID

						</label>

						<input
							type="number"
							name="empId"
							class="form-control"
							placeholder="Enter Employee ID"
							required>

					</div>


					<!-- Employee Name -->

					<div class="col-md-6 mb-3">

						<label class="form-label">

							Employee Name

						</label>

						<input
							type="text"
							name="name"
							class="form-control"
							placeholder="Enter Employee Name"
							required>

					</div>


					<!-- Email -->

					<div class="col-md-6 mb-3">

						<label class="form-label">

							Email

						</label>

						<input
							type="email"
							name="email"
							class="form-control"
							placeholder="Enter Email Address"
							required>

					</div>


					<!-- Password -->

					<div class="col-md-6 mb-3">

						<label class="form-label">

							Password

						</label>

						<input
							type="password"
							name="password"
							class="form-control"
							placeholder="Enter Password"
							required>

					</div>


					<!-- Department -->

					<div class="col-md-6 mb-3">

						<label class="form-label">

							Department

						</label>

						<input
							type="text"
							name="department"
							class="form-control"
							placeholder="Enter Department"
							required>

					</div>

				</div>


				<!-- Buttons -->

				<div class="mt-4">

					<button type="submit"
						class="btn btn-primary me-2">

						<i class="fa-solid fa-user-plus"></i>

						Add Employee

					</button>


					<a href="${pageContext.request.contextPath}/ManageEmployeeServlet"
						class="btn btn-secondary">

						<i class="fa-solid fa-arrow-left"></i>

						Cancel

					</a>

				</div>


			</form>

		</div>

	</div>


	<!-- Footer -->

	<%@ include file="../common/footer.jsp"%>


</body>

</html>