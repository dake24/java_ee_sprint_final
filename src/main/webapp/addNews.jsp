<%@ page import="kz.example.practiceJavaEE.model.NewsCategory" %>
<%@ page import="java.util.List" %>
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
                <div class="col-sm-6 offset-3">
                    <%
                        String success = request.getParameter("success");
                        if(success != null){
                    %>
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <strong>Congratulations!</strong> Item added successfully.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    <%
                        }
                    %>
                    <form action="/news/add" method="post">
                        <div class="form-group">
                            <label>Category : </label>
                            <select name="newsCategory" class="form-select" aria-label="Default select example">
                                <% for (NewsCategory nc : (List<NewsCategory>) request.getAttribute("newsCategories")) {%>
                                <option value="<%=nc.getId()%>"><%=nc.getName()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Наименование : </label>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Описание : </label>
                            <input type="text" name="content" class="form-control">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>