package org.example.repository;

import org.example.model.TaskEntity;

import java.util.List;

public interface TaskRepository {
    void create(TaskEntity entity);

    void update(TaskEntity entity);

    List<TaskEntity> findAll();

    void delete(Long taskId);

    List<TaskEntity> findAllByUserId(Long userId);
}
