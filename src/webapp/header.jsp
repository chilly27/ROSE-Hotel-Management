<%@ page import="Model.Customer" %>
<link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/flaticon.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<style>
    .avatar-navbar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        object-fit: cover;
        cursor: pointer;
    }

    .profile-dropdown {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
    }

    .profile-dropdown ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    .profile-dropdown ul li {
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        cursor: pointer;
        text-align: left;
    }

    .profile-dropdown ul li:hover {
        background-color: #f1f1f1;
    }

    .profile-dropdown ul li a {
        text-decoration: none;
        color: black;
    }

    .profile-dropdown ul li a:hover {
        text-decoration: none;
        color: black;
    }


</style>

<%
    Customer account = (Customer) session.getAttribute("account");
%>

<header class="header-section">
    <div class="top-nav">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="tn-left">
                        <li><i class="fa fa-phone"></i> (12) 345 67890</li>
                        <li><i class="fa fa-envelope"></i> info.colorlib@gmail.com</li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <div class="tn-right">
                        <div class="top-social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-tripadvisor"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                        </div>

                        <a href="#" class="bk-btn">Booking Now</a>
                        <div class="language-option">
                            <img src="img/flag.jpg" alt="">
                            <span>EN <i class="fa fa-angle-down"></i></span>
                            <div class="flag-dropdown">
                                <ul>
                                    <li><a href="#">Zi</a></li>
                                    <li><a href="#">Fr</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="menu-item">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo">
                        <a href="/">
                            <img src="img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="nav-menu">
                        <nav class="mainmenu">
                            <ul>
                                <li class="active"><a href="/">Home</a></li>
                                <li><a href="./rooms.html">Rooms</a></li>
                                <li><a href="./about-us.html">About Us</a></li>
                                <li><a href="./pages.html">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="./room-details.html">Room Details</a></li>
                                        <li><a href="./blog-details.html">Blog Details</a></li>
                                        <li><a href="#">Family Room</a></li>
                                        <li><a href="#">Premium Room</a></li>
                                    </ul>
                                </li>
                                <li><a href="./blog.html">News</a></li>
                                <li><a href="./contact.html">Contact</a></li>
                            </ul>
                        </nav>

                        <% if (account == null) { %>
                            <div class="nav-right">
                                <a href="/login" class="btn btn-secondary login-btn">Login</a>
                            </div>
                        <% } else { %>
                            <div class="nav-right">

                                <img src="<%= account.getAvatar()%>" alt="Avatar" class="avatar-navbar" id="avatar-navbar">
                                <div class="profile-dropdown">
                                    <ul>
                                        <li><a href="/profile">Profile</a></li>
                                        <li><a href="/logout">Logout</a></li>
                                    </ul>
                                </div>
                            </div>
                        <% } %>
                        <div class="nav-right search-switch">
                            <i class="icon_search"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<script>
    document.getElementById("avatar-navbar").addEventListener("click", function () {
        document.getElementsByClassName("profile-dropdown")[0].style.display = "block";
    });

    document.addEventListener("click", function (e) {
        if (!e.target.matches('.avatar-navbar')) {
            var dropdowns = document.getElementsByClassName("profile-dropdown");
            for (var d of dropdowns) {
                d.style.display = 'none';
            }
        }
    });

</script>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
