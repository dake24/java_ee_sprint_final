package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.Comment;
import kz.example.practiceJavaEE.model.News;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/news/details")
public class DetailsNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newsId = Integer.parseInt(req.getParameter("news_id"));

        List<Comment> comments = DBManager.getCommentsById(newsId);
        News news = DBManager.getNewsById(newsId);
        req.setAttribute("comments", comments);
        req.setAttribute("news", news);
        req.getRequestDispatcher("/detailsNews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
