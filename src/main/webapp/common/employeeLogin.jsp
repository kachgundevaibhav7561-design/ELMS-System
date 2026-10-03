<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ELMS Login</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<link rel="stylesheet" href="../css/employeeLogin.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/navbar.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">

</head>

<body>
	<%@ include file="navbar.jsp"%>

	<div class="login-section">

		<div class="login-box">

			<div class="text-center mb-4">

				<i class="fa-solid fa-user-lock login-icon"></i>

				<h2>Employee Login</h2>

				<p>Sign in to access your leave management dashboard.</p>

			</div>

			<form action="../LoginServlet" method="post">

				<div class="mb-3">
					<%
					String error = request.getParameter("error");

					if ("invalid".equals(error)) {
					%>

					<div class="text-danger mb-2">Invalid Employee ID or Password
					</div>

					<%
					}
					%>

					<label>Employee ID</label> <label class="form-label">
						Employee ID </label> <input type="text" name="empId" class="form-control"
						placeholder="Enter Employee ID" required>

				</div>

				<div class="mb-3">

					<label class="form-label"> Password </label> <input type="password"
						name="password" class="form-control" placeholder="Enter Password"
						required>

				</div>

				<div class="d-flex justify-content-between mb-4">

					<div>


					</div>

					<a href="../employee/forgotPassword.jsp"> Forgot Password? </a>
				</div>

				<button class="btn btn-primary w-100">Login</button>

			</form>

			<div class="text-center mt-4">

				<a href="home.jsp"> <i class="fa-solid fa-arrow-left"></i> Back
					to Home

				</a>

			</div>

		</div>

	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>