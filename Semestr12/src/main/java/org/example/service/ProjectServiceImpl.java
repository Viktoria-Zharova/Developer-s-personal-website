package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.ProjectExampleEntity;
import org.example.repository.ProjectRepository;
import org.example.repository.ProjectRepositoryImpl;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private ProjectServiceImpl(){}

    private static class SingletonHolder{
        public static final ProjectService INSTANCE = new ProjectServiceImpl();
    }

    public static ProjectService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private final ProjectRepository repository = ProjectRepositoryImpl.getInstance();
    @Override
    public List<ProjectExampleEntity> getAllExamples() {
        return repository.findAll();
    }

    @Override
    public ProjectExampleEntity findById(HttpServletRequest request){
        return repository.findById(Long.valueOf(request.getParameter("example_id")));
    }

    @Override
    public ProjectExampleEntity findById(Long id){
        return repository.findById(id);
    }

    @Override
    public ProjectExampleEntity create(HttpServletRequest request){
        ProjectExampleEntity example = ProjectExampleEntity.builder()
                .description(request.getParameter("description"))
                .title(request.getParameter("title"))
                .gitUrl(request.getParameter("url"))
                .build();
        return repository.create(example);
    }

    @Override
    public void delete(HttpServletRequest req) {
        repository.delete(Long.valueOf(req.getParameter("project_id")));
    }


    public ProjectExampleEntity update(HttpServletRequest request){
        ProjectExampleEntity example = ProjectExampleEntity.builder()
                .id(Long.valueOf(request.getParameter("example_id")))
                .description(request.getParameter("description"))
                .title(request.getParameter("title"))
                .gitUrl(request.getParameter("url"))
                .build();
        repository.update(example);
        return example;
    }
}
