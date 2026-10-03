<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Add New Admin</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<!-- Admin Dashboard CSS -->
<link rel="stylesheet" href="../css/addAdmin.css">
</head>

<body>

	<div class="main-content">

		<!-- Header -->

		<div class="top-header">

			<div>

				<h2>Add New Admin</h2>

				<p>Create a new administrator account.</p>

			</div>

		</div>


		<!-- Admin Form -->

		<div class="container mt-4">

			<div class="admin-form-card">

				<h4 class="mb-4">
					<i class="fa-solid fa-user-plus"></i> Admin Registration
				</h4>


				<form action="../AdminAddServlet" method="post">


					<!-- Admin ID -->

					<div class="mb-3">

						<label class="form-label"> Admin ID </label> <input type="number"
							name="adminId" class="form-control" placeholder="Enter Admin ID"
							required>

					</div>


					<!-- Name -->

					<div class="mb-3">

						<label class="form-label"> Name </label> <input type="text"
							name="name" class="form-control" placeholder="Enter Admin Name"
							required>

					</div>


					<!-- Email -->

					<div class="mb-3">

						<label class="form-label"> Email </label> <input type="email"
							name="email" class="form-control"
							placeholder="Enter Email Address" required>

					</div>


					<!-- Password -->

					<div class="mb-3">

						<label class="form-label"> Password </label> <input
							type="password" name="password" class="form-control"
							placeholder="Enter Password" required>

					</div>


					<!-- Button -->

					<button type="submit" class="add-admin-btn">

						<i class="fa-solid fa-user-plus"></i> Add Admin

					</button>


				</form>
				<a href="adminDashboard.jsp" class="back-btn"> <i
					class="fa-solid fa-arrow-left"></i> Back to Dashboard
				</a>

			</div>

		</div>

	</div>

</body>

</html>