package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/news/delete")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (DBManager.deleteNews(id)) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/details?id=" + id + "&error");
        }
    }
}
