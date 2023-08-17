<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container mt-5">
    <h1>Role List</h1>
    <table id="roleTable" class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Can Add User</th>
                <th>Can Edit User</th>
                <th>Can Delete User</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${roles}" var="role">
                <tr>
                    <td>${role.id}</td>
                    <td>${role.name}</td>
                    <td>${role.canUserAdd}</td>
                    <td>${role.canUserEdit}</td>
                    <td>${role.canUserDelete}</td>
                    <td>
                        <!-- Edit button with a link to editUser?id=${user.id} -->
                        <a href="${pageContext.request.contextPath}/roles/updateRole/${role.id}" class="btn btn-primary btn-sm mr-2">Edit</a>
                        <!-- Delete button with a link to deleteUser?id=${user.id} -->
                        <a href="${pageContext.request.contextPath}/roles/deleteRole/${role.id}" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#roleTable').DataTable();
    });
</script>
