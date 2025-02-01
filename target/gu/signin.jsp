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
        <button class="signin">Signin</button>
        <button class="login" onclick="window.location.href='/gu/login'">Login</button>
    </div>
    <form action="/gu/addUser" name="signin" method="POST" onsubmit="validateForm(event)">
        <!-- Name Input -->
        <div>
            <div class="question">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>
        </div>
        <!-- Gender Input -->
        <p>
            <label for="gender">Gender:</label>
            <select name="gender" id="gender" required>
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
        </p>
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
        <!-- Date of Birth Input -->
        <div>
            <div class="question">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
            </div>
        </div>
        <!-- Phone Number Input -->
        <div>
            <div class="question">
                <label for="number">Phone:</label>
                <input type="text" id="number" name="phone" placeholder="Enter your phone number"
                    required>
            </div>
        </div>
        <!-- checkbox -->
        <p>
            <input type="checkbox" id="agree" name="agree" required>
            <label for="agree">Agree to above information</label>
        </p>
        <!-- button -->
        <p>
            <input type="submit" id="submit" name="submit" value="Signin">
            <input type="reset" id="reset" name="reset" value="reset">
        </p>
    </form>
    <script src="js/signinValidation.js"></script>
</body>

</html>