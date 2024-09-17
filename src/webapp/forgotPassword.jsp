<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Forgot Password Template">
    <meta name="keywords" content="Forgot Password, Template, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot Password Template</title>

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

        .forgot-password-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .forgot-password-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
        }

        .forgot-password-card h3 {
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

        .reset-password-btn {
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

        .reset-password-btn:hover {
            background-color: #0056b3;
        }

        .back-to-login {
            text-align: center;
            margin-top: 20px;
        }

        .back-to-login a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-to-login a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>

<!-- Forgot Password Section Begin -->
<section class="forgot-password-section">
    <div class="forgot-password-card">
        <h3>Forgot Your Password?</h3>
        <p class="text-center">Enter your email address and we'll send you a link to reset your password.</p>

        <!-- Forgot Password Form Begin -->
        <form action="/forgot-password" method="post">
            <div class="form-group">
                <input type="email" class="form-control" id="email" placeholder="Enter your email" required name="email">
                <i class="fa fa-envelope"></i>
            </div>
            <button type="submit" class="reset-password-btn">Reset Password</button>
        </form>
        <!-- Forgot Password Form End -->

        <div class="back-to-login">
            <p>Remember your password? <a href="/login">Back to Login</a></p>
        </div>
    </div>
</section>
<!-- Forgot Password Section End -->
<%@include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
