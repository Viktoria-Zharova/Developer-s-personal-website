package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.model.enums.ImageType;
import org.example.service.ImageService;
import org.example.service.ImageServiceImpl;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/image/upload")
@MultipartConfig
public class ImageUploadExample extends HttpServlet {

    ImageService service = ImageServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        service.saveImage(fileContent, ImageType.USER, 1);
    }
}
