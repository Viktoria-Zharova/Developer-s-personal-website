package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.UserEntity;
import org.example.service.ProjectService;
import org.example.service.ProjectServiceImpl;

import java.io.IOException;

@WebServlet("/portfolio")
public class PortfolioServlet extends HttpServlet {

    ProjectService projectService= ProjectServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projects",projectService.getAllExamples());
        req.getRequestDispatcher("/portfolio.jsp").forward(req,resp);
    }
}
