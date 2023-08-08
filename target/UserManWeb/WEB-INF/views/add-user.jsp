<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-5">
    <h2 class="mb-3">Add User</h2>
    <c:if test="${not empty errorMessage}">
        <p class="text-danger">${errorMessage}</p>
    </c:if>
    <form:form modelAttribute="user" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <form:input path="username" class="form-control"/>
            <form:errors path="username" cssClass="text-danger" />
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
        <button type="submit" class="btn btn-primary">Add</button>
    </form:form>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
