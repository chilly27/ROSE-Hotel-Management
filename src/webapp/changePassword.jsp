<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Change Password Template">
    <meta name="keywords" content="Change Password, Template, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change Password Template</title>

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

        .change-password-section {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .change-password-card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
        }

        .change-password-card h3 {
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

        .change-password-btn {
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

        .change-password-btn:hover {
            background-color: #218838;
        }

        .back-to-profile {
            text-align: center;
            margin-top: 20px;
        }

        .back-to-profile a {
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-to-profile a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>

<body>
<%@include file="header.jsp" %>
<!-- Change Password Section Begin -->
<section class="change-password-section">
    <div class="change-password-card">
        <h3>Change Your Password</h3>

        <!-- Change Password Form Begin -->
        <form action="#" method="post">
            <div class="form-group">
                <input type="password" class="form-control" id="current-password" placeholder="Current Password" required>
                <i class="fa fa-lock"></i>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="new-password" placeholder="New Password" required>
                <i class="fa fa-lock"></i>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirm-new-password" placeholder="Confirm New Password" required>
                <i class="fa fa-lock"></i>
            </div>
            <button type="submit" class="change-password-btn">Change Password</button>
        </form>
        <!-- Change Password Form End -->

        <div class="back-to-profile">
            <p>Back to <a href="/profile">Profile</a></p>
        </div>
    </div>
</section>
<!-- Change Password Section End -->
<%@include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>

</html>
