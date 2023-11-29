package org.example.servlet.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.ProjectService;
import org.example.service.ProjectServiceImpl;

import java.io.IOException;

@WebServlet("/ajax/project/delete")
public class DeleteProject extends HttpServlet {

    ProjectService service = ProjectServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(req);
    }
}
