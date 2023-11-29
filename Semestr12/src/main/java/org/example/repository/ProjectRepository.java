package org.example.repository;

import org.example.model.ProjectExampleEntity;

import java.util.List;

public interface ProjectRepository {
    List<ProjectExampleEntity> findAll();

    ProjectExampleEntity create(ProjectExampleEntity example);

    ProjectExampleEntity findById(Long exampleId);

    void update(ProjectExampleEntity example);

    void delete(Long projectId);
}
