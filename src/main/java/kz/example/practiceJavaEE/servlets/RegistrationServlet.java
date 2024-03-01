package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.Users;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullName = request.getParameter("fullName");
        if (repassword.equals(password)) {
            Users user = new Users();
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setRoleId(2);

            DBManager.createUser(user);
            request.setAttribute("success", "");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }
}

