package org.example.service;

import org.example.model.enums.ImageType;

import java.io.InputStream;

public interface ImageService {

    void saveImage(InputStream file, ImageType type, long id);
}
