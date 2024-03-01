<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object loggedInUser = request.getSession(false).getAttribute("loggedInUser");
    Object adminLoggedIn = request.getSession(false).getAttribute("adminLoggedIn");
%>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkslategrey">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">NEWS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <% if (loggedInUser != null) { %>
                    <a class="nav-link" href="${pageContext.request.contextPath}/">All news</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if (loggedInUser != null && adminLoggedIn != null) { %>
                    <a class="nav-link" href="${pageContext.request.contextPath}/news/add">Add news</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if (loggedInUser != null && adminLoggedIn != null) { %>
                    <a class="nav-link" href="${pageContext.request.contextPath}/users">All users</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if (loggedInUser != null && adminLoggedIn != null) { %>
                    <a class="nav-link" href="${pageContext.request.contextPath}/users/add">Add user</a>
                    <% } %>
                </li>
            </ul>
        </div>
    </div>
</nav>
