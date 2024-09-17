<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Login Template">
    <meta name="keywords" content="Login, Template, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Template</title>

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

        .login-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
        }

        .login-card h3 {
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

        .login-btn-page {
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

        .login-btn-page:hover {
            background-color: #0056b3;
        }

        .forgot-password {
            display: block;
            text-align: right;
            margin-top: 10px;
            color: #007bff;
            transition: color 0.3s ease;
        }

        .forgot-password:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        .social-login {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }

        .social-login a {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 48%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            transition: background-color 0.3s ease, border-color 0.3s ease;
            text-decoration: none;
            color: #555;
        }

        .social-login a:hover {
            border-color: #007bff;
            background-color: #007bff;
            color: #fff;
        }

        .social-login a i {
            margin-right: 8px;
        }

        .register {
            text-align: center;
            margin-top: 15px;
            color: #555;
        }

        .register a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .register a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>

<body>
<%@ include file="header.jsp" %>
<!-- Login Section Begin -->
<section class="login-section">
    <div class="login-card">
        <h3>Login to Your Account</h3>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-success mt-3">${success}</div>
        </c:if>
        <!-- Login Form Begin -->
        <form action="/login" method="post">
            <div class="form-group">
                <input type="email" class="form-control" id="email" placeholder="Email" required name="email">
                <i class="fa fa-envelope"></i>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" placeholder="Password" required name="password">
                <i class="fa fa-lock"></i>
            </div>
            <button type="submit" class="login-btn-page">Login</button>
            <a href="/forgot-password" class="forgot-password">Forgot Password?</a>
        </form>
        <div class="register">
            <p>Don't have an account? <a href="/register">Register</a></p>
        </div>

        <div class="social-login">
<%--            <a href="#" class="facebook-login">--%>
<%--                <i class="fa fa-facebook"></i> Facebook--%>
<%--            </a>--%>
            <a href="/auth/google" class="google-login">
                <i class="fa fa-google"></i> Google
            </a>
        </div>
    </div>
</section>
<!-- Login Section End -->
<%@ include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
