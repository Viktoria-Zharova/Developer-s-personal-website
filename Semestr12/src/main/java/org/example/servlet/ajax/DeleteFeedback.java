package org.example.servlet.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.FeedbackService;
import org.example.service.FeedbackServiceImpl;

import java.io.IOException;

@WebServlet("/ajax/feedback/delete")
public class DeleteFeedback extends HttpServlet {

    FeedbackService service = FeedbackServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteFeedback(req);
    }
}
