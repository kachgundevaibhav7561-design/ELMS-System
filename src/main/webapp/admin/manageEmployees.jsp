<%@page import="com.pojo.Employee"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ELMS - Manage Employees</title>


<!-- Bootstrap -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Font Awesome -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">


<!-- Custom CSS -->

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

			<i class="fa-solid fa-calendar-check"></i> <span>ELMS Admin</span>

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

				<i class="fa-solid fa-chart-pie"></i> <span>Dashboard</span>

			</a>


			<!-- Manage Employees -->

			<a href="${pageContext.request.contextPath}/ManageEmployeeServlet"
				class="active"> <i class="fa-solid fa-users"></i> <span>Manage
					Employees</span>

			</a>


			<!-- Leave Requests -->

			<a href="${pageContext.request.contextPath}/LeaveRequestsServlet">

				<i class="fa-solid fa-file-circle-check"></i> <span>Leave
					Requests</span>

			</a>


		</nav>


		<!-- Logout -->

		<div class="logout-section">

			<a href="${pageContext.request.contextPath}/LogoutServlet"> <i
				class="fa-solid fa-right-from-bracket"></i> <span>Logout</span>

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

				<h2>Manage Employees</h2>

				<p>View and manage employee records.</p>

			</div>


			<div class="admin-profile">

				<i class="fa-solid fa-circle-user"></i>

				<div>

					<strong>Administrator</strong> <small>System Admin</small>

				</div>

			</div>

		</div>



		<!-- =================================
         PAGE ACTIONS
    ================================= -->

		<div class="page-actions">

			<div>

				<h4>Employee List</h4>

				<p>Manage all registered employees.</p>

			</div>


			<a href="${pageContext.request.contextPath}/admin/addEmployee.jsp"
				class="add-btn"> <i class="fa-solid fa-user-plus"></i> Add
				Employee

			</a>

		</div>



		<!-- =================================
         SEARCH
    ================================= -->

		<div class="search-section">

			<div class="search-box">

				<i class="fa-solid fa-magnifying-glass"></i> <input type="text"
					id="employeeSearch" placeholder="Search employee by name or ID...">
			</div>


			<div class="employee-count">

				<i class="fa-solid fa-users"></i> <span>Employee List</span>

			</div>

		</div>



		<!-- =================================
         EMPLOYEE TABLE
    ================================= -->

		<div class="employee-card">


			<div class="table-responsive">


				<table class="table employee-table">


					<!-- TABLE HEADER -->

					<thead>

						<tr>

							<th>Employee</th>

							<th>Employee ID</th>

							<th>Email</th>

							<th>Department</th>

							<th>Status</th>

							<th>Action</th>

						</tr>

					</thead>



					<!-- TABLE BODY -->

					<tbody>

						<%
						// Get Employee List from Servlet

						List<Employee> employees = (List<Employee>) request.getAttribute("employees");

						// Check Employee Records

						if (employees != null && !employees.isEmpty()) {

							// Loop through Employee List

							for (Employee emp : employees) {
						%>


						<tr>


							<!-- Employee Name -->

							<td><%=emp.getName()%></td>


							<!-- Employee ID -->

							<td><%=emp.getEmpId()%></td>


							<!-- Employee Email -->

							<td><%=emp.getEmail()%></td>


							<!-- Department -->

							<td><%=emp.getDepartment()%></td>


							<!-- Status -->

							<td><span class="badge bg-success"> Active </span></td>


							<!-- Action -->

							<td>
								<!-- Edit --> <a
								href="${pageContext.request.contextPath}/EditEmployeeServlet?empId=<%= emp.getEmpId() %>"
								class="btn btn-sm btn-primary" title="Edit Employee"> <i
									class="fa-solid fa-pen"></i>

							</a> <!-- Delete --> <a
								href="${pageContext.request.contextPath}/DeleteEmployeeServlet?empId=<%= emp.getEmpId() %>"
								class="btn btn-sm btn-danger" title="Delete Employee"
								onclick="return confirm('Are you sure you want to delete this employee?');">

									<i class="fa-solid fa-trash"></i>

							</a>


							</td>


						</tr>


						<%
						}

						} else {
						%>


						<!-- No Records -->

						<tr>

							<td colspan="6" class="text-center">No Employee Records
								Found</td>

						</tr>


						<%
						}
						%>


					</tbody>


				</table>


			</div>



			<!-- =================================
             PAGINATION
        ================================= -->

			<%
			Integer currentPage = (Integer) request.getAttribute("currentPage");

			Integer totalPages = (Integer) request.getAttribute("totalPages");

			if (currentPage == null) {
				currentPage = 1;
			}

			if (totalPages == null) {
				totalPages = 1;
			}

			// Show pagination only when more than one page

			if (totalPages > 1) {
			%>


			<div class="d-flex justify-content-center mt-4 mb-3">


				<nav aria-label="Employee pagination">


					<ul class="pagination">


						<!-- PREVIOUS -->

						<li
							class="page-item
                        <%=(currentPage == 1) ? "disabled" : ""%>">


							<a class="page-link"
							href="${pageContext.request.contextPath}/ManageEmployeeServlet?page=<%= currentPage - 1 %>">


								<i class="fa-solid fa-chevron-left"></i> Previous


						</a>


						</li>



						<!-- PAGE NUMBERS -->

						<%
						for (int i = 1; i <= totalPages; i++) {
						%>


						<li
							class="page-item
                        <%=(i == currentPage) ? "active" : ""%>">


							<a class="page-link"
							href="${pageContext.request.contextPath}/ManageEmployeeServlet?page=<%= i %>">

								<%=i%>

						</a>


						</li>


						<%
						}
						%>



						<!-- NEXT -->

						<li
							class="page-item
                        <%=(currentPage == totalPages) ? "disabled" : ""%>">


							<a class="page-link"
							href="${pageContext.request.contextPath}/ManageEmployeeServlet?page=<%= currentPage + 1 %>">


								Next <i class="fa-solid fa-chevron-right"></i>


						</a>


						</li>


					</ul>


				</nav>


			</div>


			<%
			}
			%>


		</div>


	</div>



	<!-- Footer -->

	<%@ include file="../common/footer.jsp"%>

<script>

    const employeeSearch =
        document.getElementById("employeeSearch");

    employeeSearch.addEventListener("input", function () {

        const searchValue =
            this.value.trim().toLowerCase();

        const rows =
            document.querySelectorAll("tbody tr");

        rows.forEach(function (row) {

            const employeeName =
                row.cells[0].textContent
                    .trim()
                    .toLowerCase();

            const employeeId =
                row.cells[1].textContent
                    .trim()
                    .toLowerCase();

            if (employeeName.includes(searchValue)
                    || employeeId.includes(searchValue)) {

                row.style.display = "";

            } else {

                row.style.display = "none";
            }

        });

    });

</script>

</body>

</html>