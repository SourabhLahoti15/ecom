<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ include file="sidebar.jsp" %>
            <%@ include file="nav.jsp" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Profile</title>
                    <link rel="stylesheet" href="css/profile.css">
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                        crossorigin="anonymous" referrerpolicy="no-referrer" />
                    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                </head>

                <body>
                    <main>
                        <form class="profile-form" action="/gu/updateProfile" var="user" method="post" name="editProfile" enctype="multipart/form-data" onsubmit="validateForm(event)">
                            <!-- userId -->
                            <input type="hidden" name="userId" value="${user.userId}">

                            <!-- name -->
                            <div>
                                <div class="question">
                                    <label for="name">Name:</label>
                                    <input type="text" id="name" name="name" value="${user.name}"
                                        placeholder="Enter your name" required>
                                </div>
                            </div>

                            <!-- Gender Question -->
                            <div class="question gender-question">
                                <label for="gender">Gender:</label>
                                <div class="gender-question-answer">
                                    <div class="gender-answer">
                                        <div>
                                            <input type="radio" id="male" name="gender" value="Male" <c:if
                                                test="${user.gender == 'Male'}">checked</c:if>>
                                            <label for="male">Male</label>
                                        </div>
                                        <div>
                                            <input type="radio" id="female" name="gender" value="Female" <c:if
                                                test="${user.gender == 'Female'}">checked</c:if>>
                                            <label for="female">Female</label>
                                        </div>
                                        <div>
                                            <input type="radio" id="other" name="gender" value="Other" <c:if
                                                test="${user.gender == 'Other'}">checked</c:if>>
                                            <label for="other">Other</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Email Input -->
                            <div>
                                <div class="question">
                                    <label for="email">Email:</label>
                                    <input type="email" id="email" name="email" value="${user.email}"
                                        placeholder="Enter your email" required>
                                </div>
                            </div>

                            <!-- Password Input -->
                            <div>
                                <div class="question">
                                    <label for="password">Password:</label>
                                    <input type="password" id="password" name="password" value="${user.password}"
                                        placeholder="Enter your password" required>
                                </div>
                            </div>

                            <!-- Date of Birth Input -->
                            <div>
                                <div class="question">
                                    <label for="dob">Date of Birth:</label>
                                    <input type="date" id="dob" name="dob" value="${user.dob}" required>
                                </div>
                            </div>

                            <!-- Phone Number Input -->
                            <div>
                                <div class="question">
                                    <label for="phone">Phone:</label>
                                    <input type="tel" id="phone" name="phone" value="${user.phone}" placeholder="Enter your phone number" required>
                                </div>
                            </div>

                            <div class="btns">
                                <div>
                                    <input type="submit" id="submit" name="submit" value="Edit">
                                    <input type="reset" id="reset" name="reset" value="Reset">
                                </div>
                                <div>
                                    <button type="button" class="delete-account-btn" onclick="confirmDelete()">Delete Account</button>
                                </div>
                            </div>
                        </form>
                    </main>
                    <script src="js/notification.js"></script>
                    <script src="js/profileValidation.js"></script>
                    <script>
                        document.addEventListener('DOMContentLoaded', function () {
                            const form = document.forms.editProfile;

                            form.addEventListener('submit', function (event) {
                                event.preventDefault();
                                const formData = new FormData(form);
                                const data = new URLSearchParams(formData);
                                fetch('/gu/updateProfile', {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/x-www-form-urlencoded',
                                    },
                                    body: data
                                })
                                    .then(response => response.json())
                                    .then(data => {
                                        if (data.success) {
                                            localStorage.setItem('notification', JSON.stringify({
                                                success: data.success,
                                                message: data.message,
                                                type: data.type
                                            }));
                                            window.location.reload();
                                        } else {
                                            showNotification(data.success, data.message, data.type);
                                        }
                                    })
                                    .catch(error => {
                                        console.error('Error:', error);
                                        showNotification(false, 'An error occurred while updating the profile.', 'error');
                                    });
                            });

                            const notificationData = localStorage.getItem('notification');
                            if (notificationData) {
                                const { success, message, type } = JSON.parse(notificationData);
                                showNotification(success, message, type);
                                localStorage.removeItem('notification');
                            }
                        });
                        function confirmDelete() {
                            if (confirm('Are you sure you want to delete your account? This action cannot be undone.')) {
                                // window.location.href = '/gu/deleteUser';
                                const url = "/gu/deleteUser";
                                fetch(url,{
                                    method: 'POST'
                                })
                            }
                        }
                    </script>
                </body>

                </html>