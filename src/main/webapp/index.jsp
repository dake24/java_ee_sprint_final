<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.example.practiceJavaEE.model.Tasks" %>
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
                    <h4>
                        List of Tasks
                    </h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Наименование</th>
                                <th>Крайний срок</th>
                                <th>Выполнено</th>
                                <th>Детали</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Tasks> tasks = (ArrayList<Tasks>) request.getAttribute("tasks");
                                if(tasks!=null){
                                    for(Tasks t : tasks){
                            %>
                            <tr>
                                <td>
                                    <%=t.getId()%>
                                </td>
                                <td>
                                    <%=t.getName()%>
                                </td>
                                <td>
                                    <%=t.getDeadlineDate()%>
                                </td>
                                <td>
                                    <%=t.getIsDone()%>
                                </td>
                                <td>
                                    <a href="/details?id=<%=t.getId()%>" class="btn btn-info btn-sm" style="background-color: cadetblue; border-color: cadetblue">Детали</a>
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