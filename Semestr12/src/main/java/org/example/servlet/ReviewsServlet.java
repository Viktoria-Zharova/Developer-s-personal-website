package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.UserEntity;
import org.example.service.FeedbackService;
import org.example.service.FeedbackServiceImpl;

import java.io.IOException;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {
    FeedbackService feedbackService= FeedbackServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("feedbacks", feedbackService.getAllFeedback());

        req.getRequestDispatcher("/reviews.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        feedbackService.createFeedback(req);
        resp.sendRedirect("/reviews");
    }
}
