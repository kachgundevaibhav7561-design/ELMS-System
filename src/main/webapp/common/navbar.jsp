<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg elms-navbar">

		<div class="container">

			<!-- Logo / Brand -->
			<a class="navbar-brand" href="home.jsp"> <span class="elms-title">
					ELMS </span> <span class="elms-full-form"> Employee Leave
					Management System </span>

			</a>


			<!-- Mobile Menu Button -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarContent">

				<span class="navbar-toggler-icon"></span>

			</button>


			<!-- Navbar Menu -->
			<div class="collapse navbar-collapse" id="navbarContent">

				<ul class="navbar-nav ms-auto">

					<!-- Home -->
					<li class="nav-item"><a class="nav-link" href="home.jsp">

							<i class="fa-solid fa-house"></i> Home

					</a></li>

					<!-- Admin Login -->
					<li class="nav-item"><a class="nav-link" href="adminLogin.jsp">

							<i class="fa-solid fa-user-shield"></i> Admin Login

					</a></li>


					<!-- Employee Login -->
					<li class="nav-item"><a class="nav-link"
						href="employeeLogin.jsp"> <i class="fa-solid fa-user"></i>
							Employee Login

					</a></li>


				</ul>

			</div>

		</div>

	</nav>

</body>
</html>