<%@ page import="kz.example.practiceJavaEE.model.News" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<div class="container">
  <div class="row mt-5">
    <div class="col-sm-12">
      <div class="p-5 mb-4 bg-light rounded-3">
        <%
          News news = (News) request.getAttribute("news");
        %>
        <h2><%=news.getTitle()%></h2>
        <br>
        <p>Posted time: <%=news.getPostDate()%></p>
        <br>
        <h5>
          <%=news.getContent()%>
        </h5>
        <a href="/news/edit?id=<%=news.getId()%>" class="btn btn-info btn-sm" style="background-color: cadetblue; border-color: cadetblue">Изменить</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>