<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <nav>
        <div class="back-forward">
            <i class="fa-solid fa-angle-left" onclick="goBack()"></i>
            <i class="fa-solid fa-angle-right" onclick="goForward()"></i>
        </div>
        <form class="search-product-form" action="/gu/searchProduct" method="GET">
            <div class="search-bar">
                <input type="text" name="searchedProduct" placeholder="Search for products, brands and more">
                <button class="search-btn" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
        </form>
        <% if (session==null || session.getAttribute("user_id")==null) { %>
            <div class="login">
                <button class="sign-up" onclick="window.location.href='/gu/signin'">Sign up</button>
                <button class="log-in" onclick="window.location.href='/gu/login'">Log in</button>
            </div>
        <% } %>
        
        <% if (session==null || session.getAttribute("user_id")==null) { %>
            <div id="notification"></div>
        <% } else { %>
            <div id="notification"></div>
        <% } %>
    </nav>
    <script>
        function goBack() {
            window.history.back();
        }

        function goForward() {
            window.history.forward();
        }
    </script>
</body>

</html>