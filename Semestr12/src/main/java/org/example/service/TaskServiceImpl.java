package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.TaskEntity;
import org.example.model.UserEntity;
import org.example.repository.TaskRepository;
import org.example.repository.TaskRepositoryImpl;

import java.util.List;

public class TaskServiceImpl implements TaskService{

    private TaskServiceImpl(){}

    private static class SingletonHolder{
        public static final TaskService INSTANCE = new TaskServiceImpl();
    }

    public static TaskService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private final TaskRepository repository = TaskRepositoryImpl.getInstance();

    @Override
    public void createTask(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        TaskEntity entity = TaskEntity.builder()
                .userId(user.getId())
                .description(request.getParameter("description"))
                .build();
        repository.create(entity);
    }

    @Override
    public void delete(HttpServletRequest request) {
        repository.delete(Long.valueOf(request.getParameter("task_id")));
    }

    @Override
    public TaskEntity update(HttpServletRequest request) {
        TaskEntity entity = TaskEntity.builder()
                .id(Long.valueOf(request.getParameter("task_id")))
                .description(request.getParameter("description"))
                .build();
        repository.update(entity);
        return entity;
    }

    @Override
    public List<TaskEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<TaskEntity> getAllByUserId(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        return repository.findAllByUserId(user.getId());
    }
}
