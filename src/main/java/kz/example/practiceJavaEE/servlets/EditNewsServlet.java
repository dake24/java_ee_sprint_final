package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.News;
import kz.example.practiceJavaEE.model.NewsCategory;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/news/edit")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        News news = DBManager.getNewsById(id);
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedInUser") != null && session.getAttribute("adminLoggedIn") != null) {
            request.setAttribute("news", news);
            request.setAttribute("newsCategories", DBManager.getAllNewsCategory());
            request.getRequestDispatcher("/editNews.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        int newsCategoryId = Integer.parseInt(request.getParameter("newsCategory"));

        NewsCategory category = DBManager.getNewsCategoryById(newsCategoryId);
        News news = new News(id, null, category, name, content);

        boolean isSaved = DBManager.updateNews(news);

        if (isSaved) response.sendRedirect("/news/edit?id=" + id + "&success");
        else response.sendRedirect("/news/edit?id=" + id + "&error");
    }
}
