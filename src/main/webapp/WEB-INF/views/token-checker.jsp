<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Call POST Method Example</title>
    <!-- Optional: Add your CSS styles if needed -->
</head>
<body>
    <!-- Optional: Content of the page -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // Function to read cookie value by name
        function getCookie(name) {
            var value = "; " + document.cookie;
            var parts = value.split("; " + name + "=");
            if (parts.length === 2) {
                return parts.pop().split(";").shift();
            }
        }

        console.log("");
        // Retrieve the JWT token from the cookie
        var jwtToken = getCookie("jwt_token");

        console.log("Token:");
        console.log(jwtToken);

        if (!jwtToken){
        	jwtToken = "";
        }

     // Make the POST request using AJAX
        var apiUrl = "http://localhost:8082/UserManWeb/auth/tokenChecker"; // Replace with the actual URL

        $.ajax({
            url: apiUrl,
            headers: { 
                Accept : "text/plain; charset=utf-8",
                "Content-Type": "text/plain; charset=utf-8"
            },
            type: "POST",
            data: jwtToken, // Send the JWT token in the request body
            success: function(responseData) {
                // Handle success response
                console.log(responseData);
            },
            error: function(xhr, textStatus, errorThrown) {
                // Handle error response
                console.error(errorThrown);
            }
        });
    </script>
    <!-- Optional: Add your JavaScript scripts if needed -->
</body>
</html>