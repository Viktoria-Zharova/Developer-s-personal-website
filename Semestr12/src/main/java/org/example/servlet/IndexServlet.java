package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.FeedbackService;
import org.example.service.FeedbackServiceImpl;
import org.example.service.ProjectService;
import org.example.service.ProjectServiceImpl;

import java.io.IOException;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {

    private final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    private final ProjectService projectService = ProjectServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("feedback_list", feedbackService.getAllFeedback());
        req.getSession().setAttribute("examples_list", projectService.getAllExamples());
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/home");
    }
}
