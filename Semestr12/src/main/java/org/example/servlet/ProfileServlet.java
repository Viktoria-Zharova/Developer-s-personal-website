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
import org.example.service.ImageService;
import org.example.service.ImageServiceImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    UserService service = UserServiceImpl.getInstance();
    ImageService imageService = ImageServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity entity = (UserEntity) req.getSession().getAttribute("user");
        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        imageService.saveImage(fileContent, ImageType.USER, entity.getId());
        service.updateUser(req);
        resp.sendRedirect("/profile");
    }
}
