<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
        content="width=device-width, initial-scale=1.0">

    <title>Verify OTP - ELMS</title>

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

                <i class="fa-solid fa-shield-halved fa-3x text-primary mb-3"></i>

                <h3>Verify OTP</h3>

                <p class="text-muted">
                    Enter the 6-digit OTP
                </p>

            </div>

            <form action="../VerifyEmployeeOtpServlet" method="post">

                <div class="mb-3">

                    <label class="form-label">
                        OTP
                    </label>

                    <input type="text"
                        name="otp"
                        class="form-control text-center"
                        placeholder="Enter 6-digit OTP"
                        maxlength="6"
                        pattern="[0-9]{6}"
                        required>

                </div>

                <button type="submit"
                    class="btn btn-primary w-100">

                    <i class="fa-solid fa-check me-2"></i>
                    Verify OTP

                </button>

            </form>

            <div class="text-center mt-3">

                <a href="forgotPassword.jsp">
                    <i class="fa-solid fa-arrow-left me-1"></i>
                    Back
                </a>

            </div>

        </div>

    </div>

</body>

</html>