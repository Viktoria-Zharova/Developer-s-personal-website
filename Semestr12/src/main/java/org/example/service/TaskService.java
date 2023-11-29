package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.TaskEntity;

import java.util.List;

public interface TaskService {

    void createTask(HttpServletRequest request);

    void delete(HttpServletRequest request);

    TaskEntity update(HttpServletRequest request);

    List<TaskEntity> getAll();

     List<TaskEntity> getAllByUserId(HttpServletRequest request);

}
