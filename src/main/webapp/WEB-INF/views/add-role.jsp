<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0">Add Role</h2>
        </div>
        <div class="card-body">
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">${errorMessage}</p>
            </c:if>
            <form:form modelAttribute="role" method="post">
                <div class="form-group">
                    <label for="email">Name:</label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" cssClass="text-danger" />
                </div>
                <div class="form-group">
                    <label for="canUserAdd">Can Add User:</label>
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="canUserAdd" name="canUserAdd">
                        <label class="custom-control-label" for="canUserAdd">Can Add User</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="canUserEdit">Can Edit User:</label>
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="canUserEdit" name="canUserEdit">
                        <label class="custom-control-label" for="canUserEdit">Can Edit User</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="canUserDelete">Can Delete User:</label>
                    <div class="custom-control custom-switch">
                        <input type="checkbox" class="custom-control-input" id="canUserDelete" name="canUserDelete">
                        <label class="custom-control-label" for="canUserDelete">Can Delete User</label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form:form>
        </div>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>