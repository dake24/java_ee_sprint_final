package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.Tasks;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String deadlineDate = request.getParameter("deadlineDate");

        Tasks task = new Tasks(null, name, description, deadlineDate, false);
        DBManager.saveTask(task);

        response.sendRedirect("/add?success");
    }
}
