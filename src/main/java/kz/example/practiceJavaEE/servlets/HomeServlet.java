package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedInUser") != null) {
            request.setAttribute("allNews", DBManager.getAllNews());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/practiceJavaEE_war/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
