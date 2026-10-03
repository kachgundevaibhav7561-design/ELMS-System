<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Boolean otpVerified = (Boolean) session.getAttribute("otpVerified");

if (otpVerified == null || !otpVerified) {

	response.sendRedirect(request.getContextPath() + "/employee/forgotPassword.jsp");

	return;
}
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Reset Password - ELMS</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>

<body>

	<div class="container d-flex justify-content-center align-items-center"
		style="min-height: 100vh;">

		<div class="card shadow p-4" style="width: 100%; max-width: 450px;">

			<div class="text-center mb-4">

				<i class="fa-solid fa-lock fa-3x text-primary mb-3"></i>

				<h3>Reset Password</h3>

				<p class="text-muted">Enter your new password</p>

			</div>

			<form action="../EmployeeResetPasswordServlet" method="post">

				<input type="hidden" name="email"
					value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>">

				<div class="mb-3">

					<label class="form-label"> New Password </label> <input
						type="password" name="newPassword" id="newPassword"
						class="form-control" placeholder="Enter new password"
						minlength="6" required>

				</div>

				<div class="mb-3">

					<label class="form-label"> Confirm Password </label> <input
						type="password" name="confirmPassword" id="confirmPassword"
						class="form-control" placeholder="Confirm new password"
						minlength="6" required>

				</div>

				<button type="submit" class="btn btn-primary w-100">

					<i class="fa-solid fa-key me-2"></i> Reset Password

				</button>

			</form>

			<div class="text-center mt-3">

				<a href="../common/employeeLogin.jsp"> <i
					class="fa-solid fa-arrow-left me-1"></i> Back to Login
				</a>

			</div>

		</div>

	</div>

</body>

</html>