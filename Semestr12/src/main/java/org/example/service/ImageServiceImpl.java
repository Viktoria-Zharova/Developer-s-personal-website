package org.example.service;

import org.example.model.enums.ImageType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageServiceImpl implements ImageService{

    private ImageServiceImpl(){}

    private static class SingletonHolder{
        public static final ImageService INSTANCE = new ImageServiceImpl();
    }

    public static ImageService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void saveImage(InputStream fileToSave, ImageType type, long id) {
        try {
            new File("..\\images\\" + type.getValue()).mkdirs();
            File file = new File("..\\images\\" + type.getValue() +"\\" + id + ".png");
            Files.copy(fileToSave, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Image save error");
        }
    }
}
