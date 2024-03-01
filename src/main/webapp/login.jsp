<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
</head>
<body>
<%@include file="vendor/navbar.jsp" %>
<br>
<div class="container">
    <%
        String error = (String) request.getAttribute("error");
        if(error != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%response.getOutputStream().print(error);%>
    </div>
    <%
        }
    %>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="inputEmail" class="form-label">Email address</label>
            <input name="email" type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="Enter email">
        </div>
        <div class="mb-3">
            <label for="inputPassword" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="inputPassword" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <a href="register" class="btn btn-secondary">Register</a>
    </form>
</div>
</body>
</html>