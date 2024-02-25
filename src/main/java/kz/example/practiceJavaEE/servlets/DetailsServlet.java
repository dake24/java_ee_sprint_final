package kz.example.practiceJavaEE.servlets;

import kz.example.practiceJavaEE.model.Tasks;
import kz.example.practiceJavaEE.repository.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Tasks task = DBManager.getTask(id);

        if (task != null) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String deadlineDate = request.getParameter("deadlineDate");
        Boolean isDone = Boolean.valueOf(request.getParameter("isDone"));

        boolean isSaved = DBManager.saveTask(new Tasks(id, name, description, deadlineDate, isDone));
        if (isSaved) response.sendRedirect("/details?id=" + id + "&success");
        else response.sendRedirect("/details?id=" + id + "&error");
    }
}
