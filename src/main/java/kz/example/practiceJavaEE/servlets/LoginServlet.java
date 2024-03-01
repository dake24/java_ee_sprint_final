package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.Users;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Users u = DBManager.getUserByEmail(email);
            if (u != null && u.getPassword().equals(password)){
                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", u);
                if (u.getRoleId() == 1){
                    session.setAttribute("adminLoggedIn", u);
                }
                resp.sendRedirect("/");
            } else {
                req.setAttribute("error", "Invalid username or password! Please, try again!");
                req.getRequestDispatcher("/login").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}