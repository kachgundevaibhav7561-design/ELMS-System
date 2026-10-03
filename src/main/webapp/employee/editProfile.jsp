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

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Edit Profile - ELMS</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer.css">

</head>

<body>

	<div class="main-content">

		<div class="topbar">

			<div>

				<h4>Edit Profile</h4>

				<p>Update your employee information.</p>

			</div>

			<div class="top-user">

				<i class="fa-solid fa-circle-user"></i> <span>Employee</span>

			</div>

		</div>


		<div class="profile-card">

			<div class="profile-header">

				<div class="profile-avatar">

					<i class="fa-solid fa-user-pen"></i>

				</div>

				<div>

					<h2>Edit Profile</h2>

					<p>Update your personal information.</p>

				</div>

			</div>


			<form
				action="${pageContext.request.contextPath}/UpdateProfileServlet"
				method="post">

				<input type="hidden" name="empId" value="<%=employee.getEmpId()%>">


				<div class="profile-info">


					<div class="info-box">

						<div class="info-icon">

							<i class="fa-solid fa-user"></i>

						</div>

						<div class="w-100">

							<span>Full Name</span> <input type="text" name="name"
								class="form-control" value="<%=employee.getName()%>" required>

						</div>

					</div>


					<div class="info-box">

						<div class="info-icon">

							<i class="fa-solid fa-envelope"></i>

						</div>

						<div class="w-100">

							<span>Email Address</span> <input type="email" name="email"
								class="form-control" value="<%=employee.getEmail()%>" required>

						</div>

					</div>


					<div class="info-box">

						<div class="info-icon">

							<i class="fa-solid fa-lock"></i>

						</div>

						<div class="w-100">

							<span>Password</span> <input type="password" name="password"
								class="form-control" value="<%=employee.getPassword()%>"
								required>

						</div>

					</div>


					<div class="info-box">

						<div class="info-icon">

							<i class="fa-solid fa-building"></i>

						</div>

						<div class="w-100">

							<span>Department</span> <input type="text" name="department"
								class="form-control" value="<%=employee.getDepartment()%>"
								required>

						</div>

					</div>

				</div>


				<div class="profile-action">

					<a href="${pageContext.request.contextPath}/ProfileServlet"
						class="btn btn-secondary me-2"> <i
						class="fa-solid fa-arrow-left"></i> Cancel

					</a>


					<button type="submit" class="btn btn-primary">

						<i class="fa-solid fa-save"></i> Save Changes

					</button>

				</div>

			</form>

		</div>

	</div>

	<%@ include file="../common/footer.jsp"%>

</body>

</html>