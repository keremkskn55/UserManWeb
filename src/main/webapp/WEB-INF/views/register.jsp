<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>My Application</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0">Register Page</h2>
        </div>
        <div class="card-body">
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">${errorMessage}</p>
            </c:if>
            <form:form modelAttribute="registerCredential" method="post">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <form:input path="email" class="form-control"/>
                    <form:errors path="email" cssClass="text-danger" />
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <form:input path="password" class="form-control" type="password"/>
                    <form:errors path="password" cssClass="text-danger" />
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" cssClass="text-danger" />
                </div>
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <form:input path="surname" class="form-control"/>
                    <form:errors path="surname" cssClass="text-danger" />
                </div>
                <div class="form-group">
                    <label for="age">Age:</label>
                    <form:input path="age" class="form-control"/>
                    <form:errors path="age" cssClass="text-danger" />
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form:form>
            <div class="mt-3">
                <a href="/UserManWeb/auth/signIn" class="btn btn-secondary">Login</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>




