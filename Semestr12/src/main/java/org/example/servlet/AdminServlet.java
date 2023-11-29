package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.model.ProjectExampleEntity;
import org.example.model.UserEntity;
import org.example.model.enums.ImageType;
import org.example.service.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/admin")
@MultipartConfig
public class AdminServlet extends HttpServlet {

    TaskService taskService= TaskServiceImpl.getInstance();
    FeedbackService feedbackService= FeedbackServiceImpl.getInstance();
    ProjectService projectService= ProjectServiceImpl.getInstance();

    ImageService service = ImageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks", taskService.getAll());
        req.setAttribute("feedbacks", feedbackService.getAllFeedback());
        req.setAttribute("projects", projectService.getAllExamples());

        req.getRequestDispatcher("/admin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectExampleEntity example = projectService.create(req);
        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        service.saveImage(fileContent, ImageType.PROJECT, example.getId());
        resp.sendRedirect("/forma");
    }
}
