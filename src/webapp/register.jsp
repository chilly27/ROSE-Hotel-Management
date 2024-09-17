<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Register Template">
    <meta name="keywords" content="Register, Template, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register Template</title>

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

        .register-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .register-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
        }

        .register-card h3 {
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

        .register-btn {
            background-color: #28a745;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            font-size: 18px;
            width: 100%;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .register-btn:hover {
            background-color: #218838;
        }

        .already-account {
            text-align: center;
            margin-top: 15px;
            color: #555;
        }

        .already-account a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .already-account a:hover {
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
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<!-- Register Section Begin -->
<section class="register-section">
    <div class="register-card">
        <h3>Create an Account</h3>

        <!-- Register Form Begin -->
        <form action="/register" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="username" placeholder="User Name" required name="username">
                <i class="fa fa-user"></i>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" placeholder="Email" required name="email">
                <i class="fa fa-envelope"></i>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" placeholder="Password" required name="password">
                <i class="fa fa-lock"></i>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirm-password" placeholder="Confirm Password" required name="confirm-password">
                <i class="fa fa-lock"></i>
            </div>
            <div class="form-group">
                <input type="date" class="form-control" id="dob" placeholder="Date of Birth (mm/dd/yyyy)" required name="dob">
                <i class="fa fa-calendar"></i>
            </div>
            <div class="form-group">
                <select class="form-control" id="sex" required name="gender">
                    <option value="" disabled selected>Sex</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
                <i class="fa fa-venus-mars"></i>
            </div>
            <div class="form-group">
                <input type="tel" class="form-control" id="phone" placeholder="Phone Number" required name="phone">
                <i class="fa fa-phone"></i>
            </div>
            <button type="submit" class="register-btn">Register</button>
        </form>
        <!-- Register Form End -->

        <div class="already-account">
            <p>Already have an account? <a href="/login">Login</a></p>
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
<!-- Register Section End -->
<%@include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
