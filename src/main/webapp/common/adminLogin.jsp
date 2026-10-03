<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Administrator Login</title>


<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Font Awesome -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">


<!-- Admin Login CSS -->

<link rel="stylesheet" href="../css/adminLogin.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/navbar.css">

</head>


<body>
	<%@ include file="navbar.jsp"%>

	<div class="login-section">


		<div class="login-box">


			<!-- Header -->

			<div class="text-center mb-4">


				<i class="fa-solid fa-user-shield login-icon"></i>


				<h2>Administrator Login</h2>


				<p>Sign in to manage employees and handle leave requests.</p>


			</div>



			<!-- Login Form -->

			<form action="../AdminLoginServlet" method="post">


				<!-- Admin ID -->

				<div class="mb-3">


					<label class="form-label"> Administrator ID </label>

					<%
					String errorMessage = request.getParameter("error");

					if (errorMessage != null) {
					%>

					<div style="color: red; font-size: 14px; margin-bottom: 5px;">
						<%=errorMessage%>
					</div>

					<%
					}
					%>

					<input type="number" name="adminId" class="form-control"
						placeholder="Enter Administrator ID" required>

				</div>



				<!-- Password -->

				<div class="mb-3">


					<label class="form-label"> Password </label> <input type="password"
						name="password" class="form-control" placeholder="Enter Password"
						required>


				</div>



				<!-- Remember and Forgot -->

				<div class="d-flex justify-content-between mb-4">


					<div></div>



					<a href="../admin/adminForgotPassword.jsp"> Forgot Password? </a>

				</div>



				<!-- Login Button -->

				<button type="submit" class="btn btn-primary w-100">


					<i class="fa-solid fa-right-to-bracket"></i> Admin Login


				</button>


			</form>



			<!-- Back Home -->

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