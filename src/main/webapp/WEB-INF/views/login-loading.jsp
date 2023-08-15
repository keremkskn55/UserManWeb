<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loading Screen</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Your custom CSS (if needed) -->
    <style>
        /* Center the loading spinner */
        .loading-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
    </style>
    <script>
        function setCookie(name, value, days) {
            // ... (as shown in previous examples)
        }

        // Retrieve JWT token from the model
        var jwtToken = '<c:out value="${jwtToken}" />';

        // Set the token in a cookie
        setCookie("jwt_token", jwtToken, 1); // Expires in 1 day

        // Redirect to another page after storing the token
        window.location.href = "/other-page"; // Replace with the actual URL
    </script>
</head>
<body>
    <div class="loading-container">
        <!-- Loading spinner from Bootstrap -->
        <div class="spinner-border text-primary" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Optional: Additional scripts if needed -->
    <!-- Bootstrap JS (you can add other scripts here if needed) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>