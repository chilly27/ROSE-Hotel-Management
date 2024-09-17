<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Verify OTP Template">
    <meta name="keywords" content="Verify OTP, Template, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Verify OTP</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <style>
        body {
            background-color: #f2f2f2;
            font-family: 'Cabin', sans-serif;
        }

        .verify-otp-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .verify-otp-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
        }

        .verify-otp-card h3 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px;
        }

        .form-group {
            position: relative;
        }

        .form-control {
            padding: 10px 12px;
            margin-bottom: 20px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            transition: border-color 0.3s ease;
        }

        .form-control:focus {
            border-color: #007bff;
            box-shadow: none;
        }

        .form-group i {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #007bff;
        }

        .verify-otp-btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            font-size: 18px;
            width: 100%;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .verify-otp-btn:hover {
            background-color: #0056b3;
        }

        .resend-otp {
            text-align: center;
            margin-top: 20px;
        }

        .resend-otp a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .resend-otp a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>

<!-- Verify OTP Section Begin -->
<section class="verify-otp-section">
    <div class="verify-otp-card">
        <h3>Verify Your OTP</h3>
        <p class="text-center">Enter the OTP sent to your email or phone to verify your account.</p>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success mt-3">${success}</div>
        </c:if>
        <!-- Verify OTP Form Begin -->
        <form action="/forgot-password-verify" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="otp" placeholder="Enter OTP" required name="verifyCode">
                <i class="fa fa-lock"></i>
            </div>
            <button type="submit" class="verify-otp-btn">Verify OTP</button>
        </form>
        <!-- Verify OTP Form End -->

        <div class="resend-otp">
            <p>Didn't receive an OTP? <a href="/resend-otp">Resend OTP</a></p>
        </div>
    </div>
</section>
<!-- Verify OTP Section End -->
<%@include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
