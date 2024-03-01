package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.News;
import kz.example.practiceJavaEE.model.NewsCategory;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddServlet", value = "/news/add")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedInUser") != null && session.getAttribute("adminLoggedIn") != null) {
            request.setAttribute("newsCategories", DBManager.getAllNewsCategory());
            request.getRequestDispatcher("/addNews.jsp").forward(request, response);
        } else {
            response.sendRedirect("/practiceJavaEE_war/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String content = request.getParameter("content");
        int newsCategoryId = Integer.parseInt(request.getParameter("newsCategory"));

        NewsCategory category = DBManager.getNewsCategoryById(newsCategoryId);
        News news = new News(null, null, category, name, content);
        DBManager.createNews(news);

        response.sendRedirect("/add?success");
    }
}
