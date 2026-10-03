<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Employee Leave Management System</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="../css/home.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/navbar.css">
</head>

<body>
	<%@ include file="navbar.jsp"%>
	<!-- Hero Section -->

	<section class="hero">

		<div class="container">

			<div class="text-center mb-5">
				<h1>Employee Leave Management System</h1>
				<p>Welcome to ELMS. Please select your login portal.</p>
			</div>

			<div class="row justify-content-center">

				<!-- Admin Card -->
				<div class="col-lg-4 col-md-5 mb-4">

					<div class="login-card">

						<div class="icon">👨‍💼</div>

						<h3>Administrator</h3>

						<p>Manage employee records and streamline leave approvals with
							ease..</p>

						<a href="adminLogin.jsp" class="btn btn-primary w-100"> <i
							class="fa-solid fa-right-to-bracket"></i> Admin Login
						</a>
					</div>

				</div>

				<!-- Employee Card -->
				<div class="col-lg-4 col-md-5 mb-4">

					<div class="login-card">

						<div class="icon">👨‍💻</div>

						<h3>Employee</h3>

						<p>Access your dashboard, apply for leave, and track your
							leave history effortlessly.</p>

						<a href="employeeLogin.jsp" class="btn btn-success w-100"> <i
							class="fa-solid fa-right-to-bracket"></i> Employee Login
						</a>

					</div>

				</div>

			</div>
		</div>

	</section>
	<%@ include file="footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>