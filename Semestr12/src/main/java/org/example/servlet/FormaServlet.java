package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.UserEntity;
import org.example.service.TaskService;
import org.example.service.TaskServiceImpl;

import java.io.IOException;

@WebServlet("/forma")
public class FormaServlet extends HttpServlet {

    TaskService taskService= TaskServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks", taskService.getAllByUserId(req));
        req.getRequestDispatcher("/forma.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        taskService.createTask(req);
        resp.sendRedirect("/forma");
    }
}
