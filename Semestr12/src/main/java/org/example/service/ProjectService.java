package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.ProjectExampleEntity;

import java.util.List;

public interface ProjectService {
    List<ProjectExampleEntity> getAllExamples();

    ProjectExampleEntity findById(HttpServletRequest request);

    ProjectExampleEntity findById(Long id);

    ProjectExampleEntity create(HttpServletRequest request);

    void delete(HttpServletRequest req);
}
