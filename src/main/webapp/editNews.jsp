<%@ page import="kz.example.practiceJavaEE.model.News" %>
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
                <div class="col-sm-12">
                    <div class="p-5 mb-4 bg-light rounded-3">
                        <%
                            News news = (News) request.getAttribute("news");
                            if(news != null){
                                String success = request.getParameter("success");
                                if(success != null){
                        %>
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <strong>Congratulations!</strong> Item saved successfully.
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <%
                                }
                                String error = request.getParameter("error");
                                if(error != null){
                            %>
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <strong>Oh, no!</strong> Something went wrong.
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <%
                                }
                            %>
                            <form action="/news/edit" method="post">
                                <input type="hidden" name="id" value="<%=news.getId()%>">
                                <div class="form-group">
                                    <label>Category : </label>
                                    <select name="newsCategory" class="form-select" aria-label="Default select example">
                                        <% for (NewsCategory nc : (List<NewsCategory>) request.getAttribute("newsCategories")) {%>
                                        <option value="<%=nc.getId()%>" selected="<%=nc.getId() == news.getCategory().getId()%>">
                                            <%=nc.getName()%>
                                        </option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Наименование : </label>
                                    <input type="text" name="name" class="form-control" value="<%=news.getId()%>">
                                </div>
                                <div class="form-group">
                                    <label>Content : </label>
                                    <input type="text" name="content" class="form-control" value="<%=news.getContent()%>">
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-success">SAVE ITEM</button>
                                    <button type="button" class="btn btn-danger float-right" data-bs-toggle="modal" data-bs-target="#deletePhoneModal">
                                        DELETE ITEM
                                    </button>
                                </div>
                            </form>

                            <div class="modal fade" id="deletePhoneModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <form action="/news/delete" method="post">
                                            <input type="hidden" name="id" value="<%=news.getId()%>">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="staticBackdropLabel">Confirm Delete Process</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Are you sure???
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
                                                <button type="submit" class="btn btn-danger">YES</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>