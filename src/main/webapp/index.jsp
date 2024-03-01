<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.example.practiceJavaEE.model.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <h4>
                        List of News
                    </h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Дата публикации</th>
                                <th>Категория</th>
                                <th>Наименование</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<News> news = (ArrayList<News>) request.getAttribute("allNews");
                                if(news!=null){
                                    for(News n: news){
                            %>
                            <tr>
                                <td>
                                    <%=n.getId()%>
                                </td>
                                <td>
                                    <%=n.getPostDate()%>
                                </td>
                                <td>
                                    <%=n.getCategory().getName()%>
                                </td>
                                <td>
                                    <%=n.getTitle()%>
                                </td>
                                <td>
                                    <a href="/news/details?id=<%=n.getId()%>" class="btn btn-info btn-sm" style="background-color: cadetblue; border-color: cadetblue">Подробнее</a>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>