<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="vendor/head.jsp" %>
</head>
<body>
<%@include file="vendor/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <%
                String success = request.getAttribute("success") != null ? "success" : "";
                String error = request.getAttribute("error") != null ? "error" : "";
                if (!success.isEmpty()){
            %>
            <div class="alert alert-success<%= success %>" role="alert">
                <strong>Congratulations!</strong> Registration successful.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                } else if (!error.isEmpty()) {
            %>
            <div class="alert alert-danger<%= error %>" role="alert">
                <strong>Error!</strong> Passwords do not match. Please try again.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/register" method="post">
                <div class="form-group">
                    <label>Email: </label>
                    <input type="email" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label>Password: </label>
                    <input type="password" name="password" class="form-control">
                </div>
                <div class="form-group">
                    <label>Re-password: </label>
                    <input type="password" name="repassword" class="form-control">
                </div>
                <div class="form-group">
                    <label>Full Name: </label>
                    <input type="text" name="fullName" class="form-control">
                </div>
                <br>
                <div class="form-group">
                    <button class="btn btn-success">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
