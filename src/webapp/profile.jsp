<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Personal Profile Template">
    <meta name="keywords" content="Profile, Portfolio, Personal, HTML">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Personal Profile | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <style>
        .profile-section {
            margin-top: 50px;
        }

        .profile-card {
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            padding: 20px;
            background-color: #fff;
        }

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            cursor: pointer;
        }

        .edit-profile-btn {
            float: right;
            margin-top: -30px;
        }

        .form-control {
            margin-bottom: 15px;
        }

        .profile-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .profile-info h3 {
            margin-bottom: 0;
        }

        .edit-form {
            display: none;
        }

        /* Popup Modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fff;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 40%;
            border-radius: 10px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .profile-info-data {
            text-align: left;
            margin-top: 20px;
        }

    </style>
</head>

<body>
<!-- Header Section Begin -->
<%@ include file="header.jsp" %>
<!-- Header End -->

<!-- Profile Section Begin -->
<section class="profile-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <div class="profile-card text-center">
                    <!-- Avatar with click to change -->
                    <img src="<%= account.getAvatar() %>" alt="Avatar" class="avatar" id="avatar">

                    <div class="profile-info">
                        <h3><%= account.getUserName() %></h3>
                        <button class="btn btn-primary edit-profile-btn" id="editProfileBtn">Edit Profile</button>
                    </div>
                    <div class="profile-info-data">
                        <p><strong>Email:</strong> <%= account.getEmail() %></p>
                        <p><strong>Date of Birth:</strong> <%= account.getDob() %></p>
                        <p><strong>Phone:</strong> <%= account.getCusPhone() %></p>
                        <p><strong>Address:</strong> <%= account.getAddress() %></p>
                    </div>

                    <!-- Form to Edit Profile -->
                    <form action="/profile" id="editForm" class="edit-form" method="POST">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" class="form-control" id="name" value="<%= account.getUserName() %>" name="name">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" value="<%= account.getEmail() %>" readonly name="email">
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth</label>
                            <input type="text" class="form-control" id="dob" value="<%= account.getDob() %>" name="dob">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control" id="phone" value="<%= account.getCusPhone() %>" name="phone">
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" value="<%= account.getAddress() %>" name="address">
                        </div>
                        <button type="submit" class="btn btn-success">Save Changes</button>
                        <button type="button" class="btn btn-secondary" id="cancelEdit">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Profile Section End -->

<!-- Avatar Change Modal -->
<div id="avatarModal" class="modal">
    <div class="modal-content">
        <span class="close" id="closeModal">&times;</span>
        <h4>Change Avatar</h4>
        <form method="post" action="/change-avatar" enctype="multipart/form-data">
            <div class="form-group">
                <label for="avatarUpload">Upload New Avatar</label>
                <input type="file" class="form-control-file" id="avatarUpload" name="avatarFile">
            </div>
            <button type="submit" class="btn btn-primary">Upload</button>
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>
<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script>
    const editProfileBtn = document.getElementById('editProfileBtn');
    const editForm = document.getElementById('editForm');
    const cancelEdit = document.getElementById('cancelEdit');
    const avatar = document.getElementById('avatar');
    const avatarModal = document.getElementById('avatarModal');
    const closeModal = document.getElementById('closeModal');

    editProfileBtn.addEventListener('click', function () {
        editForm.style.display = 'block';
        editProfileBtn.style.display = 'none';
    });

    cancelEdit.addEventListener('click', function () {
        editForm.style.display = 'none';
        editProfileBtn.style.display = 'block';
    });

    avatar.addEventListener('click', function () {
        avatarModal.style.display = 'block';
    });

    closeModal.addEventListener('click', function () {
        avatarModal.style.display = 'none';
    });

    window.onclick = function (event) {
        if (event.target === avatarModal) {
            avatarModal.style.display = 'none';
        }
    };
</script>
</body>

</html>
