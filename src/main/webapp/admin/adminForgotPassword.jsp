<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
        content="width=device-width, initial-scale=1.0">

    <title>Admin Forgot Password - ELMS</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">

    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>

<body>

    <div class="container d-flex justify-content-center align-items-center"
        style="min-height: 100vh;">

        <div class="card shadow p-4"
            style="width: 100%; max-width: 450px;">

            <div class="text-center mb-4">

                <i class="fa-solid fa-user-shield fa-3x text-primary mb-3"></i>

                <h3>Admin Forgot Password</h3>

                <p class="text-muted">
                    Enter your registered email address
                </p>

            </div>

            <form action="../AdminForgotPasswordServlet" method="post">

                <div class="mb-3">

                    <label class="form-label">
                        Email Address
                    </label>

                    <input type="email"
                        name="email"
                        class="form-control"
                        placeholder="Enter admin email"
                        required>

                </div>

                <button type="submit"
                    class="btn btn-primary w-100">

                    <i class="fa-solid fa-paper-plane me-2"></i>
                    Continue

                </button>

            </form>

            <div class="text-center mt-3">

                <a href="../common/adminLogin.jsp">

                    <i class="fa-solid fa-arrow-left me-1"></i>
                    Back to Login

                </a>

            </div>

        </div>

    </div>

</body>

</html>