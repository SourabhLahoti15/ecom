<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/signin.css">
</head>

<body>
    <div class="btn">
        <button class="signin" onclick="window.location.href='/gu/signin'">Signin</button>
        <button class="login">Login</button>
    </div>
    <form action="/gu/loginCheck" name="login" onsubmit="validateForm(event)">
        <!-- Email Input -->
        <div>
            <div class="question">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Enter your email" required>
            </div>
        </div>
        <!-- Password Input -->
        <div>
            <div class="question">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
        </div>
        <!-- button -->
        <p>
            <input type="submit" id="submit" name="submit" value="Login">
            <input type="reset" id="reset" name="reset" value="reset">
        </p>
    </form>
    <script src="js/loginValidation.js"></script>
</body>

</html>