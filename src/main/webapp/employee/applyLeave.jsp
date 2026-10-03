<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Apply Leave</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<!-- Apply Leave CSS -->
<link rel="stylesheet" href="../css/applyLeave.css">
</head>

<%
Integer empId = (Integer) session.getAttribute("empId");

if (empId == null) {
	response.sendRedirect("../common/employeeLogin.jsp");
	return;
}
%>

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

			</a> <a href="applyLeave.jsp" class="active"> <i
				class="fa-solid fa-file-pen"></i> <span>Apply Leave</span>

			</a> <a href="${pageContext.request.contextPath}/LeaveHistoryServlet">
				<i class="fa-solid fa-clock-rotate-left"></i> <span>Leave
					History</span>

			</a> <a href="${pageContext.request.contextPath}/employee/profile.jsp">
				<i class="fa-solid fa-user"></i> <span>Profile</span>

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

				<h4>Apply for Leave</h4>

				<p>Submit your leave request for approval.</p>

			</div>


			<div class="top-user">

				<i class="fa-solid fa-circle-user"></i> <span>Employee</span>

			</div>

		</div>


		<!-- Apply Leave Card -->

		<div class="leave-container">


			<div class="leave-header">

				<div class="leave-icon">

					<i class="fa-solid fa-file-circle-plus"></i>

				</div>

				<div>

					<h3>Apply for Leave</h3>

					<p>Fill in the details below to submit your leave request.</p>

				</div>

			</div>


			<!-- Leave Form -->

			<form action="../ApplyLeaveServlet" method="post">

				<!-- Leave Type -->

				<div class="mb-4">

					<label class="form-label"> <i class="fa-solid fa-list"></i>

						Leave Type

					</label> <select name="leaveType" class="form-select" required>

						<option value="">Select Leave Type</option>

						<option value="Casual Leave">Casual Leave</option>

						<option value="Sick Leave">Sick Leave</option>

						<option value="Emergency Leave">Emergency Leave</option>

						<option value="Other">Other</option>

					</select>

				</div>


				<!-- Dates -->

				<div class="row">


					<div class="col-md-6 mb-4">

						<label class="form-label"> <i
							class="fa-solid fa-calendar-days"></i> From Date

						</label> <input type="date" name="fromDate" id="fromDate" class="form-control" required>

					</div>


					<div class="col-md-6 mb-4">

						<label class="form-label"> <i
							class="fa-solid fa-calendar-days"></i> To Date

						</label> <input type="date" name="toDate" id="toDate" class="form-control" required>
					</div>

				</div>


				<!-- Reason -->

				<div class="mb-4">

					<label class="form-label"> <i
						class="fa-solid fa-comment-dots"></i> Reason for Leave

					</label>


					<textarea name="reason" class="form-control" rows="5"
						placeholder="Please enter the reason for your leave..." required></textarea>

				</div>


				<!-- Buttons -->

				<div class="form-buttons">

					<a
						href="${pageContext.request.contextPath}/employee/employeeDashboard.jsp"
						class="btn btn-secondary"> <i class="fa-solid fa-arrow-left"></i>
						Back

					</a>


					<button type="submit" class="btn btn-primary">

						<i class="fa-solid fa-paper-plane"></i> Submit Leave

					</button>

				</div>


			</form>

		</div>


	</div>
	<%@ include file="../common/footer.jsp"%>

<script>
    const fromDate = document.getElementById("fromDate");
    const toDate = document.getElementById("toDate");

    // Get today's date
    const today = new Date();
    const todayString = today.toISOString().split("T")[0];

    // From Date cannot be before today
    fromDate.min = todayString;

    // To Date cannot be before today
    toDate.min = todayString;

    // To Date must be same as or after From Date
    fromDate.addEventListener("change", function () {

        toDate.min = this.value;

        // Clear To Date if it is before From Date
        if (toDate.value && toDate.value < this.value) {
            toDate.value = "";
        }
    });
</script>
</body>
</html>